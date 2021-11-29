package kr.hs.emirim.ohyoonseo.project_lbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ImageButton menu;
    GridView gridL;
    GridAdapter adapter;
    LBM_database myHelper;
    ArrayList<testDate> td;
    SQLiteDatabase rsqlDB, wsqlDB;
    Cursor cursor;
    Button book1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myHelper = new LBM_database(this);
        rsqlDB = myHelper.getReadableDatabase();    // 디비 읽기
        wsqlDB = myHelper.getWritableDatabase();    // 디비수정
        menu = findViewById(R.id.dot3);
        FloatingActionButton addLetter = findViewById(R.id.add_write);
        registerForContextMenu(menu);

        addLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WriteActivity.class);
                startActivity(intent);
            }
        });
        cursor = rsqlDB.rawQuery("SELECT * FROM groupTBL;", null);

        // 데이터 가져옴
        gridL = findViewById(R.id.grid_letter);


        td = new ArrayList<>();     // 편지데이터
        while(cursor.moveToNext()){
            Log.i("TAG", "onCreate: "+cursor.getString(0));
            td.add(new testDate(cursor.getString(0)));
        }

        adapter = new GridAdapter(this,td);
        gridL.setAdapter(adapter);

        gridL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 보내기
                Log.i("TAG", "onItemClick: "+td.get(position).toString());
                Intent intent = new Intent(getApplicationContext(),activity_openletter.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("date",td.get(position).toString());
//                intent.putExtras(bundle);       // 데이터 보내기
                intent.putExtra("date1", td.get(position).toString());
                startActivity(intent);
            }
        });
        gridL.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dir = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("편지삭제")
                        .setMessage(td.get(position)+"편지를 정말 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                td.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소",null)
                        .show();
                return false;
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(menu);
                openContextMenu(menu);
                unregisterForContextMenu(menu);
            }
        });
        cursor.close();
        rsqlDB.close();
        wsqlDB.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(R.string.menu_title);
        inflater.inflate(R.menu.sub2_menu, menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menu2 = getMenuInflater();
        menu2.inflate(R.menu.sub2_menu, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.item_button1:
                final String[] versionArrray = new String[] {"월요일","화요일","수요일","목요일","금요일","토요일","일요일"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(HomeActivity.this);
                dlg.setTitle("편지데이선택");
                dlg.setSingleChoiceItems(versionArrray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity button1 = null;
                        button1.setTitle(versionArrray[which]);
                    }
                });
                dlg.show();
                return true;
            case R.id.item_del:
                Toast.makeText(HomeActivity.this,"편지를 길게 누르면 삭제됩니다",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_sort:
                return true;
            case R.id.item_sort1:
                return true;
            case R.id.item_sort2:
                return true;
            default:
                return false;
        }
    }

}