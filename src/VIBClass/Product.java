package VIBClass;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hailey on 16/11/5.
 */
public class Product {
    OraManager oraManager;

    public Product() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public void addProduct(int productID,float price,String brand,int inventoryNumber,String productType) {
        oraManager.buildConnection();
        String insertQuery = "INSERT INTO product VALUES ('"
                + productID + "',"
                + price + "',"
                + brand + "',"
                + inventoryNumber +"',"
                + productType + ")";
        System.out.println(insertQuery);
        oraManager.execute(insertQuery);
    }

    public void checkProductbyID(int productID){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE productID = " + productID;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
    }

    public void checkProductbyBrand(String brand){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE brand = " + brand;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
    }

    public void checkProductbyType(String productType){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM product WHERE productType = " + productType;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
    }

    public int checkInventory(int productID){
        oraManager.buildConnection();
        String selectQuery = "SELECT inventoryNumber FROM product WHERE productID = " + productID;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        int result = 0;
        try {
            result = rs.getInt(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateInventory(int productID, int change){
        oraManager.buildConnection();
        int currentInv = this.checkInventory(productID);
        int newInv = currentInv + change;
        String updateQuery = "UPDATE service SET serviceCapacity ="+ newInv+ "WHERE serviceID = " + productID;
        System.out.println(updateQuery);
        ResultSet rs = oraManager.query(updateQuery);
    }

    public void purchaseProduct (int productID,int purchaseID){
        oraManager.buildConnection();
        int currentInv = this.checkInventory(productID);
        if(currentInv >0) {
            this.updateInventory(productID, -1);
            //TODO: Update the purchaseHistory with purchaseID
        }
    }

    public void returnProduct (int productID,int purchaseID){
        oraManager.buildConnection();
        //TODO: STEP1 check the purchaseID
        this.updateInventory(productID, 1);
        //TODO: STEP2 Update the purchaseHistory with purchaseID, purchaseHistory should deduct point for customer
    }

    public void popularProduct(){
        String query = "SELECT productID, FROM product, WHERE NOT EXISTS" +
                "(SELECT *, FROM customer, WHERE NOT EXISTS" +
                "(SELECT name, phoneNumber, FROM purchaseOrder, productOrder, WHERE" +
                "product.productID = productOrder.productID AND purchaseOrder.name = customer.name " +
                "AND purchaseOrder.phoneNumber = customer.phoneNumber AND purchaseOrder.purchaseID = productOrder.purchaseID));";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int productID = rs.getInt("productID");
                System.out.println("popular products: "+ productID + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }
    }

    public void mostExpensive(){
        String query = "SELECT productID, FROM product, WHERE price IN (SELECT MAX(price), FROM product);";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int productID = rs.getInt("productID");
                System.out.println("most expensive: "+ productID + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }
    }

    public void leastExpensive(){
        String query = "SELECT productID, FROM product, WHERE price IN (SELECT MIN(price), FROM product);";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int productID = rs.getInt("productID");
                System.out.println("least expensive: "+ productID + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }
    }

    public void avgpricepertype(){
        String query = "SELECT AVG(price) AS avg, productType, FROM product, GROUPBY productType;";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int producttype = rs.getInt("productType");
                int avg = rs.getInt("avg");

                System.out.println("type: "+ producttype + "average price: " + avg + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }
    }



    public static void main(String[] args) {
        Product p = new Product();
        int result = p.checkInventory(6969);
        System.out.print("Before changing inventory: " + result);
        p.updateInventory(6969, 5);
        result = p.checkInventory(6969);
        System.out.print("After changing inventory: " + result);
    }

}
