package kr.hs.emirim.ohyoonseo.project_lbm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class LBM_database extends SQLiteOpenHelper {
    public LBM_database(Context context){
        super(context, "groupDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE groupTBL (date CHAR(40) PRIMARY KEY, text TEXT, color INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS groupTB;");
        onCreate(db);
    }
}
