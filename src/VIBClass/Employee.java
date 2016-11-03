package VIBClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ivylu on 2016-11-01.
 */
public class Employee {

    int employeeID;
    String name;
    private ArrayList<Employee> employeelist;
    OraManager oraManager;

    // Constructor
    public Employee() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public boolean isEmployee(int employeeID) {
        String query = "SELECT * FROM employee WHERE employeeID = " + employeeID;
        ResultSet rs = oraManager.query(query);
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

    }

    //return the list of name of customer who has birthday this month
    public List<String> birthdayGift(String currentMonth) {
        return null;
    }

    // select all tuples with inventory less than low
    public List<String> lowStockReport(int low) {
        return null;
    }

    // select the matching product
    // then add the numberAdded to current inventory
    public void addInventory(int productID, int numAdded) {

    }

    // let employee add in new member (Change customer to member)
    public void addNewMember(int accountNum) {

    }


    public static void main(String argv[]) {
        Employee employee = new Employee();

         //Test add employee
         employee.addEmployee(12455, "'IT WORKS'");

        // Test isEmployee
        System.out.println(employee.isEmployee(12455));
        System.out.println(employee.isEmployee(1298374));


    }
}