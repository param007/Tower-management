drop table if exists hibernate_sequence;
create table hibernate_sequence (next_val bigint) engine=MyISAM;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

INSERT INTO user (id, username, password,role) VALUES (1, 'Snorlxx', '$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi','ADMIN');
INSERT INTO user (id, username, password,role) VALUES (2, 'Diksha', '$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi','USER');
INSERT INTO user (id, username, password,role) VALUES (3, 'dkjala', '$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi','ADMIN');
INSERT INTO user (id, username, password,role) VALUES (4, 'Saran', '$2a$10$B8.BjkA5a9l9vFBnlSfneeCoMxnBguWq0vCvgw9C4.19SpJcCiOxi','USER');

Insert into admin(admin_id,email,name) VALUES(1,'deveshchaudhary2504@gmail.com','Devesh');
Insert into admin(admin_id,email,name) VALUES(3,'kajladk@gmail.com','Deepak');

Insert into company VALUES (1,'Verizon');
Insert into company VALUES (2,'Infosys');

Insert into tower_company VALUES (1,1);
Insert into tower_company VALUES (1,2);

Insert into tower VALUES (1,'olympia','Chennai','Stephen','HW',67.8,'farm',153.5,'adobe',1,2); 
Insert into tower VALUES (2,'RMZ','Hyderabad','Param','HW',65.8,'sidco',103.5,'google',1,4);
Insert into tower VALUES (3,'guindy','Chennai','Stephen','poop',87.8,'opern',163.5,'adobe',1,2); 
Insert into tower VALUES (4,'perungudi','Hyderabad','Param','jojo',69.6,'zozo',96.9,'google',1,4); 

Insert into csa VALUES (2,'Chennai','dikshashresth@gmail.com','Diksha');
Insert into csa VALUES (4, 'Hyderabad', 'saranraj.14cs@kct.ac.in','Saran');

Insert into complaint VALUES (1,'NEW','2018-09-09','2018-09-11','Constant Battery drain','BATTERY',false,2,1);
Insert into complaint VALUES (2,'REJECTED','2018-09-10','2018-12-11','Capacitor not working','CAPACITOR',true,4,2);
Insert into complaint VALUES (3,'ACCEPTED','2018-09-10','2018-12-11','Net speed slow','BANDWIDTH',true,2,1);