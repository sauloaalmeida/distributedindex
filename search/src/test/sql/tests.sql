create database distributedindextest;

use distributedindextest;

drop table search_index;

create table search_index (term varchar(1000) not null primary key, documents text not null, index idx_term(term(300))) engine = MYISAM;

--inserts useds in tests
insert into table  search_index (term,documents) values('heart','pg100.txt,pg10004.txt,pg10.txt,pg10012.txt,pg10017.txt,pg10006.txt,pg10013.txt,pg10001.txt,pg10011.txt,pg10010.txt,pg10018.txt,pg10008.txt,pg10009.txt,pg10005.txt,pg1001.txt,pg1002.txt,pg10020.txt,pg10014.txt,pg10007.txt,pg10016.txt,pg10019.txt,pg10002.txt,pg10003.txt,pg10015.txt');
insert into table  search_index (term,documents) values('love','pg100.txt,pg10005.txt,pg10009.txt,pg1000.txt,pg10018.txt,pg10006.txt,pg10001.txt,pg10010.txt,pg10017.txt,pg10013.txt,pg10008.txt,pg10.txt,pg10012.txt,pg10004.txt,pg10014.txt,pg10003.txt,pg10016.txt,pg10007.txt,pg10020.txt,pg10002.txt,pg1001.txt,pg1002.txt,pg10019.txt,pg10015.txt');

--LOAD DATA LOCAL INFILE 'part-00000' INTO TABLE search_index LINES TERMINATED BY '\n';

--GRANT ALL PRIVILEGES ON *.* TO root@192.168.56.1 IDENTIFIED BY '123456' WITH GRANT OPTION;
