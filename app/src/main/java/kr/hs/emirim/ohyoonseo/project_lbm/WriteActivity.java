package kr.hs.emirim.ohyoonseo.project_lbm;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {
    EditText edit1;
    Button send;
    LBM_database myHelper=new LBM_database(this);;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        update();
        setContentView(R.layout.activity_writeletter);
        ImageView homeBtn = findViewById(R.id.write_home_btn);
        edit1 = findViewById(R.id.Edit1);
        send = findViewById(R.id.send);
        send.setOnClickListener(listener);
        homeBtn.setOnClickListener(listener);

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.send){
                //디비 저장
                Date now = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formatedNow = formatter.format(now);
                String text = edit1.getText().toString();
                int color = 1;
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES( '"+formatedNow+"', '"+text+"',"+color+");");
                sqlDB.close();
                Log.i("테스트","완료");


            }
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    };


    public void update(){
        sqlDB = myHelper.getWritableDatabase();
        myHelper.onUpgrade(sqlDB, 1,2);
        sqlDB.close();
    }
}