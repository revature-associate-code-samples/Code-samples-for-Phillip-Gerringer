create table acme_type (
    type_id number(10) primary key,
    name varchar2(36) unique not null
    );
/
create table acme_user (
    user_id number(10) primary key,
    first_name varchar2(36) not null,
    last_name varchar2(36) not null,
    login_id varchar2(36) unique not null,
    password varchar2(36) not null
    );
/
create table acme_account (
    account_id number(10) primary key,
    user_id number(10) not null,
    type_id number(10) not null,
    balance number(10,2) default 0 not null,
    CONSTRAINT "FK_TYPE_ID" FOREIGN KEY (type_id)
	  REFERENCES acme_type (type_id) ENABLE, 
	 CONSTRAINT "FK_USERID" FOREIGN KEY (user_id)
	  REFERENCES acme_user (user_id) ENABLE
    );
/    
create sequence acme_type_seq;
create sequence acme_user_seq;
create sequence acme_account_seq;
/
create or replace trigger acme_type_trig 
BEFORE insert on acme_type
for each row 
BEGIN
  select acme_type_seq.nextval into :new.type_id from dual;
END;
/

create or replace trigger acme_user_trig 
BEFORE insert on acme_user
for each row 
BEGIN
  select acme_user_seq.nextval into :new.user_id from dual;
END;
/

create or replace trigger acme_account_trig 
BEFORE insert on acme_account
for each row 
BEGIN
  select acme_account_seq.nextval into :new.account_id from dual;
END;
/

insert into acme_type (name) values('Checking');
insert into acme_type (name) values('Savings');
insert into acme_type (name) values('Credit');
/

create or replace procedure all_user 
(user_cur out sys_refcursor) as 
begin
  open user_cur for
    select user_id, first_name, last_name, login_id, password 
    from acme_user;
end;
/

commit;

