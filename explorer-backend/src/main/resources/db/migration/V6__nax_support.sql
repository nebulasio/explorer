-- nax_stage: table
CREATE TABLE IF NOT EXISTS `nax_stage` (
  `id`           int(11) NOT NULL AUTO_INCREMENT,
  `stage`        int(11) NOT NULL,
  `start`        int(11)          DEFAULT NULL,
  `end`          int(11)          DEFAULT NULL,
  `estimateNax`  decimal(50, 0)   DEFAULT NULL,
  `actualNax`    decimal(50, 0)   DEFAULT NULL,
  `destroyedNax` decimal(50, 0)   DEFAULT NULL,
  `totalNax`     decimal(50, 0)   DEFAULT NULL,
  `pledgeNas`    decimal(50, 0)   DEFAULT NULL,
  `totalNas`     decimal(50, 0)   DEFAULT NULL,
  `status`       int(11)          DEFAULT '0'
  COMMENT '是否已发放NAX - 0:NO; 1:YES',
  `createdAt`    datetime         DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nax_stage_id_uindex` (`id`),
  UNIQUE KEY `nax_stage_stage_uindex` (`stage`),
  KEY `nax_stage_status_index` (`status`)
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


-- nax_profit: table
CREATE TABLE IF NOT EXISTS `nax_profit` (
  `id`        int(11) NOT NULL                    AUTO_INCREMENT,
  `address`   varchar(35) COLLATE utf8_unicode_ci DEFAULT NULL,
  `txHash`    varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `timestamp` datetime                            DEFAULT NULL,
  `profit`    decimal(50, 0)                      DEFAULT NULL
  COMMENT 'NAX收益',
  `stage`     int(11)                             DEFAULT NULL
  COMMENT '第几期',
  `source`    int(11)                             DEFAULT NULL,
  `block`     int(11)                             DEFAULT NULL,
  `createdAt` datetime                            DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nax_test_id_uindex` (`id`),
  KEY `nax_test_address_index` (`address`),
  KEY `nax_test_stage_index` (`stage`)
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;


-- neb_transaction_events: table
CREATE TABLE `neb_transaction_events` (
  `id`        int(11)                             NOT NULL AUTO_INCREMENT,
  `txHash`    varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `timestamp` datetime                                     DEFAULT NULL,
  `block`     int(11)                                      DEFAULT NULL,
  `topic`     varchar(128) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `data`      mediumtext COLLATE utf8_unicode_ci,
  `createdAt` datetime                                     DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `neb_transaction_events_id_uindex` (`id`),
  KEY `neb_transaction_events_tx_hash_index` (`txHash`)
)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
