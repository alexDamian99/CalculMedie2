package com.example.alex.calculmedie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickMedie(View v){
        //hide keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.btnMedie).getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

        String sNote; // stringul pt note
        String[] note; // notele introduse in editNote
        sNote = ((EditText)findViewById(R.id.editNote)).getText().toString(); // gets the marks from the editNote
        if (sNote.compareTo("") == 0) Toast.makeText(getApplicationContext(), "Introduceti note", Toast.LENGTH_LONG).show();
        note = sNote.split("\\s"); // splits them in note[]

        String sTeza; // stringul pt teza
        sTeza = ((EditText)findViewById(R.id.editTeza)).getText().toString(); // gets the marks from editTeza
        String[] teza;
        teza = sTeza.split("\\s"); // puts all the marks from editTeza in teza[]

        double S = 0;
        int K = 0;

        try{
            for(String str : note){
                    double nr;
                    if (str.compareTo("") != 0) {
                        nr = Integer.parseInt(str);
                        S += nr;
                        K++;
                    }
            }
            S/=K;

            try{
                double Teza = Integer.parseInt(teza[0]); // parses the first mark in editTeza
                S = (S * 3 + Teza)/4;
            }catch (Exception e){
                Toast t =  Toast.makeText(getApplicationContext(), "Lipsa teza", Toast.LENGTH_SHORT);
                t.show();
            }

            if (sNote.compareTo("") != 0) ((TextView)findViewById(R.id.txtViewMedie)).setText(String.format("%.2f", S));  // if the editTexts are filled do make the medie
            else ((TextView)findViewById(R.id.txtViewMedie)).setText(""); // else show Medie as ""

        }catch(Exception e){
            Toast t = Toast.makeText(getApplicationContext(), "Date incorecte", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    public void onClickClear(View v){
        //hide keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0); // aici nu am gasit cum sa fie doar "show keyboard".. :/ am gasit doar toggle
        /*asta nu merge
        imm.showSoftInput(findViewById(R.id.btnClear), InputMethod.SHOW_FORCED); */

        ((TextView)findViewById(R.id.txtViewMedie)).setText("");
        ((EditText)findViewById(R.id.editNote)).setText("");
        ((EditText)findViewById(R.id.editTeza)).setText("");
    }
}
