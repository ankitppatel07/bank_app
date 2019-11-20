drop table credentials;
drop table transac_log;
drop table accounts;
drop table cust_info;

create table cust_info(
	acct_id int NOT NULL,
	cust_name varchar(30) not null,
	city varchar(30),
	state varchar(10),
	country varchar(10) not null,
	phone_no int not null,
	password varchar(30) not null,
	primary key (acct_id) 
);	

create table credentials(
	acct_id int not null,
	password varchar(30) not null,
	FOREIGN KEY (acct_id) 
        REFERENCES cust_info(acct_id)
);

create table accounts(
	acct_id int NOT NULL,
	balance int,
	acct_status varchar(30) not null,
	FOREIGN KEY (acct_id) 
        REFERENCES cust_info(acct_id)
);	

create table transac_log(
	acct_id int not null,
	transac_type varchar(30),
	transac_status varchar(30),
	init_bal int,
	final_bal int,
	FOREIGN KEY (acct_id) 
        REFERENCES cust_info(acct_id)
);	

insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) values(1, 'Ankit', 'Binghamton', 'NY', 'US', 12345, '123');
insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) values(2, 'Sourabh', 'Hartford', 'MA', 'US', 12346, 'a32q98n');
insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) values(3, 'Aseem', 'Columbus', 'IN', 'US', 12347, '1gf6b');
insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) values(4, 'Uday', 'Nashville', 'TN', 'US', 12348, '6yyf83w47g');
insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) values(5, 'Akshay', 'Oregon', 'CA', 'US', 12349, '8gbsw43r');
insert into cust_info (acct_id, cust_name, city, state, country, phone_no, password) values(6, 'Will', 'Pittsburgh', 'PA', 'US', 12350, '93h8fbb');