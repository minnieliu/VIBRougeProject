
package VIBClass;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IvyLuo on 2016-11-01.
 */
public class OraManager {
    public java.sql.Connection conn;
    public Statement stmt;

    public OraManager() {}


    public java.sql.Connection buildConnection() {

        try {
            //System.out.println("Loading driver");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // System.out.println("Finished Loading");
        } catch (SQLException e) {
            e.printStackTrace();
            //  System.out.println("Unable to load driver");
            System.exit(-1);
        }

        try{
            //System.out.println("Connecting database...");

            String url = "jdbc:oracle:thin:@localhost:1522:ug";
            conn = DriverManager.getConnection(url, "ora_u3v9a", "a40796147");
            conn.setAutoCommit(true);

            //System.out.println("Connect Successful");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Connection fail"+e);

        }
        return conn;
    }

    public void execute(String stringForExecute){
        // int rowCount = -1;
        buildConnection();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            stmt.executeUpdate(stringForExecute);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(stringForExecute + " : update fails");
        }

    }



    public ResultSet query(String stringForQuery){
        ResultSet resultset = null;
        Statement stm = null;
        buildConnection();
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            resultset = stm.executeQuery(stringForQuery);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(stringForQuery + " : query fails");
        }
        return resultset;
    }

    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("disconnect fails");
        }
    }

}