-- nax_period: table
CREATE TABLE IF NOT EXISTS `nax_period` (
  `id`              int(11) NOT NULL AUTO_INCREMENT,
  `period`          int(11) NOT NULL,
  `lastDistribute`  decimal(50, 0)   DEFAULT NULL,
  `totalSupply`     decimal(50, 0)   DEFAULT NULL,
  `totalNAS`        decimal(50, 0)   DEFAULT NULL,
  `totalDistribute` decimal(50, 0)   DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nax_period_period_uindex` (`period`)
)
  DEFAULT CHARSET = utf8;


-- nax_record: table
CREATE TABLE IF NOT EXISTS `nax_record` (
  `id`        int(11)   NOT NULL AUTO_INCREMENT,
  `address`   varchar(40)        DEFAULT NULL,
  `txHash`    varchar(64)        DEFAULT NULL,
  `block`     int(11)            DEFAULT NULL,
  `amount`    decimal(50, 0)     DEFAULT NULL,
  `source`    int(11)            DEFAULT NULL,
  `timestamp` timestamp NOT NULL  DEFAULT CURRENT_TIMESTAMP ,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nax_record_id_uindex` (`id`),
  KEY `nax_record_address_index` (`address`),
  KEY `nax_record_txHash_index` (`txHash`),
  KEY `nax_record_block_index` (`block`)
)
  DEFAULT CHARSET = utf8;