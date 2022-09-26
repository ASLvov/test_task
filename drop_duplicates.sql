DROP TABLE client_balance;

CREATE TABLE client_balance (
	client_id NUMBER NOT NULL,
	client_name VARCHAR(255) NOT NULL,
	client_balance_date TIMESTAMP NOT NULL,
	client_balance_value NUMBER NOT NULL
);

INSERT INTO client_balance(client_id, client_name, client_balance_date, client_balance_value) VALUES 
(1, 'klient_1', TO_TIMESTAMP('20-MAY-22 06:50:00 PM'), '1500.00');
INSERT INTO client_balance(client_id, client_name, client_balance_date, client_balance_value) VALUES 
(1, 'klient_1', TO_TIMESTAMP('20-MAY-22 06:50:00 PM'), '1500.00');
INSERT INTO client_balance(client_id, client_name, client_balance_date, client_balance_value) VALUES 
(1, 'klient_1', TO_TIMESTAMP('20-MAY-22 05:50:00 PM'), '1000.00');
INSERT INTO client_balance(client_id, client_name, client_balance_date, client_balance_value) VALUES 
(2, 'klient_2', TO_TIMESTAMP('20-MAY-22 05:50:00 PM'), '500.00');
INSERT INTO client_balance(client_id, client_name, client_balance_date, client_balance_value) VALUES 
(2, 'klient_2', TO_TIMESTAMP('20-MAY-22 06:50:00 PM'), '1000.00');
INSERT INTO client_balance(client_id, client_name, client_balance_date, client_balance_value) VALUES 
(2, 'klient_2', TO_TIMESTAMP('20-MAY-22 06:50:00 PM'), '1000.00');

SELECT cb.* FROM client_balance cb;

DELETE FROM client_balance cb
WHERE cb.ROWID IN
	(SELECT ROWID FROM
		(SELECT ROWID, ROW_NUMBER() OVER
			(PARTITION BY client_id, client_name, client_balance_date, client_balance_value
			    ORDER BY client_id, client_name, client_balance_date, client_balance_value) dup_row_num
		FROM client_balance) tbl
		WHERE tbl.dup_row_num > 1);
		
SELECT cb.* FROM client_balance cb;
