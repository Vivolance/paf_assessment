-- TASK 1: --

use leisure;

create table users (
    user_id CHAR(8) primary key,
    username VARCHAR(20) not null,
    name VARCHAR(50)
);



-- TASK 5: --

create table task (
    task_id serial primary key,
    description VARCHAR(255),
    priority INTEGER CHECK (priority >= 1 and priority <= 3),
    due_date DATE,
    user_id INTEGER REFERENCES user(user_id)
);

