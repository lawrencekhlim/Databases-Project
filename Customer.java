import java.sql.*;
import java.util.ArrayList;

public class Customer {
    private int TID;
    private String name;
    private String address;
    private int PIN;
    private ArrayList <Account> accounts;
    
    public Customer(){
        TID = 0;
        name = "";
        address = "";
        PIN = 0;
    }
    
    public Customer(int TID){
        DatabaseHelper.getInstance().openConnection();
        this.TID = TID;

        String query = "SELECT * FROM Customer C WHERE C.TID="+TID;
        ResultSet rs = DatabaseHelper.getInstance().executeQuery(query);
        try {
            if (rs.next()) {
                name = rs.getString("name");
                address = rs.getString("address");
                PIN = Integer.parseInt(rs.getString("PIN"));
            }
            else {
                name = "";
                address = "";
                PIN = -1;
                this.TID = -1;
            }
        } catch (SQLException e) {
            name = "";
            address = "";
            PIN = -1;
            TID = -1;
        }
        DatabaseHelper.getInstance().closeConnection();
    }
    
    public Customer(String name, String address, int PIN){
        this.name = name;
        this.address = address;
        this.PIN = PIN;
        createID();
        createCustomer();
    }

    public Customer(String pin)
    {
        DatabaseHelper.getInstance().openConnection();
        String query = "SELECT * FROM Customer C WHERE C.PIN="+pin;
        PIN = Integer.parseInt(pin);
        ResultSet rs = DatabaseHelper.getInstance().executeQuery(query);
        try {
            if (rs.next()) {
                name = rs.getString("name");
                address = rs.getString("address");
                TID = rs.getInt("TID");
            }
        } catch (SQLException e) {
            name = "";
            address = "";
            PIN = -1;
            TID = -1;
        }
        DatabaseHelper.getInstance().closeConnection();

    }
    
    public Customer(int TID, String name, String address, int PIN){
        this.TID = TID;
        this.name = name;
        this.address = address;
        this.PIN = PIN;
        //createCustomer();
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
    
    
    public boolean createCustomer () {
        //createID();
        String query = "INSERT INTO Customer (TID, name, address, PIN) VALUES (?, ?, ?, ?)";
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (query);
        
        boolean success = true;
        try {
            stmt.setInt (1, TID);
            stmt.setString (2, name);
            stmt.setString (3, address);
            stmt.setString (4, PIN+"");
            stmt.execute();
        } catch (SQLException e) {
            System.err.println ("Execution failed");
            success = false;
            e.printStackTrace();
        }

        DatabaseHelper.getInstance().closeConnection();
        return success;
        
    }

     public static ArrayList <String> getAllPins () {
        //TODO: make sure to hash or whatever
        String query1 = "SELECT C.PIN FROM Customer C" ;
        //String query2 = "SELECT C.account_id FROM CoOwner C WHERE C.TID="+TID;
        ArrayList<String> pins = new ArrayList <String> ();
        DatabaseHelper.getInstance().openConnection();

        try {
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query1);
            while (rs.next()) {
                String pin = rs.getString("PIN");
                pins.add (pin);
                System.out.println("pin "+pin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHelper.getInstance().closeConnection();
        
        return pins;
    }

    
    public ArrayList <Account> getAccounts () {
        accounts = new ArrayList <Account>();
        
        DatabaseHelper.getInstance().openConnection();
        
        String query1 = "SELECT A.account_id FROM Account A WHERE A.primOwner="+TID;
        accounts = new ArrayList <Account> ();
        DatabaseHelper.getInstance().openConnection();
        ArrayList <Integer> accountNumbers = new ArrayList <Integer>();
        
        try {
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query1);
            while (rs.next()) {
                int acctID = rs.getInt("account_id");
                accountNumbers.add (acctID);
                System.out.println("prim act id "+acctID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHelper.getInstance().closeConnection();
        
        String query2 = "SELECT B.account_id FROM CoOwner B WHERE B.TID=" +TID;
        System.out.println (query2);
        DatabaseHelper.getInstance().openConnection();
        try {
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query2);
            while (rs.next()) {
                int acctID = rs.getInt("account_id");
                accountNumbers.add (acctID);
                System.out.println("act  id "+acctID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHelper.getInstance().closeConnection();
        
        for (int i = 0; i < accountNumbers.size(); i++) {
            accounts.add (new Account (accountNumbers.get (i)));
            //System.out.println("populatng accts "+accountNumbers.get(i));
        }


        return accounts;
    }

    public ArrayList <Account> getMainAccounts () {
        if (accounts == null) {
            getAccounts();
        }
        ArrayList <Account> mainAccounts = new ArrayList <Account>();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getPrimaryOwner() == TID) {
                mainAccounts.add (accounts.get(i));
            }
        }
        
        return mainAccounts;
    }
    
    public ArrayList<Account> getCoOwnedAccounts(){
        if (accounts == null) {
            getAccounts();
        }
        ArrayList <Account> coOwnedAccounts = new ArrayList <Account>();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getPrimaryOwner() == TID) {
                coOwnedAccounts.add (accounts.get(i));
            }
        }
        return coOwnedAccounts;
    }

    public ArrayList <Account> getAccountOfType (int accountType) {
        if (accounts == null) {
            getAccounts();
        }
        ArrayList <Account> accountsOfType = new ArrayList <Account>();
        //System.out.println("ACCTS 2 " +accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountType() == accountType) {
                accountsOfType.add (accounts.get(i));
                //System.out.println("added this acct type " +accounts.get(i).getAccountID());
            }
        }
        //System.out.println("accountsOfType "+ accountType+ " length "+accountsOfType.size());
        return accountsOfType;
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

        Customer ctest = new Customer(2);
        System.out.println(ctest.getAccounts());
    }
    
}

