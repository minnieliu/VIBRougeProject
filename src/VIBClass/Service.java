package VIBClass;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hailey on 16/11/5.
 */
public class Service {
    OraManager oraManager;

    /*
    new service table
    create table service
        (
          serviceID   INTEGER  NOT NULL,
          serviceDate CHAR(20) NOT NULL,
          serviceCapacity INTEGER NOT NULL,
          serviceName CHAR(50) NOT NULL
          PRIMARY KEY (serviceID)
        )
     */
    public Service() {
        oraManager = new OraManager();
        oraManager.buildConnection();
    }

    public void addService(int serviceID, String date) {
        oraManager.buildConnection();
        String insertQuery = "INSERT INTO service VALUES ('"
                + serviceID + "',"
                + date + ")";

        System.out.println(insertQuery);
        oraManager.execute(insertQuery);
    }

    public boolean checkService(int serviceID){
        oraManager.buildConnection();
        String selectQuery = "SELECT * FROM service WHERE serviceID = " + serviceID;
        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        Boolean result = null;
        try {
            result = rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int checkCapacity(int serviceID){
        oraManager.buildConnection();
        String selectQuery = "SELECT serviceCapacity FROM service WHERE serviceID = " + serviceID;
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

    public void updateCapacity(int serviceID){
        oraManager.buildConnection();
        int currentCapacity = this.checkCapacity(serviceID);
        int newCapacity = currentCapacity-1;
        String updateQuery = "UPDATE service SET serviceCapacity ="+ newCapacity+ "WHERE serviceID = " + serviceID;
        System.out.println(updateQuery);
        ResultSet rs = oraManager.query(updateQuery);
    }

    public String checkServiceForThisMonth(String month){
        oraManager.buildConnection();
        String updateQuery = "SELECT serviceName FROM service WHERE serviceDate LIKE " + "%-"+month+"-%";
        System.out.println(updateQuery);
        ResultSet rs = oraManager.query(updateQuery);
        String result = "";
        try {
            result = rs.getString("serviceName");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        Service s = new Service();
        boolean result = s.checkService(123456);
        System.out.print("Before adding: " + result);
        s.addService(123456,"2016-2-15");
        result = s.checkService(123456);
        System.out.print("After adding: " + result);
    }
}
