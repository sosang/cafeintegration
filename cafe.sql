INSERT INTO item(item_no, item_name, origin, grade, processing, roasting_date, roasting_level, item_info, photo, price, total_product, def_exchange, def_refund) VALUES(item_seq.nextval,'s','s',1,'s',sysdate,'s','s','s',123,123,0,0);
select * from board_faq
alter table board_faq modify(content_faq clob);
SELECT * FROM member
SELECT user_passwd from member where user_email='gg@gmail.com' and passwd_inquiry='아버지 성함은?' and passwd_answer='김알지';
insert into member values('admin', '123ad123min123', '글쓰기용어드민생성사용안함','asdf','영자','010','1313','3131','153704','서울시','4층',0,0,0,0,0,'ha',sysdate);
SELECT  DBMS_LOB.SUBSTR(content_rev, 1000, 1) AS CONT_SUB   -- 1000 개까지 출력(문자열) 
        , DBMS_LOB.INSTR(content_rev, 'test', 1, 1) AS CONT_INS   -- 검색 문구의 위치 출력(숫자)
  FROM board_reviews
  where bd_no_rev = 2;
  WHERE DBMS_LOB.INSTR(content_rev, 'test', 1, 1) > 0             -- 검색 문구가 존재할때
;
select * from item
select * from board_reviews
create table member(
	user_email varchar2(50) primary key,
	user_passwd varchar2(30),
	passwd_inquiry varchar2(45),
	passwd_answer varchar2(30),
	user_alias varchar2(30)  unique,
	user_phone1 varchar2(6) ,
	user_phone2 varchar2(8),
	user_phone3 varchar2(8),
	user_postcode varchar2(7),
	user_address1 varchar2(200),
	user_address2 varchar2(200),
	user_level number(2) default 0,
	user_point number(6) default 0,
	user_num_of_article number(5) default 0,
	user_num_of_comments number(8) default 0,
	user_num_of_practice number(4) default 0,
	user_facebook_email varchar2(50) default null,
	join_date date default sysdate
);

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
drop table board_reviews cascade constraint
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


create table board_faq(
	bd_no_faq number(4) primary key,
	title_faq varchar2(300) not null,
	content_faq varchar2(4000) not null
);

create sequence board_faq_seq
increment by 1
start with 1;

create table board_notice(
	bd_no_ntc number(4) primary key,
	title_ntc varchar2(300) not null,
	content_ntc varchar2(300) not null,
	date_ntc date default sysdate not null,
	count_ntc number(4) default 0
);

create sequence board_notice_seq
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


create sequence item_seq
increment by 1
start with 1;




create table purchase(
	pur_no number(4) primary key,
	user_email varchar2(50) not null,
	time_of_purchase date default sysdate,
	pur_canceltime date default sysdate,
	pur_sent number(1) default 0,
	receiver varchar2(50) not null,
	rec_phone varchar2(50) not null,
	rec_addr varchar2(200) not null,
	rec_postcode varchar2(6) not null,
	remarks varchar2(300),
	foreign key (user_email) references member (user_email) ON DELETE CASCADE

);

create sequence purchase_seq
increment by 1
start with 1;

create table purchase_line(
	pur_line_no number(4),
	pur_no number(4) not null,
	item_no number(4) not null,
	num_of_product number(3) not null,
	
	foreign key (pur_no) references purchase (pur_no) ON DELETE CASCADE,
	foreign key (item_no) references item (item_no) ON DELETE CASCADE
	);

	create sequence pur_line_seq
	increment by 1
	start with 1;


CREATE TABLE postcode
(
zipcode varchar2(20),
SiDo varchar2(30),
SiGunGu varchar2(30),
eupMyeonDong varchar2(30),
Ri varchar2(30),
Beonji varchar2(30),
Bldg varchar2(100)
);

create table cart(
	cart_num number(4) primary key,
	user_email varchar(50) not null,
	item_no number(4) not null,
	cart_num_of_product number(4),
	cart_sub_total number(8),
	foreign key (item_no) references item (item_no) ON DELETE CASCADE,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE
);

create sequence cart_seq
increment by 1
start with 1;

create table experience(
	exp_no number(4) primary key,
	exp_title varchar2(100) not null,
	exp_target_applicant number(3) not null,
	exp_date date default sysdate,
	exp_fee number(7) not null,
	pay_check number(1) default 0 not null
);

create sequence experience_seq
increment by 1
start with 1;

create table reservation(
	res_no number(4) primary key,
	user_email varchar2(50) not null,
	exp_no number(4) not null,
	res_table_num varchar2(3) not null,
	res_time date default sysdate not null,
	res_cancel_time date default sysdate not null,
	res_current_applicant number(3),
	res_max_applicant number(3),
	res_flag number(1) default 0,
	res_index_detail varchar2(2000) not null,
	foreign key (exp_no) references experience (exp_no) ON DELETE CASCADE,
	foreign key (user_email) references member (user_email) ON DELETE CASCADE
);


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



create table adminstrator(

	admin_email varchar2(50) primary key,

	admin_grade number(1) default 0 not null,

	admin_passwd varchar2(30) not null

);

--테스트용 
insert into adminstrator values('admin','1','admin');

INSERT INTO member VALUES('cafe4','cafe4','cafe4.0','010','7777','7777','123456','가산디지털단지', '4층',1,500,3,7,12, ‘facebook’ ,sysdate);
insert into ITEM values(item_seq.nextval,'루티나','에티오피아',2,'연하게',sysdate,'시나몬','연하고 시나몬 맛 나는 커피','bean.jpg',4000,10,0,0);
insert into ITEM values(item_seq.nextval,'시다모','에티오피아',2,'연하게',sysdate,'과일향','연한 꽃내음 나는 커피','bean.jpg',7000,10,0,0);



select * from user_sequences;
select * from user_tables;
select * from purchase;
select * from purchase_seq;
select * from purchase_line;
select * from member;
select * from item;
select * from postcode where eupmyeondong like '반송%';


drop table adminstrator;
drop table cart;
drop table purchase_line;
drop table purchase;
drop table save_file_path;
drop table item;
drop table board_notice;
drop table board_faq;
drop table board_reviews_comments;
drop table board_reviews CASCADE CONSTRAINTS;
drop table BOARD_QA_COMMENTS;
drop table board_qa;
drop table recommend_recorder;
drop table reservation;
drop table experience;
drop table member cascade constraint;



drop sequence board_faq_seq;
drop sequence board_notice_seq;
drop sequence board_qa_comments_seq;
drop sequence board_qa_seq;
drop sequence board_reviews_comments_seq;
drop sequence board_reviews_seq;
drop sequence recommend_recorder_seq;
drop sequence save_file_path_seq;
drop sequence cart_seq;
drop sequence item_seq;
drop sequence purchase_seq;
drop sequence experience_seq;
drop sequence pur_line_seq;
drop sequence reservation_seq;

--member테이블 test명 

--새로 생성시 기본 테스트용 추가 테이블 꼭 추가 시킬것!
select * from member;
update member user_email set sdf;
INSERT INTO member VALUES('sdf@sdf.com','sdfa','어머니 성함은?','김여사','솔솔이', '010','7788','7788','123456','서울특별시 중랑구 면목동', '면목사거리 공공칠빵집',0,0,0,0,0, null , sysdate);
INSERT INTO member VALUES('gg@gmail.com','gdav00','아버지 성함은?','김알지','뱀발', '010','7766','7734','625456','경상북도 경주시 알동', '계림 알탕',0,0,0,0,0, null , sysdate);
INSERT INTO member VALUES('nn@naver.com','sasa00','아버지 성함은?','박혁거세','똬리', '010','7711','7734','625446','경상북도 경주시 천막동', '사로6촌',0,0,0,0,0, null , sysdate);

insert into ITEM values(item_seq.nextval,'시다모','에티오피아',2,'연하게',sysdate,'과일향','연한 꽃내음 나는 커피','bean.jpg',7000,10,0,0);

********************************************************************************************************

SELECT bd_no_rev bdNoRev, user_email userEmail, user_alias userAlias, title_rev titleRev, DBMS_LOB.SUBSTR(content_rev, 1000, 1) AS contentRev, count_rev countRev, recommend_rev recommendRev, ref_rev refRev, re_step reStep, re_level reLevel, date_rev dateRev, user_ip userIp FROM board_reviews WHERE bd_no_rev = 2;
select * from board_reviews where bd_no_rev = 2;
SELECT * FROM board_reviews_comments WHERE bd_no_rev = 2 ORDER BY bd_no_rev_comments

delete member where user_email = 'sdf3@sdf.com';


==================  월별 가입자 수 확인용 View 생성 ===================
GRANT CREATE VIEW TO scott;


					
