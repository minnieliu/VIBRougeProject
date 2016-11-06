package VIBClass;

import java.sql.ResultSet;


public class PurchaseHistory {


    OraManager oraManager;

    // Constructor
    public PurchaseHistory() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public boolean createPurchaseHistory (String methodOfPayment, String date, int purchaseID, String CName, String CPhoneNum){
        try {
            String insertpur = "INSERT INTO purchaseOrder VALUES ("
                    + CName + ","
                    + CPhoneNum + ","
                    + methodOfPayment + "," +
                    +purchaseID +","
                    + date +")";


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
    public boolean additem(int purchaseID, int productID){
        try{
            String insertprod = "INSERT INTO productOrder VALUES ("
                    + productID + ","
                    + purchaseID + ")";
            oraManager.execute(insertprod);
            System.out.println("added successfully");
            return true;
        }
        catch (Exception e){
            System.out.println("found error: " + e);
            return false;
        }
    }


    public void checkHistory(int purchaseID) {

                ResultSet rs = oraManager.query("SELECT *," +
                "FROM purchaseOrder, productOrder," +
                "WHERE purchaseOrder.purchaseID = productOrder.purchaseID AND" +
                "purchaseID="+purchaseID+";");
       try{
        while (rs.next()) {
            int productID = rs.getInt("productID");
            System.out.println("purchaseID: "+ purchaseID + "productID: "+ productID + "\n");
        }
        rs.close();}
       catch (Exception e){
           System.out.println("found error: " + e);
       }


//return boolean
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
                    "WHERE purchaseID = "+ purchaseID+
                    "AND productID = "+productID+";");
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
        ps.createPurchaseHistory("Visa","1988-09-24" ,555, "clara","7782341039");
        ps.additem(181,555);
        ps.additem(182,555);
        ps.checkHistory(555);
        ps.deleteprod(181,555);
        ps.checkHistory(555);
        ps.deleteEntirePurchase(555);


    }
}
