package com.example.pdapp4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_FOOD = "food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LOCATION = "food_location";
    private static final String COLUMN_ADDRESS = "food_address";
    private static final String COLUMN_DATE = "food_date";
    private static final String COLUMN_DISH = "food_dish";
    private static final String COLUMN_STARS = "food_stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFoodTableSql =  "CREATE TABLE " + TABLE_FOOD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_LOCATION + " TEXT ," + COLUMN_ADDRESS + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_DISH + " TEXT, " + COLUMN_STARS + " INTEGER )";

        db.execSQL(createFoodTableSql);
        Log.i("info","created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "food");
        onCreate(db);
    }

    public long insertFood(String location, String address,String date ,String bestdish,int stars) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_DISH, bestdish);
        values.put(COLUMN_STARS, stars);

        long result = db.insert(TABLE_FOOD, null, values);

        //check if record is inserted successfully
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }

        db.close();
        Log.d("SQL Insert ", "" + result);
        //id returned, shouldn’t be -1

        return result;
    }

    public int updateFood(Food data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_LOCATION, data.getLocation());
        values.put(COLUMN_ADDRESS, data.getAddress());
        values.put(COLUMN_DATE, data.getDate());
        values.put(COLUMN_DISH, data.getBestdish());
        values.put(COLUMN_STARS, data.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};

        int result = db.update(TABLE_FOOD, values, condition, args);

        //check if record is updated successfully if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;

    }

    public int deleteFood(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};

        int result = db.delete(TABLE_FOOD, condition, args);

        //check if record is successfully updated if the affected record is 1
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;

    }

    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foods = new ArrayList<Food>();

        String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_LOCATION +
                "," + COLUMN_ADDRESS + "," + COLUMN_DATE + "," + COLUMN_DISH + "," +COLUMN_STARS +
                " FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String location = cursor.getString(1);
                String address = cursor.getString(2);
                String date = cursor.getString(3);
                String dish = cursor.getString(4);
                int stars = cursor.getInt(5);
                Food obj = new Food(id,location,address,date,dish,stars);
                foods.add(obj);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foods;

    }
}
