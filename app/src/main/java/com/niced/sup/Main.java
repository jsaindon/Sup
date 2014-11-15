package com.niced.sup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//
//    public void doLaunchContactPicker(View view) {
//        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contents.CONTENT_URI);
//        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case CONTACT_PICKER_RESULT:
//                    Uri result = data.getData();
//                    String id = result.getLastPathSegment();
//                    break;
//            }
//
//        } else {
//            // gracefully handle failure
//            Log.w(DEBUG_TAG, "Warning: activity result not ok");
//        }
//    }
}
