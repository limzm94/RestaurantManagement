package com.example.restaurantmanagement.utility;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.restaurantmanagement.admin.Entity.UserEntity;
import com.example.restaurantmanagement.admin.Entity.UserObject;
import com.example.restaurantmanagement.customer.Entity.OrderObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
        // 0 - not valid coupon , 1- is valid
        String codeQuery = "CREATE TABLE coupons (couponId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "code TEXT," +
                "description TEXT," +
                "isActive TEXT," +
                "discount INTEGER)";

        // used for cart
        //isFulfilled 0 = false (not fulfilled) , 1= true (is fulfilled)
        String orderDetailQuery = "CREATE TABLE OrderDetail (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ProductName TEXT," +
                "Quantity TEXT," +
                "Price FLOAT," +
                "Discount INTEGER," +
                "DiscountedPrice FLOAT," +
                "CustomerName TEXT," +
                "UserID INTEGER," +
                "OrderDate TEXT," +
                "MenuId TEXT," +
                "OrderId INTEGER," +
                "isFulfilled TEXT)";

//        String rolesQuery = "CREATE TABLE " + "roles" + " ("
//                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
//                "isActive INTEGER,"
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

//    public void suspendRoles(int id ) {
//        SQLiteDatabase db = getReadableDatabase();
//        String delete = String.format("UPDATE roles SET isActive = %s WHERE ID = %d",0, id);
//
//        db.execSQL(delete);
//    }

    public void deleteMenuItem(int menuId) {
        SQLiteDatabase db = getReadableDatabase();
        String delete = String.format("DELETE FROM Foods WHERE menuId = %d", menuId);

        db.execSQL(delete);
    }

    public void deleteCoupon(int couponId) {
        SQLiteDatabase db = getReadableDatabase();
        String delete = String.format("DELETE FROM Coupons WHERE menuId = %d", couponId);
        db.execSQL(delete);
    }

    public void deleteUser(int userID) {
        SQLiteDatabase db = getReadableDatabase();
        String delete = String.format("DELETE FROM Foods WHERE id = %d", userID);

        db.execSQL(delete);
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

    public Boolean checkCustomer(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and role = ?", new String[]{username, "Customer"});
        return cursor.getCount() > 0;
    }

    public Boolean checkFoodName(String foodName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from foods where name = ?", new String[]{foodName});
        return cursor.getCount() > 0;
    }

    public Boolean checkCouponCode(String couponCode) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from coupons where code = ?", new String[]{couponCode});
        return cursor.getCount() > 0;
    }

    public Boolean checkCouponValid(String couponCode) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from coupons where code = ? and isActive = ?", new String[]{couponCode, "Active"});
        return cursor.getCount() > 0;
    }

    public Boolean checkOrderFulfilled(int orderId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from OrderDetail where orderId = ? and isFulfilled = ?", new String[]{String.valueOf(orderId), "Fulfilled"});
        return cursor.getCount() > 0;
    }


    //to list all the data in the table by column
    public ArrayList<String> listColumnsDataStr(String specifiedTable, String specifiedColumn) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<String> tableString;
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


    //to list all the data in the table by column
    public ArrayList<OrderObject> listOrderObject(int orderId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<OrderObject> tableString;
        Cursor allRows = MyDB.rawQuery("SELECT * FROM OrderDetail WHERE orderId = " + "'" + orderId  +"'" , null);
        tableString = cursorToOrderObject(allRows);
        return tableString;
    }

    //to list all the data in the table by column
    @SuppressLint("Range")
    public ArrayList<OrderObject> cursorToOrderObject(Cursor cursor) {
        ArrayList<OrderObject> orderList = new ArrayList<>();
        if (cursor.moveToFirst()) {
           // String[] columnNames = cursor.getColumnNames();
            do {
                String foodName = cursor.getString(cursor.getColumnIndexOrThrow("ProductName"));
                String isFulfilled = cursor.getString(cursor.getColumnIndexOrThrow("isFulfilled"));
                String OrderDate = cursor.getString(cursor.getColumnIndexOrThrow("OrderDate"));
                String CustomerName = cursor.getString(cursor.getColumnIndexOrThrow("CustomerName"));
                int Quantity = cursor.getInt(cursor.getColumnIndexOrThrow("Quantity"));
                int OrderId = cursor.getInt(cursor.getColumnIndexOrThrow("OrderId"));
                int Discount = cursor.getInt(cursor.getColumnIndexOrThrow("Discount"));
                int MenuId = cursor.getInt(cursor.getColumnIndexOrThrow("MenuId"));
                Float price = cursor.getFloat(cursor.getColumnIndexOrThrow("Price"));
                OrderObject order = new OrderObject(foodName, "", price, Quantity, CustomerName, isFulfilled, OrderId, MenuId, Discount, OrderDate);
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        return orderList;
    }

    public ArrayList<Double> listColumnsDataDbl(String specifiedTable, String specifiedColumn) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<Double> tableDouble;
        Cursor allRows = MyDB.rawQuery("SELECT * FROM " + specifiedTable, null);
        tableDouble = cursorToDouble(allRows, specifiedColumn);
        return tableDouble;
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

    public ArrayList<Integer> listColumnsDataInt(String specifiedTable, String specifiedColumn) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<Integer> tableInteger;
        Cursor allRows = MyDB.rawQuery("SELECT * FROM " + specifiedTable, null);
        tableInteger = cursorToInt(allRows, specifiedColumn);
        return tableInteger;
    }

    public ArrayList<Integer> listIsItFulfilled(String specifiedTable, String specifiedColumn, String isItFulfilled) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<Integer> tableInteger;
        Cursor allRows = MyDB.rawQuery("SELECT * FROM " + specifiedTable + " WHERE isFulfilled = " + "'" +isItFulfilled + "'", null);
        tableInteger = cursorToInt(allRows, specifiedColumn);
        Set<Integer> set = new HashSet<>(tableInteger);
        tableInteger.clear();
        tableInteger.addAll(set);
        return tableInteger;
    }

    //to list all the data in the table by column
    @SuppressLint("Range")
    public ArrayList<Integer> cursorToInt(Cursor cursor, String specifiedColumn) {
        ArrayList<Integer> userList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            String[] columnNames = cursor.getColumnNames();
            do {
                for (String name : columnNames) {
                    if (name.equals(specifiedColumn)) {
                        userList.add(cursor.getInt(cursor.getColumnIndex(name)));
                    }
                }
            } while (cursor.moveToNext());
        }
        return userList;
    }

   /* public ArrayList<Integer> listOrderId(String fulfilled) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Log.d("", "tableToString called");
        ArrayList<Integer> tableInteger;
        Cursor allRows = MyDB.rawQuery("SELECT * FROM " + specifiedTable, null);
        tableInteger = cursorToInt(allRows, specifiedColumn);
        return tableInteger;
    }*/


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

    public void updateOrderFulFilled(int orderId) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("isFulfilled", "Fulfilled");
        db.update("OrderDetail", args, "OrderId" + "=" + orderId, null);
    }

    public void updateUserInfo(String username, String password, String personName, String status, String role, int userKey) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("username", username);
        args.put("password", password);
        args.put("name", personName);
        args.put("status", status);
        args.put("role", role);
        db.update("Users", args, "id" + "=" + userKey, null);
    }

    public void updateFoodInfo(String foodName, String foodDesc, double price, int foodKey) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("name", foodName);
        args.put("description", foodDesc);
        args.put("price", price);
        db.update("Foods", args, "menuId" + "=" + foodKey, null);
    }

    public void updateCouponInfo(String couponCode, String couponDesc, int discount, String status, int couponId) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("code", couponCode);
        args.put("description", couponDesc);
        args.put("isActive", status);
        args.put("discount", discount);
        db.update("coupons", args, "couponId" + "=" + couponId, null);
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


    public UserObject getUserByID(Integer userid) {
        String[] arguments = new String[]{String.valueOf(userid)};
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<UserObject> users = new ArrayList<UserObject>();
        UserObject user = null;
        Cursor c = db.rawQuery("Select * from users where id = ?", arguments);
        if (c != null && c.moveToFirst()) {
            Integer id = c.getInt(c.getColumnIndexOrThrow("id"));
            String name = c.getString(c.getColumnIndexOrThrow("name"));
            String username = c.getString(c.getColumnIndexOrThrow("username"));
            String password = c.getString(c.getColumnIndexOrThrow("password"));
            String status = c.getString(c.getColumnIndexOrThrow("status"));
            String position = c.getString(c.getColumnIndexOrThrow("role"));
            user = new UserObject(id, name, status, position, username, password);

        }
        return user;
    }

    // get user by username
    public ArrayList<UserObject> getUserByUsername(String inputUsername) {
        String[] arguments = new String[]{inputUsername};
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<UserObject> users = new ArrayList<UserObject>();
        Cursor c = db.rawQuery("Select * from users where username = ?", arguments);
        if (c != null && c.moveToFirst()) {
            Integer id = c.getInt(c.getColumnIndexOrThrow("id"));
            String name = c.getString(c.getColumnIndexOrThrow("name"));
            String username = c.getString(c.getColumnIndexOrThrow("username"));
            String password = c.getString(c.getColumnIndexOrThrow("password"));
            String status = c.getString(c.getColumnIndexOrThrow("status"));
            String position = c.getString(c.getColumnIndexOrThrow("role"));
            UserObject user = new UserObject(id, name, status, position, username, password);
            users.add(user);
        }
        return users;
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

    public Boolean insertCouponData(String couponCode, String couponDesc, int discount, String status) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", couponCode);
        contentValues.put("description", couponDesc);
        contentValues.put("isActive", status);
        contentValues.put("discount", discount);
        long result = MyDB.insert("coupons", null, contentValues);
        return result != -1;
    }

    public void insertOrderDetails(String foodName, String orderDate, double price, double discountedPrice, int quantity, String customerName, String isFulfilled, int orderId, int foodKey, int discount) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ProductName", foodName);
        contentValues.put("Quantity", quantity);
        contentValues.put("Price", price);
        contentValues.put("Discount", discount);
        contentValues.put("DiscountedPrice", discountedPrice);
        contentValues.put("CustomerName", customerName);
        contentValues.put("OrderDate", orderDate);
        contentValues.put("MenuId", foodKey);
        contentValues.put("OrderId", orderId);
        contentValues.put("isFulfilled", isFulfilled);
        long result = MyDB.insert("OrderDetail", null, contentValues);
        // return result != -1;
    }

    public int getDiscount(String code) {
        String[] params = new String[]{code};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select discount from coupons where code = ?", params);
        int discountCode = 0;
        if (c.moveToFirst()) {
            discountCode = c.getInt(0);
        }
        return discountCode;
    }

    // Date string split
    // not working
    public List<String> getOrdersByDate(String date) {
        String[] params = new String[]{date};
        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<String> tableString;
        List<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from OrderDetail where DateOrdered = ?", params);
        if (c.moveToFirst()) {
            list.add(c.getString(c.getColumnIndexOrThrow("ProductName")));
            list.add(c.getString(c.getColumnIndexOrThrow("Price")));
        }

        // day
        int day = Integer.parseInt(date.substring(0, 1));
        int month = Integer.parseInt(date.substring(3, 4));
        int year = Integer.parseInt(date.substring(6, 9));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

        System.out.print(list);
        return list;
    }

    //select CustomerName, sum(price) from OrderDetail where OrderDate = '21-05-1999' group by CustomerName;

    //get count of visits of user by date
    public ArrayList<OrderObject> getSpendingHabit() {
//        select CustomerName, sum(price) from OrderDetail where OrderDate = '21-05-1999'
//        group by CustomerName;
        ArrayList<OrderObject> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        String[] params = new String[]{};
        Cursor c = db.rawQuery("select CustomerName, OrderDate, sum(price) from OrderDetail group by CustomerName, OrderDate", params);
        if (c.moveToFirst()) {
            do {
                String CustomerName = c.getString(c.getColumnIndexOrThrow("CustomerName"));
                String OrderDate = c.getString(c.getColumnIndexOrThrow("OrderDate"));
                Float price = c.getFloat(c.getColumnIndexOrThrow("sum(price)"));
                OrderObject yay = new OrderObject("","",price,0,CustomerName,"",0,0,0, OrderDate);
                orderList.add(yay);
            }while (c.moveToNext());
        }
        return orderList;
    }

    //get count of visits of user by date
    public int getFrequencyVisit(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        String[] params = new String[]{date};
        Cursor c = db.rawQuery("Select COUNT(DISTINCT(OrderId)) from OrderDetail where OrderDate = ?", params);
        if (c.moveToFirst()) {
            totalEarning = c.getInt(0);
        }
        return totalEarning;
    }

    //get count of visits of user by date
    public int getFrequencyVisitByMonth(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        int first = date.indexOf("-");
        int last = date.indexOf("-");
        String dayWithYear = date.substring(first + 1);
        String yearString = dayWithYear.substring(last + 1);
        String monthString = dayWithYear.substring(0, last);
        System.out.println(yearString);
        System.out.println(monthString);
        System.out.println("FISIFII");
        String[] params = new String[]{monthString,yearString};
        Cursor c = db.rawQuery("Select COUNT(DISTINCT(OrderId)) from OrderDetail where substr(OrderDate,4,2) = ? AND substr(OrderDate,7,4) = ?", params);
        if (c.moveToFirst()) {
            totalEarning = c.getInt(0);
        }
        return totalEarning;
    }

    //get count of visits of user by date
    public int getFrequencyVisitByYear(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        int first = date.indexOf("-");
        int last = date.indexOf("-");
        String dayWithYear = date.substring(first + 1);
        String yearString = dayWithYear.substring(last + 1);
        String[] params = new String[]{yearString};
        Cursor c = db.rawQuery("Select COUNT(DISTINCT(OrderId)) from OrderDetail where substr(OrderDate,7,4) = ?", params);
        if (c.moveToFirst()) {
            totalEarning = c.getInt(0);
        }
        return totalEarning;
    }


    //get dish of orders on that day with the most orders??

//    select * from Foods where menuId = (SELECT       menuId
//    FROM     OrderDetail
//    WHERE OrderDate = '21-03-1999'
//    GROUP BY menuId
//    ORDER BY COUNT(*) DESC
//    LIMIT    1);

    public String getPreferenceFood(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        String food = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(12,3,22));//Set specific Date if you want to
        String[] params = new String[]{date};
        Cursor c = db.rawQuery("select name from Foods where menuId = (" +
                "SELECT menuId from OrderDetail WHERE OrderDate = ? GROUP BY menuId " +
                "ORDER BY COUNT(*) DESC LIMIT 1)", params);
        if (c.moveToFirst()) {
            food = c.getString(0);
        }
        return food;
    }

    public String getMonthPreferenceFood(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        String food = "";
        int first = date.indexOf("-");
        int last = date.indexOf("-");
        String dayWithYear = date.substring(first + 1);
        String yearString = dayWithYear.substring(last + 1);
        String monthString = dayWithYear.substring(0, last);
        String[] params = new String[]{monthString, yearString};
        Cursor c = db.rawQuery("select name from Foods where menuId = (" +
                "SELECT menuId from OrderDetail where substr(OrderDate,4,2) = ? " +
                "AND substr(OrderDate,7,4) = ? GROUP BY menuId " +
                "ORDER BY COUNT(*) DESC LIMIT 1)", params);
        if (c.moveToFirst()) {
            food = c.getString(0);
        }
        return food;
    }

    public String getYearPreferenceFood(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        String food = "";
        int first = date.indexOf("-");
        int last = date.indexOf("-");
        String dayWithYear = date.substring(first + 1);
        String yearString = dayWithYear.substring(last + 1);
        String[] params = new String[]{yearString};
        Cursor c = db.rawQuery("select name from Foods where menuId = (" +
                "SELECT menuId from OrderDetail WHERE substr(OrderDate,7,4) = ? GROUP BY menuId " +
                "ORDER BY COUNT(*) DESC LIMIT 1)", params);
        if (c.moveToFirst()) {
            food = c.getString(0);
        }
        return food;
    }

    public int getDailyEarnings(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        String[] params = new String[]{date};
        Cursor c = db.rawQuery("Select SUM(PRICE) from OrderDetail where OrderDate = ?", params);
        if (c.moveToFirst()) {
            totalEarning = c.getInt(0);
        }
        return totalEarning;
    }

    //todo: add year
    public int getMonthlyEarnings(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        int first = date.indexOf("-");
        String dayWithYear = date.substring(first + 1);
        int last = dayWithYear.indexOf("-");
        String yearString = dayWithYear.substring(last + 1);
        String monthString = dayWithYear.substring(0, last);
        String[] params = new String[]{monthString};
        Cursor c = db.rawQuery("Select SUM(Price) from OrderDetail where substr(OrderDate,4,2) = ?", params);
        if (c.moveToFirst()) {
            totalEarning = c.getInt(0);
        }
        return totalEarning;
    }

    public int getYearlyEarnings(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        int totalEarning = 0;
        int first = date.indexOf("-");
        int last = date.indexOf("-");
        String dayWithYear = date.substring(first + 1);
        String yearString = dayWithYear.substring(last + 1);
        System.out.print(yearString);
        System.out.print("yearString");
        String[] params = new String[]{yearString};
        // 8 when date is "date"
//        OrderDate,8,4
        Cursor c = db.rawQuery("Select SUM(Price) from OrderDetail where substr(OrderDate,7,4) = ?", params);
        if (c.moveToFirst()) {
            totalEarning = c.getInt(0);
        }
        return totalEarning;
    }


    //get orderId that is fulfilled or unfulfilled
    public ArrayList<Integer> getAllFulfilledEtc(String fulfilled) {
        String[] params = new String[]{fulfilled};
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Integer> list=new ArrayList<Integer>();
        Cursor c = db.rawQuery("Select OrderId from OrderDetail where isFulfilled = ?", params);
        if (c.moveToFirst()) {
            list.add(c.getInt(0));
        }
        return list;
    }


    //get all the order rows by orderID
    public ArrayList<OrderObject> getOrderByID(String orderId) {
        String[] params = new String[]{orderId};
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<OrderObject> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from OrderDetail where OrderId = ?", params);
        if (c.moveToFirst()) {
            String foodname = c.getString(c.getColumnIndexOrThrow("ProductName"));
            String isFulfilled = c.getString(c.getColumnIndexOrThrow("isFulfilled"));
            String OrderDate = c.getString(c.getColumnIndexOrThrow("OrderDate"));
            String CustomerName = c.getString(c.getColumnIndexOrThrow("CustomerName"));
            int Quantity = c.getInt(c.getColumnIndexOrThrow("Quantity"));
            int OrderId = c.getInt(c.getColumnIndexOrThrow("OrderId"));
            int Discount = c.getInt(c.getColumnIndexOrThrow("Discount"));
            int MenuId = c.getInt(c.getColumnIndexOrThrow("MenuId"));
            Float price = c.getFloat(c.getColumnIndexOrThrow("Price"));
            OrderObject order = new OrderObject(foodname, "", price, Quantity, CustomerName, isFulfilled, OrderId, MenuId, Discount, OrderDate);
            list.add(order);
        }
        return list;
    }

    //view monthly spending
    public int getMonthlyEarning(String date1, String date2) {
        String[] params = new String[]{"20/10/2013", "25/10/2013"};
        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<String> tableString;
        List<Number> earnings;
        List<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select Price from OrderDetail where OrderDate BETWEEN ? AND ?", params);
        int total = 0;
        if (c.moveToFirst()) {
            total = c.getInt(0);
        }

        return total;
    }


    //view weekly spending
    public ArrayList<Order> getOrderHistory(String userid) {
        String[] params = new String[]{userid};
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Order> orders = new ArrayList<Order>();
        List<Number> earnings;
        List<String> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from OrderDetail where UserID = ?", params);
        int total = 0;
        String date;
        if (c != null && c.moveToFirst()) {
            int id = c.getInt(c.getColumnIndexOrThrow("ID"));
            String productId = c.getString(c.getColumnIndexOrThrow("ProductId"));
        }
        return orders;
    }

    public int getOrderID() {
        String[] params = new String[]{};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select OrderId from OrderDetail ORDER BY OrderId DESC LIMIT 1", params);
        int name = 0;
        if (c.moveToFirst()) {
            name = c.getInt(0);
        }
        return name;
    }


}