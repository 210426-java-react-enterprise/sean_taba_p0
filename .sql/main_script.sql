select * from project0.customers c ;

delete from project0.customers ;

select * from project0.credentials c ;

delete from project0.credentials ;

select * from project0.addresses a ;

drop table project0.addresses ;

select * from project0.accounts a ;

create table project0.credentials (
id serial not null,
user_name varchar(15) not null unique,
password varchar(50) not null,
customer_ssn varchar not null,

primary key(user_name),
foreign key(customer_ssn)
references project0.customers(ssn)
on delete cascade
);

drop table project0.addresses ;

create table project0.addresses (
id serial not null,
unit varchar(3),
street varchar(30) not null,
city varchar(15) not null,
state varchar(15) not null,
zip varchar(5) not null,
customer_ssn varchar(12) not null,

primary key(customer_ssn),
foreign key(customer_ssn)
references project0.customers(ssn)
on delete cascade
);

create table project0.accounts (
id serial not null,
user_name varchar(15) not null,
account varchar(10) not null,
customer_ssn varchar(12) not null,

foreign key(customer_ssn)
references project0.customers(ssn)
on delete cascade
);

insert into project0.accounts 
values
	(1,'seantaba','checking','478589658'),
	(2,'seantaba','saving','478589658'),
	(3,'seantaba','trust','478589658');



   select  customers.first_name,customers.last_name,customers.ssn,customers.email,customers.phone,
   addresses.unit,addresses.street,addresses.city,addresses.state,addresses.zip,
   credentials.user_name,credentials.password,
   accounts.account 
   from project0.customers
   join project0.addresses on project0.customers.ssn = project0.addresses.customer_ssn
   join project0.credentials on project0.customers.ssn = project0.credentials.customer_ssn
   join project0.accounts on project0.customers.ssn = project0.accounts.customer_ssn;

drop table project0.transactions 

create table project0.transactions (
id serial not null unique,
type varchar(10) not null,
amount decimal not null,
balance decimal not null,
account_number varchar(10) not null,
customer_ssn varchar(10) not null,

primary key(id),
foreign key(customer_ssn)
references project0.customers(ssn)
);

delete from project0.temp 

select  customers.first_name,customers.last_name,customers.ssn,customers.email,customers.phone,addresses.unit,addresses.street,addresses.city,addresses.state,addresses.zip,credentials.password 
from project0.credentials
join project0.addresses on project0.credentials.customer_ssn = project0.addresses.customer_ssn 
join project0.customers on project0.credentials.customer_ssn = project0.customers.ssn
where project0.credentials.user_name = 'jondoe';

create view myView as select * from project0.accounts a 

drop table temp cascade

create table accounts as table temp 


insert into project0.accounts (id,user_name,account,customer_ssn)
(select id,user_name,account,customer_ssn  from oldaccounts )

drop table oldaccounts 
