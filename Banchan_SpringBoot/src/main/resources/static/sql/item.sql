drop table item cascade constraints;
create table item(
   id        		number primary key,										
   name      		varchar2(30),
   seller    		varchar2(20) references member(id) on delete cascade,
   price      		number,
   image      		varchar2(300),
   original			varchar2(300),
   description      varchar2(4000),
   location      	varchar2(50),
   readcount   		number,
   regdate 			date default sysdate,
   status 			number
);

drop sequence itemId_seq;
create sequence itemId_seq;

insert into item 
values(itemId_seq.nextval, '해산물', 'aaaaa', 3000, '2022-10-21/bbs2022102117725537.jpg,2022-10-21/bbs20221021323662.jpg,', 'mussel.jpg', '쭈꾸미볶음이랑 홍합입니다. 안 비리고 맛있어요~', '서울시 종로구 종로4가', 2, sysdate, 1);

insert into item 
values(itemId_seq.nextval, '나물모둠', 'bbbbb', 2500, '2022-10-21/bbs2022102110106676.jpg,', 'banchan.jpg', '김치, 시금치 등등 묶어서 팝니다  연락주세요!', '서울시 송파구 문정동', 1, sysdate, 1);

insert into item 
values(itemId_seq.nextval, '멸치볶음 팔아요', 'ccccc', 1000, '2022-10-21/bbs2022102123609773.jpg,', 'Anchovy.jpg', '오늘 만든 멸치볶음입니다~', '서울시 종로구 혜화동', 5, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '생선, 제육볶음', 'aaaaa', 5000, '2022-10-21/bbs2022102145118145.jpg,2022-10-21/bbs2022102192070950.jpg,', 'jeyuk.jpg ', '각각 5000원에 팜', '서울시 강동구 천호동', 10, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '시금치무침!!!', 'aaaaa', 1000, '2022-10-21/bbs2022102184657823.jpg,', 'namul_1.jpg', '너무 많이 만들어서 팝니다!!!', '서울시 은평구 응암동', 4, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '김치 팔아요.', 'aaaaa', 10000, '2022-10-21/bbs2022102150241711.jpg,', 'namul_2.jpg', '김치 이것저것 팝니다.  문의주세요.', '서울시 마포구 합정동', 2, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '진짜 맛있는 돈까스', 'aaaaa', 3000, '2022-10-21/bbs2022102185451425.jpg,', 'pokr_cutlets.jpg', '직접 튀겼는데 진짜 맛있어요', '서울시 종로구 사직동', 3, sysdate, 0);

insert into item 
values(itemId_seq.nextval, '애호박전 호박전', 'aaaaa', 1000, '2022-10-21/bbs2022102180946181.jpg,', 'squash.jpg', '우와 맛있다 싸다', '서울시 노원구 공릉동', 4, sysdate, 0);


select * from item;

--delete from item where seller = 'admin';

