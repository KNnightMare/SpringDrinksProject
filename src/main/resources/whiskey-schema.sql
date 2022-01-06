CREATE TABLE `whiskey` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `blend` varchar(255) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `proof` bigint NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);