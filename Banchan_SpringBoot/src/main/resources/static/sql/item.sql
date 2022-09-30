drop table item cascade constraints;
create table item(
   id        		number primary key,
   name      		varchar2(30),
   seller    		varchar2(20) references member(id) on delete cascade,
   price      		number,
   image      		varchar2(50),
   description      varchar2(4000),
   location      	varchar2(50),
   allergy      	varchar2(50),
   readcount   		number,
   regdate 			date default sysdate,
   status 			number
);

drop sequence itemId_seq;
create sequence itemId_seq;

insert into item 
values(itemId_seq.nextval, '반찬1', 'aaaaa', 1000, 'itemImage', '반찬설명1', '강남구 역삼동', '새우', 0, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '반찬2', 'bbbbb', 2000, 'itemImage', '반찬설명2', '종로구 봉익동', '새우', 0, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '반찬3', 'ccccc', 3000, 'itemImage', '반찬설명3', '종로구 봉익동', '새우', 0, sysdate, 0);

select * from item;