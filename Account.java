//import java.sql.Date;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
//checking mums
//0 - interest checking
//1 - student checking
//2 - savings 
//3 - pocket 
public class Account {
	private int accountID;
	private int accountType;
	private double money;
	private java.sql.Date deleteDate;
    private double annualRate;
    private int primaryOwner;

	public Account(){
		accountID=0;
		accountType=0;
		money=0.0;
		deleteDate = null;
		annualRate = 0;
		primaryOwner = 0;
	}

	public Account(int actID){
		DatabaseHelper.getInstance().openConnection();
        String query = "SELECT * FROM Account A WHERE A.account_id="+actID;
      	accountID = actID;
		ResultSet rs = DatabaseHelper.getInstance().executeQuery(query);
		try {
            if (rs.next()) {
                accountType = rs.getInt("account_type");
				money = rs.getFloat("moneyVal");
				deleteDate =  rs.getDate("deletedDate");
                if (rs.wasNull()) {
                    deleteDate = null;
                }
				annualRate =  rs.getFloat("annualRate");
				primaryOwner = rs.getInt("primOwner");
				System.out.println("money val: "+money+ " primaryOwner: "+primaryOwner);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            accountID=-1;
            accountType=0;
            money=0.0;
            deleteDate = null;
            annualRate = 0;
            primaryOwner = -1;
        }

        DatabaseHelper.getInstance ().closeConnection();
	}
	
	public void generateAccountID(){
		try {
            DatabaseHelper.getInstance ().openConnection();
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery("SELECT MAX (A.account_id) FROM Account A");
            if (rs.next())
                accountID = rs.getInt(1)+1;
            DatabaseHelper.getInstance ().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            accountID = 0;
        }
        System.out.println (accountID);
        
	}

	public Account(int actT, double m, java.sql.Date dDate, double aRate, int primOwner){
		this.accountType = actT;
		this.money = m;
		this.deleteDate = dDate;
		this.annualRate = aRate;
		this.primaryOwner = primOwner;
        generateAccountID();
		createAccount();
	}
    
    public Account(int actT, double m, java.sql.Date dDate, double aRate, int primOwner, boolean callCreateAccount){
        this.accountType = actT;
        this.money = m;
        this.deleteDate = dDate;
        this.annualRate = aRate;
        this.primaryOwner = primOwner;
        generateAccountID();
        if (callCreateAccount)
            createAccount();
    }
    
    public Account (int accountID, int actT, double m, java.sql.Date dDate, double aRate, int primOwner, boolean callCreateAccount) {
        this.accountID = accountID;
        this.accountType = actT;
        this.money = m;
        this.deleteDate = dDate;
        this.annualRate = aRate;
        this.primaryOwner = primOwner;
        if (callCreateAccount)
            createAccount();
    }

    public boolean createAccount (java.sql.Date current) {
        boolean success = true;
        String query = "INSERT INTO Account (account_id, deletedDate, account_type, moneyVal, primOwner, annualRate) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println (query);
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (query);
        
        try {
            stmt.setInt (1, accountID);
            if (deleteDate == null)
                stmt.setNull (2, Types.DATE);
            else
                stmt.setDate (2, deleteDate);
            
            stmt.setInt (3, accountType);
            if ((accountType == 0 || accountType == 1 || accountType == 2) && current != null) {
                stmt.setDouble (4, 0.0);
            }
            else {
                stmt.setDouble (4, money);
            }
            stmt.setInt (5, primaryOwner);
            stmt.setDouble(6, annualRate);
            //System.out.println ("Here");
            stmt.execute();
        } catch (SQLException e) {
            System.err.println ("Execution failed");
            success = false;
            e.printStackTrace();
        }
        //System.out.println ("Here2");
        //System.out.println ("After execution");
        DatabaseHelper.getInstance().closeConnection();
        
        if (success && (accountType == 0 || accountType == 1 || accountType == 2) && current != null) {
            Transaction t = new Transaction (current, (float)money, 0, accountID, -1);
            t.createTransaction();
        }
        return success;
    }
    
    public boolean createLinkedAccount (int linkedAccountID) {
        // Check that the linked account is one that the owner owns
        Account d = new Account (linkedAccountID);
        ArrayList <Customer> customers = d.getAccountOwners ();
        boolean passOwnerTest = false;
        for (Customer customer: customers) {
            if (customer.getID() == primaryOwner) {
                passOwnerTest = true;
            }
        }
        
        if (d.getAccountType () == 3 || !(d.getMoney () > 0) || !passOwnerTest) {
            return false;
        }
        
        DatabaseHelper.getInstance().openConnection();
        String query = "INSERT INTO PocketAccount (account_id, linked_account, recent_fee) VALUES (?, ?, ?)";
        boolean success = true;
        PreparedStatement stmt = DatabaseHelper.getInstance().createAction (query);
        try {
            stmt.setInt (1, accountID);
            stmt.setInt (2, linkedAccountID);
            stmt.setNull (3, Types.DATE);
            stmt.execute();
        } catch (Exception e) {
            success = false;
        }
        DatabaseHelper.getInstance().closeConnection();
        return success;
    }
    
    public java.sql.Date getRecentFee () {
        java.sql.Date recent = null;
        if (accountType == 3) {
            DatabaseHelper.getInstance().openConnection();
            String query = "SELECT A.recent_fee FROM PocketAccount A WHERE A.account_id=" + accountID;
            try {
                ResultSet rs = DatabaseHelper.getInstance().executeQuery (query);
                if (rs.next()) {
                    recent = rs.getDate ("recent_fee");
                    if (rs.wasNull()) {
                        recent = null;
                    }
                }
            } catch (Exception e) {
                recent = null;
            }
        }
        return recent;
    }
    
    public Account getLinkedAccount () {
        Account linked = null;
        int linkedID = -1;
        if (accountType == 3) {
            
            DatabaseHelper.getInstance().openConnection();
            //String query = "SELECT B.account_id, B.deletedDate, B.account_type, B.moneyVal, B.primOwner, B.annualRate FROM PocketAccount A, Account B WHERE A.linked_account=B.account_id A.account_id=" + accountID;
            String query = "SELECT A.linked_account FROM PocketAccount A WHERE A.account_id=" + accountID;
            try {
                ResultSet rs = DatabaseHelper.getInstance().executeQuery (query);
                if (rs.next()) {
                    linkedID = rs.getInt ("linked_account");
                    if (rs.wasNull()) {
                        linkedID = -1;
                    }
                }
                else {
                    linkedID = -1;
                }
            } catch (Exception e) {
                linkedID = -1;
            }
        }
        if (linkedID != -1)
            return new Account (linkedID);
        else
            return null;
    }
                                              
    public boolean createAccount () {
        return createAccount (null);
    }
    
    public ArrayList <Transaction> getTransactions () {
        DatabaseHelper.getInstance().openConnection();
        String query = "SELECT * FROM Account A, Transaction T WHERE (A.account_id=T.incrAcctID OR A.account_id=T.decrAcctID) AND A.account_id="+accountID;
        
        ResultSet stmt = DatabaseHelper.getInstance ().executeQuery (query);
        ArrayList <Transaction> ret = new ArrayList <Transaction> ();
        try {
            while (stmt.next()) {
                int incrAcctID = stmt.getInt ("incrAcctID");
                if (stmt.wasNull())
                    incrAcctID = -1;
                int decrAcctID = stmt.getInt ("decrAcctID");
                if (stmt.wasNull())
                    decrAcctID = -1;
                
                ret.add (new Transaction (stmt.getInt ("transaction_id"), stmt.getDate ("transDate"), stmt.getFloat ("moneyTrans"), stmt.getInt("transType"), incrAcctID, decrAcctID));
            }
        } catch (Exception e) {
            
        }
        DatabaseHelper.getInstance().closeConnection();
        return ret;
    }
    
    public ArrayList <Customer> getAccountOwners () {
        DatabaseHelper.getInstance().openConnection();
        String query = "SELECT C.TID, C.name, C.address, C.PIN FROM Account A, Customer C WHERE A.primOwner=C.TID AND A.account_id="+accountID + " UNION SELECT Y.TID, Y.name, Y.address, Y.PIN FROM Customer Y, CoOwner Z WHERE Y.TID=Z.TID AND Z.account_id=" +accountID;
        
        ResultSet stmt = DatabaseHelper.getInstance ().executeQuery (query);
        ArrayList <Customer> ret = new ArrayList <Customer> ();
        try {
            while (stmt.next()) {
                String name = stmt.getString ("name");
                if (stmt.wasNull())
                    name = "";
                String address = stmt.getString ("address");
                if (stmt.wasNull())
                    address = "";
                
                ret.add (new Customer (stmt.getInt ("TID"), name, address, stmt.getString ("PIN") ));
            }
        } catch (Exception e) {
            
        }
        DatabaseHelper.getInstance().closeConnection();
        return ret;
    }

	public int getAccountID(){
		return accountID;
	}

	public int getAccountType(){
		return accountType;
	}
    
    public int getPrimaryOwner () {
        return primaryOwner;
    }


	public double getMoney(){
		return money;
	}


	public Date getDeleteDate(){
		return deleteDate;
	}



	public void setMoney(double m){
        DatabaseHelper.getInstance().openConnection();
        String updateQuery = "UPDATE Account SET moneyVal=? WHERE account_id=?";
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction(updateQuery);
        
        this.money = m;
        try {
            stmt.setDouble(1, m);
            stmt.setInt(2, accountID);
            stmt.execute();
        }catch (Exception e) {
            e.printStackTrace();
            
        }
        DatabaseHelper.getInstance().closeConnection();
	}

	public void setDeleteDate(java.sql.Date d){
        DatabaseHelper.getInstance().openConnection();
        String updateQuery = "UPDATE Account SET deletedDate=? WHERE account_id=?";
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction(updateQuery);
        
        this.deleteDate = d;
        try {
            stmt.setDate(1, d);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
             
        }
        DatabaseHelper.getInstance().closeConnection();

	}

	public void setAnnualRate(double annRate){
		  DatabaseHelper.getInstance().openConnection();
          String updateQuery = "UPDATE Account SET annualRate=? WHERE account_id=?";
          PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (updateQuery);
          this.annualRate = annRate;
          try {
                stmt.setDouble(1, annRate);
                stmt.setInt(2, accountID);
                stmt.execute();
          } catch (Exception e) {
                e.printStackTrace();
             
           }
           DatabaseHelper.getInstance().closeConnection();

	}

	public void setPrimaryOwner(int ownID){
		  DatabaseHelper.getInstance().openConnection();
          String updateQuery = "UPDATE Account SET primOwner=? WHERE account_id=?";
          PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (updateQuery);
          this.primaryOwner = ownID;
          try {
                stmt.setInt(1, primaryOwner);
                stmt.setInt(2, accountID);
                stmt.execute();
          } catch (Exception e) {
                e.printStackTrace();
             
          }
          DatabaseHelper.getInstance().closeConnection();

	}


	


	public static void main (String [] args) {
        // Customer c = new Account ("Lawrence Lim", "11813 Trinity Spring Ct", 4001);
        // int testSubjectA = c.getID ();
        // Customer c2 = new Customer (testSubjectA);
        // System.out.println (c2.getName());
        try{
        java.text.DateFormat df = new java.text.SimpleDateFormat("MM-dd-yyyy");
		java.sql.Date date = new java.sql.Date(df.parse("02-04-2015").getTime());
        Account a  = new Account(0, 100.0,null, 1.1, 0);
        int testA =  a.getAccountID();
        Account test2 = new Account(testA);
        System.out.println(test2.getMoney());
        test2.setMoney(150.0);
         date = new java.sql.Date(df.parse("11-04-2018").getTime());

        test2.setDeleteDate(date);
        Account test3 = new Account(test2.getAccountID());
        System.out.println(test3.getMoney());

      

        } catch(Exception e) {
        	System.out.println("Something went wrong with teh date");
        }
		

    }



}
