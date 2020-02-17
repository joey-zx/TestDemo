create table user
(
	ID int auto_increment primary key NOT NULL,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(60),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
);