-- TASK 1: --

-- Creates a database if it does not exist --
create DATABASE IF NOT EXISTS leisure;

-- Switch the user database --
use leisure;

-- Create a `user` table if it does not exist --
create TABLE IF NOT EXISTS user (
  user_id CHAR(8) primary key,
  username VARCHAR(20) not null,
  name VARCHAR(50)
);



-- TASK 5: --

create TABLE IF NOT EXISTS task (
    task_id VARCHAR(8) primary key,
    description VARCHAR(255),
    priority INTEGER CHECK (priority >= 1 and priority <= 3),
    due_date DATE,
    user_id CHAR(8) REFERENCES user(user_id)
);

