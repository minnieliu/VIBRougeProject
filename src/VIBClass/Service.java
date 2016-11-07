package VIBClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.print.StreamPrintService;
import java.sql.*;
import java.util.*;
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

    public void addService(int serviceID, String date, int capacity, String name) {
        oraManager.buildConnection();
        String insertQuery = "INSERT INTO service VALUES ("
                + serviceID + ",'"
                + date +"',"
                + capacity +",'"
                + name+ "')";

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
            rs.first();
            System.out.println("Displaying record...");
            result=rs.getInt("serviceCapacity");
            System.out.print("serviceCapacity: " + result);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateCapacity(int serviceID, int change){
        oraManager.buildConnection();
        int currentCapacity = this.checkCapacity(serviceID);
        int newCapacity = currentCapacity + change;
        String updateQuery = "UPDATE service SET serviceCapacity ="+ newCapacity+ "WHERE serviceID = " + serviceID;
        System.out.println(updateQuery);
        ResultSet rs = oraManager.query(updateQuery);
    }

    public String checkServiceForThisMonth(String month){
        oraManager.buildConnection();
        String checkeQuery = "SELECT serviceName, serviceID FROM service WHERE serviceDate LIKE " + "'%-"+month+"-%'";
        System.out.println(checkeQuery);
        ResultSet rs = oraManager.query(checkeQuery);
        String result = "";
        try {
            System.out.print("serviceCapacity:" +System.lineSeparator());
            while(rs.next()){
                String serviceName=rs.getString("serviceName");
                String serviceID=rs.getString("serviceID");
                System.out.println(serviceID+ " "+serviceName+System.lineSeparator());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        Service s = new Service();
        boolean result = s.checkService(7959);
        System.out.print("Before adding: " + result);
        s.addService(7959, "2016-3-22", 30, "make-up");
        s.addService(8989, "2016-3-23", 15, "gift session");
        result = s.checkService(7959);
        int capacity= s.checkCapacity(7959);
        System.out.print("After adding: " + result + capacity);
        s.updateCapacity(7959, -1);
        capacity= s.checkCapacity(7959);
        System.out.print("After update capacity: " + capacity);
        s.checkServiceForThisMonth("3");

    }
}
