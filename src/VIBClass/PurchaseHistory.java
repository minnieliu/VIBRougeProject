package VIBClass;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;


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
    public int purchaseProduct (int productID, int quantity,String CPhoneNum,String CName, String methodOfPayment, String date) throws Exception {
        int currentInv = product.checkInventory(productID);
        if (currentInv > 0 && productID !=0 && quantity >0 && CPhoneNum !=null && CName !=null && methodOfPayment != null && date!=null) {
            // Update the inventory
            if(date.indexOf("-") == 4 && date.lastIndexOf("-") == 7) {
                product.updateInventory(productID, -quantity);
                //Update the purchaseHistory with purchaseID
//
//            if (checkpurchaseID(purchaseID)==true){
//                this.additem(purchaseID,productID,quantity);
//            }

                Random rand = new Random();
                int purchaseID = rand.nextInt(99999999);
                this.additem(purchaseID, productID, quantity);
                this.createPurchaseHistory(purchaseID, CPhoneNum, CName, methodOfPayment, date);
                this.additem(purchaseID, productID, quantity);

                //Check whether the customer is a member
                if (customer.isMember(CName, CPhoneNum)) {
                    int price = product.checkPrice(productID);
                    int point = price * quantity;
                    sephoraMember.updatePoint(CName, CPhoneNum, point);
                }
                return purchaseID;
            }
            else {
                Exception e= new Exception("Date must be YYYY-MM-DD");
                throw e;
            }
        }
        else{
            Exception e= new Exception("You cannot finish this purchase");
            throw e;
        }
    }

    public boolean checkpurchaseID(int enteredpurchaseID){
        String query = "SELECT purchaseID from purchaseOrder";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int purchaseID = rs.getInt("purchaseID");
                if (purchaseID==enteredpurchaseID){
                    return true;
                }
            }
            return false;
        }
        catch(SQLException e){
            System.out.println("caught sql exception");
            return false;
        }
    }

    public boolean returnProduct (int productID,int purchaseID) throws Exception{

        //check the purchaseID
        if(this.checkHistory(purchaseID))
        {
            //update the inventory number
            product.updateInventory(productID, 1);

            //should deduct point for customer
            String selectQuery = "SELECT phoneNumber,name FROM purchaseOrder WHERE purchaseID = " + purchaseID;
            ResultSet rs = oraManager.query(selectQuery);
            String name = "";
            String phoneNumber = "";
            try {
                rs.first();
                name = rs.getString("name");
                phoneNumber = rs.getString("phoneNumber");
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(customer.isMember(name,phoneNumber)){
                int price = product.checkPrice(productID);
                sephoraMember.updatePoint(name, phoneNumber, -price);
            }
            //delete product
            this.deleteprod(purchaseID, productID, 1);
            return true;
        }else{
            Exception e= new Exception("There is no record for this purchase");
            throw e;
        }
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

    public boolean checkproductitem(int purchaseID, int productID){
        String query = "SELECT purchaseID, productID from productOrder";
        ResultSet rs = oraManager.query(query);
        try{
            while (rs.next()) {
                int getpurchaseID = rs.getInt("purchaseID");
                int getproductID = rs.getInt("productID");
                if (purchaseID==getpurchaseID&&productID==getproductID){
                    System.out.println("product in purchase history");
                    return true;
                }
            }
            return false;
        }
        catch(SQLException e){
            System.out.println("caught sql exception");
            return false;
        }
    }
    public boolean additem(int purchaseID, int productID, int quantityPurchased){
        try{
            if (checkproductitem(purchaseID,productID)==true){
                String updateprodquantity = "UPDATE productOrder SET quantitypurchased=quantitypurchased+"+quantityPurchased +
                        " WHERE purchaseID ="+purchaseID+" AND productID="+productID;
                oraManager.execute(updateprodquantity);
                System.out.println("added successfully");
                return true;
            }
            else{String insertprod = "INSERT INTO productOrder VALUES ("
                    + purchaseID + ","
                    + productID +","
                    + quantityPurchased+")";
                oraManager.execute(insertprod);
                System.out.println("added successfully");
                return true;}
        }
        catch (Exception e){
            System.out.println("found error: " + e);
            return false;
        }
    }

    public JTable checkMemberPurchaseHistory(int accountno) throws Exception{
        if(accountno<=0){
            Exception e= new Exception("You are currently not a member");
            throw e;
        }
        ResultSet rs = oraManager.query("SELECT purchaseDate, purchaseOrder.purchaseID, productID, methodOfPayment, quantityPurchased " +
                "FROM purchaseOrder, productOrder, member1 " +
                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID AND purchaseOrder.name=member1.name AND purchaseOrder.phoneNumber=member1.phoneNumber AND" +
                " member1.accountNo="+accountno);
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
                System.out.println(md.getColumnName(i));
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

//
//    public void checkMemberPurchaseHistory(int accountno) {
//
//        ResultSet rs = oraManager.query("SELECT * " +
//                "FROM purchaseOrder, productOrder, member1 " +
//                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID AND purchaseOrder.name=member1.name AND purchaseOrder.phoneNumber=member1.phoneNumber AND" +
//                " member1.accountNo="+accountno);
//        try{
//            while (rs.next()) {
//                int productID = rs.getInt("productID");
//                int quantityPurchased = rs.getInt("quantityPurchased");
//                System.out.println("accountno: "+ accountno + "productID: "+ productID + "quantitypurchased: "+quantityPurchased+"\n");
//            }
//            rs.close();}
//        catch (Exception e){
//            System.out.println("found error: " + e);
//        }
//    }



    public boolean checkHistory(int purchaseID) {

        ResultSet rs = oraManager.query("SELECT * " +
                "FROM purchaseOrder, productOrder " +
                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID AND" +
                " purchaseOrder.purchaseID="+purchaseID);
        Boolean result = true;

        try{
            //edited by Hailey
            while (rs.next()) {
                int productID = rs.getInt("productID");
                int quantityPurchased = rs.getInt("quantityPurchased");
                System.out.println("purchaseID: "+ purchaseID + "productID: "+ productID + "quantityPurchased "+quantityPurchased+"\n");
            }
            rs.close();
        }
        catch (Exception e){
            System.out.println("found error: " + e);
            result = false;
        }


        return result;
    }
    //average item purchased per transaction per customer
    public JTable averageitemspercustomer(){
        oraManager.query("CREATE VIEW purchasecount AS " +
                "SELECT SUM(productOrder.quantityPurchased) AS count, purchaseOrder.name, purchaseOrder.phoneNumber, purchaseOrder.purchaseID FROM purchaseOrder, productOrder " +
                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID " +
                "GROUP BY purchaseOrder.name, purchaseOrder.phoneNumber, purchaseOrder.purchaseID");
        ResultSet rs = oraManager.query("SELECT AVG(count) AS avgcount, name FROM purchasecount GROUP BY name, phoneNumber");
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

    public boolean deleteEntirePurchase(int purchaseID){
        try{
            oraManager.execute("DELETE FROM purchaseOrder " +
                    "WHERE purchaseID = " + purchaseID);
            oraManager.execute("DELETE FROM productOrder " +
                    "WHERE purchaseID = "+ purchaseID );
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean deleteprod(int purchaseID, int productID,int quantity){
        try{
            oraManager.execute("UPDATE productOrder " +
                    "SET quantityPurchased = quantityPurchased-" + quantity +
                    "WHERE purchaseID = " + purchaseID +
                    " AND productID = " + productID);
            System.out.println("added successfully");
            oraManager.execute("DELETE productOrder " +
                    "WHERE purchaseID = " + purchaseID
                    +" AND productID = " + productID+ " AND quantityPurchased <=0");
            return true;

        }
        catch (Exception e){
            System.out.println("found error: " + e);
            return false;
        }
    }




    public static void main(String argv[]) {
        PurchaseHistory ps = new PurchaseHistory();
        // ps.purchaseProduct(5555, 55543215, 2, "7782341039", "Sarah Kwong","credit", "2016-07-24");
        ps.checkHistory(55543215);
        //       ps.returnProduct(5555, 55543215);
        ps.checkHistory(55543215);

//        ps.createPurchaseHistory(55543215,"7782341039", "Sarah Kwong", "Visa","2016-07-24");
//        System.out.println("adding first item");
//        ps.additem(55543215, 5555, 2);
//        System.out.println("adding second item");
//
//        ps.additem(55543215, 6969, 1);
//        System.out.println("checking history");
//
//        ps.checkHistory(55543215);
//        System.out.println("deleting product");
//
//        ps.deleteprod(55543215, 5555, 1);
//        System.out.println("checking history again");
//
//
//        ps.checkHistory(55543215);
//        ps.checkMemberPurchaseHistory(42590000);
//        System.out.println("deletingentire purchase");
//        ps.deleteEntirePurchase(55543215);
//
//        ps.createPurchaseHistory(123,"7782341039","Sarah Kwong", "credit","2016-07-07");
//        ps.additem(123, 6969, 1);
//
//        ps.averageitemspercustomer();


    }


    }