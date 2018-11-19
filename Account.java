import java.util.Date;
import java.sql.*;

public class Account {
	private int accountID;
	private int accountType;
	private double money;
	private Date deleteDate;
    private double annualRate;
    private int primaryOwner;

	public Account(){
		accountID=0;
		accountType=0;
		money=0.0;
		Date deleteDate = null;
		annualRate = 0;
		primaryOwner = 0;
	}

	public Account(int actID){
		DatabaseHelper.initialize();
		DatabaseHelper db = DatabaseHelper.getInstance();
		String query = "SELECT * FROM Account A WHERE A.account_id="+actID;
		ResultSet rs = db.executeQuery(query);
		accountType = rs.getInt("account_type")
		money = rs.getFloat("moneyVal");
		deleteDate =  rs.getDate("deletedDate");
		annualRate =  rs.getFloat("annualRate");
		primaryOwner = rs.getInt("primOwner");


	}

	public Account(int actID, int actT, double m, Date dDate, double aRate, int primOwner){
		accountID = actID;
		accountType = actT;
		money = m;
		deleteDate = dDate;
		annualRate = aRate;
		primaryOwner = primOwner;

	}

	public int getAccountID(){
		return accountID;
	}


	public int getAccountType(){
		return accountType;
	}


	public double getMoney(){
		return money;
	}


	public Date getDeleteDate(){
		return deleteDate;
	}

	public Date getDeleteDate(){
		return deleteDate;
	}
	




}