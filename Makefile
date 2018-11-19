compile:
	javac -cp ".:ojdbc6.jar" -d . JDBCExample.java
	javac -cp ".:ojdbc6.jar" -d . DatabaseHelper.java
run:	
	java -cp ".:ojdbc6.jar" JDBCExample
clean:
	rm JDBCExample.class
