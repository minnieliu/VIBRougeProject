package VIBClass;

import javax.print.StreamPrintService;
import java.sql.*;
import java.sql.ResultSet;

class JDBCManager {

    public static void main(String argv[]){
        try {
            System.out.println("Loading driver");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Finished Loading");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to load driver");
            System.exit(-1);
        }

        try{
            System.out.println("Connecting database...");

            String url = "jdbc:oracle:thin:@localhost:1522:ug";

            Connection conn = DriverManager.getConnection(url, "ora_u3v9a", "a40796147");
            conn.setAutoCommit(true);

            System.out.println("Connect Successful");
            Statement stmt = null;
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            //int rowCount = stmt.executeUpdate("INSERT INTO newauthors VALUES ('345')");
            ResultSet rs = stmt.executeQuery("SELECT productID, brand FROM product WHERE inventoryNumber < 50");
            System.out.println("Query: SELECT Brand, ProductID FROM product WHERE inventoryNumber < 50");
            System.out.println("printing result " + rs);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String brand = rs.getString("brand");
                System.out.println("Brand: "+ brand + "Product ID: "+ productID + "\n");
                }

                //Move to the next line to print the next row.

        rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection fail"+e);
        }
    }
}