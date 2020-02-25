//package com.ascending.jdbc;
//
//import com.ascending.model.Seller;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SellerDao {
//    static final String DB_URL = "jdbc:postgresql://localhost:5431/Feixiong";
//    static final String USER = "admin";
//    static final String PASS= "password";
//    public List<Seller> products(){
//        List<Seller> sellers = new ArrayList();
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            //STEP 2: Open a connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            //STEP 3: Execute a query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM products";
//            rs = stmt.executeQuery(sql);
//            //STEP 4: Extract data from result set
//            while(rs.next()) {
//                //Retrieve by column name
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                double phone_number = rs.getDouble("phone_number");
//                String description = rs.getString("description");
//                String location = rs.getString("location");
//
//                //Fill the object
//                Seller Seller = new Seller();
//                Seller.setId(id);
//                Seller.setName(name);
//                Seller.setEmail(email);
//                Seller.setEmail(email);
//                Seller.setPhone_number(phone_number);
//                Seller.setDescription(description);
//                Seller.setLocation(location);
//                sellers.add(Seller);
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            //STEP 6: finally block used to close resources
//            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
//                if(conn != null) conn.close();
//            }
//            catch(SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return sellers;
//    }
//}
