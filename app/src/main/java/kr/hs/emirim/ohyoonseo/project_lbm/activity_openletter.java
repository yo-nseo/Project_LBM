package kr.hs.emirim.ohyoonseo.project_lbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class activity_openletter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openletter);
        ImageView home_btn = findViewById(R.id.open_home_btn);
        Button sendBtn = findViewById(R.id.send_letter);
        home_btn.setOnClickListener(goHomeListener);
        sendBtn.setOnClickListener(goHomeListener);

    }
    View.OnClickListener goHomeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.send_letter){
                // 디비 연결 편지저장
            }
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    };
}