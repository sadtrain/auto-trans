mysql -uroot
use mysql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'127.0.0.1' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'::1' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
FLUSH PRIVILEGES;

create database auto_trans;
use auto_trans;
CREATE TABLE
    t_bot
(
    id       INT,
    bot_num  bigint UNSIGNED,
    username VARCHAR(255),
    password VARCHAR(255),
    enable   TINYINT(1)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE
    t_consumer
(
    id            INT,
    listener_id   INT,
    to_group_nums VARCHAR(1024)
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE
    t_listener
(
    id        INT,
    bot_num   bigint UNSIGNED,
    group_num bigint UNSIGNED
)
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;