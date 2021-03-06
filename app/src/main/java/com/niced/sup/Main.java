package com.niced.sup;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main extends Activity {
    private final String DEBUG_TAG = "Sup";

    private final String PHONE_KEY = "phone";
    private final String NAME_KEY = "name";
    private final String DELETE_TEXT = "Delete";
    private final int MENU_CONTEXT_DELETE_ID = 1010;

    // Info of Contacts we're sending "Sup" to
    ArrayList<Map<String,String>> contact_list = new ArrayList<Map<String,String>>();

    // Array Adapter to update contents of ListView containing contact names
    SimpleAdapter adapter;

    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find list view
        ListView listView = (ListView) findViewById(R.id.contacts_listview);

        // Set adapter to list view
        adapter = new SimpleAdapter(this, contact_list, android.R.layout.simple_list_item_2,
                new String[]{NAME_KEY, PHONE_KEY},
                new int[] {android.R.id.text1, android.R.id.text2} );
        listView.setAdapter(adapter);

        // Register for context menu for deletes
        registerForContextMenu(listView);

        // Create send button event listeners
        sendBtn = (Button) findViewById(R.id.send_button);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // If view is list view, option to delete
        if (v.getId() == R.id.contacts_listview) {
            menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, Menu.NONE, DELETE_TEXT);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // If need to delete item, do so
        switch(item.getItemId()) {
        case MENU_CONTEXT_DELETE_ID:
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Log.d(DEBUG_TAG, "Removing item at position: " + info.position);

            // Delete item from listview
            this.contact_list.remove(info.position);
            this.adapter.notifyDataSetChanged();
            return true;
        default:
            return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final int CONTACT_PICKER_RESULT = 1001;

    public void doLaunchContactPicker(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    // Get contact's name and number
                    Uri contactData = data.getData();
                    //String[] contacts = new String[] {Contacts.DISPLAY_NAME, Phone.NUMBER};
                    Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        // get phone number if contact has one
                        int phoneIndex = cursor.getColumnIndex(Phone.NUMBER);
                        int nameIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME);

                        String name = cursor.getString(nameIndex);
                        String phone = cursor.getString(phoneIndex);

                        // Add contact info to lists
                        Map<String,String> contact_info = new HashMap<String,String>(2);
                        contact_info.put(PHONE_KEY, phone);
                        contact_info.put(NAME_KEY, name);
                        contact_list.add(contact_info);

                        // Notify adapter
                        adapter.notifyDataSetChanged();
                    }

                    cursor.close();
                    break;
            }

        } else {
            // gracefully handle failure
            Log.w(DEBUG_TAG, "Warning: activity result not ok");
        }
    }

    public void sendSMSMessage() {
        if (contact_list.isEmpty()){
            return;
        }

        Log.i("Send SMS", "");

        try {
            SmsManager smsManager = SmsManager.getDefault();
            for(Map<String,String> contact_info : contact_list)  {
                String phone_num = contact_info.get(PHONE_KEY);
                smsManager.sendTextMessage(phone_num, null, "Sup?", null, null);
            }
            contact_list.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
