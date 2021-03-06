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
     java.sql.Date current = new java.sql.Date(java.lang.System.currentTimeMillis());
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
                if (rs.wasNull ()) {
                    incrAcctID = -1;
                }
                decrAcctID = rs.getInt ("decrAcctID");
                if (rs.wasNull ()) {
                    decrAcctID = -1;
                }
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
        this.transDate = transDate;
        this.moneyTrans = moneyTrans;
        this.transType = transType;
        this.incrAcctID = incrAcctID;
        this.decrAcctID = decrAcctID;
        createID();
    }

    public Transaction(Date transDate, float moneyTrans, int transType, int incrAcctID, int decrAcctID, boolean useID){
        this.transDate = transDate;
        this.moneyTrans = moneyTrans;
        this.transType = transType;
        this.incrAcctID = incrAcctID;
        this.decrAcctID = decrAcctID;
    }
    
    public Transaction(int transaction_id, Date transDate, float moneyTrans, int transType, int incrAcctID, int decrAcctID){
        this.transaction_id = transaction_id;
        this.transDate = transDate;
        this.moneyTrans = moneyTrans;
        
        this.transType = transType;
        this.incrAcctID = incrAcctID;
        this.decrAcctID = decrAcctID;
    
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
        //System.out.println ("Hello");
        Account decrAccount = null;
        Account incrAccount = null;
        if (decrAcctID != -1) {
            decrAccount = new Account (decrAcctID);
            if(decrAccount.getMoney()<moneyTrans || decrAccount.getDeleteDate() != null) {
                System.out.println("NEGATIVE BALANCE or closed account");
                return false;
            }
        }
        
        //System.out.println ("Hello");
      
        if (incrAcctID != -1) {
            incrAccount = new Account (incrAcctID);
            if (incrAccount.getDeleteDate() != null) {
                System.out.println("closed account");
                return false;

            }
        }
        
        if(decrAcctID!=-1 || incrAcctID!=-1)
        {
            decrAccount = new Account (decrAcctID);
            incrAccount = new Account(incrAcctID);


            if(decrAccount.getDeleteDate() != null || incrAccount.getDeleteDate()!=null)
            {
                return false;
            }
        }
        String query = "INSERT INTO Transaction (transaction_id, transDate, moneyTrans, transType, incrAcctID, decrAcctID) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println (query);
        DatabaseHelper.getInstance().openConnection();
        PreparedStatement stmt = DatabaseHelper.getInstance ().createAction (query);
        
        try {
            stmt.setInt (1, transaction_id);
            stmt.setDate (2, transDate);
            stmt.setFloat (3, moneyTrans);
            stmt.setInt (4, transType);
            if (incrAcctID == -1)
                stmt.setNull (5, Types.INTEGER);
            else
                stmt.setInt (5, incrAcctID);
            if (decrAcctID == -1)
                stmt.setNull (6, Types.INTEGER);
            else
                stmt.setInt (6, decrAcctID);
            System.out.println("tranID:"+transaction_id+ "  transDate:"+transDate+ " moneyTrans"+moneyTrans+" transType"+transType);
            stmt.execute();

        } catch (SQLException e) {
            System.err.println ("Execution failed");
            e.printStackTrace();
            return false;
        }
        DatabaseHelper.getInstance().closeConnection();
        
        if (decrAcctID != -1) {
            decrAccount.setMoney (decrAccount.getMoney() - moneyTrans);
            if(decrAccount.getMoney() <=.01 && decrAccount.getAccountType() != 3) {
                //System.out.println("NEGATIVE BALANCE");
                decrAccount.setDeleteDate(transDate);
            }
        }
        
        System.out.println (query);
        if (incrAcctID != -1) {

            incrAccount.setMoney (incrAccount.getMoney() + moneyTrans);
        }
        System.out.println (query);
        return true;
        
    }
    
    public boolean createPocketTransaction (java.sql.Date current) {
        
        Account decrAccount = null;
        if (decrAcctID != -1) {
            decrAccount = new Account (decrAcctID);
            Date recfee = decrAccount.getRecentFee ();
            long day30 = 31l * 24 * 60 * 60 * 1000;
            if (recfee != null && !recfee.after(new Date(current.getTime() - day30))) {
                return createTransaction ();
            }
            else if(((recfee == null || recfee.after(new Date(current.getTime() - day30))) && decrAccount.getMoney() >= moneyTrans + 5)) {
                if (createTransaction()) {
                    Transaction fee = new Transaction (current, 5, 10, -1, decrAcctID);
                    
                    DatabaseHelper.getInstance().openConnection();
                    String query = "UPDATE PocketAccount SET recent_fee=? WHERE account_id=?";
                    PreparedStatement stmt = DatabaseHelper.getInstance().createAction(query);
                    try {
                        stmt.setDate (1, current);
                        stmt.setInt (2, decrAcctID);
                        stmt.execute();
                    } catch (Exception e) {
                        System.out.println ("FAILED Update FEE Transaction Date");
                    }
                    
                    DatabaseHelper.getInstance().closeConnection();
                    
                    return fee.createTransaction();
                } else
                    return false;
            }
        }
        return false;
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

