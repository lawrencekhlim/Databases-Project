CREATE TABLE Customer ( TID			INTEGER,
				    	name		CHAR(30),
				    	address		CHAR(40),
				   	    PIN			CHAR(4) NOT NULL,
				    	PRIMARY KEY (TID));

CREATE TABLE Account (  account_id          INTEGER,
                        deletedDate         DATE,
                        account_type        INTEGER,
                        moneyVal            FLOAT NOT NULL  CHECK (moneyVal >= 0),
                        primOwner           INTEGER NOT NULL,
                        annualRate          FLOAT           CHECK (annualRate >= 0),
                        PRIMARY KEY (account_id),
                        FOREIGN KEY (primOwner) REFERENCES Customer(TID));

CREATE TABLE CoOwner (  TID			INTEGER,
					    account_id	INTEGER,
					    PRIMARY KEY(TID, account_id),
					    FOREIGN KEY (TID) REFERENCES Customer ON DELETE CASCADE,
					    FOREIGN KEY (account_id) REFERENCES Account ON DELETE CASCADE);

CREATE TABLE Transaction ( transaction_id 	INTEGER,
						   transDate 		DATE,
						   moneyTrans		FLOAT NOT NULL      CHECK (moneyTrans>=0),
						   transType 		INTEGER NOT NULL,
						   incrAcctID		INTEGER,
						   decrAcctID		INTEGER,
						   PRIMARY KEY(transaction_id),
						   FOREIGN KEY(incrAcctID) REFERENCES Account(account_id) ON DELETE SET NULL,
						   FOREIGN KEY(decrAcctID) REFERENCES Account(account_id) ON DELETE SET NULL);
