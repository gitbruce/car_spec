INSERT INTO country(id, name) VALUES (1, 'Thailand');
INSERT INTO country(id, name) VALUES (2, 'China');
INSERT INTO country(id, name) VALUES (3, 'Japan');
INSERT INTO `car_brand` (id, countryName, createTime, updateTime, logo, NAME, country_id) VALUES (3, "日本", '2021-05-22 15:55:44.156000', '2021-05-22 15:55:44.156000', 'http://www.baidu.com', '丰田', 3);
INSERT INTO `car_factory` VALUES (1000, '2021-05-25 20:30:41.653000', '2021-05-25 20:30:41.935000', '丰田', '广汽丰田2', 3);
INSERT INTO `car_model` VALUES (1000, '2021-05-25 20:30:42.283000', '2021-05-25 20:30:42.567000', '丰田', '广汽丰田', '雷凌2', 0, 'stopped', 3, 1000);
INSERT INTO `car_model_year` VALUES (1000, '2021-05-25 20:33:49.950000', '2021-05-25 20:33:50.473000', '丰田', '广汽丰田', '雷凌', '丰田-雷凌-2021款2', 3, 1000, 1000);
INSERT INTO `car_model_type` VALUES (1000, '2021-05-25 20:34:48.704000', '2021-05-25 20:34:49.441000', '丰田', '广汽丰田', 120000, 90000, '雷凌', '丰田-雷凌 / 2021款 / 2021款 TNGA 1.5L CVT进取版', 'Y', 3, 1000, 1000, 1000);
