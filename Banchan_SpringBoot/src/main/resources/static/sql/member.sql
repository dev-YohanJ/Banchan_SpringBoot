drop table member cascade constraints;
create table member(
   id        	varchar2(20) primary key,
   password		varchar2(60),
   name      	varchar2(10),
   phone    	varchar2(20),
   email      	varchar2(30),
   address      varchar2(50),
   picture      varchar2(50) default 'basicpic',
   rating      	number,
   intro      	varchar2(200),
   nickname   	varchar2(20)
);

insert into member 
values('admin', '1', '홍길동', '01012341234', 'admin@naver.com', '서울시 종로구', 'basicpic', 0, '자기소개', '관리자');

insert into member 
values('aaaaa', '1', '이름1', '01012341234', 'email1@naver.com', '서울시 종로구', 'basicpic', 0, '자기소개', '유저1');

insert into member 
values('bbbbb', '1', '이름2', '01012341234', 'email2@naver.com', '서울시 종로구', 'basicpic', 0, '자기소개', '유저2');

insert into member 
values('ccccc', '1', '이름3', '01012341234', 'email3@naver.com', '서울시 종로구', 'basicpic', 0, '자기소개', '유저3');

select * from member;