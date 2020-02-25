INSERT INTO locations (name, phone_number, email, address, seller_id) VALUES
('location1', '200-000-0001', 'location1@123.com', 'address1', '1'),
('location2', '200-000-0002', 'location2@123.com', 'address2', '2'),
('location3', '200-000-0003', 'location3@123.com', 'address3', '3'),
('location4', '200-000-0004', 'location4@123.com', 'address4', '4')
;
commit;

INSERT INTO products (product_name, description, price, weight, year, location_id) VALUES
('product_name1','des1','100','100','2020','1'),
('product_name2','des2','200','200','2020','2'),
('product_name3','des3','300','300','2020','3'),
('product_name4','des4','400','400','2020','4')
;
COMMIT;