package VIBClass;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by minnieliu on 2016-11-01.
 */
public class OraManager {
    public java.sql.Connection con;
    public Statement stmt;

    public OraManager() {}


    public void buildConnection() {

        try {
            System.out.println("Loading driver");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Finished Loading");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to load driver");
            System.exit(-1);
        }

        String connectURL = "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug";
        try{
            System.out.println("Connecting database...");

            String url = "jdbc:oracle:thin:@localhost:1522:ug";
            java.sql.Connection conn = DriverManager.getConnection(url, "ora_m6v9a", "a31147144");
            conn.setAutoCommit(true);
            stmt = con.createStatement();
            System.out.println("Connect Successful");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Connection fail"+e);

        }
    }

    public int execute(String stringForExecute){
        int rowCount = -1;
        try {
            rowCount = stmt.executeUpdate(stringForExecute);
            System.out.println("row " + rowCount + " updated");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(stringForExecute + " : update fails");
        }
        return rowCount;
    }



    public ResultSet query(String stringForQuery){
        ResultSet rs = null;
//        try {
//            System.out.print("making stmt " + stringForQuery);
//            stmt = con.createStatement();
//        } catch (SQLException e) {
//            System.out.print("stmt failed");
//            e.printStackTrace();
//        }
       //  System.out.print("out of try/catch");
        if(stmt==null)
            System.out.println("stmt is NULL");
        try {
            System.out.print("executing query" + stringForQuery);
            rs = stmt.executeQuery(stringForQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(stringForQuery + " : query fails");
        }
        return rs;
    }
    public void disconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disconnect fails");
        }
    }

}