package VIBClass;

import java.sql.*;
import java.util.ArrayList;


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
    public ArrayList<String> birthdayGift(Date currentDate) {
        ArrayList<String> list = new ArrayList();
        ResultSet rs = null;
        String query = "SELECT name FROM customer WHERE birthday = " + currentDate;
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

    // select all tuples with inventory less than low
    public ArrayList<Product> lowStockReport(int low) {
        ArrayList<Product> list = new ArrayList();
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
                Product product  = new Product(productID, price, brand, inventoryNumber, productType);
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // select the matching product
    // then add the numberAdded to current inventory
    public void addInventory(int productID, int numAdded) {
        String updateQuery = "UPDATE inventorynumber SET " +
                "inventoryNumber = inventoryNumber + " + numAdded +
                "WHERE productID = " + productID;
        oraManager.execute(updateQuery);
    }

    // let employee add in new member (Change customer to member)
    public void addNewMember(int accountNum) {

    }


    public static void main(String argv[]) {
        Employee employee = new Employee();

        // Test add employee
        // employee.addEmployee(9348534, "'IT DOES WORKS'");

        // Test isEmployee
        System.out.println("Should be true: " + employee.isEmployee(9348534));
        System.out.println("Should be false: " + employee.isEmployee(1298374));
        System.out.println("Should be true for in? : " + employee.isEmployee(36475));

        // Test removeEmployee
        System.out.println("Before remove is true: " + employee.isEmployee(12645));
        employee.removeEmployee(12645);
        System.out.println("After removing Adam Smith, is false: " + employee.isEmployee(12645));
    }
}
