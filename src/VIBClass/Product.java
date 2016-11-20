package VIBClass;

import javax.swing.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by hailey on 16/11/5.
 */
public class Product {
    OraManager oraManager;

    public Product() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public void addProduct(int productID,double price,String brand,int inventoryNumber,String productType) {
        oraManager.buildConnection();
        String insertQuery = "INSERT INTO product VALUES ("
                + productID + ","
                + price + ",'"
                + brand + "',"
                + inventoryNumber +",'"
                + productType + "')";
        System.out.println(insertQuery);
        oraManager.execute(insertQuery);
    }

    public JTable checkProductbyIDForUI (int productID) throws Exception{
        ResultSet rs = null;
        if(productID <=0 || !checkProductbyID(productID)){
            Exception e= new Exception("It is not a valid product ID");
            throw e;
        }
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE productID = " + productID;
        rs = oraManager.query(selectQuery);
        ResultSetMetaData md = null;
        ArrayList<String> result = new ArrayList<String>();
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


    public boolean checkProductbyID(int productID){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE productID = " + productID;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        Boolean result = null;
        try {
            result = rs.isBeforeFirst();
            if(result) {
                System.out.println("The selected product by ID " + productID + " is/are");
                while (rs.next()) {
                    System.out.println(rs.getInt("productID") + " " + rs.getDouble("price") + " " + rs.getString("brand") + " " + rs.getInt("inventoryNumber") + " " + rs.getString("productType"));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public JTable checkProductbyBrandforUI (String brand) throws Exception{
        ResultSet rs = null;
        if(brand ==null || !checkProductbyBrand(brand)){
            Exception e= new Exception("It is not a valid brand ID");
            throw e;
        }
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE brand = '" + brand +"'";
        rs = oraManager.query(selectQuery);
        ResultSetMetaData md = null;
        ArrayList<String> result = new ArrayList<String>();
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

    public boolean checkProductbyBrand(String brand){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE brand = '" + brand +"'";
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        Boolean result = null;
        try {
            result = rs.isBeforeFirst();
            if(result) {
                System.out.println("The selected product by brand " + brand + " is/are");
                while (rs.next()) {
                    System.out.println(rs.getInt("productID") + " " + rs.getDouble("price") + " " + rs.getString("brand") + " " + rs.getInt("inventoryNumber") + " " + rs.getString("productType"));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JTable checkProductbyTypeforUI (String productType) throws Exception{
        ResultSet rs = null;
        if(productType ==null ||!checkProductbyType(productType)){
            Exception e= new Exception("It is not a valid type");
            throw e;
        }
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE productType = '" + productType+"'";;
        rs = oraManager.query(selectQuery);
        ResultSetMetaData md = null;
        ArrayList<String> result = new ArrayList<String>();
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

    public boolean checkProductbyType(String productType){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE productType = '" + productType+"'";
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        Boolean result = null;
        try {
            result = rs.isBeforeFirst();
            if(result) {
                System.out.println("The selected product by productType " + productType + " is/are");
                while (rs.next()) {
                    System.out.println(rs.getInt("productID") + " " + rs.getDouble("price") + " " + rs.getString("brand") + " " + rs.getInt("inventoryNumber") + " " + rs.getString("productType"));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public int checkInventory(int productID){
        oraManager.buildConnection();
        String selectQuery = "SELECT inventoryNumber FROM product WHERE productID = " + productID;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        int result = 0;
        try {
            rs.first();
            result = rs.getInt("inventoryNumber");
            System.out.println("Inventory Number for productID "+ productID+ " is "+ result);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int checkPrice(int productID){
        oraManager.buildConnection();
        String selectQuery = "SELECT price FROM product WHERE productID = " + productID;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        int result = 0;
        try {
            rs.first();
            result = rs.getInt("price");
            System.out.println("price for productID "+ productID+ " is "+ result);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateInventory(int productID, int change) throws SQLException{
        oraManager.buildConnection();
        int currentInv = this.checkInventory(productID);
        int newInv = currentInv + change;
        String updateQuery = "UPDATE product SET inventoryNumber ="+ newInv+ "WHERE productID = " + productID;
        System.out.println(updateQuery);

        ResultSet rs = oraManager.query(updateQuery);
        if (rs == null){
            throw new SQLException("error");
        }

        int result = this.checkInventory(productID);
        System.out.println("After changing "+ change+", Inventory Number for productID "+ productID+ " is "+ result);
        return result;
    }

    public boolean removeProduct(int productID) {
        oraManager.buildConnection();
        if(checkProductbyID(productID)) {
            String deleteQuery = "DELETE FROM product WHERE productID = " + productID;
            oraManager.execute(deleteQuery);
            System.out.println("delete the productID" +productID);
            return true;
        }
        else{
            System.out.println("there is no such productID");
            return false;
        }
    }

    public JTable popularProduct(){
        ResultSet rs = null;
        String query = "SELECT * FROM product WHERE NOT EXISTS" +
                "(SELECT * FROM customer WHERE NOT EXISTS" +
                "(SELECT name, phoneNumber FROM purchaseOrder, productOrder WHERE" +
                " product.productID = productOrder.productID AND purchaseOrder.name = customer.name " +
                "AND purchaseOrder.phoneNumber = customer.phoneNumber AND purchaseOrder.purchaseID = productOrder.purchaseID))";
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


//        try{
//            while (rs.next()) {
//                int productID = rs.getInt("productID");
//                System.out.println("popular products: "+ productID + "\n");
//            }
//            rs.close();}
//        catch (Exception e){
//            System.out.println("found error: " + e);
//        }
    }

    public ArrayList<String> mostExpensive(){
        ArrayList<String> result=new ArrayList<String>();
        String query = "SELECT * FROM product WHERE price IN (SELECT MAX(price) FROM product)";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int productID = rs.getInt("productID");
                result.add(String.valueOf(productID));
                double price= rs.getDouble("price");
                result.add(String.valueOf(price));
                String brand=rs.getString("brand");
                result.add(brand);
                int inventory= rs.getInt("inventoryNumber");
                result.add(String.valueOf(inventory));
                String type=rs.getString("productType");
                result.add(type);
                System.out.println("most expensive: "+ productID + "\n");
            }
            rs.close();
        }
        catch (Exception e){
            System.out.println("found error: " + e);
        }
        return result;
    }

    public ArrayList<String> leastExpensive(){
        ArrayList<String> result=new ArrayList<String>();
        String query = "SELECT * FROM product WHERE price IN (SELECT MIN(price) FROM product)";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int productID = rs.getInt("productID");
                result.add(String.valueOf(productID));
                double price= rs.getDouble("price");
                result.add(String.valueOf(price));
                String brand=rs.getString("brand");
                result.add(brand);
                int inventory= rs.getInt("inventoryNumber");
                result.add(String.valueOf(inventory));
                String type=rs.getString("productType");
                result.add(type);
                System.out.println("least expensive: "+ productID + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }
        return result;
    }

    public JTable avgpricepertype(){
        String query = "SELECT AVG(price) AveragePrice, productType FROM product GROUP BY productType";
        ResultSet rs = oraManager.query(query);
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



    public static void main(String[] args) {
        Product p = new Product();
//        p.removeProduct(1001);
//        p.addProduct(1001, 10.99, "OPI", 100, "nail (red)");
//        p.checkProductbyID(1001);
//        p.checkProductbyBrand("OPI");
//        p.checkProductbyType("nail");
//        p.updateInventory(1001, 5);
//        p.addProduct(1002, 50.6, "Tom Ford", 2, "lipsticks (red)");
//        p.popularProduct();
//        p.mostExpensive();
//        p.leastExpensive();
//        p.avgpricepertype();

    }

}