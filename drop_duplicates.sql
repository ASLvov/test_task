drop table if exists public.client_balance;

CREATE TABLE public.client_balance (
	client_id int8 NOT NULL,
	client_name varchar NOT NULL,
	client_balance_date timestamp NOT NULL,
	client_balance_value numeric NOT NULL
);

insert into public.client_balance(client_id, client_name, client_balance_date, client_balance_value) values 
(1, 'klient_1', to_timestamp('20-05-2022 18:50:00', 'dd-mm-yyyy hh24:mi:ss'), '1500.00'),
(1, 'klient_1', to_timestamp('20-05-2022 18:50:00', 'dd-mm-yyyy hh24:mi:ss'), '1500.00'),
(1, 'klient_1', to_timestamp('20-05-2022 17:50:00', 'dd-mm-yyyy hh24:mi:ss'), '1000.00'),
(2, 'klient_2', to_timestamp('20-05-2022 17:50:00', 'dd-mm-yyyy hh24:mi:ss'), '500.00'),
(2, 'klient_2', to_timestamp('20-05-2022 18:50:00', 'dd-mm-yyyy hh24:mi:ss'), '1000.00'),
(2, 'klient_2', to_timestamp('20-05-2022 18:50:00', 'dd-mm-yyyy hh24:mi:ss'), '1000.00');
	
with del as 
	(delete from public.client_balance cb2)
	insert into public.client_balance 
		select distinct * from public.client_balance cb 
			order by client_id, client_name, client_balance_date, client_balance_value;