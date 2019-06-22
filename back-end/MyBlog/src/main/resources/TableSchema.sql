create
	table
		user(id varchar(100) primary key,
		name varchar(200) unique not null,
		password varchar(200) not null,
		create_date timestamp,
		update_date timestamp);

create
	table
		article(id varchar(100) primary key,
		title varchar(1000),
		create_date timestamp,
		update_date timestamp );

create
	table
		article_content(id bigint primary key auto_increment,
		article_id varchar(100),
		content varchar(4000),
		sort int);