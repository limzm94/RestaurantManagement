package com.example.restaurantmanagement.utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.example.restaurantmanagement.utility.Order;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private  static final String DB_NAME ="EatItDB.db";
    private  static final int DB_VER = 2;
    public Database(Context context) {

        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlselect ={"ID","ProductName", "ProductId","Quantity","Price","Discount","Image"};
        String sqlTable ="OrderDetail";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlselect,null,null,null,null,null);
        final List<Order> result = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                result.add(new Order(
                        c.getInt(c.getColumnIndexOrThrow("ID")),
                        c.getString(c.getColumnIndexOrThrow("ProductId")),
                        c.getString(c.getColumnIndexOrThrow("ProductName")),
                        c.getString(c.getColumnIndexOrThrow("Quantity")),
                        c.getString(c.getColumnIndexOrThrow("Price")),
                        c.getString(c.getColumnIndexOrThrow("Discount")),
                        c.getString(c.getColumnIndexOrThrow("Image"))
                ));
            }while (c.moveToNext());
        }
        return result;
    };

    public void addToCart (Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount,Image) VALUES('%s','%s', '%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount(),
                order.getImage());
        db.execSQL(query);
    }
    public void cleanCart (){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");

        db.execSQL(query);
    }

    public void addToFavorites (String foodId)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Favorites VALUES('%s');",foodId);
        db.execSQL(query);
    }

    public void removeFromFavorites (String foodId)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Favorites WHERE FoodId ='%s';",foodId);
        db.execSQL(query);
    }
    public boolean isFavorite (String foodId)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM FAVORITES WHERE FoodId ='%s';",foodId);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public int getCountCart() {
        int count = 0;
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT COUNT(*) FROM OrderDetail");
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            do{
                count = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        return count;
    };

    public void updateCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE OrderDetail SET Quantity = %s WHERE ID = %d", order.getQuantity(), order.getID());
        db.execSQL(query);
    }
}