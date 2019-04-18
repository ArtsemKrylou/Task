SELECT * FROM task2.subject;
insert into subject(name, mark) VALUES ('new', 5);
update subject set name = 'new2' where mark = 5;
delete from subject where mark = 5;
SELECT * FROM task2.subject;