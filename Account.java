//import java.sql.Date;
import java.sql.*;
import java.util.Date;
//checking mums
//0 - interest checking
//1 -  student checking
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
		createAccount();
	}

	 public void createAccount () {
        generateAccountID();
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
            stmt.setDouble (4, money);
            stmt.setInt (5, primaryOwner);
            stmt.setDouble(6, annualRate);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println ("Execution failed");
            e.printStackTrace();
        }
        System.out.println ("After execution");
        DatabaseHelper.getInstance().closeConnection();
        
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
          PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (updateQuery);
          this.money = m;
          try {
                stmt.setDouble(1, m);
                stmt.setInt(2, accountID);
                stmt.execute();
          } catch (Exception e) {
                e.printStackTrace();
             
           }
           DatabaseHelper.getInstance().closeConnection();

	}

	public void setDeleteDate(java.sql.Date d){
		  DatabaseHelper.getInstance().openConnection();
          String updateQuery = "UPDATE Account SET deletedDate=? WHERE account_id=?";
          PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (updateQuery);
          this.deleteDate = d;
          try {
                stmt.setDate(1, d);
                stmt.setInt(2, accountID);
                stmt.execute();
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
         date = new java.sql.Date(df.parse("03-04-2015").getTime());

        test2.setDeleteDate(date);
        Account test3 = new Account(test2.getAccountID());
        System.out.println(test3.getMoney());

      

        } catch(Exception e) {
        	System.out.println("Something went wrong with teh date");
        }
		

    }



}
