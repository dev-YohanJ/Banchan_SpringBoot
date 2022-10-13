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
values(sell_listId_seq.nextval, 3, 'admin');

select * from sell_list;

select *
from item
inner join sell_list
on item.id = sell_list.item_id
where member_id = 'admin'