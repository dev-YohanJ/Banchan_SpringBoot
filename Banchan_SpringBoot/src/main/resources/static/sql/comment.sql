drop table comments cascade constraints;
create table comments(
	num			number primary key,
	id			varchar2(30) references member(id) on delete cascade,
	content		varchar2(200),
	reg_date	date,
	board_num	number references qna(BOARD_NUM) on delete cascade
);

drop sequence com_seq;
create sequence com_seq;

insert into comments
values(com_seq.nextval, 'admin', '답변 내용', sysdate, '1');

select * from comments;