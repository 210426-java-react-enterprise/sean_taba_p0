select * from project0.customers c ;

delete from project0.customers ;

select * from project0.credentials c ;

delete from project0.credentials ;

select * from project0.addresses a ;

drop table project0.credentials ;

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
city varchar(10) not null,
state varchar(10) not null,
zip varchar(5) not null,
customer_ssn varchar(12) not null,

primary key(customer_ssn),
foreign key(customer_ssn)
references project0.customers(ssn)
on delete cascade
);


