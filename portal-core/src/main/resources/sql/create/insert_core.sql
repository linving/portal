INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('contact.form.enable','Contact Form: Enables the contact form',{ts '2009-01-05 23:16:52.000'},'admin',{ts '2009-01-05 23:16:52.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('emailnotification.registered.user','Email notification: New registered user',null,null,null,null);
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('emailnotification.unknown.application.error','Email notification: Unknown application error',{ts '2009-01-07 08:12:44.000'},'admin',{ts '2009-01-07 08:12:44.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('box.admin','Box Administration',{ts '2009-01-05 14:11:43.000'},'admin',{ts '2009-01-10 21:28:18.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('configuration.admin','Configuration Administration',{ts '2009-01-10 21:28:45.000'},'admin',{ts '2009-01-10 21:28:45.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('contact','Contact Form: User who are allowed to access the contact form',{ts '2009-01-05 23:01:39.000'},'admin',{ts '2009-01-06 15:59:04.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('emailtemplate.admin','Email Templates Administration',{ts '2009-01-10 21:24:57.000'},'admin',{ts '2009-01-10 21:24:57.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('right.admin','Rights Administration',{ts '2009-01-10 21:23:44.000'},'admin',{ts '2009-01-10 21:23:44.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('role.admin','Roles Administration',{ts '2009-01-10 21:23:01.000'},'admin',{ts '2009-01-10 21:24:11.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('user.admin','User Administration',{ts '2009-01-10 21:21:23.000'},'admin',{ts '2009-01-10 21:21:53.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('theme.admin','Theme Administration',{ts '2009-01-10 21:21:23.000'},'admin',{ts '2009-01-10 21:21:53.000'},'admin');
INSERT INTO core_right (right_id,description,created_at,created_by,modified_at,modified_by) VALUES ('modulemgmt.admin','Module Administration',{ts '2009-05-10 00:30:24.000'},'admin',{ts '2009-05-10 00:30:24.000'},'admin');

INSERT INTO core_right (right_id,created_at,created_by,modified_at,modified_by,description) VALUES ('captcha.disabled',{ts '2009-05-10 00:29:41.000'},'admin',{ts '2009-05-10 00:29:41.000'},'admin','Disables all captchas');

INSERT INTO core_role (id,active,description,created_at,created_by,modified_at,modified_by) VALUES (1,1,'Admin',{ts '2008-12-26 04:03:10.000'},'admin',{ts '2009-01-04 21:47:37.000'},'admin');
INSERT INTO core_role (id,active,description,created_at,created_by,modified_at,modified_by) VALUES (2,1,'Guest',{ts '2009-01-05 15:36:51.000'},'admin',{ts '2009-01-05 15:36:51.000'},'admin');
INSERT INTO core_role (id,active,description,created_at,created_by,modified_at,modified_by) VALUES (3,1,'Registered user',null,null,null,null);


INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'contact.form.enable');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'contact');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'emailnotification.registered.user');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'user.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'theme.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'right.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'role.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'emailtemplate.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'box.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'configuration.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'contact');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'modulemgmt.admin');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (1,'captcha.disabled');
INSERT INTO core_role_right_xref (role_id,right_id) VALUES (3,'captcha.disabled');



INSERT INTO core_user (id,active,birthday,changed_at,confirm_app_at,confirmation_code,confirm_req_at,confirmed,email,firstname,forgot_code,last_ip,last_login_at,lastname,password,reg_date,session_id,username,role_id,enable_contact_form) VALUES (2,1,{ts '2008-08-09 00:00:00.000'},{ts '2009-01-05 23:18:13.000'},{ts '2008-08-10 16:35:37.000'},'bfda0f177e0b56113118131fe696412',{ts '2008-08-10 16:55:20.000'},1,'your@email.de','admin',null,'62.216.221.209',{ts '2009-01-06 17:21:56.000'},'admin','21232f297a57a5a743894a0e4a801fc3',{ts '2008-07-25 15:25:29.000'},'d4212b818096297975e5c59b738a7a98','admin',1,1);
INSERT INTO core_user (id,active,birthday,changed_at,confirm_app_at,confirmation_code,confirm_req_at,confirmed,email,firstname,forgot_code,last_ip,last_login_at,lastname,password,reg_date,session_id,username,role_id,enable_contact_form) VALUES (9,1,{ts '2009-01-05 00:00:00.000'},{ts '2009-01-05 23:26:40.000'},null,null,null,1,'email@test.de','test',null,'127.0.0.1',{ts '2009-01-06 15:37:10.000'},'user','5d9c68c6c50ed3d02a2fcf54f63993b6',{ts '2009-01-05 23:25:59.000'},'c73ff2a18ca36b9304d93dca157b521c','testuser',3,1);


INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('show_real_author','Show real author name','General','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('show_modified_by','Show modified by','General','java.lang.Boolean','false');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('show_modified_at_as_created_at','Show modified at as created at','General','java.lang.Boolean','false');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('input_date_format','Default input date format','General','java.lang.String','yyyy-MM-dd');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('input_date_time_format','Default input date time format','General','java.lang.String','yyyy-MM-dd HH:mm');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('display_date_format','Default display date format','General','java.lang.String','EEEEE, MMMMM dd, yyyy');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('display_date_time_format','Default display date time format','General','java.lang.String','EEEEE, MMMMM dd, yyyy HH:mm');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('email_validation','Enable email validation for registration','User','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('page_title','Page title','General','java.lang.String','devproof.org');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('copyright_owner','Copyright owner','General','java.lang.String','devproof.org Copyright 2011');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('footer_content','Footer content','General','java.lang.String','&copy; 2011 - www.devproof.org');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('from_email_address','From email address','Email','java.lang.String','your@email.com');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('from_email_name','From email name','Email','java.lang.String','devproof.org');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('page_name','Page name','General','java.lang.String','devproof.org');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('registration_required_birthday','Require birthday for registration','User','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('registration_required_name','Require first and lastname for registration','User','java.lang.Boolean','true');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.fontService.findAllSystemFonts.name.name.string2image','String to image font','General','java.lang.String','Times New Roman');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.emailService.findAll.subject.id.forgotemail','Forgot your password email','Email','java.lang.Integer','2');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.emailService.findAll.subject.id.reconfirmemail','Reconfirmation email, when email was changed','Email','java.lang.Integer','5');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.emailService.findAll.subject.id.contactformemail','Contact form email template','Email','java.lang.Integer','6');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.emailService.findAll.subject.id.regemail','Registration email','Email','java.lang.Integer','1');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.emailService.findAll.subject.id.registereduser','Notification: A new user has been registered','Email','java.lang.Integer','3');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.emailService.findAll.subject.id.unknownerror','Notification: Unknown error email','Email','java.lang.Integer','4');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.roleService.findAll.description.id.guestrole','Default guest role','User','java.lang.Integer','2');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.roleService.findAll.description.id.registerrole','Default role for registration','User','java.lang.Integer','3');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('box_num_tags','Number of related tags','Tags','java.lang.Integer','10');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('google_analytics_enabled','Google Analytics enabled','Google Analytics','java.lang.Boolean','false');
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('google_webproperty_id','Google Web Property-ID','Google Analytics','java.lang.String','empty');

INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('hidden.selected_theme_uuid','Selected theme','hidden','java.lang.String','_default_');


INSERT INTO core_email_tpl (id,created_at,created_by,modified_at,modified_by,content,subject) VALUES (1,{ts '2009-01-05 15:32:07.000'},'admin',{ts '2009-01-05 15:32:07.000'},'admin','<p>Hi #FIRSTNAME#,<br /><br />Congratulations! Now you are a member of #PAGENAME#.  Please save this email for your records.<br />Your user name to log into #PAGENAME# is #USERNAME#.<br /><br />Click the following link to confirm the registration: <a href="#CONFIRMATIONLINK#">#CONFIRMATIONLINK#</a></p>
<p>Kind regards<br />Your #PAGENAME# team</p>','Your registration on #PAGENAME#');
INSERT INTO core_email_tpl (id,created_at,created_by,modified_at,modified_by,content,subject) VALUES (2,null,null,{ts '2008-07-31 22:35:07.000'},'admin','<p>Hi #FIRSTNAME#,</p>
<p>you requested a password reset for your account #USERNAME# on #PAGENAME#.</p>
<p>Click the following link to set a new password:</p>
<p><a href="#PASSWORDRESETLINK#">#PASSWORDRESETLINK#</a></p>
<p>Kind regards</p>
<p>Your #PAGENAME# team</p>','Password reset for your account on #PAGENAME#');
INSERT INTO core_email_tpl (id,created_at,created_by,modified_at,modified_by,content,subject) VALUES (3,{ts '2009-01-04 17:30:28.000'},'admin',{ts '2009-01-04 17:44:44.000'},'admin','<p>Hi,</p>
<p>there is a new registered user on #PAGENAME#!</p>
<p>User: #USERNAME#<br />Firstname: #FIRSTNAME#<br />Lastname: #LASTNAME#<br />Email: #EMAIL#</p>
<p>Birthday: #BIRTHDAY#</p>
<p>Kind regards</p>
<p>Your #PAGENAME# Team</p>','Admin: New registered user on #PAGENAME#');
INSERT INTO core_email_tpl (id,created_at,created_by,modified_at,modified_by,content,subject) VALUES (4,{ts '2009-01-04 17:44:48.000'},'admin',{ts '2009-01-04 17:44:48.000'},'admin','<p>Hi,</p>
<p>there occurred an unknown error on #PAGENAME#:</p>
<p>#CONTENT#</p>
<p>Kind regards</p>
<p>Your #PAGENAME# team</p>','Admin: Unknown error on #PAGENAME#');
INSERT INTO core_email_tpl (id,created_at,created_by,modified_at,modified_by,content,subject) VALUES (5,null,null,{ts '2008-08-10 16:31:01.000'},'admin','<p>Hi #FIRSTNAME#,<br /><br />you just changed your email address. <br /><br />Click the following link to confirm the registration: <a href="#CONFIRMATIONLINK#">#CONFIRMATIONLINK#</a></p>

<p>Kind regards<br />Your #PAGENAME# team</p>','Email reconfirmation for your account on  #PAGENAME#');
INSERT INTO core_email_tpl (id,created_at,created_by,modified_at,modified_by,content,subject) VALUES (6,{ts '2009-01-05 22:22:31.000'},'admin',{ts '2009-01-05 22:52:16.000'},'admin','<p>Hi #USERNAME#,</p>
<p>Mr./Mrs #CONTACT_FULLNAME# send you this email from #PAGENAME#. If you do not like too receive emails, you can disable this function on #PAGENAME# under Settings.</p>
<p>Contact email: #CONTACT_EMAIL#</p>
<p>Contact IP: #CONTACT_IP#</p>
<p>#CONTENT#</p>
<p>&nbsp;</p>
<p>Kind regards</p>
<p>Your #PAGENAME# Team</p>','Contact request from #PAGENAME#');


INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (1, {ts '2009-01-05 11:43:49.000'},'admin',{ts '2009-01-05 13:22:25.000'},'admin','SearchBoxPanel',null,null,1,'Search Box',0);
INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (2, {ts '2009-01-05 12:17:03.000'},'admin',{ts '2009-01-05 15:36:40.000'},'admin','PageAdminBoxPanel',null,null,2,'Page Administration Box',0);
INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (3, {ts '2009-01-05 12:17:35.000'},'admin',{ts '2009-01-05 13:11:05.000'},'admin','GlobalAdminBoxPanel',null,null,3,'Global Administration Box',0);
INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (4, {ts '2009-01-05 11:41:22.000'},'admin',{ts '2009-01-05 13:22:25.000'},'admin','TagCloudBoxPanel', null,null,4,'Related Tags Box',0);
INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (5, {ts '2009-01-05 12:19:08.000'},'admin',{ts '2009-01-05 14:28:30.000'},'admin','OtherBoxPanel','whiteBoxTemplate','A little <i>bit</i> content',8,'Other Test Box',0);
INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (6, {ts '2009-01-05 12:19:08.000'},'admin',{ts '2009-01-05 14:28:30.000'},'admin','FeedBoxPanel','whiteBoxTemplate',null,9,'Feed Box',1);
INSERT INTO core_box (id, created_at,created_by,modified_at,modified_by,box_type,custom_style,content,sort,title,hide_title) VALUES (7, {ts '2009-01-05 12:19:08.000'},'admin',{ts '2009-01-05 14:28:30.000'},'admin','OtherBoxPanel','whiteBoxTemplate', '<!-- AddThis Button BEGIN -->
<a class="addthis_button" href="http://www.addthis.com/bookmark.php?v=250&amp"><img src="http://s7.addthis.com/static/btn/v2/lg-share-en.gif" width="125" height="16" alt="Bookmark and Share" style="border:0"/></a><script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js"></script>
<!-- AddThis Button END -->
<br/>',10,'AddThis.com',1);
INSERT INTO core_configuration (conf_key,conf_description,conf_group,conf_type,conf_value) VALUES ('spring.fontService.findSyntaxHighlighterThemes.theme','Syntax Highlighter Theme','General','java.lang.String','Eclipse');