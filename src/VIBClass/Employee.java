package VIBClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;


/**
 * Created by ivylu on 2016-11-01.
 */
public class Employee {

    OraManager oraManager;

    // Constructor
    public Employee() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public boolean isEmployee(int employeeID) {
        ResultSet rs = null;
        String query = "SELECT * FROM employee WHERE employeeID = " + employeeID;
        rs = oraManager.query(query);
        Boolean result = null;
        try {
            result = rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public void addEmployee(int employeeID, String name) {
        String insertQuery = "INSERT INTO employee VALUES (" + employeeID + "," + name + ")";

        oraManager.execute(insertQuery);


    }

    //delete employee tuple that matches the employeeID
    public void removeEmployee(int employeeID) {
        String deleteQuery = "DELETE FROM employee WHERE employeeID = '"+employeeID+"'";

        oraManager.execute(deleteQuery);

    }

    //return the list of name of customer who has birthday on the current date
    public ArrayList<String> birthdayGift(int currentDate) {
        ArrayList<String> list = new ArrayList();
        ResultSet rs;
        String query = "SELECT name FROM member1 WHERE birthday LIKE " + "'%-"+currentDate+"-%'";
        rs = oraManager.query(query);

        try {
            while(rs.next()){
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // select all tuples with inventory less than or equal to low
    public ArrayList<String> lowStockReport(int low) {
        ArrayList<String> list = new ArrayList();
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE inventoryNumber <= " + low;
        rs = oraManager.query(query);

        try {
            while(rs.next()){
                Integer productID = rs.getInt("productID");
                Integer price = rs.getInt("price");
                String brand = rs.getString("brand");
                Integer inventoryNumber = rs.getInt("inventoryNumber");
                String productType = rs.getString("productType");
                list.add(productID + " " + price + " " + brand + " " + inventoryNumber + " " + productType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // select all tuples in product that has a price higher than the given price
    public ArrayList<String> higherPriceReport(int givenprice) {
        ArrayList<String> list = new ArrayList();
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE price >= " + givenprice;
        rs = oraManager.query(query);

        try {
            while(rs.next()){
                Integer productID = rs.getInt("productID");
                Integer price = rs.getInt("price");
                String brand = rs.getString("brand");
                Integer inventoryNumber = rs.getInt("inventoryNumber");
                String productType = rs.getString("productType");
                list.add(productID + " " + price + " " + brand + " " + inventoryNumber + " " + productType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // select all tuples in product that has a price higher than the given price
    public ArrayList<String> lowerPriceReport(int givenprice) {
        ArrayList<String> list = new ArrayList();
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE price <= " + givenprice;
        rs = oraManager.query(query);

        try {
            while(rs.next()){
                Integer productID = rs.getInt("productID");
                Integer price = rs.getInt("price");
                String brand = rs.getString("brand");
                Integer inventoryNumber = rs.getInt("inventoryNumber");
                String productType = rs.getString("productType");
                //Product product  = new Product(productID, price, brand, inventoryNumber, productType);
                // list.add(product);
                list.add(productID + " " + price + " " + brand + " " + inventoryNumber + " " + productType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // select the matching product
    // then add the numberAdded to current inventory
    public void addInventory(int productID, int numAdded) {
        String updateQuery = "UPDATE product SET " +
                "inventoryNumber = inventoryNumber + " + numAdded +
                " WHERE productID = " + productID;
        oraManager.execute(updateQuery);
    }

    public void addMember(String email, String password, String birthday, String name, String phoneNum){

        Random rand = new Random();
        int accountNum = rand.nextInt(99999999);
        int yearSpenttoDate = 0;
        int currentPoints = 0;

        String insertQuery = "INSERT INTO member1 VALUES ("
                + accountNum + ","
                + yearSpenttoDate + ",'"
                + email + "', '"
                + password + "', '"
                + birthday + "',"
                + currentPoints + ", '"
                + name + "',"
                + phoneNum
                + ")";
        oraManager.execute(insertQuery);
    }


    public static void main(String argv[]) {
        Employee employee = new Employee();
        OraManager oramanager =  new OraManager();
        // Test add employee
//        employee.addEmployee(9348534, "'IT DOES WORKS'");
//        employee.addEmployee(123456, "'ABC'");

        // Test isEmployee
//        System.out.println("Should be true: " + employee.isEmployee(9348534));
//        System.out.println("Should be false: " + employee.isEmployee(1298374));
//        System.out.println("Should be true for contain carter wong? : " + employee.isEmployee(36475));

        // Test removeEmployee
//        System.out.println("Before remove is true: " + employee.isEmployee(12645));
//        employee.removeEmployee(12645);
//        System.out.println("After removing Adam Smith, is false: " + employee.isEmployee(12645));
//        employee.removeEmployee(36475);

        // Test Birthday
//        ArrayList<String> list = employee.birthdayGift(10);
//        for(int i=0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }

        // Test Low Price Report
//        ArrayList<String> list = employee.lowStockReport(20);
//        for(int i=0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }

        // Test Higher Price Report
//        ArrayList<String> list = employee.higherPriceReport(30);
//        for(int i=0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }

        // Test Lower Price Report
//        ArrayList<String> list = employee.lowerPriceReport(50);
//        for(int i=0; i<list.size(); i++){
//            System.out.println(list.get(i));
//        }

        //Test AddInventory
//        employee.addInventory(6969,30);
//        String query = "SELECT * FROM product";
//        ResultSet rs=null;
//        rs = oramanager.query(query);
//        try {
//            while(rs.next())
//            {
//                System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString("brand") + " " + rs.getInt("inventoryNumber") + " "  + rs.getString("productType"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Test Addmember
//        employee.addMember("itworks@ubc.ca", "1234password", "01-02-2011", "Charles Roberts", "7781369280");
//        String query = "SELECT * FROM member1";
//        ResultSet rs=null;
//        rs = oramanager.query(query);
//        try {
//            while(rs.next())
//            {
//                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +  rs.getString(3)+ " " +  rs.getString(4)+ " " +  rs.getString(5));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
