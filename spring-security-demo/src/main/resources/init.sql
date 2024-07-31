CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL,
                        `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `enabled` tinyint(1) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert INTO user ( username, password, enabled)
values
    ('admin','{bcrypt}$2a$10$qfdWNpmKyuqgNhKuNNsfVeYJ65xIbT.Q4eaqmXv.lwalXymXxZTk6',true),
    ('Helen','{bcrypt}$2a$10$SZv3T0yqwEAYDetYLzHDCOogp1VGqVmk0vQzjh3Ertr9XBZ/kcuZO',true),
    ('Tom','{bcrypt}$2a$10$8ks1o3q4ic9LvkOIRz3tZuR4DFTs2q9Cw08uAV6Z3yCth/vJwl8mu',true)
;
-- 初始密码都为123456