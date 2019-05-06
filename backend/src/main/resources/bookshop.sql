# noinspection SqlNoDataSourceInspectionForFile
drop database if exists `bookshop`;
create database `bookshop` character set utf8mb4;
use `bookshop`;

create table `author_t`
(
    `id`         int(11) primary key comment '豆瓣id，不自增',
    `name`       varchar(99) not null,
    `original`   varchar(99) comment '原外文名',
    `country`    varchar(99)   default '中国' comment '国家 / 地区',
    `intro`      text comment '作者简介',
    `pic_url`    varchar(256) comment '图片链接',
    `author_url` varchar(256) comment '豆瓣链接',
    `gender`     numeric(1, 0) default 0 comment '性别：0未知，1男，2女',
    `born`       date comment '出生日期',
    `died`       date comment '逝世日期'
);

create table `book_t`
(
    `id`           int(11) primary key comment '豆瓣id，不自增',
    `title`        varchar(99) not null comment '书名',
    `original`     varchar(99) comment '原作名',
    `author_id`    int(11) comment '作者id',
    `author`       varchar(99) comment '作者',
    `translator`   varchar(99) comment '译者',
    `press`        varchar(99) comment '出版社',
    `publish_date` varchar(99) comment '出版日期',
    `pages`        int comment '页数',
    `price`        numeric(8, 2) comment '定价',
    `binding`      varchar(99) comment '装帧',
    `ISBN`         varchar(99),
    `douban_score` numeric(2, 1) comment '豆瓣评分',
    `book_url`     varchar(256) comment '豆瓣链接',
    `pic_url`      varchar(256) comment '图片链接',
    `summary`      text comment '作品简介',
    `author_intro` text comment '作者简介',
    foreign key (`author_id`) references `author_t` (`id`)
);

create table `category_t`
(
    `id`    int(11) auto_increment primary key,
    `title` varchar(99) comment '类别名称',
    unique key (`title`)
);

create table `tag_t`
(
    `id`          int(11) auto_increment primary key,
    `title`       varchar(99) comment '标签名称',
    `category_id` int(11) default 0 comment '类别id，0表示无类别',
    unique key (`title`),
    foreign key (`category_id`) references `category_t` (`id`) on delete cascade
);

create table `book_tag_t`
(
    `book_id` int(11),
    `tag_id`  int(11),
    primary key (`book_id`, `tag_id`),
    foreign key (`book_id`) references `book_t` (`id`) on delete cascade,
    foreign key (`tag_id`) references `tag_t` (`id`) on delete cascade
);

create table `book_recommended_t`
(
    `subject_id` int(11),
    `rec_id`     int(11) comment '推荐相似图书id',
    primary key (`subject_id`, `rec_id`),
    foreign key (`subject_id`) references `book_t` (`id`) on delete cascade
);

create table `short_comment_t`
(
    `id`      int(11) auto_increment primary key,
    `book_id` int(11) not null,
    `comment` text comment '豆瓣短评论',
    foreign key (`book_id`) references `book_t` (`id`) on delete cascade
);

/* ------------------ user ------------------ */

create table `user_t`
(
    `id`       int(11) auto_increment primary key,
    `username` varchar(99) not null,
    `password` varchar(99) not null,
    `gender`   numeric(1, 0) default 0 comment '性别：0未知，1男，2女',
    `age`      numeric(3, 0) default 0,
    unique key (`username`)
);

/* 用户收藏列表 */
create table if not exists `collection_t`
(
    `id`      int(11) auto_increment primary key,
    `user_id` int(11) not null,
    `book_id` int(11) not null,
    unique key (`user_id`, `book_id`),
    foreign key (`user_id`) references `user_t` (`id`) on delete cascade,
    foreign key (`book_id`) references `book_t` (`id`) on delete cascade
);

/* 用户评分+评论 */
create table if not exists `rating_t`
(
    `id`      int(11) auto_increment primary key,
    `user_id` int(11) not null,
    `book_id` int(11) not null,
    `score`   numeric(1, 0) default 0 comment '用户评分，取值为1-5，0表示无评分',
    `comment` text comment '用户评论',
    unique key (`user_id`, `book_id`),
    foreign key (`user_id`) references `user_t` (`id`) on delete cascade,
    foreign key (`book_id`) references `book_t` (`id`) on delete cascade
);

/* 喜爱标签 */
create table `favor_tag_t`
(
    `user_id` int(11),
    `tag_id`  int(11),
    primary key (`user_id`, `tag_id`),
    foreign key (`user_id`) references `user_t` (`id`) on delete cascade,
    foreign key (`tag_id`) references `tag_t` (`id`) on delete cascade
);



