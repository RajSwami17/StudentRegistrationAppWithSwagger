create table student_details (
  student_id bigint auto_increment primary key,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  date_of_birth date not null,
  gender varchar(255) not null,
  email_address varchar(254) not null,
  phone_number bigint not null,
  address varchar(255) not null
);


create table course_details (
    cousre_id bigint auto_increment primary key,
    course_name varchar(255) not null,
    description varchar(255) not null,
    course_duration varchar(255) not null,
    course_fees double not null,
    student_id bigint,
	primary key(course_id)
    --foreign key (student_id) references student_details(student_id)
);

