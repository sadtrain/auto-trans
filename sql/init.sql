set -x
wget -P /opt https://mirrors.huaweicloud.com/java/jdk/11+28/jdk-11_linux-x64_bin.tar.gz
tar -zxf jdk-11_linux-x64_bin.tar.gz -C /opt
mv /opt/jdk-11 /opt/java
sed -i '$a export JAVA_HOME=/opt/java \nexport PATH=$PATH:$JAVA_HOME/bin:/opt/maven/bin' ~/.bashrc
source ~/.bashrc
apt-get install -y mysql-server
wget -P /opt https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.79/bin/apache-tomcat-8.5.79.tar.gz
tar -zxf apache-tomcat-8.5.79.tar.gz -C /opt
mv /opt/apache-tomcat-8.5.79 /opt/tomcat
rm -rf /opt/tomcat/webapps/*
touch ~/.vimrc
sed -i '$a if has('mouse')\n    set mouse-=a\nendif' ~/.vimrc

vi /etc/mysql/mariadb.conf.d/50-server.cnf
vi /etc/mysql/mysql.conf.d/mysqld.cnf
修改为0.0.0.0
mysql -uroot
use mysql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Alias2011';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'127.0.0.1' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'::1' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'Alias2011' WITH GRANT OPTION;
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


INSERT INTO t_bot (id, bot_num, username, password, enable) VALUES (1672577025, 425497793, null, 'Alias2011', null);


INSERT INTO t_consumer (id, listener_id, to_group_nums) VALUES (-1137606654, 2054258690, '639164037');
INSERT INTO t_consumer (id, listener_id, to_group_nums) VALUES (1236348929, -546242559, '387501317');


INSERT INTO t_listener (id, bot_num, group_num) VALUES (2054258690, 425497793, 637546059);
INSERT INTO t_listener (id, bot_num, group_num) VALUES (-546242559, 425497793, 387501317);
