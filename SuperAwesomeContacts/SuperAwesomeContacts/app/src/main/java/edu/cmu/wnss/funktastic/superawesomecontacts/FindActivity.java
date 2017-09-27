package edu.cmu.wnss.funktastic.superawesomecontacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Funky McAwesome on some date.
 *
 */

public class FindActivity extends Activity implements FindReturnable {
    public static final String FIND_CONTACT_INTENT_TYPE =
            "edu.cmu.wnss.funktastic.superawesomecontacts.findcontact.intent.type";
    public static final String FIND_CONTACT_EXTRA_KEY =
            "edu.cmu.wnss.funktastic.superawesomecontacts.findcontact.findQueryString";
    public static final String FIND_CONTACT_EXTRA_RESULTS =
            "edu.cmu.wnss.funktastic.superawesomecontacts.findcontact.queryResults";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        setFinishOnTouchOutside(false);

        // Get the queryString from the intent and find some phat contacts yo!
        // get the intent which started this activity
        Intent sourceIntent = getIntent();

        String queryString = sourceIntent.getStringExtra(FindActivity.FIND_CONTACT_EXTRA_KEY);
        Log.d("Tag : query string 1 ",sourceIntent.getType()+ "     str");
        Log.d("Tag : query string 2 ",getCallingActivity().getShortClassName()+ "     str");
        Log.d("Tag : query string 3 ",getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent().getShortClassName()+ "     str");
        Log.d("Tag : query string 4 ",FindActivity.FIND_CONTACT_EXTRA_KEY+ "     str");
        Log.d("Tag : query string",queryString+ "     str");


//        if(sourceIntent.getType() != null && sourceIntent.getType().equals(
//                FindActivity.FIND_CONTACT_INTENT_TYPE) && getCallingActivity().getShortClassName().equals(
//                getPackageManager().getLaunchIntentForPackage(getPackageName()).getComponent().getShortClassName())) {
            queryString = sourceIntent.getStringExtra(FindActivity.FIND_CONTACT_EXTRA_KEY);
            if(queryString != null) {
                Log.d("Tag : query string : ",queryString+ "     str");
                Thread getContacts = new Thread(new PopulateContactList(getContentResolver(), this, queryString));
                Log.d("Tag : finished",queryString+ "     str");
                getContacts.start();
            }
//        }
    }

    @Override
    public void setSearchResult(String results) {

        Log.d("setSearchResult started","str");

        Intent resultIntent = new Intent();
        resultIntent.putExtra(FindActivity.FIND_CONTACT_EXTRA_RESULTS, "SEARCH RESULTS\n" + results);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}