drop table buy_list cascade constraints;

create table buy_list(
	id			number 		primary key,
	item_id		number 		references item(id) on delete cascade,
	member_id 	varchar2(20) 	references member(id) on delete cascade
);

drop sequence buy_listId_seq;
create sequence buy_listId_seq;

insert into buy_list 
values(buy_listId_seq.nextval, 1, 'admin');

insert into buy_list
values(buy_listId_seq.nextval, 2, 'admin');

insert into buy_list
values(buy_listId_seq.nextval, 3, 'admin');

select * from buy_list;

delete from buy_list;