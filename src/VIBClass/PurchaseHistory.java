package VIBClass;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PurchaseHistory {


    OraManager oraManager;
    Product product;
    Customer customer;
    SephoraMember sephoraMember;
    // Constructor
    public PurchaseHistory() {
        oraManager = new OraManager();
        product=new Product();
        customer=new Customer();
        sephoraMember=new SephoraMember();

        oraManager.buildConnection();
    }

    //edit by Hailey
    public void purchaseProduct (int productID,int purchaseID, int quantity,String CPhoneNum,String CName, String methodOfPayment, String date){

        int currentInv = product.checkInventory(productID);
        if(currentInv >0) {
            // Update the inventory
            product.updateInventory(productID, -quantity);
            //Update the purchaseHistory with purchaseID
            this.createPurchaseHistory(purchaseID,CPhoneNum, CName, methodOfPayment, date);
            //Check whether the customer is a member
            if(customer.isMember(CName,CPhoneNum)){
                int price= product.checkPrice(productID);
                int point= price * quantity;
                sephoraMember.updatePoint(CName, CPhoneNum, point);
            }
        }
    }


    public void returnProduct (int productID,int purchaseID){
        oraManager.buildConnection();

        //check the purchaseID
        if(this.checkHistory(purchaseID))
        {
            //update the inventory number
            product.updateInventory(productID, 1);

            //should deduct point for customer
            String selectQuery = "SELECT phoneNumber,name FROM purchaseOrder WHERE purchaseID = " + purchaseID;
            String secondSelectQuery = "SELECT quantityPurchased FROM productOrder WHERE purchaseID = " + purchaseID;
            ResultSet rs = oraManager.query(selectQuery);
            ResultSet newrs = oraManager.query(selectQuery);
            String name = "";
            String phoneNumber = "";
            int quantity=0;
            try {
                rs.first();
                newrs.first();
                name = rs.getString("name");
                phoneNumber = rs.getString("phoneNumber");
                quantity = newrs.getInt("quantityPurchased");
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(customer.isMember(name,phoneNumber)){
                int price = product.checkPrice(productID);
                sephoraMember.updatePoint(name, phoneNumber, -price*quantity);
            }
            //clean the whole purchase history
            this.deleteEntirePurchase(purchaseID);
        };
    }

    //edit the sequence of insert by Hailey to make it the same with sql
    public boolean createPurchaseHistory (int purchaseID, String CPhoneNum,String CName, String methodOfPayment, String date){
        try {
            String insertpur = "INSERT INTO purchaseOrder VALUES ("
                    + purchaseID + ",'"
                    + CPhoneNum + "','"
                    + CName + "','"
                    +methodOfPayment +"','"
                    + date +"')";


            System.out.println(insertpur);

            oraManager.execute(insertpur);
            System.out.println("added successfully");
            return true;
        }
        catch (Exception e){
            System.out.println("found error: "+e);
            return false;
        }

    }
    public boolean additem(int purchaseID, int productID, int quantityPurchased){
        try{
            String insertprod = "INSERT INTO productOrder VALUES ("
                    + productID + ","
                    + purchaseID +","
                    + quantityPurchased+")";
            oraManager.execute(insertprod);
            System.out.println("added successfully");
            return true;
        }
        catch (Exception e){
            System.out.println("found error: " + e);
            return false;
        }
    }
    public void checkMemberPurchaseHistory(int accountno) {

        ResultSet rs = oraManager.query("SELECT *," +
                "FROM purchaseOrder, productOrder, member1" +
                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID AND purchaseOrder.name=member1.name AND purchaseOrder.phoneNumber=member1.phoneNumber AND" +
                "accountNo="+accountno+";");
        try{
            while (rs.next()) {
                int productID = rs.getInt("productID");
                System.out.println("accountno: "+ accountno + "productID: "+ productID + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }


//return boolean
    }



    public boolean checkHistory(int purchaseID) {

        ResultSet rs = oraManager.query("SELECT *," +
                "FROM purchaseOrder, productOrder," +
                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID AND" +
                "purchaseID="+purchaseID+";");
        Boolean result = null;

        try{
            //edited by Hailey
            result = rs.isBeforeFirst();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                System.out.println("purchaseID: "+ purchaseID + "productID: "+ productID + "\n");
            }
            rs.close();
        }
        catch (Exception e){
            System.out.println("found error: " + e);
        }
        //return boolean
       try{
           //edited by Hailey
          result = rs.isBeforeFirst();
        while (rs.next()) {
            int productID = rs.getInt("productID");
            System.out.println("purchaseID: "+ purchaseID + "productID: "+ productID + "\n");
        }
        rs.close();
       }
       catch (Exception e){
           System.out.println("found error: " + e);
       }
       //return boolean

        return result;
    }
    //average item purchased per transaction per customer
    public void averageitemspercustomer(){
        oraManager.query("CREATE VIEW purchasecount (" +
                "SELECT COUNT(productID) AS count, name, phoneNumber, purchaseID, FROM purchaseOrder, productOrder, " +
                "WHERE purchaseOrder.purchaseID = productOrder.productID," +
                "GROUPBY name, phoneNumber, purchaseID);");
        ResultSet rs = oraManager.query("SELECT AVG(count) AS avgcount, name, FROM purchasecount, GROUPBY name, phoneNumber;");
        try{
            while (rs.next()) {
                int avgcount = rs.getInt("avgcount");
                String name = rs.getString("name");
                System.out.println("name:  "+ name + "avg count: "+ avgcount + "\n");
            }
            rs.close();}
        catch (Exception e){
            System.out.println("found error: " + e);
        }

    }

    public boolean deleteEntirePurchase(int purchaseID){
        try{
            oraManager.execute("DELETE FROM purchaseOrder," +
                    "WHERE purchaseID = "+ purchaseID +";");
            oraManager.execute("DELETE FROM productOrder," +
                    "WHERE purchaseID = "+ purchaseID +";");
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean deleteprod(int purchaseID, int productID){
        try{
            oraManager.execute("DELETE FROM prodOrder," +
                    "WHERE purchaseID = " + purchaseID +
                    "AND productID = " + productID + ";");
            System.out.println("added successfully");
            return true;

        }
        catch (Exception e){
            System.out.println("found error: " + e);
            return false;
        }
    }




    public static void main(String argv[]) {
        PurchaseHistory ps = new PurchaseHistory();
        //ps.createPurchaseHistory(123,"7789849871","Elaine Wong", "credit","2016-07-07");

        ps.additem(123,79823094,123);
        ps.additem(123,55726347,321);
        ps.checkHistory(123);
        ps.deleteprod(55726347,123);
        ps.checkHistory(123);
        ps.deleteEntirePurchase(123);


    }
}