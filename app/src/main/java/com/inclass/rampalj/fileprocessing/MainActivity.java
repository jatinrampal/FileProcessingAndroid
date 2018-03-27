package com.inclass.rampalj.fileprocessing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLookUp(View view) {
        //access the edit field from Layout
        EditText word = (EditText) findViewById(R.id.word) ;
        String theWord  = word.getText().toString();

        String definition = findDefinition(theWord);
        TextView theDef = (TextView) findViewById(R.id.def);

        if(definition!=null)
            theDef.setText(definition);
        else
            theDef.setText("Word not found");
    }

    private String findDefinition(String word) {
        //Open raw resources and connect to Input String variable
        InputStream input = getResources().openRawResource(R.raw.myvocabulary);

        //Open Scanner object based on input stream
        Scanner scan  =new Scanner(input);

        //Reading stream line by line
        while(scan.hasNext())
        {
            String line = scan.nextLine(); //The full line with word and definition

            String[] pieces = line.split("=");

            if(pieces[0].equalsIgnoreCase(word.trim()))
            {
                return pieces[1];
            }
        }
        return null;

    }
}
