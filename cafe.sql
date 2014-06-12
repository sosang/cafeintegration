SELECT file_path from save_file_path WHERE item_no = 50
select * from SAVE_FILE_PATH
select constraint_name from user_constraints where table_name='MEMBER';
alter table member drop constraint SYS_C0011855;
alter table member drop constraint SYS_C0011856;
alter table member drop constraint SYS_C0011857;
alter table member drop constraint SYS_C0011858;
alter table member drop constraint SYS_C0011859;
alter table member drop constraint SYS_C0011860;
alter table member drop constraint SYS_C0011861;
alter table member drop constraint SYS_C0011862;



select * from member
select * from member where substr(to_char(sysdate,'YYYYMMDD'),1,8) = substr(to_char(join_date,'YYYYMMDD'),1,8) ;
create table member(
	user_email varchar2(50) primary key,
	user_passwd varchar2(30) ,
	user_alias varchar2(30)  unique not null ,
	user_phone1 varchar2(6) ,
	user_phone2 varchar2(8) ,
	user_phone3 varchar2(8) ,
	user_postcode varchar2(7) ,
	user_address1 varchar2(200) ,
	user_address2 varchar2(200) ,
	user_level number(2) default 0,
	user_point number(6) default 0,
	user_num_of_article number(5) default 0,
	user_num_of_comments number(8) default 0,
	user_num_of_practice number(4) default 0,
	user_facebook_key varchar2(30),
	join_date date default sysdate
);
INSERT INTO member VALUES('cafe4','cafe4','cafe4.0','010','7777','7777','123-456','가산디지털단지', '4층',1,500,3,7,12, sysdate);

select substr(to_char(sysdate,'YYYYMMDD'),1,8) sys from dual;
drop table member cascade constraint;
select * from member;
alter table member rename column user_num_of_reply TO user_num_of_comments;
alter table member add(join_date date default sysdate);
create table board_qa(
	bd_no_qa number(4) primary key,
	user_email varchar2(50) not null,
	user_alias varchar2(30) not null,
	title_qa varchar2(300) not null,
	content_qa varchar2(4000) not null,
	count_qa number(4) default 0,
	ref_qa number(4) default 0,
	re_step number(4) default 0,
	re_level number(4) default 0,
	date_qa date default sysdate,
	user_ip varchar2(15) not null,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE,
	foreign key (user_alias) references member (user_alias) ON DELETE CASCADE
);

create sequence board_qa_seq
increment by 1
start with 1;

create table board_qa_comments(
	bd_no_qa_comments number(4) primary key,
	bd_no_qa number(4) not null,
	user_email varchar2(50) not null,
	user_alias varchar2(30) not null,
	bd_qa_comments_content varchar2(4000) not null,
	bd_qa_comments_date date default sysdate,
	bd_qa_comments_ip varchar2(15) not null,
	foreign key (bd_no_qa) references board_qa (bd_no_qa) ON DELETE CASCADE,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE,
	foreign key (user_alias) references member (user_alias) ON DELETE CASCADE
);

create sequence board_qa_comments_seq
increment by 1
start with 1;

create table board_reviews(
	bd_no_rev number(4) primary key,
	user_email varchar2(50) not null,
	user_alias varchar2(30) not null,
	title_rev varchar2(300) not null,
	content_rev clob not null,
	count_rev number(4) default 0,
	recommend_rev number(3) default 0,
	ref_rev number(4) default 0,
	re_step number(4) default 0,
	re_level number(4) default 0,
	date_rev date default sysdate,
	user_ip varchar2(15) not null,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE,
	foreign key (user_alias) references member (user_alias) ON DELETE CASCADE
);

create sequence board_reviews_seq
increment by 1
start with 1;

create table board_reviews_comments(
	bd_no_rev_comments number(4) primary key,
	bd_no_rev number(4) not null,
	user_email varchar2(50) not null,
	user_alias varchar2(30) not null,
	bd_rev_comments_content varchar2(4000) not null,
	bd_rev_comments_date date default sysdate,
	bd_rev_comments_ip varchar2(15) not null,
	foreign key (bd_no_rev) references board_reviews (bd_no_rev) ON DELETE CASCADE,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE,
	foreign key (user_alias) references member (user_alias) ON DELETE CASCADE
);

create sequence board_reviews_comments_seq
increment by 1
start with 1;


create table board_faq(
	bd_no_faq number(4) primary key,
	title_faq varchar2(300) not null,
	content_faq varchar2(4000) not null
);

create sequence board_faq_seq
increment by 1
start with 1;

insert into board_faq values(board_faq_seq.nextval,'연습공지사항 없어서 올림4','아코디언 내용이 보이니까 신기하죠? ㅎ ㅔㅎ ㅔ');
create table adminstrator(
	admin_email varchar2(50) primary key,
	admin_grade number(1) default 0 not null,
	admin_passwd varchar2(30) not null
);
insert into adminstrator values('admin','1','admin');


create table board_notice(
	bd_no_ntc number(4) primary key,
	title_ntc varchar2(300) not null,
	content_ntc varchar2(4000) not null,
	date_ntc date default sysdate not null,
	count_ntc number(4) default 0
);

create sequence board_notice_seq
increment by 1
start with 1;



create table recommend_recorder(
	rec_rec_no number(4) primary key,
	bd_no_rev number(4) not null,
	user_email varchar2(50) not null,
	user_alias varchar2(30) not null,
	rec_rec_date date default sysdate,
	user_ip varchar2(15) not null,
	foreign key (bd_no_rev) references board_reviews (bd_no_rev) ON DELETE CASCADE,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE,
	foreign key (user_alias) references member (user_alias) ON DELETE CASCADE
);

create sequence recommend_recorder_seq
increment by 1
start with 1;
drop table save_file_path;
create table save_file_path(
	save_file_path_no number(4) primary key,
	file_path varchar2(100),
	file_size number(8),
	bd_no_rev number(4),
	item_no number(4),
	foreign key (bd_no_rev) references board_reviews (bd_no_rev) ON DELETE CASCADE,
	foreign key (item_no) references item (item_no) ON DELETE CASCADE
);

create sequence save_file_path_seq
increment by 1
start with 1;

create table item(
	item_no number(4) primary key,
	item_name varchar2(40) not null,
	origin varchar2(20) not null,
	grade varchar2(15) not null,
	processing varchar2(15) not null,
	roasting_date date not null,
	roasting_level varchar2(15) not null,
	item_info varchar2(4000) not null,
	price number(7) not null,
	total_product number(4) not null,
	def_exchange number(3) default 0,
	def_refund number(3) default 0
);

drop table item cascade constraint;
select * from save_file_path ;


drop table reservation;
drop table experience;
drop table cart;
drop table adminstrator;
drop table post;
drop table purchase_line;
drop table purchase;
drop table item;
drop table board_notice;
drop table board_faq;

drop table board_reviews_comments;
drop table save_file_path;
drop table recommend_recorder;
drop table board_reviews CASCADE CONSTRAINTS;

drop table board_qa;
drop table member;

drop sequence board_faq_seq;
drop sequence board_notice_seq;
drop sequence board_qa_seq;
drop sequence board_revies_seq;
drop sequence cart_seq;
drop sequence experience_seq;
drop sequence item_seq;
drop sequence pur_line_seq;
drop sequence purchase_seq;
drop sequence reservation_seq;

