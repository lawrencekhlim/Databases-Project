import java.sql.*;
import java.util.ArrayList;

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
        
        ResultSet rs = DatabaseHelper.getInstance().executeQuery(query);
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
        createID();
        createCustomer();
    }
    
    public Customer(int TID, String name, String address, int PIN){
        this.TID = TID;
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
        //createID();
        String query = "INSERT INTO Customer (TID, name, address, PIN) VALUES (?, ?, ?, ?)";
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (query);
        
        try {
            stmt.setInt (1, TID);
            stmt.setString (2, name);
            stmt.setString (3, address);
            stmt.setString (4, PIN+"");
            stmt.execute();
        } catch (SQLException e) {
            System.err.println ("Execution failed");
            e.printStackTrace();
        }

        DatabaseHelper.getInstance().closeConnection();
        
    }
    public ArrayList<Integer> getAllAccts(){
        ArrayList<Integer> mainList = getMainAccounts();
        mainList.addAll(getCoOwnerAcct());
        return mainList;
    }

    public ArrayList <Integer> getMainAccounts () {
        String query1 = "SELECT A.account_id FROM Account A WHERE A.primOwner="+TID;
        //String query2 = "SELECT C.account_id FROM CoOwner C WHERE C.TID="+TID;
        ArrayList<Integer> accounts = new ArrayList <Integer> ();
        DatabaseHelper.getInstance().openConnection();

        try {
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query1);
            while (rs.next()) {
                int acctID = rs.getInt("account_id");
                accounts.add (acctID);
                System.out.println("primowerner act id "+acctID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHelper.getInstance().closeConnection();
        
        return accounts;
    }

    public ArrayList<Integer> getCoOwnerAcct(){
        String query2 = "SELECT C.account_id FROM CoOwner C WHERE C.TID="+TID;
        ArrayList<Integer> accounts = new ArrayList <Integer> ();
        DatabaseHelper.getInstance().openConnection();
        try {
            System.out.println("im here");
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query2);
            System.out.println(rs);
            while (rs.next()) {
                  System.out.println("enter me");
                int acctID = rs.getInt("account_id");
                accounts.add (acctID);
                  System.out.println("CoOwner acct id "+acctID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHelper.getInstance().closeConnection();
        return accounts;
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
    
    public boolean verifyPIN (int PINnumber) {
        return PINnumber == PIN;
    }
    
    public boolean setPIN (int prevPIN, int newPIN) {
        if (verifyPIN (prevPIN)) {
            // Update
            DatabaseHelper.getInstance().openConnection();
            String updateQuery = "UPDATE Customer SET PIN=? WHERE TID=?";
            PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (updateQuery);
            try {
                stmt.setInt(1, newPIN);
                stmt.setInt(2, TID);
                stmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            DatabaseHelper.getInstance().closeConnection();
            PIN = newPIN;
            return true;
        }
        else // No update
            return false;
    }
    
    
    public static void main (String [] args) {
       // Customer c = new Customer (4000021, "Lawrence Lim", "11813 Trinity Spring Ct", 1717);
        // Customer c = new Customer (4000021, "Lawrence Lim", "11813 Trinity Spring Ct", 1717);
        // int testSubjectA = c.getID ();
        // Customer c2 = new Customer (testSubjectA);
        // System.out.println (c2.getName());
        // System.out.println (c2.getPIN());
        // c2.setPIN (1717, 3001);
        // Customer c3 = new Customer (testSubjectA);
        // System.out.println (c2.getPIN());

        Customer ctest =  new Customer(2);
        System.out.println(ctest.getAllAccts().size());
    }
    
}

