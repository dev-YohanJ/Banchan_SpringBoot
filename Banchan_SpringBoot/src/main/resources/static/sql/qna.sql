drop table qna cascade constraints purge;
CREATE TABLE qna(
    BOARD_NUM 		NUMBER,			--글 번호
    BOARD_ID		VARCHAR2(20) references member(id) on delete cascade, -- 글쓴이 닉네임
    BOARD_PASS 		VARCHAR2(30),	--비밀번호
    BOARD_SUBJECT 	VARCHAR2(300),	--제목
    BOARD_CONTENT 	VARCHAR2(4000),	--내용
    BOARD_READCOUNT NUMBER,		--글의 조회수
    BOARD_DATE 		DATE,		--글의 작성 날짜
    PRIMARY KEY(BOARD_NUM)
);

drop sequence qna_seq;
create sequence qna_seq;

insert into qna values(qna_seq.nextval, 'aaaaa', '1', '질문1', '질문 내용', 0, sysdate);
insert into qna values(qna_seq.nextval, 'bbbbb', '1', '질문2', '질문 내용', 0, sysdate);
insert into qna values(qna_seq.nextval, 'ccccc', '1', '질문3', '질문 내용', 0, sysdate);


select * from qna;
