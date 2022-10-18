drop table sell_list cascade constraints;

create table sell_list(
	id			number 		primary key,
	item_id		number 		references item(id) on delete cascade,
	member_id 	varchar2(20) 	references member(id) on delete cascade
);

drop sequence sell_listId_seq;
create sequence sell_listId_seq;

insert into sell_list 
values(sell_listId_seq.nextval, 1, 'admin');

insert into sell_list 
values(sell_listId_seq.nextval, 2, 'admin');

insert into sell_list 
values(sell_listId_seq.nextval, 21, 'admin');

delete from SELL_LIST
where item_id = 21;

update ITEM
set
status = 1
where id = 22;

select * from sell_list;

