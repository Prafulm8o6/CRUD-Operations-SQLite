package com.example.crudoperations_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudoperations_sqlite.mydbhandler.MyDBHandler;

public class UpdateActivity extends AppCompatActivity {

    MyDBHandler mdh;
    EditText etUpdFruitName, etUpdFruitPrize;
    Button btnUpdateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mdh = new MyDBHandler(this);

        etUpdFruitPrize = findViewById(R.id.etUpdFruitPrize);
        btnUpdateData = findViewById(R.id.btnUpdateData);

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mdh.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(MyDBHandler.COL_Fruit_Prize, Integer.parseInt(etUpdFruitPrize.getText().toString()));

                Cursor cursor = db.rawQuery("SELECT * FROM " + mdh.TABLE_NAME + " WHERE " + mdh.COL_Fruit_Name + " = ? ", new String[]{mdh.COL_Fruit_Name});
                if (cursor.getCount() > 0) {

                    long l = db.update(mdh.TABLE_NAME, cv, mdh.COL_Fruit_Name + "=?", new String[]{mdh.COL_Fruit_Name});

                    if (l != -1) {
                        Toast.makeText(UpdateActivity.this, "Updated.", Toast.LENGTH_SHORT).show();
//                        etFruitPrize.setText("");
//                        etFruitName.setText("");
                    } else {
                        Toast.makeText(UpdateActivity.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateActivity.this, "Data Not Found.", Toast.LENGTH_SHORT).show();
                }
//                db.close();
            }
        });
    }
}