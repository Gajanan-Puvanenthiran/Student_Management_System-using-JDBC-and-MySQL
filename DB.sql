create database WinAcedamy;
use WinAcedamy;

create table students(
id int primary key auto_increment,
name varchar(80) not null,
age int not null,
department varchar(100) not null,
district varchar(100) not null,
NIC varchar(10) not null,
gender varchar(10) not null,
performance int not null
);

describe students;
truncate table students;

delimiter //
create procedure GetAll()
begin
select * from students;
end //
delimiter ;

delimiter //
create procedure GetById(in student_id int)
begin
select * from students where id=student_id;
end //
delimiter ;

delimiter //
create procedure GetNameById(in student_id int, out student_name varchar(100))
begin
select name into student_name  from students where id=student_id;
end //
delimiter ;