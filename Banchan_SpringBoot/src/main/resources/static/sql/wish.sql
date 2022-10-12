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

select * from wish;