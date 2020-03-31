insert into roles (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/locations,/products,/sellers', TRUE, TRUE, TRUE, FALSE),
('user', '/locations,/products,/sellers', TRUE, FALSE, FALSE, FALSE)
;
commit;
insert into users (name, password,first_name, last_name, email) values
('feixiong', 'de358fb22a24e768c3e335811dcfcffa', 'Feixiong', 'Meng', '1093599417@qq.com'),
('rhang', '25f9e794323b453885f5181f1b624d0b', 'Ryo', 'Hang', 'rhang@training.ascendingdc.com'),
('xyhuang', '25f9e794323b453885f5181f1b624d0b', 'Xinyue', 'Huang', 'xyhuang@training.ascendingdc.com')
;
commit;
insert into users_roles values
(1, 1),
(2, 2),
(2, 3),
(1, 2),
(1, 3)
;
commit;