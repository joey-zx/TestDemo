create table publish
(
	id int auto_increment,
	title varchar(60),
	content text,
	tag varchar(256),
	gmt_create long,
	gmt_modified int,
	creator int,
	comment_count int,
	view_count int not null,
	like_count int,
	constraint publish_pk
		primary key (id)
);
