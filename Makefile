compile:
	javac -cp ".:ojdbc6.jar" -d . JDBCExample.java
	javac -cp ".:ojdbc6.jar" -d . DatabaseHelper.java
	javac -cp ".:ojdbc6.jar" -d . ATMInterface.java
	javac -cp ".:ojdbc6.jar" -d . BankTellerInterface.java
run:	
	java -cp ".:ojdbc6.jar" ATMInterface
	java -cp ".:ojdbc6.jar" JDBCExample
	java -cp ".:ojdbc6.jar" BankTellerInterface

clean:
	rm JDBCExample.class
