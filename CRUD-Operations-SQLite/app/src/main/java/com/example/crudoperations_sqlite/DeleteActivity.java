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

public class DeleteActivity extends AppCompatActivity {

    MyDBHandler mdh;
    EditText etDelFruitName;
    Button btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        mdh = new MyDBHandler(this);

        etDelFruitName = findViewById(R.id.etDelFruitName);
        btnDeleteData = findViewById(R.id.btnDeleteData);

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = mdh.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(mdh.COL_Fruit_Name, etDelFruitName.getText().toString());

                Cursor cursor = db.rawQuery("SELECT * FROM " + mdh.TABLE_NAME + " WHERE " + mdh.COL_Fruit_Name + " = ? ", new String[]{mdh.COL_Fruit_Name});

                if (cursor.getCount() > 0) {

                    long l = db.delete(mdh.TABLE_NAME, mdh.COL_Fruit_Name + "=?", new String[]{mdh.COL_Fruit_Name});

                    if (l != -1) {
                        Toast.makeText(DeleteActivity.this, "Deleted.", Toast.LENGTH_SHORT).show();
//                        etFruitPrize.setText("");
//                        etFruitName.setText("");
                    } else {
                        Toast.makeText(DeleteActivity.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DeleteActivity.this, "Data Not Found.", Toast.LENGTH_SHORT).show();
                }
//                db.close();
            }
        });
    }
}