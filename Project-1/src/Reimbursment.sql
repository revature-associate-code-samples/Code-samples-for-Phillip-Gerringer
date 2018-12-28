create table ROLE 
(	ROLE_ID number not null enable, 
	NAME varchar2(20 byte) not null enable, 
    constraint ROLE_PK primary key (ROLE_ID)
    using index pctfree 10 initrans 2 maxtrans 255 
);
/
create sequence ROLE_ID_SEQ  minvalue 1 maxvalue 9999999999999999999999999999 
increment by 1 start with 1 cache 20 noorder  nocycle  nopartition;
/
create or replace trigger ROLE_ID_TRIG 
before insert on ROLE
for each row 
begin
  select ROLE_ID_SEQ.nextval into :new.ROLE_ID from dual;
end;
/
insert into ROLE (NAME) values ('Employee');
insert into ROLE (NAME) values ('Manager');
insert into ROLE (NAME) values ('Administrator');
/
commit;
/
create table TYPE 
(	TYPE_ID number not null enable, 
	NAME varchar2(20 byte) not null enable, 
    constraint TYPE_PK primary key (TYPE_ID)
    using index pctfree 10 initrans 2 maxtrans 255 
);
/
create sequence TYPE_ID_SEQ  minvalue 1 maxvalue 9999999999999999999999999999 
increment by 1 start with 1 cache 20 noorder  nocycle  nopartition;
/
create or replace trigger TYPE_ID_TRIG 
before insert on TYPE
for each row 
begin
  select TYPE_ID_SEQ.nextval into :new.TYPE_ID from dual;
end;
/
insert into TYPE (NAME) values ('Lodging');
insert into TYPE (NAME) values ('Food');
insert into TYPE (NAME) values ('Travel');
insert into TYPE (NAME) values ('Training');
insert into TYPE (NAME) values ('Other');
/
commit;
/
create table STATUS 
(	STATUS_ID number not null enable, 
	NAME varchar2(20 byte) not null enable, 
    constraint STATUS_PK primary key (STATUS_ID)
    using index pctfree 10 initrans 2 maxtrans 255 
);
/
create sequence STATUS_ID_SEQ  minvalue 1 maxvalue 9999999999999999999999999999 
increment by 1 start with 1 cache 20 noorder  nocycle  nopartition;
/
create or replace trigger STATUS_ID_TRIG 
before insert on STATUS
for each row 
begin
  select STATUS_ID_SEQ.nextval into :new.STATUS_ID from dual;
end;
/
insert into STATUS (NAME) values ('Submitted');
insert into STATUS (NAME) values ('Approved');
insert into STATUS (NAME) values ('Denied');
/
commit;
/
create table EMPLOYEE
(	EMPLOYEE_ID number not null enable, 
	FIRST_NAME varchar2(100 byte) not null enable, 
	LAST_NAME varchar2(100 byte) not null enable, 
	USER_NAME VARCHAR2(50 byte) not null enable, 
	PASSWORD varchar2(50 byte) not null enable, 
	EMAIL varchar2(150 byte) not null enable, 
	ROLE_ID number not null enable, 
    constraint EMPLOYEE_PK primary key (EMPLOYEE_ID)
    using index pctfree 10 initrans 2 maxtrans 255,
    constraint FK_EMPLOYEE_ROLE_ID foreign key (ROLE_ID)
    references ROLE (ROLE_ID) enable
);
/
create sequence EMPLOYEE_ID_SEQ  minvalue 1 maxvalue 9999999999999999999999999999 
increment by 1 start with 1 cache 20 noorder  nocycle  nopartition;
/
create or replace trigger EMPLOYEE_ID_TRIG 
before insert on EMPLOYEE
for each row 
begin
  select EMPLOYEE_ID_SEQ.nextval into :new.EMPLOYEE_ID from dual;
end;
/
insert into employee (first_name, last_name, role_id, password, user_name, email)
values ('Phil', 'Gerringer', 3, 'password', 'pgerringer', 'pgerringer@gmail.com');
/
commit;
/
create table REIMBURSEMENT
(	REIMBURSEMENT_ID number not null enable, 
	AMOUNT number not null enable, 
	SUBMIT_DATE timestamp not null enable,
    RESOLVE_DATE timestamp,
    DETAIL varchar2(250 byte),
    ATTACHMENT blob,
    SUBMITTER_ID number not null enable,
    RESOLVER_ID number, 
    STATUS_ID number not null enable,
    TYPE_ID number not null enable,
	constraint REIMBURSEMENT_PK primary key (REIMBURSEMENT_ID)
    using index pctfree 10 initrans 2 maxtrans 255,
    constraint FK_SUBMITTER_ID foreign key (SUBMITTER_ID)
    references EMPLOYEE (EMPLOYEE_ID) enable, 
    constraint FK_RESOLVER_ID foreign key (RESOLVER_ID)
    references EMPLOYEE (EMPLOYEE_ID) enable,
    constraint FK_STATUS_ID foreign key (STATUS_ID)
    references STATUS (STATUS_ID) enable,
    constraint FK_TYPE_ID foreign key (TYPE_ID)
    references TYPE (TYPE_ID) enable
);
/
create sequence REIMBURSEMENT_ID_SEQ  minvalue 1 maxvalue 9999999999999999999999999999 
increment by 1 start with 1 cache 20 noorder  nocycle  nopartition;
/
create or replace trigger REIMBURSEMENT_INSERT_TRIG 
before insert on REIMBURSEMENT
for each row 
begin
  select REIMBURSEMENT_ID_SEQ.nextval into :new.REIMBURSEMENT_ID from dual;
  select current_timestamp into :new.SUBMIT_DATE from dual;
end;
/
create or replace trigger REIMBURSEMENT_UPDATE_TRIG 
before update on REIMBURSEMENT
for each row 
begin
  select current_timestamp into :new.RESOLVE_DATE from dual;
end;
/
commit;
/