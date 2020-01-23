package com.ascending.jdbc;

import com.ascending.model.location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class locationDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/training_db";
    static final String USER = "admin";
    static final String PASS= "Training123!";
    public List<location> location(){
        List<location> locations = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM location";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                int seller_id = rs.getInt("seller_id");

                //Fill the object
                location Location = new location();
                Location.setId(id);
                Location.setName(name);
                Location.setPhone_number(phone_number);
                Location.setEmail(email);
                locations.add(Location);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return locations;
    }
}
