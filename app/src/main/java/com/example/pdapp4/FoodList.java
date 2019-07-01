package com.example.pdapp4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {

    Button btnadd;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Food> Foodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        btnadd = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.lvFoodlist);
        Foodal = new ArrayList<Food>();
        aa = new FoodAdapter(this,R.layout.row,Foodal);
        DBHelper db = new DBHelper(FoodList.this);
        final ArrayList<Food> data = db.getAllFood();
        aa = new FoodAdapter(this,R.layout.row,data);
        lv.setAdapter(aa);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodList.this,AddFood.class);
                startActivity(i);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(FoodList.this,
                        EditFood.class);
                int dataID = data.get(position).getId();
                String dataLocation = data.get(position).getLocation();
                String dataAddress = data.get(position).getAddress();
                String dataDate = data.get(position).getDate();
                String dataDish = data.get(position).getBestdish();
                int dataStar = data.get(position).getStars();

                Food target = new Food(dataID, dataLocation, dataAddress, dataDate, dataDish, dataStar);
                i.putExtra("data", target);
                startActivityForResult(i, 9);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            lv.setAdapter(aa);
        }
    }
}
