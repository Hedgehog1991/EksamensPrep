CREATE DATABASE bookDb;

CREATE USER 'Tester' IDENTIFIED BY 'tester12345';
GRANT ALL ON bookDb.* TO 'Tester';
/*log in to this spesific user in terminal with
  mysql -u Tester -p  pw : tester12345
  */