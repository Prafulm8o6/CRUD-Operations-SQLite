package com.example.crudoperations_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudoperations_sqlite.mydbhandler.MyDBHandler;

public class InsertActivity extends AppCompatActivity {

    MyDBHandler mdh;
    EditText etFruitName, etFruitPrize;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mdh = new MyDBHandler(this);


        etFruitName = findViewById(R.id.etFruitName);
        etFruitPrize = findViewById(R.id.etFruitPrize);
        btnAddData = findViewById(R.id.btnAddData);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = mdh.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(mdh.COL_Fruit_Name, etFruitName.getText().toString());
                cv.put(mdh.COL_Fruit_Prize, Integer.parseInt(etFruitPrize.getText().toString()));

                long l = db.insert(mdh.TABLE_NAME, null, cv);

                if (l != -1) {
                    Toast.makeText(InsertActivity.this, "Inserted.", Toast.LENGTH_SHORT).show();
                    etFruitPrize.setText("");
                    etFruitName.setText("");
                } else {
                    Toast.makeText(InsertActivity.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                }

//                db.close();

            }
        });
    }
}