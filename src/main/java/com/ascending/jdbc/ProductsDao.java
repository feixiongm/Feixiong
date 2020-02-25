//package com.ascending.jdbc;
//
//import com.ascending.model.Products;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductsDao {
//    static final String DB_URL = "jdbc:postgresql://localhost:5431/Feixiong";
//    static final String USER = "admin";
//    static final String PASS= "password";
//    public List<Products> products(){
//        List<Products> Products = new ArrayList();
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
//                String product_name = rs.getString("name");
//                String description = rs.getString("description");
//                double price = rs.getDouble("price");
//                double weight = rs.getDouble("weight");
//                int year = rs.getInt("year");
//                int location_id = rs.getInt("location_id");
//
//                //Fill the object
//                com.ascending.model.Products Product = new Products();
//                Product.setId(id);
//                Product.setName(product_name);
//                Product.setDescription(description);
//                Product.setPrice(price);
//                Product.setWeight(weight);
//                Product.setYear(year);
//                Product.setLocation_id(location_id);
//                Products.add(Product);
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
//        return Products;
//    }
//}
