package kr.hs.emirim.ohyoonseo.project_lbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.Calendar;

public class SubActivity2 extends AppCompatActivity {
    GridView gridL;
    GridAdapter adapter;

    ArrayList<testDate> td;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        gridL = findViewById(R.id.grid_letter);

        td = new ArrayList<>();
        td.add(new testDate("2021.07.31"));
        td.add(new testDate("2021.08.10"));
        td.add(new testDate("2021.08.21"));

        adapter = new GridAdapter(this,td);
        gridL.setAdapter(adapter);

    }
}