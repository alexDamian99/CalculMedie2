package com.example.alex.calculmedie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //private boolean isReached = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //un fadein la inceput pt logo
        ImageView logo = (ImageView)findViewById(R.id.logo);
        logo.setVisibility(View.INVISIBLE);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(2000);

        AnimationSet logoAnimation = new AnimationSet(false);
        logoAnimation.addAnimation(fadeIn);

        logo.setAnimation(logoAnimation);
        logo.setVisibility(View.VISIBLE);


    }
    private boolean isBtnClicked = false;

    public void onClickMedie(View v){
        //creez niste variabile pentru imgViewLogo si pentru TxtMedie
        ImageView logo = (ImageView)findViewById(R.id.logo);
        TextView txtMedie = (TextView)findViewById(R.id.txtViewMedie);
        //FADE ANIM
        //fade in(o creem)
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setStartOffset(1000);//o creez cu un offset(va incepe mai tarziu(in cazul acesta 1 secunda)
        fadeIn.setDuration(1000);
        //fade out(o creem)
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        //creem niste animationSet (asa era codul)
        AnimationSet logoAnimation = new AnimationSet(false);
        AnimationSet medieAnimation = new AnimationSet(false);

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

            for(String str : note){//pentru fiecare variabila de tip String din vectorul note vom face:
                    if (str.compareTo("") != 0) {//in cazul in care nu este null il adunam la suma
                        double nr;
                        nr = Integer.parseInt(str);
                        S += nr;
                        K++;
                    }
            }

            S/=K;//facem media pentru note

            try{
                double Teza = Integer.parseInt(teza[0]); // parses the first mark in editTeza
                S = (S * 3 + Teza)/4;
            }catch (Exception e){
                Toast t =  Toast.makeText(getApplicationContext(), "Lipsa teza", Toast.LENGTH_SHORT);
                t.show();
            }

            if (sNote.compareTo("") != 0) {
                txtMedie.setText(String.format("%.2f", S));  // if the editTexts are filled do make the medie and make animation

                //if-ul este pt cazul in care facem mai multe medii(apasam butonul de mai multe ori
                if(!isBtnClicked) {
                    // this will make the logo disappear
                    isBtnClicked = true;
                    logoAnimation.addAnimation(fadeOut);
                    logo.setAnimation(logoAnimation);
                    logo.setVisibility(View.INVISIBLE);

                    //dis make txtMEDIE appear

                    medieAnimation.addAnimation(fadeIn);
                    txtMedie.setAnimation(medieAnimation);
                    txtMedie.setVisibility(View.VISIBLE);

                }

            }
            else txtMedie.setText(""); // else show Medie as ""

        }catch(Exception e){
            Toast t = Toast.makeText(getApplicationContext(), "Date incorecte", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    public void onClickClear(View v){
        //show keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0); // aici nu am gasit cum sa fie doar "show keyboard".. :/ am gasit doar toggle
        /*asta nu merge
        imm.showSoftInput(findViewById(R.id.btnClear), InputMethod.SHOW_FORCED); */

        ((TextView)findViewById(R.id.txtViewMedie)).setText("");
        ((EditText)findViewById(R.id.editNote)).setText("");
        ((EditText)findViewById(R.id.editTeza)).setText("");
    }
}
