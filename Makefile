compile:
	javac -cp ".:ojdbc6.jar" -d . JDBCExample.java
	javac -cp ".:ojdbc6.jar" -d . DatabaseHelper.java
	javac -cp ".:odjbc6.jar" -d . Customer.java
runExample:	
	java -cp ".:ojdbc6.jar" JDBCExample
	
runCustomerTest:
	java -cp ".:ojdbc6.jar" Customer
clean:
	rm JDBCExample.class
	rm DatabaseHelper.class
	rm Customer.class