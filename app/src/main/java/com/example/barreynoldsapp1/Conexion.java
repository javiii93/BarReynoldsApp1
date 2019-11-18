package com.example.barreynoldsapp1;

import java.sql.DriverManager;
import java.sql.Statement;

public interface Conexion {
    String url = "jdbc:mysql://192.68.40.44:3306/barreynolds?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user="root";
    String pass="";
}
