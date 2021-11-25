package kr.hs.emirim.ohyoonseo.project_lbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_openletter extends AppCompatActivity {
    Intent intent;
    LBM_database myHelper;
    ArrayList<testDate> td;
    SQLiteDatabase rsqlDB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openletter);
        intent = getIntent();
        ImageView home_btn = findViewById(R.id.open_home_btn);
        Button sendBtn = findViewById(R.id.send_letter);
        TextView context = findViewById(R.id.open_textView);
        ImageView imgv = findViewById(R.id.imgR);
        int colorkey;
        home_btn.setOnClickListener(goHomeListener);
        sendBtn.setOnClickListener(goHomeListener);
        myHelper = new LBM_database(this);
        rsqlDB = myHelper.getReadableDatabase();    // 디비 읽기
        Cursor cursor;

        String thisdate = intent.getStringExtra("date1");
        cursor = rsqlDB.rawQuery("SELECT text,color FROM groupTBL WHERE date = '"+thisdate+"';",null);

        while(cursor.moveToNext()){
            context.setText(cursor.getString(0));
            colorkey = cursor.getInt(1);
            switch (colorkey){
                case 1:
                    imgv.setImageResource(R.drawable.color_red);
            }
        }
        cursor.close();
    }
    View.OnClickListener goHomeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    };
}