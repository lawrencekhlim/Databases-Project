import java.util.Date;
import java.sql.*;

public class Customer {
    private int TID;
    private String name;
    private String address;
    private int PIN;
    
    public Customer(){
        TID = 0;
        name = "";
        address = "";
        PIN = 0;
    }
    
    public Customer(int TID){
        DatabaseHelper.getInstance().openConnection();
        String query = "SELECT * FROM Customer C WHERE C.TID="+TID;
        System.out.println (query);
        
        
        ResultSet rs = DatabaseHelper.getInstance().executeQuery(query);
        //System.out.println ("Here");
        try {
            if (rs.next()) {
                name = rs.getString("name");
                address = rs.getString("address");
                PIN = Integer.parseInt(rs.getString("PIN"));
            }
        } catch (SQLException e) {
            name = "";
            address = "";
            PIN = -1;
        }
        this.TID = TID;
        DatabaseHelper.getInstance().closeConnection();
    }
    
    public Customer(String name, String address, int PIN){
        this.name = name;
        this.address = address;
        this.PIN = PIN;
        createCustomer();
    }
    
    public void createID () {
        try {
            DatabaseHelper.getInstance ().openConnection();
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery("SELECT MAX (C.TID) FROM Customer C");
            if (rs.next())
                TID = rs.getInt(1)+1;
            DatabaseHelper.getInstance ().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            TID = 0;
        }
        System.out.println (TID);
    }
    
    
    public void createCustomer () {
        createID();
        String query = "INSERT INTO Customer (TID, name, address, PIN) VALUES (?, ?, ?, ?)";
        System.out.println (query);
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (query);
        
        try {
            stmt.setInt (1, TID);
            stmt.setString (2, name);
            stmt.setString (3, address);
            stmt.setString (4, PIN+"");
            System.out.println ("Here!");
            stmt.execute();
            System.out.println ("Here2!");
        } catch (SQLException e) {
            System.err.println ("Execution failed");
            e.printStackTrace();
        }
        System.out.println ("After execution");
        DatabaseHelper.getInstance().closeConnection();
        
    }
    
    public int getID () {
        return TID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress () {
        return address;
    }
    
    public int getPIN () {
        return PIN;
    }
    
    public static void main (String [] args) {
        Customer c = new Customer ("Lawrence Lim", "11813 Trinity Spring Ct", 4001);
        int testSubjectA = c.getID ();
        Customer c2 = new Customer (testSubjectA);
        System.out.println (c2.getName());
    }
    
}

