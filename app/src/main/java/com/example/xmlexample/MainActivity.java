package com.example.xmlexample;

import java.io.File;
import java.io.FileOutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.*;

public class MainActivity extends Activity {

    private EditText description;
    private EditText street;
    private EditText cityStateZip;
    private EditText clue1;
    private EditText clue2;

    FileOutputStream locationFile;
    XmlSerializer serializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        description = (EditText) findViewById(R.id.editDescription);
        description.setText("");

        street = (EditText) findViewById(R.id.editStreet);
        street.setText("");

        cityStateZip = (EditText) findViewById(R.id.editCityStateZip);
        cityStateZip.setText("");

        clue1 = (EditText) findViewById(R.id.editClue1);
        clue1.setText("");

        clue2 = (EditText) findViewById(R.id.editClue2);
        clue2.setText("");

        // create the initial xml document if it already does not exist
        try {
            File filename = new File(getFilesDir(), "location.xml");

            if (!filename.exists()) {
                locationFile = openFileOutput("location.xml", Activity.MODE_APPEND);

                // create a new XmlSerializer object 
                serializer = Xml.newSerializer();

                // use myFile as your xml serializer and set to UTF-8 encoding
                serializer.setOutput(locationFile, "UTF-8");

                // Write <?xml declaration with encoding 
                serializer.startDocument(null, Boolean.valueOf(true));

                // set indentation option 
                serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

                // start a new top level tag and other tags
                serializer.startTag(null, "AddressBook");
            } else {
                // use myFile as your xml serializer and set to UTF-8 encoding
                locationFile = openFileOutput("location.xml", Activity.MODE_APPEND);

                // create a new XmlSerializer object 
                serializer = Xml.newSerializer();

                // use myFile as your xml serializer and set to UTF-8 encoding
                serializer.setOutput(locationFile, "UTF-8");
            }
        } catch (Exception e) {
            Log.e("Exception: ", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onSaveButton(View v) {
        try {
            // start a new top level tag and other tags, for our FINAL project, Contact could be Treasure Item
            // we don't need to have nested <Treasure> followed by Items, the format can be similar to the below
            // this way, we don't need to add an end tag for outermost element

            // create tags and values
            serializer.startTag(null, "Location");
            serializer.startTag(null, "Description");
            serializer.text(description.getText().toString());  // retrieve name entered by user from text field
            serializer.endTag(null, "Description");

            serializer.startTag(null, "Street");
            serializer.text(street.getText().toString());
            serializer.endTag(null, "Street");

            serializer.startTag(null, "CityStateZip");
            serializer.text(cityStateZip.getText().toString());
            serializer.endTag(null, "CityStateZip");

            serializer.startTag(null, "Clue 1");
            serializer.text(clue1.getText().toString());
            serializer.endTag(null, "Clue 1");

            serializer.startTag(null, "Clue 2");
            serializer.text(clue2.getText().toString());
            serializer.endTag(null, "Clue 2");

            serializer.endTag(null, "Location");

            // perform the write by flushing
            serializer.flush();

            // clear the text fields
            description.setText("");
            street.setText("");
            cityStateZip.setText("");
            clue1.setText("");
            clue2.setText("");
        } catch (Exception e) {
            // use logcat to display errors
            Log.e("Exception::", e.getMessage());
        }
    }
}
