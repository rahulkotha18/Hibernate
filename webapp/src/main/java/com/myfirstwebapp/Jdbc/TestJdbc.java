package com.myfirstwebapp.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String args[])
    {
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String user = "springstudent";
        String password = "springstudent";
        try
        {
            System.out.println("Connecting...");
            Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
            System.out.println(connection);
            System.out.println("Connected");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
