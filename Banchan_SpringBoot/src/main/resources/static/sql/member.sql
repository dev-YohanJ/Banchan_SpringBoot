drop table member cascade constraints;
create table member(
   id        	varchar2(20) primary key,
   password		varchar2(60),
   name      	varchar2(50),
   phone    	varchar2(20),
   email      	varchar2(50),
   address      varchar2(200),
   picture      varchar2(50) default 'basicpic',
   pic_original	varchar2(50),
   rating      	number,
   intro      	varchar2(1000),
   nickname   	varchar2(50)
);

insert into member 
values('admin', '1', '홍길동', '01012341234', 'admin@naver.com', '12345/서울시 종로구/우리집', 'basicpic', 'basicpic1', 0, '자기소개', '관리자');

insert into member 
values('aaaaa', '1', '이름1', '01012341234', 'email1@naver.com', '서울시 종로구', 'basicpic', 'basicpic2', 0, '자기소개', '유저1');

insert into member 
values('bbbbb', '1', '이름2', '01012341234', 'email2@naver.com', '서울시 종로구', 'basicpic', 'basicpic3', 0, '자기소개', '유저2');

insert into member 
values('ccccc', '1', '이름3', '01012341234', 'email3@naver.com', '서울시 종로구', 'basicpic', 'basicpic4', 0, '자기소개', '유저3');

select * from member;

delete from MEMBER where id = 'admin';