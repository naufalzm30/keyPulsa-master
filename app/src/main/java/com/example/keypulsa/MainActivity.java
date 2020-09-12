package com.example.keypulsa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button pinButton;
    EditText aturPin;
    TextView pinsebelumnya;
    private String selectedName;
    private int selectedID;

    public TextView te;
    public EditText et;
    private Button btpin;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    public static String textpin;


    public static String getTextpin() {
        return textpin;
    }

    public static void setTextpin(String textpin) {
        MainActivity.textpin = textpin;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        te = (TextView) findViewById(R.id.pinsebelum);
        et = (EditText) findViewById(R.id.atur_pin);
        btpin = (Button) findViewById(R.id.pinButton);

        btpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te.setText(et.getText().toString());
                saveData();
            }
        });
        loadData();
        updateViews();



    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, te.getText().toString());

        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textpin = sharedPreferences.getString(TEXT,"");
    }

    public void updateViews(){
        te.setText(textpin);
    }



}
