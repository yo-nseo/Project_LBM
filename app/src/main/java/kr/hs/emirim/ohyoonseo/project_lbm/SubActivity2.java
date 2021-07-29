package kr.hs.emirim.ohyoonseo.project_lbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class SubActivity2 extends AppCompatActivity {
    ImageButton menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        menu = findViewById(R.id.dot3);
        registerForContextMenu(menu);
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