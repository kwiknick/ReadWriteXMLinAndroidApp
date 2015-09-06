package com.example.xmlexample;

import java.io.File;
import java.io.FileReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReadActivity extends Activity {

    // text view control
    private TextView tv;

    // xml read stuff
    XmlPullParserFactory factory;
    XmlPullParser xpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        // get my textview resource so I can call append method to show text
        tv = (TextView) findViewById(R.id.textView1);
        tv.setText("");

        // get the xml file
        File filename = new File(getFilesDir(), "example.xml");

        // if the file exists, then read it
        if (filename.exists()) {
            readXML(filename);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "File not Found", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void readXML(File filename) {
        try {
            // xml classes used to read xml file
            factory = XmlPullParserFactory.newInstance();
            xpp = factory.newPullParser();

            // important, need to point xml parser to file
            xpp.setInput(new FileReader(filename));

            // Start and end tags and end document
            int eventType = xpp.getEventType();

            // current tag
            String currentTag;

            // current value of tag/element
            String currentElement;

            int counter = 0;

            // parse the entire xml file until done
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // look for start tags
                if (eventType == XmlPullParser.START_TAG) {
                    // get the name of the start tag
                    currentTag = xpp.getName();

                    if (currentTag.equals("Contact")) {
                        tv.append("Contact " + ++counter + "\n");
                        tv.append("-----------------------------------------\n");
                    } else if (currentTag.equals("Name")) {
                        currentElement = xpp.nextText();
                        tv.append("Name:  " + currentElement + "\n");
                    } else if (currentTag.equals("Address")) {
                        currentElement = xpp.nextText();
                        tv.append("Address:  " + currentElement + "\n");
                    } else if (currentTag.equals("State")) {
                        currentElement = xpp.nextText();
                        tv.append("State:  " + currentElement + "\n");
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    currentTag = xpp.getName();

                    if (currentTag.equals("Contact")) {
                        tv.append("------------------------------------\n\n");
                    }
                }

                // get next tag
                eventType = xpp.next();
            }
        } catch (Exception e) {
            Log.e("XMLExample", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read, menu);
        return true;
    }

}
