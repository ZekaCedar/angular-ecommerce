USE `full-stack-ecommerce`;

-- clean up previous database tables
-- temporarily disable foreign key checks
SET FOREIGN_KEY_CHECKS=0;
-- truncate faster than delete
-- resets auto_increment back to starting values
TRUNCATE customer;
TRUNCATE orders;
TRUNCATE order_item;
TRUNCATE address;
-- enable foreign key checks
SET FOREIGN_KEY_CHECKS=1;

-- make the email address unique
ALTER TABLE customer ADD UNIQUE (email);