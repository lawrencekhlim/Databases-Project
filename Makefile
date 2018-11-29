compile:
	javac -cp ".:ojdbc6.jar" -d . DatabaseHelper.java
	javac -cp ".:odjbc6.jar" -d . Customer.java
	javac -cp ".:ojdbc6.jar" -d . ATMInterface.java
	javac -cp ".:ojdbc6.jar" -d . BankTellerInterface.java
	javac -cp ".:ojdbc6.jar" -d . Account.java
	javac -cp ".:ojdbc6.jar" -d . Transaction.java

runExample:	
	java -cp ".:ojdbc6.jar" JDBCExample
	
runCustomerTest:
	java -cp ".:ojdbc6.jar" Customer

runAccountTest:
	java -cp ".:ojdbc6.jar" Account

runTransactionTest:
	java -cp ".:ojdbc6.jar" Transaction

runATM:	
	java -cp ".:ojdbc6.jar" ATMInterface

runBankTeller:
	java -cp ".:ojdbc6.jar" BankTellerInterface

clean:
	rm *.class