drop table market cascade constraints;
drop table maskrate cascade constraints;
drop table review cascade constraints;
drop table customer cascade constraints;
drop table local cascade constraints;
drop table category cascade constraints;

-- category Table Create SQL
CREATE TABLE category
(
    cateid    NUMBER          NULL, 
    shop      VARCHAR2(40)    NULL, 
    CONSTRAINT CATEGORY_PK PRIMARY KEY (cateid)
)
;

-- category Table Create SQL
CREATE TABLE local
(
    localid    NUMBER          NOT NULL, 
    ci         VARCHAR2(40)    NULL, 
    gu         VARCHAR2(40)    NULL, 
    CONSTRAINT LOCAL_PK PRIMARY KEY (localid)
)
;
-- category Table Create SQL
CREATE TABLE market
(
    marketid    NUMBER           NOT NULL, 
    cateid      NUMBER           NOT NULL, 
    name        VARCHAR2(40)     NULL, 
    address     VARCHAR2(100)     NULL, 
    tel         VARCHAR2(40)     NULL, 
    time        DATE             NULL, 
    localid     NUMBER           NULL, 
    img         VARCHAR2(100)    NULL, 
    CONSTRAINT MARKET_PK PRIMARY KEY (marketid)
)
;
ALTER TABLE market
    ADD CONSTRAINT FK_market_cateid_category_cate FOREIGN KEY (cateid)
        REFERENCES category (cateid)
;

ALTER TABLE market
    ADD CONSTRAINT FK_market_localid_local_locali FOREIGN KEY (localid)
        REFERENCES local (localid)
;

-- category Table Create SQL
CREATE TABLE customer
(
    tel         VARCHAR2(40)    NOT NULL, 
    name        VARCHAR2(40)    NOT NULL, 
    password    VARCHAR2(40)    NOT NULL, 
    id          VARCHAR2(40)    NOT NULL, 
    CONSTRAINT CUSTOMER_PK PRIMARY KEY (id)
)
;

-- category Table Create SQL
CREATE TABLE maskrate
(
    maskid      NUMBER    NOT NULL, 
    type1       NUMBER    NULL, 
    type2       NUMBER    NULL, 
    type3       NUMBER    NULL, 
    type4       NUMBER    NULL, 
    marketid    NUMBER    NULL, 
    CONSTRAINT MASKRATE_PK PRIMARY KEY (maskid)
)
;

ALTER TABLE maskrate
    ADD CONSTRAINT FK_maskrate_marketid_market_ma FOREIGN KEY (marketid)
        REFERENCES market (marketid)
;


-- category Table Create SQL
CREATE TABLE review
(
    reviewid    NUMBER           NOT NULL, 
    id          VARCHAR2(40)     NULL, 
    time        TIMESTAMP        NULL, 
    text        VARCHAR2(500)    NULL, 
    marketid    NUMBER           NULL, 
    maskid      NUMBER           NULL, 
    CONSTRAINT REVIEW_PK PRIMARY KEY (reviewid)
)
;

ALTER TABLE review
    ADD CONSTRAINT FK_review_marketid_market_mark FOREIGN KEY (marketid)
        REFERENCES market (marketid)
;

ALTER TABLE review
    ADD CONSTRAINT FK_review_maskid_maskrate_mask FOREIGN KEY (maskid)
        REFERENCES maskrate (maskid)
;

ALTER TABLE review
    ADD CONSTRAINT FK_review_id_customer_id FOREIGN KEY (id)
        REFERENCES customer (id)
;
alter table market modify(time varchar2(80));






INSERT INTO CUSTOMER VALUES( '01039502883', '���缱', '1234', 'JASON');
INSERT INTO CUSTOMER VALUES( '01066208420', '�����', '1004', 'KYONGAH');
INSERT INTO CUSTOMER VALUES( '01011112222', '������', '1004', 'HYUNWO');
INSERT INTO CUSTOMER VALUES( '01033334444', '��α�', '1004', 'MINGUEN');
INSERT INTO CUSTOMER VALUES( '01055556666', 'ä��ȯ', '1004', 'YOUNGHWAN');
INSERT INTO CUSTOMER VALUES( '01077778888', 'Ȳ�¿�', '1004', 'TAEWOOK');
INSERT INTO CUSTOMER values ('01077778888','�����','1004','DUHAN');
INSERT INTO CUSTOMER values ('01077778888','����','1004','GORAE');
INSERT INTO CUSTOMER values ('01077778888','�述��','1004','YEOMSO');
INSERT INTO CUSTOMER values ('01077778888','�赵��','1004','DORAN');
INSERT INTO CUSTOMER values ('01077778888','�ǿ���','1004','OHHYEON');
INSERT INTO CUSTOMER values ('01077778888','���¿�','1004','KIMTAE');
INSERT INTO CUSTOMER values ('01077778888','�뷮��','1004','NORYANG');
INSERT INTO CUSTOMER values ('01055554444','���׽�','1004','TESS');
INSERT INTO CUSTOMER values ('01054447555','��ȣ��','1004','HODONG');
INSERT INTO CUSTOMER values ('01054775411','�̼���','1004','SUNSIN');
INSERT INTO CUSTOMER values ('01087445741','��ι�','1004','DUBAK');

INSERT INTO CATEGORY VALUES ( 1, '������');
INSERT INTO CATEGORY VALUES ( 2, 'ī��');
INSERT INTO CATEGORY VALUES ( 3, '������');

INSERT INTO LOCAL VALUES (1, '�λ걤����', '�λ�����');

INSERT INTO MARKET VALUES (1, 2, '�������̺�����Ű', '�λ� �λ����� ������ 37����26',
'051-756-5115', '���� 12:00-19:00', 1, '../img/�������̺�����Ű.jpg');
INSERT INTO MARKET VALUES (2, 2, '����Ŀ�� ��������', '�λ� �λ����� ������� 209����15',
'051-807-4047', '���� 10:30-20:00', 1, '../img/����Ŀ��.jpg');
INSERT INTO MARKET VALUES (3, 2, '������̺�', '�λ� �λ����� �߾Ӵ�� 680����45-9 3��',
'051-809-0301', '���� 12:00-23:00', 1, '../img/������̺�.jpg');
INSERT INTO MARKET VALUES (4, 2, '����ũ', '�λ� �λ����� ������ 58����115',
'051-992-1113', '���� 12:00-23:00', 1, '../img/����ũ.jpg');
INSERT INTO MARKET VALUES (5, 2, '����Ŀ��', '�λ� �λ����� ������ 37����26',
'051-944-4952', '���� 10:00-23:00', 1, '../img/����Ŀ��.jpg');
INSERT INTO MARKET VALUES (6, 2, '�ô���۸���Ű', '�λ� �λ����� ������675-29',
'051-9025-2501', '�������޹�', 1, '../img/�ô���۸���Ű.jpg');
INSERT INTO MARKET VALUES (7, 1, '�������', '�λ� �λ����� �߾Ӵ��680������ 80-30',
'051-807-0377', '���� 17:30 - 03:00', 1, '../img/�������.jpg');
INSERT INTO MARKET VALUES (8, 1, '���̼���', '�λ� �λ����� ������46���� 6',
'0507-1369-9399', '���� 11:30 - 20:30', 1, '../img/���̼���.jpg');
INSERT INTO MARKET VALUES (9, 1, '3found', '�λ� �λ����� ������ 50',
'0507-1330-8853', '���� 11:00 - 21:30', 1, '../img/3found.jpg');
INSERT INTO MARKET VALUES (10, 1, 'ȭ������������', '�λ� �λ����� �߾Ӵ��680������ 80-3',
'051-807-0377', '���� 17:30 - 03:00', 1, '../img/ȭ������������.jpg');
INSERT INTO MARKET VALUES (11, 1, 'ī���ͽĴ�', '������ 8�� �ⱸ �λ����� Ⱦ�ܺ��� �ǳ��� 1��',
'0507-1308-6106', '���� 00:00 - 00:01', 1, '../img/ī���ͽĴ�.jpg');
INSERT INTO MARKET VALUES (12, 1, '���ż�', '�λ� �λ����� ��õ��108����',
'051-911-4960', '���� 11:30 - 21:00', 1, '../img/���ż�.jpg');
INSERT INTO MARKET VALUES (13, 3, 'GS25 ����������', '�λ� �λ����� �߾Ӵ��680���� 45-9',
'051-818-2478', '����', 1, '../img/GS25.jpg');
INSERT INTO MARKET VALUES (14, 3, '�̸�Ʈ24 �������Ÿ����', '�λ� �λ����� �߾Ӵ�� 672',
'051-520-3773', '���� 10:00 - 02:00', 1, '../img/emart24.jpg');
INSERT INTO MARKET VALUES (15, 3, '�����Ϸ��� �λ�и�������', '�λ� �λ����� ������ 25 �̿���������',
'051-817-3372', '����', 1, '../img/�����Ϸ���.jpg');
INSERT INTO MARKET VALUES (16, 3, 'CU �ξ���', '�λ� �λ����� ��õ��� 204',
'051-808-3328', '����', 1, '../img/CU.jpg');
INSERT INTO MARKET VALUES (17, 3, 'GS25 ����������', '�λ� �λ����� ��õ�� 72',
'051-818-9866', '����', 1, '../img/GS25.jpg');
INSERT INTO MARKET VALUES (18, 3, 'CU ���鼾Ʈ����Ÿ��', '�λ� �λ����� ��õ���50���� 34',
'051-802-7434', '����', 1, '../img/CU.jpg');



INSERT INTO MASKRATE VALUES ( 1, 3,5,2,2, 1);
INSERT INTO MASKRATE VALUES ( 2, 2,4,3,3, 1);
INSERT INTO MASKRATE VALUES ( 3, 3,5,2,2, 2);
INSERT INTO MASKRATE VALUES ( 4, 1,2,2,1, 3);
INSERT INTO MASKRATE VALUES ( 5, 4,4,4,4, 4);
INSERT INTO MASKRATE VALUES ( 6, 3,3,3,3, 6);
Insert into MASKRATE values (7,4,4,4,4,5);
Insert into MASKRATE values (8,3,3,3,3,8);
Insert into MASKRATE values (9,5,5,5,5,9);
Insert into MASKRATE values (10,6,6,1,2,10);
Insert into MASKRATE values (11,4,2,4,2,11);
Insert into MASKRATE values (12,5,2,3,4,12);
Insert into MASKRATE values (13,4,3,2,1,13);
Insert into MASKRATE values (14,3,2,1,1,14);
Insert into MASKRATE values (15,2,2,2,2,15);
Insert into MASKRATE values (16,2,3,3,2,16);
Insert into MASKRATE values (17,3,2,2,3,17);
Insert into MASKRATE values (18,3,3,3,3,5);
Insert into MASKRATE values (19,3,3,3,3,7);
Insert into MASKRATE values (20,4,4,4,4,18);

INSERT INTO REVIEW VALUES (1, 'JASON', '2020/12/10', '���', 1 , 1);
INSERT INTO REVIEW VALUES (2, 'KYONGAH', '2020/12/10', '����', 1 , 2);
INSERT INTO REVIEW VALUES (3, 'MINGUEN', '2020/12/10', '���', 2 , 3);
INSERT INTO REVIEW VALUES (4, 'YOUNGHWAN', '2020/12/10', '�ؼ�', 3 , 4);
INSERT INTO REVIEW VALUES (5, 'TAEWOOK', '2020/12/10', '���', 4 , 5);
INSERT INTO REVIEW VALUES (6, 'HYUNWO', '2020/12/10', '�״�', 6 , 6);
Insert into REVIEW  values (7,'HODONG','20/12/16 09:35:50.348000000','���',5,7);
Insert into REVIEW  values (8,'TESS','20/12/16 09:36:18.610000000','���� ����',8,8);
Insert into REVIEW  values (9,'KIMTAE','20/12/16 09:37:16.889000000','����',9,9);
Insert into REVIEW  values (10,'DORAN','20/12/16 09:37:40.392000000','���� ����',10,10);
Insert into REVIEW  values (11,'DUHAN','20/12/16 09:38:01.623000000','����',11,11);
Insert into REVIEW  values (12,'OHHYEON','20/12/16 09:38:36.904000000','����',12,12);
Insert into REVIEW  values (13,'YEOMSO','20/12/16 09:38:57.938000000','����',13,13);
Insert into REVIEW  values (14,'GORAE','20/12/16 09:39:18.889000000','����',14,14);
Insert into REVIEW  values (15,'DUBAK','20/12/16 09:39:40.867000000','���� ����',15,15);
Insert into REVIEW  values (16,'SUNSIN','20/12/16 09:40:06.801000000','����',16,16);
Insert into REVIEW  values (17,'YEOMSO','20/12/16 09:40:34.037000000','����',17,17);
Insert into REVIEW  values (18,'DUBAK','20/12/16 10:09:26.595000000','����',5,18);
Insert into REVIEW  values (19,'MINGUEN','20/12/16 11:31:42.038000000','����',7,19);
Insert into REVIEW  values (20,'DUBAK','20/12/17 10:05:27.567000000','����',18,20);