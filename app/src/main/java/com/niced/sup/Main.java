/*package com.niced.sup;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Main extends Activity {
    String DEBUG_TAG = "Sup";

    // Info of Contacts we're sending "Sup" to
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> phones = new ArrayList<String>();

    // Array Adapter to update contents of ListView containing contact names
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find list view
        ListView listView = (ListView) findViewById(R.id.contacts_listview);

        // Set adapter to list view
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    // Get contact's name and number
                    Uri contactData = data.getData();
                    String[] contacts = new String[] {Contacts.DISPLAY_NAME, Phone.NUMBER};
                    Cursor cursor = getContentResolver().query(contactData, contacts, null, null, null);
                    cursor.moveToFirst();

                    // get phone number if contact has one
                    int phoneIndex = cursor.getColumnIndex(Phone.NUMBER);
                    int nameIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME);

                    String name = cursor.getString(nameIndex);
                    String phone = cursor.getString(phoneIndex);

                    cursor.close();

                    // Add contact info to lists
                    phones.add(phone);

                    // Notify adapter
                    adapter.add(name);

                    break;
            }

        } else {
            // gracefully handle failure
            Log.w(DEBUG_TAG, "Warning: activity result not ok");
        }
    }
}
*/

package com.niced.sup;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sendsmsdemo.R;


public class Main extends Activity {

    //Button sendBtn;
    //EditText txtphoneNo;
    //EditText txtMessage;

    public ArrayAdapter<Button> adapter;
    ListView contactsListView;
    String[] contactNameArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ListView.
        contactsListView= (ListView) findViewById(R.id.contacts_listview);

        contactNameArray = this.setButtonArray();

        // Initialize the adapter. Put menuItemsArray into the ListView.
        adapter = new ArrayAdapter<Button>(this, android.R.layout.simple_list_item_1, contactsArray);
        contactsListView.setAdapter(adapter);

        /*
        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        txtMessage = (EditText) findViewById(R.id.editTextSMS);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });

        */

    }

    public String[] setButtonArray() {

        buttonArray = new String[10];

    }
    /*
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");

        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
