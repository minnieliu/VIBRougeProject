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
    }

    public int addCustomer(String name, int phoneNumber, String gender) {
        String insertQuery = "INSERT INTO customer VALUES ("
                + name + ","
                + phoneNumber + ","
                + gender + ")";

        System.out.println(insertQuery);
        int rowCountInsertQuery = oraManager.execute(insertQuery);
        System.out.println(rowCountInsertQuery);
        return rowCountInsertQuery;

    }


    public boolean isMember(String name, int phoneNumber){
        ResultSet rs = null;
        String selectQuery = "SELECT * FROM member1 WHERE name = " + name +
                            " AND phoneNumber = " + phoneNumber + ")";

        rs = oraManager.query(selectQuery);
        if (rs == null){
            return false;
        }
        else{
            return true;
        }
    }

    public int addMember(String email, String password, Date birthday, String name, int phoneNum){
        // changed birthday from string to date
        Random rand = new Random();
        int accountNum = rand.nextInt(99999999);
        int yearSpenttoDate = 0;
        String insertQuery = "INSERT INTO member1 VALUES ("
                + accountNum + ","
                + yearSpenttoDate + ","
                + email + ","
                + password + ","
                + birthday + ","
                + name + ","
                + phoneNum
                + ")";
     int rowCountInsertQuery =oraManager.execute(insertQuery);
        return rowCountInsertQuery;
    }

    public static void main(String[] args) {
        Customer c = new Customer();
        c.isMember("Minnie", 9849000);
    }
}



