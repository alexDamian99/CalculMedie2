package devsaa.calculmedie;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class history extends AppCompatActivity {

    String istoric = "";
    TextView istoricView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        Intent intent = getIntent(); // iti ia intentul care ti-a deschis activity-ul asta

        istoric = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); // pui istoricul din intentul ce face legatura cu mainActivityul

        istoricView = (TextView)findViewById(R.id.txtViewIstoric);

        istoricView.setText(istoric + "\n");
    }

    public void onClickBack(View v){
        finish(); //inchidem activity-ul istoric
    }

    public void onClickClearHistory(View v){
        MainActivity.istoric = "";
        MainActivity.indice = 0;

        istoricView.setText("");
    }
}
