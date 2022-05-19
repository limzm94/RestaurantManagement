package com.example.restaurantmanagement.admin.Entity;

import android.content.Context;

import com.example.restaurantmanagement.utility.DBHandler;

import java.util.ArrayList;

public class UserEntity {
    private Context context;

    public UserEntity(Context context) {
        this.context = context;
    }

    public ArrayList<UserObject> userList(String searchRequirement){
        DBHandler DB = new DBHandler(context);
        ArrayList<UserObject> userAccList = new ArrayList<>();
        ArrayList<Integer> allUserKey = DB.listColumnsDataInt("users", "id");
        System.out.println(allUserKey);
        ArrayList<String> allUsername = DB.listColumnsDataStr("users", "username");
        System.out.println(allUsername);
        ArrayList<String> allPassword = DB.listColumnsDataStr("users", "password");
        System.out.println(allPassword);
        ArrayList<String> allPersonName = DB.listColumnsDataStr("users", "name");
        System.out.println(allPersonName);
        ArrayList<String> allStatus = DB.listColumnsDataStr("users", "status");
        System.out.println(allStatus);
        ArrayList<String> allRoles = DB.listColumnsDataStr("users", "role");
        System.out.println(allRoles);
        int count = 0;

        if (!searchRequirement.equals("")) {
            while (allUsername.size() > count) {
                if (allUsername.get(count).equals(searchRequirement) ||
                        allPersonName.get(count).equals(searchRequirement) ||
                        allStatus.get(count).equals(searchRequirement)) {
                    System.out.println(allUsername.get(count));
                    userAccList.add(new UserObject(allUserKey.get(count), allPersonName.get(count), allStatus.get(count),
                            allRoles.get(count), allUsername.get(count), allPassword.get(count)));
                }
                count++;
            }
        } else {
            while (allUsername.size() > count) {
                userAccList.add(new UserObject(allUserKey.get(count), allPersonName.get(count), allStatus.get(count),
                        allRoles.get(count), allUsername.get(count), allPassword.get(count)));
                count++;
            }
        }

        return  userAccList;
    }

    public Boolean checkUser(String username) {
        DBHandler DB = new DBHandler(context);
        boolean checkUser;
        checkUser = DB.checkUsername(username);
        return checkUser;
    }

    public Boolean insertUser(String username,String password,String status,String personName, String role) {
        DBHandler DB = new DBHandler(context);
        boolean insertUser;
        insertUser = DB.insertUserData(username, password, status, personName, role);
        return insertUser;
    }

    public void updateUser(String editedUsername,String editedPassword,String editedPersonName,String editedStatus, String editedRole, int userKey) {
        DBHandler DB = new DBHandler(context);
        boolean updateUser;
        DB.updateUserInfo(editedUsername, editedPassword, editedPersonName, editedStatus, editedRole, userKey);
    }
}
