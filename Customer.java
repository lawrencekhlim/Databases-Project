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
        }
        DatabaseHelper.getInstance().closeConnection();

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

    //TODO: this doesn't work, only gets main accts
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


    //TODO: this doesn't work, only gets type 0 not 1
     public ArrayList <Integer> getCheckingAccounts () {
        //TODO: THIS doesn't accound for student checking...even thouhg the sql command works, it only returns type 0 no type 1
        String query1 = "SELECT A.account_id FROM Account A WHERE A.primOwner="+TID+" AND (A.account_type=0 OR A.account_type=1)" ;
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


    public ArrayList <Integer> getSavingsAccounts () {
        System.out.println("saving in");
        //TODO: This query isnt working... not returning the rightthigns idk i legit copy pasted everything is bad
        //fr tho i tested this on sql and it works.. just doesn't work here 
        String query1 = "SELECT B.account_id FROM Account B WHERE B.primOwner="+TID+" AND B.account_type="+2+"" ;
       
        ArrayList<Integer> accounts = new ArrayList <Integer> ();
        DatabaseHelper.getInstance().openConnection();

        try {
            // WE GET HERE 
             System.out.println("saving in 2");
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query1);
            while (rs.next()) {
                //WE DON'T GET HERE THO
                int acctID = rs.getInt("account_id");
                accounts.add (acctID);
                System.out.println("savings act id "+acctID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseHelper.getInstance().closeConnection();
        
        return accounts;
    }

     //TODO: guess what?? this also doesn't work at all
    public ArrayList <Integer> getPocketAccounts () {
       
        String query1 = "SELECT A.account_id FROM Account A WHERE A.primOwner="+TID+" AND A.account_type=3" ;
        //String query2 = "SELECT C.account_id FROM CoOwner C WHERE C.TID="+TID;
        ArrayList<Integer> accounts = new ArrayList <Integer> ();
        DatabaseHelper.getInstance().openConnection();

        try {
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery(query1);
            while (rs.next()) {
                int acctID = rs.getInt("account_id");
                accounts.add (acctID);
                System.out.println("pcoket act id "+acctID);
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

