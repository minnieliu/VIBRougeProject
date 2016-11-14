package VIBClass;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;


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
    public boolean removeEmployee(int employeeID) {

        if (this.isEmployee(employeeID)) {
            String deleteQuery = "DELETE FROM employee WHERE employeeID = '" + employeeID + "'";
            oraManager.execute(deleteQuery);
            return true;
        }
        else{
            return false;
        }

    }

    //return the list of name of customer who has birthday on the current date
    public JTable birthdayGift(int currentMonth) {

        ResultSet rs;
        String query = "SELECT name FROM member1 WHERE birthday LIKE " + "'%-"+currentMonth+"-%'" + " OR birthday LIKE " + "'%-0" + currentMonth + "-%'";
        rs = oraManager.query(query);
        ResultSetMetaData md = null;
        Vector columnNames = new Vector();
        Vector data = new Vector();
        try {
            md = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            int columns = md.getColumnCount();
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            //  Get row data
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }

                data.addElement(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(data,columnNames);
        return table;
    }

    // select all tuples with inventory less than or equal to low
    public JTable lowStockReport(int low) {
        Object obj = new Object();
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE inventoryNumber <= " + low;
        rs = oraManager.query(query);
        ResultSetMetaData md = null;
        Vector columnNames = new Vector();
        Vector data = new Vector();
        try {
            md = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            int columns = md.getColumnCount();
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            //  Get row data
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }

                data.addElement(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(data,columnNames);
        return table;
    }

    // select all tuples in product that has a price higher than the given price
    public JTable higherPriceReport(int givenprice) {
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE price >= " + givenprice;
        rs = oraManager.query(query);
        ResultSetMetaData md = null;
        Vector data = new Vector();
        Vector columnNames = new Vector();
        try {
            md = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            int columns = md.getColumnCount();
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            //  Get row data
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }

                data.addElement(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(data,columnNames);
        return table;
    }

    // select all tuples in product that has a price higher than the given price
    public JTable lowerPriceReport(int givenprice) {
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE price <= " + givenprice;
        rs = oraManager.query(query);
        ResultSetMetaData md = null;
        Vector data = new Vector();
        Vector columnNames = new Vector();
        try {
            md = rs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();}
        try {
            int columns = md.getColumnCount();
            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }
            //  Get row data
            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable table = new JTable(data,columnNames);
        return table;
    }

    // select the matching product
    // then add the numberAdded to current inventory
    public boolean addInventory(int productID, int numAdded) {

        String selectQuery = "SELECT * FROM product WHERE productID = " + productID;
        ResultSet rs = oraManager.query(selectQuery);
        int count = 0;
        try {
            while (rs.next()){
                count++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count < 1){
            return false;
        }

        String updateQuery = "UPDATE product SET " +
                "inventoryNumber = inventoryNumber + " + numAdded +
                " WHERE productID = " + productID;

        try{
            oraManager.execute(updateQuery);
            return true;
        }
        catch(Exception e){
            return false;
        }
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


    public boolean removeCustomer(String number) {
        String selectquery = "SELECT * FROM customer WHERE phoneNumber = " + number;
        ResultSet rs = oraManager.query(selectquery);
        int count = 0;
        try {
            while (rs.next()) {
                count++;
                System.out.println("count is: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count == 0) {
            return false;
        }
        String deleteQuery = "DELETE FROM customer WHERE phoneNumber =" + number;
        oraManager.execute(deleteQuery);
        System.out.println("finished deleting customer");
        return true;
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
//        ArrayList<String> list = employee.birthdayGift(02);
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
//       employee.addInventory(000000,30);
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
////
//        employee.removeCustomer("6042019382");
//////
////        // Test on Delete Cascade for removeCustomer
//        System.out.println("Customer Table" + "\n");
//        String query = "SELECT * FROM customer";
//        ResultSet rs=null;
//        rs = oramanager.query(query);
//        try {
//            while(rs.next())
//            {
//                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)+ "\n");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Member Table" + "\n");
//        String query1 = "SELECT * FROM member1";
//        rs = oramanager.query(query1);
//        try {
//            while(rs.next())
//            {
//                System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " +rs.getString(7) + " "  + rs.getString(8)+ "\n");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("purchaseOrder Table" + "\n");
//    String query2 = "SELECT * FROM purchaseOrder";
//    rs = oramanager.query(query2);
//    try {
//        while(rs.next())
//        {
//            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ "\n");
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//
}

}
