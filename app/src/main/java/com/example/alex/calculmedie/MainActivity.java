package com.example.alex.calculmedie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

    /*private String clearSpace(String t){
        char[] s;
        s = t.toCharArray();
        int len = s.length;
        for(int i = 0; i < len; ++i){
            if(s[i] == ' '){
                int j = i + 1;
                while(s[j] == ' ') {
                    for(int p = j; p < len-1; ++p)
                        s[p] = s[p+1];
                    len--;
                }
            }
        }
        String l = s.toString();
        return l;
    }*/

    public void onClickMedie(View v){
        String s;
        String[] note;
        s = ((EditText)findViewById(R.id.editNote)).getText().toString();
        note = s.split("\\s");
        s = ((EditText)findViewById(R.id.editTeza)).getText().toString();
        double S = 0;
        int K = 0;

        try{
            for(String str : note){
                    //String f = clearSpace(str);
                    //System.out.println(f);
                    double nr;
                    if (str.compareTo("") != 0) {
                        nr = Integer.parseInt(str);
                        S += nr;
                        K++;
                    }
            }
            S/=K;

            try{
                double teza = Integer.parseInt(s);
                S = (S * 3 + teza)/4;
            }catch (Exception e){
                Toast t =  Toast.makeText(getApplicationContext(), "Lipsa teza", Toast.LENGTH_SHORT);
                t.show();
            }

            ((TextView)findViewById(R.id.txtViewMedie)).setText(String.format("%.2f", S));

        }catch(Exception e){
            Toast t = Toast.makeText(getApplicationContext(), "Date incorecte", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    public void onClickClear(View v){
        ((TextView)findViewById(R.id.txtViewMedie)).setText("");
        ((EditText)findViewById(R.id.editNote)).setText("");
        ((EditText)findViewById(R.id.editTeza)).setText("");
    }
}
