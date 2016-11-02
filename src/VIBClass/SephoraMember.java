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
                            "email =" + email + "AND" +
                            "phoneNumber =" + phoneNumber+ "AND" +
                            "password =" + password + "WHERE accountNo = " + accountNo;

        oraManager.execute(updateQuery);

    }

    public int bookService(int serviceid, int accountno){
    // isMember()
    // deduct points from member
    // check if service, if capacity is > 0
    // deduct capacity number from service
        return -1;
    }

}
