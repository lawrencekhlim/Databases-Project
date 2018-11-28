
import java.sql.*;
import java.util.ArrayList;
//transaction types
//0 - deposit
//1 - top up
//2 - withdrawal
//3 -  purchase
//4 - transfer
//5 - collect
//6 - pay friend
//7 - wire
//8 - write-check
//9 - accrue interest
//10 - fees

public class Transaction {
    private int transaction_id;
    private Date transDate;
    private float moneyTrans;
    private int transType;
    private int incrAcctID;
    private int decrAcctID;
    
    public Transaction(){
        transaction_id = -1;
        transDate = null;
        moneyTrans = 0;
        transType = 0;
        incrAcctID = -1;
        decrAcctID = -1;
    }
    
    public Transaction(int transaction_id){
        this.transaction_id = transaction_id;
        DatabaseHelper.getInstance().openConnection();
        String query = "SELECT * FROM Transaction T WHERE T.transaction_id="+transaction_id;
        
        ResultSet rs = DatabaseHelper.getInstance().executeQuery(query);
        try {
            if (rs.next()) {
                transDate = rs.getDate("transDate");
                moneyTrans = rs.getFloat("moneyTrans");
                transType = rs.getInt ("transType");
                incrAcctID = rs.getInt ("incrAcctID");
                decrAcctID = rs.getInt ("decrAcctID");
            }
        } catch (SQLException e) {
            transDate = null;
            moneyTrans = 0;
            transType = 0;
            incrAcctID = -1;
            decrAcctID = -1;
        }
        DatabaseHelper.getInstance().closeConnection();
    }
    
    public Transaction(Date transDate, float moneyTrans, int transType, int incrAcctID, int decrAcctID){
        this.moneyTrans = moneyTrans;
        this.transType = transType;
        this.incrAcctID = incrAcctID;
        this.decrAcctID = decrAcctID;
        createID();
        createTransaction();
    }
    
    
    public void createID () {
        try {
            DatabaseHelper.getInstance ().openConnection();
            ResultSet rs = DatabaseHelper.getInstance ().executeQuery("SELECT MAX (T.transaction_id) FROM Transaction T");
            if (rs.next())
                transaction_id = rs.getInt(1)+1;
            DatabaseHelper.getInstance ().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            transaction_id = 0;
        }
        //System.out.println (TID);
    }
    
    
    public boolean createTransaction () {
        //createID();
        if (decrAcctID != -1) {
            Account decrAccount = new Account (decrAcctID);
            if(decrAccount.getMoney()<moneyTrans)
            {
                System.out.println("NEGATIVE BALANEC");
                return false;
            }
            decrAccount.setMoney (decrAccount.getMoney() - moneyTrans);

        }
        String query = "INSERT INTO Transaction (transaction_id, transDate, moneyTrans, transType, incrAcctID, decrAcctID) VALUES (?, ?, ?, ?, ?, ?)";
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (query);
        
        try {
            stmt.setInt (1, transaction_id);
            stmt.setDate (2, transDate);
            stmt.setFloat (3, moneyTrans);
            stmt.setInt (4, transType);
            stmt.setInt (5, incrAcctID);
            stmt.setInt (6, decrAcctID);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println ("Execution failed");
            e.printStackTrace();
        }
        
        if (incrAcctID != -1) {
            Account incrAccount = new Account (incrAcctID);
            incrAccount.setMoney (incrAccount.getMoney() + moneyTrans);
        }
        
        DatabaseHelper.getInstance().closeConnection();
        return true;
        
    }
    
    public int getTransactionID () {
        return transaction_id;
    }
    
    public Date getTransactionDate () {
        return transDate;
    }
    
    public float getMoneyTransferred () {
        return moneyTrans;
    }
    
    public int getTransactionType () {
        return transType;
    }
    
    public int getAccountIncreased () {
        return incrAcctID;
    }
    
    public int getAccountDecreased () {
        return decrAcctID;
    }
    
    public static void main (String [] args) {
        //Transaction c = new Transaction ();
        try {
            java.text.DateFormat df = new java.text.SimpleDateFormat("MM-dd-yyyy");
            java.sql.Date date = new java.sql.Date(df.parse("02-04-2015").getTime());
            Account a = new Account(3, 240.0, null, 1.3, 2);
            Account b = new Account(2, 140.0, null, 1.3, 2);
            Transaction c = new Transaction (date, (float)30.0, 1, a.getAccountID(), b.getAccountID());
            Transaction d = new Transaction (c.getTransactionID());
            System.out.println(d.getMoneyTransferred());
            System.out.println (new Account (b.getAccountID()).getMoney());
        } catch (Exception e) {
            System.out.println ("Failed");
            e.printStackTrace();
        }
    }
    
}

