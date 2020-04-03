//package com.ascending.jdbc;
//
//import com.ascending.model.Location;
//import com.ascending.util.HibernateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LocationDao {
//    static final String DB_URL = "jdbc:postgresql://localhost:5431/Feixiong";
//    static final String USER = "admin";
//    static final String PASS = "password";
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    public int save(Location location) {
//        //List<Location> locations = new ArrayList<>();
//        Connection conn = null;
//        Statement stmt = null;
//        int rs= 0;
//
//        try {
//            //Step 2. Open a connection
//
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            //Step3. Execute a query
//            System.out.println("Inserting statement");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "insert into location (name, phone_number, email, address,seller_id)" +
//                    " values(" + location.getId() + ",'" + location.getName() + "','" + location.getPhone_number() + "','" + location.getEmail() + "','" + location.getAddress() + "'," + location.getSeller_id() + ")";
//            rs = stmt.executeUpdate(sql);
//
//        }catch (SQLException sqlEx){
//            logger.error("can't connect to database",sqlEx);
//        }
//        finally {
//            //STEP 4: finally block used to close resources
//            try {
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//
//            return rs;
//        }
//    }
//
//    public boolean delete(Long id) {
//        return false;
//    }
//
//    public List<Location> getLocations() {
//        List<Location> locations = new ArrayList();
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
//            sql = "SELECT * FROM location";
//            rs = stmt.executeQuery(sql);
//            //STEP 4: Extract data from result set
//            while (rs.next()) {
//                //Retrieve by column name
//                Long id = rs.getLong("id");
//                String name = rs.getString("name");
//                String phone_number = rs.getString("phone_number");
//                String email = rs.getString("email");
//                String address = rs.getString("address");
//                int seller_id = rs.getInt("seller_id");
//
//                //Fill the object
//                Location location = new Location();
//                location.setName(name);
//                location.setPhone_number(phone_number);
//                location.setEmail(email);
//                locations.add(location);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //STEP 6: finally block used to close resources
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        return locations;
//    }
//}
