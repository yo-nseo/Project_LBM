package kr.hs.emirim.ohyoonseo.project_lbm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SetupActivity extends AppCompatActivity {
    TextView week_text, inteval_text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_mail);

        week_text = findViewById(R.id.send_mail_day);
        inteval_text = findViewById(R.id.send_mail_interval);

        week_text.setOnClickListener(listener);
        inteval_text.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    startActivity(new Intent(v.getContext(), WriteActivity.class));
                    break;
                case R.id.btn2:
                    startActivity(new Intent(v.getContext(), activity_openletter.class));
                    break;
            }
        }
    };
}
