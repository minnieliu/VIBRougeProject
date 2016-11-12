
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

    public String checkStatus(int accountNo) throws SQLException {
        int yearToDateSpent = 0;
        String newStatus = "BeautyInsider";
        ResultSet rs = null;
        String selectQuery = "SELECT yearToDateSpent FROM member1 WHERE accountNo =" + accountNo;
        rs = oraManager.query(selectQuery);
        while (rs.next()) {
            System.out.print("yeartodatespent from result: " + rs.getInt("yearToDateSpent"));
            yearToDateSpent = rs.getInt("yearToDateSpent");
        }
        if (yearToDateSpent > 350){
            newStatus = "VIB";
        }
        if (yearToDateSpent > 1000){
            newStatus = "VIB Rouge";
        }
        String updateQuery = "UPDATE updateStatus SET " +
                "yearToDateSpent = " + yearToDateSpent + " AND " +
                "status = " + newStatus+
                " WHERE accountNo = " + accountNo;
        oraManager.execute(updateQuery);
         return newStatus;
    }

    public void removeMember(int accountNo){
        String deleteQuery = "DELETE FROM member1 WHERE accountNo = "+accountNo;
        oraManager.execute(deleteQuery);

    }


    public void updateAccountInfo(int accountNo, String email, String password) throws Exception {
        String updateQuery = "UPDATE member1 SET " +
                "emailAddress = '" + email + "' , " +
                "password = '" + password + "' WHERE accountNo = " + accountNo;
        if(accountNo != 0 && email != null && password!=null)
            oraManager.execute(updateQuery);
        else{
            Exception e= new Exception("This is not a valid update");
            throw e;
        }

    }

    //edit by Hailey
    public int getCurrentPointbyNameAndPhone(String name, String phoneNumber){
        String selectQuery = "SELECT currentPoints FROM member1 WHERE name ='" + name+ "'AND phoneNumber='" +phoneNumber+"'";
        ResultSet rs = oraManager.query(selectQuery);
        int result = 0;
        try {
            rs.first();
            result = rs.getInt("currentPoints");
            System.out.println("currentPoints for customer "+ name+ " with phone number "+ phoneNumber+ " is "+ result);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    //edit by Hailey
    public int updatePoint(String name, String phoneNumber,int change ){
        int currentPoints= this.getCurrentPointbyNameAndPhone(name,phoneNumber);
        int newPoints= currentPoints+change;
        String updateQuery = "UPDATE member1 SET " +
                "currentPoints= " + newPoints + "WHERE name = '" + name +"'AND phoneNumber='" +phoneNumber+"'";
        oraManager.execute(updateQuery);

        int result= this.getCurrentPointbyNameAndPhone(name,phoneNumber);
        System.out.println("After changing "+change+", currentPoints for customer "+ name+ " with phone number "+ phoneNumber+ " is "+ result);
        return result;
    }

    public boolean bookService(int serviceid, String name, String phone) throws Exception {
        // checks the name and phone to see if member
        // updates the capacity number when service is booked
        int capacityNum = 0;
        Customer c = new Customer();
        if(serviceid != 0 && name != null && phone!=null) {
            if (c.isMember(name, phone)) {
                ResultSet rs = null;
                String selectQuery = "SELECT serviceCapacity FROM service WHERE serviceID = " + serviceid;
                rs = oraManager.query(selectQuery);
                try {
                    while (rs.next()) {
                        capacityNum = rs.getInt("serviceCapacity");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (capacityNum == 0) {
                    Exception e= new Exception("This is not a valid book");
                    throw e;
                }
                capacityNum--;

                String updateQuery = "UPDATE service SET serviceCapacity = " + capacityNum + "WHERE serviceID = " + serviceid;
                oraManager.execute(updateQuery);

            }
            return true;
        }
        else{
            Exception e= new Exception("This is not a valid book");
            throw e;
        }
    }

    public static void main(String[] args) {
        SephoraMember sm = new SephoraMember();
        OraManager oramanager =  new OraManager();

        // Test updatePoints
        // sm.updatePoint("Sarah Kwong", "7782341039",70);

        // Test UpdateStatus <- need to finish
        try {
            String s = sm.checkStatus(12304509);
            System.out.print("Status: " + s);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test removeMember
        // sm.removeMember(39512350);

        // Test updateAccountInfo
        //sm.updateAccountInfo(39512350,"so@gmail.com","number1");

        // Test getCurrentPointsbyNameandPhone
        // sm.getCurrentPointbyNameAndPhone("Sarah Kwong", "7782341039");

        // Test bookService
//        sm.bookService(124356,"Sally Chang", "7785933842");
//


//        String query = "SELECT * FROM service";
//        ResultSet rs=null;
//        rs = oramanager.query(query);
//        try {
//            while(rs.next())
//            {
//                //System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getInt(6) + " " +rs.getString(7) + " "  + rs.getString(8));
//                 System.out.println(rs.getInt(1) + " " + rs.getInt(3) + " " + rs.getString(4));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}