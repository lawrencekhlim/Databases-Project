compile:
	javac -cp ".:ojdbc6.jar" -d . JDBCExample.java
run:	
	java -cp ".:ojdbc6.jar" JDBCExample
clean:
	rm JDBCExample.class
