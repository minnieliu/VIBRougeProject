package VIBClass;



import java.sql.*;
import java.util.Random;

/**
 * Created by minnieliu on 2016-11-01.
 */
public class Customer {

    OraManager oraManager;

    public Customer() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public void addCustomer(String name, String phoneNumber, String gender) {

        String insertQuery = "INSERT INTO customer VALUES ('"
                + name + "','"
                + phoneNumber + "','"
                + gender + "')";

        //System.out.println(insertQuery);
        oraManager.execute(insertQuery);
        // oraManager.disconnect();
    }


    public boolean isMember(String name, String phoneNumber){
        ResultSet rs = null;
        String selectQuery = "SELECT * FROM member1 WHERE name = '" + name +
                "' AND phoneNumber = '" + phoneNumber + "'";

        //System.out.println(selectQuery);
        rs = oraManager.query(selectQuery);
        Boolean result = null;

        try {
            result = rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // System.out.print(result);
        return result;
    }

    public void addMember(String email, String password, String birthday, String name, String phoneNum){
        // changed birthday from string to date
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
                + name + "','"
                + phoneNum
                + "')";
        oraManager.execute(insertQuery);
        oraManager.execute(insertQuery);
        //oraManager.disconnect();
    }

    public void deleteMember(int accountNo, String password){
        String selectQuery = "SELECT * FROM member1 WHERE accountNo = " + accountNo + " AND " + "password = '" +password + "'";
        ResultSet rs = null;
        rs = oraManager.query(selectQuery);
        Boolean result = null;
        try {
            result = rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (result){
            String deleteQuery = "DELETE FROM member1 WHERE accountNo = " + accountNo;
            oraManager.execute(deleteQuery);
        }
    }



    public static void main(String[] args) {
        Customer c = new Customer();
        OraManager oramanager =  new OraManager();

        // Test addCustomer
//        c.addCustomer("Minnie Liu", "6041234567", "F");
//        c.addCustomer("It Works", "123456", "M");

        // Test isMember
//        System.out.println("Should be false: " + c.isMember("Sarah Up", "2847"));
//        System.out.println("Should be true for contain Jessica Peters? : " + c.isMember("Jessica Peters", "6042958190"));

        // Test addMember
//        System.out.println("Should be false: " + c.isMember("Minnie Liu", "6041234567"));
        //c.addMember("minnieliu96@hotmail.com", "hello", "10/27/1996", "Minnie Liu", "6041234567");
//        System.out.println("Should be true after add Minnie: " + c.isMember("Minnie Liu", "6041234567"));


        // Test deleteMember
       // c.deleteMember(42590000, "eciwhe1");

        String query = "SELECT * FROM member1";
        ResultSet rs=null;
        rs = oramanager.query(query);
        try {
            while(rs.next())
            {
                System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " +rs.getString(7) + " "  + rs.getString(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}