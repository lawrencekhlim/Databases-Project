//STEP 1. Import required packages
import java.sql.*;

public class DatabaseHelper {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@cloud-34-133.eci.ucsb.edu:1521:XE";

    //  Database credentials
    static final String USERNAME = "lawrenceklim";
    static final String PASSWORD = "08221998";
  
    private static DatabaseHelper databaseHelper;
    private Connection conn = null;
    
    
    // Singleton Design Pattern.
    public static DatabaseHelper getInstance () {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }
        return databaseHelper;
    }

    private DatabaseHelper () {
    }
    
    public void openConnection () {
        try {
            if (conn == null) {
                //STEP 2: Register JDBC driver
                Class.forName (JDBC_DRIVER);
                //STEP 3: Open a connection
                System.out.println("Connecting to a selected database...");
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Connected database successfully...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection () {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn = null;
    }
 
    public ResultSet executeQuery (String query) {
        ResultSet rs = null;
        openConnection();
        Statement stmt = null;
        try{
            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            //STEP 5: Extract data from result set
            rs.close();
        }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
        }catch(Exception e){
         //Handle errors for Class.forName
          e.printStackTrace();
        }finally{
         //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            closeConnection();
        }//end try
        return rs;
    }
    
    public PreparedStatement createAction (String query) {
        PreparedStatement ret = null;
        if (conn != null) {
            try {
                ret = conn.prepareStatement(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    
    /*
    public void executeAction (String query) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //System.out.println("Connected database successfully...");
            
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.prepareStatement(query);
            System.out.println("Executing statement...");
            stmt.execute();
            System.out.println("End Executing statement...");
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        //return stmt;
    }

    */
}//end DatabaseHelper
