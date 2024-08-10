package db;

import constant.CommonConstants;

import java.sql.*;

//JDBC: Java Database Connectivity
//This class will be our gateway in accessing our MySQL database
public class MyJDBC {
    //Register a new user to the database
    //True if registration is successful, false otherwise
    public static boolean register(String username, String password, String fullname) {
        try {
            //first check if username has already been taken
            if (!checkUser(username)) {
                //Connect to the database
                Connection connection = DriverManager.getConnection(
                        CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD
                );

                //create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USER_TABLE_NAME +
                                " (username, password,  fullname) VALUES (?, ?, ?)"
                );

                //Insert parameters in the insert query
                insertUser.setString(1, username);
                insertUser.setString(2, password);
                insertUser.setString(3, fullname);

                //Execute the insert query
                int affectedRows = insertUser.executeUpdate();

                //If the insert query affects rows, it means the registration was successful
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //check if the user exists in the database
    //true if user exists, false otherwise
    public static boolean checkUser(String username) {
        try {
            Connection connection = DriverManager.getConnection(
                    CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD
            );
            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USER_TABLE_NAME +
                            " WHERE USERNAME = ?"
            );

            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();
            //check to see if the result set is empty
            //if it is empty it means that there was no data row that contains the username
            // (i.e. username does not exist)
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    //validate login credentials by checking to see if username/password pair exists in the database
    //true if login is successful, false otherwise
    public static boolean validateLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(
                    CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD
            );

            //Create select query
            PreparedStatement validateUser = connection.prepareStatement(
                "SELECT * FROM " + CommonConstants.DB_USER_TABLE_NAME +
                        " WHERE username = ? AND password = ?"
            );

            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            //check to see if the result set is empty
            //if it is empty it means that there was no data row that contains the username/password pair
            // (i.e. login is unsuccessful)
            if (!resultSet.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
