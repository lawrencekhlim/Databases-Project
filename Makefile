compile:
	javac -cp ".:ojdbc6.jar" -d . JDBCExample.java
	javac -cp ".:ojdbc6.jar" -d . DatabaseHelper.java
	javac -cp ".:odjbc6.jar" -d . Customer.java
	javac -cp ".:ojdbc6.jar" -d . ATMInterface.java
	javac -cp ".:ojdbc6.jar" -d . BankTellerInterface.java

runExample:	
	java -cp ".:ojdbc6.jar" JDBCExample
	
runCustomerTest:
	java -cp ".:ojdbc6.jar" Customer

run:	
	java -cp ".:ojdbc6.jar" ATMInterface
	java -cp ".:ojdbc6.jar" JDBCExample
	java -cp ".:ojdbc6.jar" BankTellerInterface

clean:
	rm JDBCExample.class
	rm DatabaseHelper.class
	rm Customer.class
	rm BankTellerInterface.class
	rm ATMInterface.class