alter table blog change content content mediumtext;

-- since 1.1
CREATE TABLE blog_historized (
  id int(11) NOT NULL auto_increment,
  version_number int(11) NOT NULL,
  created_at datetime default NULL,
  created_by varchar(30) default NULL,
  modified_at datetime default NULL,
  modified_by varchar(30) default NULL,
  action varchar(50) default NULL,
  action_at datetime default NULL,
  restored_from_version int(11) default NULL,
  content mediumtext,
  headline varchar(255) default NULL,
  tags text default NULL,
  rights text default NULL,
  blog_id int(11) NOT NULL,
  PRIMARY KEY  (id),
  CONSTRAINT FK3DB0669D97F3646 FOREIGN KEY (blog_id) REFERENCES blog (id)
);

SET FOREIGN_KEY_CHECKS=0;
DELETE FROM core_role_right_xref WHERE right_id LIKE 'page.BlogPage';
DELETE FROM core_right WHERE right_id LIKE 'page.BlogPage';
UPDATE core_role_right_xref SET right_id = 'blog.author' WHERE right_id LIKE 'page.BlogEditPage';
UPDATE core_right SET right_id = 'blog.author', description = 'Blog Author' WHERE right_id LIKE 'page.BlogEditPage';
SET FOREIGN_KEY_CHECKS=1;