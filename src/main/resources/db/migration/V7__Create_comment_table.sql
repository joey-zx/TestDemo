create table comment
(
	id BIGINT auto_increment,
	parent_id int not null,
	type int,
	content text,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	like_count int default 0,
	comentator int,
	constraint comment_pk
		primary key (id)
);
