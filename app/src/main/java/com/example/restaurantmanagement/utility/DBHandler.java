package com.example.restaurantmanagement.utility;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHandler extends SQLiteOpenHelper {

    // private FirebaseAuth mAuth;

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "uwuDB314";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "users";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "username";

    // below variable id for our course duration column.
    private static final String DURATION_COL = "password";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.

        String query = "CREATE TABLE Users (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT," +
                "password TEXT," +
                "name TEXT," +
                "status TEXT," +
                "role TEXT)";

        //category
        String categoryQuery = "CREATE TABLE " + "Category" + " ("
                + "categoryId" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name" + " TEXT)";

        //food menu
        String menuQuery = "CREATE TABLE Foods (menuId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT," +
                "name TEXT," +
                "price FLOAT)";

        //create coupons
        String codeQuery = "CREATE TABLE coupons (couponId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "description TEXT," +
                "expiry TEXT," +
                "discount TEXT)";

        // used for cart
        //isFulfilled 0 = false (not fulfilled) , 1= true (is fulfilled)
        String orderDetailQuery = "CREATE TABLE OrderDetail (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ProductId TEXT," +
                "ProductName TEXT," +
                "Quantity TEXT," +
                "Price TEXT," +
                "Discount TEXT," +
                "isFulfilled INTEGER," +
                "Image TEXT)";

//        String rolesQuery = "CREATE TABLE " + "roles" + " ("
//                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + "role" + " TEXT)";
//
//        String userRolesQuery = "create table users_roles(\n" +
//                "    user_role_id integer not null primary key,\n" +
//                "    user_id integer not null,\n" +
//                "    role_id integer not null,\n" +
//                "    FOREIGN KEY(user_id) REFERENCES users(id),\n" +
//                "    FOREIGN KEY(role_id) REFERENCES roles(id),\n" +
//                "    UNIQUE (user_id, role_id)\n" +
//                ");  ";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        db.execSQL(menuQuery);
        db.execSQL(categoryQuery);
        db.execSQL(orderDetailQuery);
        db.execSQL(codeQuery);
//        db.execSQL(rolesQuery);
//        db.execSQL(userRolesQuery);
    }

    public void miscQueries() {

        String foodId = "1";
        SQLiteDatabase db = getReadableDatabase();

        //USER

        //aka updateUserInfo()
        String updateUser = String.format("UPDATE users SET username = %s , password = %s, name = %s WHERE ID = %d", "", "", "", "");

        // update as admin user
        String updateUserAdmin = String.format("UPDATE users SET username = %s , password = %s, " +
                "name = %s, status = %s, role = %s " + " WHERE ID = %d", "", "", "", "", "");

        // COUPON
        String createCoupon = "Insert into coupons(couponId, description,expiry,discount) VALUES ('%s','%s', '%s','%s')";
        String deleteCoupon = String.format("DELETE FROM coods WHERE couponId ='%s';", foodId);

        // FOOD
        String ad = "INSERT INTO foods(name,discount,description,price) VALUES('%s','%s', '%s','%s','%s','%s');";
        String deleteFood = String.format("DELETE FROM foods WHERE FoodId ='%s';", foodId);
        String updateFood = String.format("UPDATE foods SET description = %s WHERE ID = %d", "", "");

//        ORDERDETAIL
        String updateOrderQty = String.format("UPDATE OrderDetail SET Quantity = %s WHERE ID = %d", "", "");
    }


    // ORDERDETAIL
    // add menu item to cart
    public void addToCart(Order order) {
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

    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");

        db.execSQL(query);
    }


    // example to check if column exist
    public boolean columnExists(String value) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT EXISTS (SELECT * FROM users WHERE username='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }


    // this method is use to add new course to our sqlite database.
    public void loginUser(String courseName, String courseDuration) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean checkFoodName(String foodName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from foods where name = ?", new String[]{foodName});
        return cursor.getCount() > 0;
    }


    //to list all the data in the table by column
    public ArrayList<String> listColumnsDataStr(String specifiedTable, String specifiedColumn) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<String> tableString = new ArrayList<>();
        Cursor allRows = MyDB.rawQuery("SELECT * FROM " + specifiedTable, null);
        tableString = cursorToString(allRows, specifiedColumn);
        return tableString;
    }

    //to list all the data in the table by column
    @SuppressLint("Range")
    public ArrayList<String> cursorToString(Cursor cursor, String specifiedColumn) {
        ArrayList<String> userList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            String[] columnNames = cursor.getColumnNames();
            do {
                for (String name : columnNames) {
                    if (name.equals(specifiedColumn)) {
                        userList.add(cursor.getString(cursor.getColumnIndex(name)));
                    }
                }
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public ArrayList<Double> listColumnsDataDbl(String specifiedTable, String specifiedColumn) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<Double> tableString = new ArrayList<>();
        Cursor allRows = MyDB.rawQuery("SELECT * FROM " + specifiedTable, null);
        tableString = cursorToDouble(allRows, specifiedColumn);
        return tableString;
    }

    //to list all the data in the table by column
    @SuppressLint("Range")
    public ArrayList<Double> cursorToDouble(Cursor cursor, String specifiedColumn) {
        ArrayList<Double> userList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            String[] columnNames = cursor.getColumnNames();
            do {
                for (String name : columnNames) {
                    if (name.equals(specifiedColumn)) {
                        userList.add(cursor.getDouble(cursor.getColumnIndex(name)));
                    }
                }
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public Boolean insertUserAdmin(String username, String password, String role) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void updateUserInfo() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE users SET username = %s , password = %s, name = %s WHERE ID = %d", "", "", "", "");
        db.execSQL(query);
    }

    public String getUserRole(String username, String password) {
        String[] params = new String[]{username, password};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select role from users where username = ? and password = ?", params);
        String name = "";
        if (c.moveToFirst()) {
            name = c.getString(0);
        }
        return name;
    }

    public Boolean getUserStatus(String username, String password) {
        String[] params = new String[]{username, password};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select status from users where username = ? and password = ?", params);
        String status = "";
        if (c.moveToFirst()) {
            status = c.getString(0);
        }
        return status.equals("Active");
    }


    public Boolean insertUserData(String username, String password, String status, String personName, String role) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("status", status);
        contentValues.put("name", personName);
        contentValues.put("role", role);
        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean insertFoodData(String foodName, String foodDesc, double price) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", foodName);
        contentValues.put("description", foodDesc);
        contentValues.put("price", price);

        long result = MyDB.insert("foods", null, contentValues);
        return result != -1;
    }

}