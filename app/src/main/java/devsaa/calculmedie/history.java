package devsaa.calculmedie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        Intent intent = getIntent(); // iti ia intentul care ti-a deschis activity-ul asta

        String istoric = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); // pui istoricul din intentul ce face legatura cu mainActivityul

        TextView textView = new TextView(this);
        textView.setText(istoric);

        ViewGroup layout = (ViewGroup)findViewById(R.id.activity_history);
        layout.addView(textView);

    }


}
