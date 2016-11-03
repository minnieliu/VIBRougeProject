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

    public void addCustomer(String name, int phoneNumber, String gender) {
        oraManager.buildConnection();
        String insertQuery = "INSERT INTO customer VALUES ('"
                + name + "',"
                + phoneNumber + ",'"
                + gender + "')";

        System.out.println(insertQuery);
        oraManager.execute(insertQuery);
       // oraManager.disconnect();
    }


    public boolean isMember(String name, int phoneNumber){
        ResultSet rs = null;
        String selectQuery = "SELECT * FROM member1 WHERE name = '" + name +
                            "' AND phoneNumber = " + phoneNumber;

        rs = oraManager.query(selectQuery);
        Boolean result = null;

        try {
            result = rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print(result);
        return result;
    }

    public void addMember(String email, String password, String birthday, String name, int phoneNum){
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
                + name + "',"
                + phoneNum
                + ")";
     oraManager.execute(insertQuery);
        oraManager.disconnect();
    }

    public static void main(String[] args) {
        Customer c = new Customer();
        boolean result = c.isMember("Minnie Liu", 9849000);
        System.out.print("Before adding: "+ result);
       // c.addCustomer("Minnie Liu", 123, "F");
       // c.addMember("minnieliu96@hotmail.com", "hello", "10/27/1996", "Minnie Liu", 123);
        result = c.isMember("Minnie Liu", 123);
        System.out.print("After adding: "+ result);

      }
}



