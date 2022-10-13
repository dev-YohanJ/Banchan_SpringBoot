drop table wish cascade constraints;

create table wish(
	id			number 		primary key,
	item_id		number 		references item(id) on delete cascade,
	member_id 	varchar2(20) 	references member(id) on delete cascade
);

drop sequence wishId_seq;
create sequence wishId_seq;

insert into wish 
values(wishId_seq.nextval, 1, 'admin');

insert into wish 
values(wishId_seq.nextval, 2, 'admin');

insert into wish 
values(wishId_seq.nextval, 3, 'admin');

insert into wish 
values(wishId_seq.nextval, 4, 'admin');

insert into wish 
values(wishId_seq.nextval, 5, 'admin');

insert into wish 
values(wishId_seq.nextval, 6, 'admin');

insert into wish 
values(wishId_seq.nextval, 7, 'admin');

insert into wish 
values(wishId_seq.nextval, 8, 'admin');

insert into wish 
values(wishId_seq.nextval, 9, 'admin');

insert into wish 
values(wishId_seq.nextval, 10, 'admin');

insert into wish 
values(wishId_seq.nextval, 11, 'admin');

insert into wish 
values(wishId_seq.nextval, 12, 'admin');

select * from wish;


select *
from item
inner join wish
on item.id = wish.item_id
where member_id = 'admin'