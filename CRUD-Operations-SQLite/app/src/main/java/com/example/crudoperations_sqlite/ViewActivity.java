package com.example.crudoperations_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crudoperations_sqlite.mydbhandler.MyDBHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    MyDBHandler mdh;
    ListView lvFruitData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mdh = new MyDBHandler(this);
        lvFruitData = findViewById(R.id.lvFruitData);
        SQLiteDatabase db = mdh.getReadableDatabase();

        String searchData = "SELECT * FROM " + mdh.TABLE_NAME;

        Cursor cursor = db.rawQuery(searchData, null);

        ArrayList<String[]> fruitlist = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                fruitlist.add(new String[]{cursor.getString(1), cursor.getString(2)});
            } while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();

        lvFruitData.setAdapter(new ArrayAdapter<Array>(getApplicationContext(), android.R.layout.simple_list_item_1, (Array[]) fruitlist.toArray()));

    }
}