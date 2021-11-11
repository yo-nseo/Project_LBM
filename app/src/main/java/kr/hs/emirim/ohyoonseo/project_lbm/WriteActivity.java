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
    Button red, yellow, green, blue, purple, brown;
    ImageView imageView;
    int color = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writeletter);

        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        purple = findViewById(R.id.purple);
        brown = findViewById(R.id.brown);

        imageView = findViewById(R.id.imgV);

        update();
        setContentView(R.layout.activity_writeletter);
        ImageView homeBtn = findViewById(R.id.write_home_btn);
        edit1 = findViewById(R.id.Edit1);
        send = findViewById(R.id.send);
        send.setOnClickListener(listener);
        homeBtn.setOnClickListener(listener);

        red.setOnClickListener(colorlistener);
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "노랑클릭", Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.yellow);
            }
        });
        green.setOnClickListener(colorlistener);
        blue.setOnClickListener(colorlistener);
        purple.setOnClickListener(colorlistener);
        brown.setOnClickListener(colorlistener);
    }


    View.OnClickListener colorlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            /*switch (v.getId()){
                case R.id.red:
                    color = 1;
                    imageView.setImageResource(R.drawable.red);
                    break;
                case R.id.yellow:
//                    Log.i("노랑색", "클릭됨");
                    color = 2;
                    imageView.setImageResource(R.drawable.yellow);
                    break;
                case R.id.green:
                    color = 3;
                    imageView.setImageResource(R.drawable.green);
                    break;
                case R.id.blue:
                    color = 4;
                    imageView.setImageResource(R.drawable.blue);
                    break;
                case R.id.purple:
                    color = 5;
                    imageView.setImageResource(R.drawable.purple);
                    break;
                case R.id.brown:
                    color = 6;
                    imageView.setImageResource(R.drawable.brown);
                    break;

            }*/
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.send){
                //디비 저장
                Date now = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formatedNow = formatter.format(now);
                String text = edit1.getText().toString();
                int letter_color = color;
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