drop table if exists book;
CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
  author varchar(255) DEFAULT NULL,
  locations int(11) NOT NULL,
  title varchar(255) DEFAULT NULL,
  theme_id int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);