package kr.hs.emirim.ohyoonseo.project_lbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ImageButton menu;
    GridView gridL;
    GridAdapter adapter;

    ArrayList<testDate> td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu = findViewById(R.id.dot3);
        FloatingActionButton addLetter = findViewById(R.id.add_write);
        registerForContextMenu(menu);
        addLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),activity_openletter.class);
                startActivity(intent);
            }
        });

        // 데이터 가져옴
        gridL = findViewById(R.id.grid_letter);

        td = new ArrayList<>();
        td.add(new testDate("2021.07.31"));
        td.add(new testDate("2021.08.10"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.07.31"));
        td.add(new testDate("2021.08.10"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.07.31"));
        td.add(new testDate("2021.08.10"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.07.31"));
        td.add(new testDate("2021.08.10"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.08.21"));
        td.add(new testDate("2021.07.31"));
        td.add(new testDate("2021.08.10"));
        td.add(new testDate("2021.08.21"));

        adapter = new GridAdapter(this,td);
        gridL.setAdapter(adapter);

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
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(R.string.menu_title);
        inflater.inflate(R.menu.sub2_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.item_setting:
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