-- Delete data
ALTER TABLE staff DROP FOREIGN KEY fk_staff_store , DROP FOREIGN KEY fk_staff_address;
DELETE FROM payment ;
DELETE FROM rental ;
DELETE FROM customer ;
DELETE FROM film_category ;
DELETE FROM film_text ;
DELETE FROM film_actor ;
DELETE FROM inventory ;
DELETE FROM film ;
DELETE FROM category ;
ALTER TABLE store CHANGE COLUMN manager_staff_id manager_staff_id TINYINT UNSIGNED NULL;
update store set manager_staff_id=null;
DELETE FROM staff ;
DELETE FROM store ;
DELETE FROM actor ;
DELETE FROM address ;
DELETE FROM city ;
DELETE FROM country ;
DELETE FROM language ;
ALTER TABLE store CHANGE COLUMN manager_staff_id manager_staff_id TINYINT UNSIGNED NOT NULL;