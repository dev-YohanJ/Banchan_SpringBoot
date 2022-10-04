drop table notice cascade constraints purge;
CREATE TABLE notice(
    BOARD_NUM 		NUMBER,			--글 번호
    BOARD_PASS 		VARCHAR2(30),	--비밀번호
    BOARD_SUBJECT 	VARCHAR2(300),	--제목
    BOARD_CONTENT 	VARCHAR2(4000),	--내용
    BOARD_READCOUNT NUMBER,		--글의 조회수
    BOARD_DATE 		DATE DEFAULT SYSDATE,		--글의 작성 날짜
    PRIMARY KEY(BOARD_NUM)
);

drop sequence notice_seq;
create sequence notice_seq;

insert into notice values(notice_seq.nextval, '1', '공지1', '공지 내용', 0, sysdate);
insert into notice values(notice_seq.nextval, '1', '공지2', '공지 내용', 0, sysdate);
insert into notice values(notice_seq.nextval, '1', '공지3', '공지 내용', 0, sysdate);


select * from notice;
