package com.example.xmlexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.*;
//This is a new comment to make sure the source control is working.

public class MainActivity extends Activity {

    private EditText name;
    private EditText address;
    private EditText state;

    FileOutputStream myFile;
    XmlSerializer serializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        name = (EditText) findViewById(R.id.editName);
        name.setText("");

        address = (EditText) findViewById(R.id.editAddress);
        address.setText("");

        state = (EditText) findViewById(R.id.editState);
        state.setText("");

        // create the initial xml document if it already does not exist
        try {
            File filename = new File(getFilesDir(), "example.xml");

            if (!filename.exists()) {
                myFile = openFileOutput("example.xml", Activity.MODE_APPEND);

                // create a new XmlSerializer object 
                serializer = Xml.newSerializer();

                // use myFile as your xml serializer and set to UTF-8 encoding
                serializer.setOutput(myFile, "UTF-8");

                // Write <?xml declaration with encoding 
                serializer.startDocument(null, Boolean.valueOf(true));

                // set indentation option 
                serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

                // start a new top level tag and other tags
                serializer.startTag(null, "AddressBook");
            } else {
                // use myFile as your xml serializer and set to UTF-8 encoding
                myFile = openFileOutput("example.xml", Activity.MODE_APPEND);

                // create a new XmlSerializer object 
                serializer = Xml.newSerializer();

                // use myFile as your xml serializer and set to UTF-8 encoding
                serializer.setOutput(myFile, "UTF-8");
            }
        } catch (Exception e) {
            Log.e("Exception::", e.getMessage());
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
            serializer.startTag(null, "Contact");
            serializer.startTag(null, "Name");
            serializer.text(name.getText().toString());  // retrieve name entered by user from text field
            serializer.endTag(null, "Name");

            serializer.startTag(null, "Address");
            serializer.text(address.getText().toString());
            serializer.endTag(null, "Address");

            serializer.startTag(null, "State");
            serializer.text(state.getText().toString());
            serializer.endTag(null, "State");
            serializer.endTag(null, "Contact");

            // perform the write by flushing
            serializer.flush();

            // clear the text fields
            name.setText("");
            address.setText("");
            state.setText("");
        } catch (Exception e) {
            // use logcat to display errors
            Log.e("Exception::", e.getMessage());
        }
    }
}
