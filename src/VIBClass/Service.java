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
        String insertQuery = "INSERT INTO service VALUES ("
                + serviceID + ",'"
                + date +"',"
                + capacity +",'"
                + name+ "')";

//        System.out.println(insertQuery);
        oraManager.execute(insertQuery);
    }

    public boolean checkService(int serviceID){
        String selectQuery = "SELECT * FROM service WHERE serviceID = " + serviceID;
//        System.out.println(selectQuery);
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

        String selectQuery = "SELECT serviceCapacity FROM service WHERE serviceID = " + serviceID;
//        System.out.println(selectQuery);
        ResultSet rs = oraManager.query(selectQuery);
        int result = 0;
        try {
            rs.first();
//            System.out.println("Displaying record...");
            result=rs.getInt("serviceCapacity");
//            System.out.print("serviceCapacity: " + result);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateCapacity(int serviceID, int change){

        int currentCapacity = this.checkCapacity(serviceID);
        int newCapacity = currentCapacity + change;
        String updateQuery = "UPDATE service SET serviceCapacity ="+ newCapacity+ "WHERE serviceID = " + serviceID;
//        System.out.println(updateQuery);
        oraManager.query(updateQuery);
    }

    public ArrayList<String> checkServiceForThisMonth(int month){

        String checkeQuery = "SELECT serviceName, serviceID, serviceCapacity FROM service WHERE serviceDate LIKE " + "'%-"+month+"-%'";
//        System.out.println(checkeQuery);
        ResultSet rs = oraManager.query(checkeQuery);
        ArrayList<String> result = new ArrayList<String>();
        try {
//            System.out.print("serviceCapacity:" );
            while(rs.next()){
                String serviceName=rs.getString("serviceName");
                Integer cap = rs.getInt("serviceCapacity");
                String serviceID=rs.getString("serviceID");
//                System.out.println(serviceID+ " "+serviceName);
                result.add(serviceID + " " + serviceName + "Capacity Left: " + cap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        Service s = new Service();
        OraManager oramanager = new OraManager();
        // Test addService
//        s.addService(123456888, "2016-5-21", 35, "meet and greet");
//        s.addService(87566777, "2016-2-23", 15, "gifon");

        // Test checkService
//        System.out.println("should be true: " + s.checkService(8989));
//        System.out.println("should be false: " + s.checkService(128459433));

        // test checkCapacity
//        s.checkCapacity(123456);
//        s.checkCapacity(111222);

        // Test updateCapacity
//        s.updateCapacity(333444,40);

        // Test checkserviceforthismonth
//        ArrayList<String> result = s.checkServiceForThisMonth(2);
//        for(int i = 0; i < result.size(); i++){
//            System.out.println(result.get(i));
//        }

//
//        String query = "SELECT * FROM service";
//        ResultSet rs=null;
//        rs = oramanager.query(query);
//        try {
//            while(rs.next())
//            {
//                System.out.println(rs.getInt(1) + " " + rs.getInt(3) + " " + rs.getString(4));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
