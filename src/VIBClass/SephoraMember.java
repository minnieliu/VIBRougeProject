package VIBClass;

import java.sql.*;
/**
 * Created by minnieliu on 2016-11-01.
 */
public class SephoraMember {


    OraManager oraManager;

    public SephoraMember() {
        oraManager = new OraManager();
    }

    public void updateStatus(int accountNo) throws SQLException {
        int yearToDateSpent = 0;
        String newStatus = "BeautyInsider";
        ResultSet rs = null;
        String selectQuery = "SELECT yearToDateSpent FROM member1 WHERE accountNo =" + accountNo;
        rs = oraManager.query(selectQuery);
        while (rs.next()) {
            yearToDateSpent = rs.getInt("yearToDateSpent");
        }
        if (yearToDateSpent > 350){
            newStatus = "VIB";
        }
        if (yearToDateSpent > 1000){
            newStatus = "VIB Rouge";
        }
        String updateQuery = "UPDATE updateStatus SET " +
                "yearToDateSpent =" + yearToDateSpent + "AND" +
                "status =" + newStatus+
                "WHERE accountNo = " + accountNo;
         oraManager.execute(updateQuery);
       // return row;
    }

    public void removeMember(int accountNo){
        String deleteQuery = "DELETE FROM member1 WHERE accountNo = "+accountNo+ ")";
        oraManager.execute(deleteQuery);

    }


    public void updateAccountInfo(int accountNo, String email, String phoneNumber, String password){
        String updateQuery = "UPDATE member1 SET " +
                            "email = '" + email + "' AND" +
                            "phoneNumber = " + phoneNumber+ "AND" +
                            "password = '" + password + "' WHERE accountNo = " + accountNo;

        oraManager.execute(updateQuery);

    }


    public boolean bookService(int serviceid, String name, int phone){
        // checks the name and phone to see if member
        // updates the capacity number when service is booked
        int capacityNum = 0;
        Customer c = new Customer();
        if (c.isMember(name,phone)){
            ResultSet rs = null;
            String selectQuery = "SELECT capacityNum FROM service WHERE serviceID = " + serviceid;
            rs = oraManager.query(selectQuery);
            try {
                while (rs.next()) {
                    capacityNum = rs.getInt("capacityNum");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (capacityNum == 0) {
                return false;
            }
            capacityNum --;

            String updateQuery = "UPDATE service SET capacityNum = " + capacityNum + "WHERE serviceID = " + serviceid;
            oraManager.execute(updateQuery);

        }
        return true;
    }
}
