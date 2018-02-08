# DROP TABLE IF EXISTS `neb_block`;

CREATE TABLE IF NOT EXISTS `neb_block` (
  `id`          CHAR(24) PRIMARY KEY
  COMMENT 'primary key, generated by program',
  `hash`        VARCHAR(64)         NOT NULL
  COMMENT 'hex string of block hash',
  `height`      BIGINT(16) UNSIGNED NOT NULL
  COMMENT 'block height',
  `timestamp`   DATETIME            NOT NULL
  COMMENT 'block timestamp',
  `parent_hash` VARCHAR(64)         NOT NULL
  COMMENT 'hex string of block parent hash',
  `miner`       VARCHAR(64)         NOT NULL
  COMMENT 'hex string of miner address',
  `coinbase`    VARCHAR(64)         NOT NULL
  COMMENT 'hex string of coinbase address',
  `nonce`       BIGINT(16) UNSIGNED
  COMMENT 'block nonce',
  `finality`    BOOL                NOT NULL
  COMMENT 'block is irreversible or not',
  `created_at`  TIMESTAMP           NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `uniq_neb_block_hash` (`hash`),
  UNIQUE KEY `uniq_neb_block_height` (`height`),
  KEY `idx_neb_block_miner` (`miner`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COMMENT 'nebulas block';

# DROP TABLE IF EXISTS `neb_address`;

CREATE TABLE IF NOT EXISTS `neb_address` (
  `id`         CHAR(24) PRIMARY KEY
  COMMENT 'primary key, generated by program',
  `hash`       VARCHAR(64) NOT NULL
  COMMENT 'hex string of address hash',
  `type`       TINYINT(2)  NOT NULL         DEFAULT 0
  COMMENT 'address type, 0: Normal; 1: Contract',
  `alias`      VARCHAR(256) COMMENT 'address alias',
  `current_balance` decimal(30,18)  unsigned  NOT NULL  DEFAULT '0.000000000000000000' COMMENT 'address current balance',
  `created_at`  timestamp NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  `updated_at`  timestamp NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `uniq_neb_address_hash` (`hash`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COMMENT 'nebulas address';

# DROP TABLE IF EXISTS `neb_transaction`;

CREATE TABLE IF NOT EXISTS `neb_transaction` (
  `id`           CHAR(24) PRIMARY KEY
  COMMENT 'primary key, generated by program',
  `hash`         VARCHAR(64)         NOT NULL
  COMMENT 'hex string of transaction hash',
  `block_hash`   VARCHAR(64)         NOT NULL
  COMMENT 'hex string of block hash',
  `block_height` BIGINT(16) UNSIGNED NOT NULL
  COMMENT 'block height',
  `from`         VARCHAR(64)         NOT NULL
  COMMENT 'hex string of the sender account address',
  `to`           VARCHAR(64)         NOT NULL
  COMMENT 'hex string of the receiver account address',
  `status`       TINYINT(2) COMMENT 'transaction status, 0: failed; 1: success; 2: pending;',
  `value`        VARCHAR(64) COMMENT 'value of transaction',
  `nonce`        BIGINT(16) UNSIGNED COMMENT 'transaction nonce',
  `timestamp`    DATETIME COMMENT 'transaction timestamp',
  `type`         VARCHAR(32) COMMENT 'transaction type, such as: binary',
  `data`         TEXT COMMENT 'transaction data',
  `gas_price`    VARCHAR(64) COMMENT 'Gas price',
  `gas_limit`    VARCHAR(64) COMMENT 'Gas limit',
  `gas_used`     VARCHAR(64) COMMENT 'Gas used',
  `created_at`   TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `uniq_neb_transaction_hash` (`hash`),
  KEY `idx_neb_transaction_from` (`from`),
  KEY `idx_neb_transaction_to` (`to`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COMMENT 'nebulas transaction';

# DROP TABLE IF EXISTS `neb_pending_transaction`;

CREATE TABLE IF NOT EXISTS `neb_pending_transaction` (
  `id`         CHAR(24)    NOT NULL
  COMMENT 'primary key, generated by program',
  `hash`       VARCHAR(64) NOT NULL
  COMMENT 'hex string of transaction hash',
  `from`       VARCHAR(64) NOT NULL
  COMMENT 'hex string of the sender account address',
  `to`         VARCHAR(64) NOT NULL
  COMMENT 'hex string of the receiver account address',
  `value`      VARCHAR(64)          DEFAULT NULL
  COMMENT 'value of transaction',
  `nonce`      BIGINT(16) UNSIGNED  DEFAULT NULL
  COMMENT 'transaction nonce',
  `timestamp`  DATETIME             DEFAULT NULL
  COMMENT 'transaction timestamp',
  `type`       VARCHAR(32)          DEFAULT NULL
  COMMENT 'transaction type, such as: binary、deploy、core、candidate、delegate',
  `data`       TEXT COMMENT 'transaction data',
  `gas_price`  VARCHAR(64)          DEFAULT NULL
  COMMENT 'Gas price',
  `gas_limit`  VARCHAR(64)          DEFAULT NULL
  COMMENT 'Gas limit',
  `created_at` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_neb_transaction_hash` (`hash`),
  KEY `idx_neb_transaction_from` (`from`),
  KEY `idx_neb_transaction_to` (`to`)
)
  ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COMMENT ='nebulas pending transaction';

# DROP TABLE IF EXISTS `neb_pending_transaction`;

CREATE TABLE IF NOT EXISTS `neb_market_capitalization` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `market_cap` decimal(20,2) DEFAULT NULL,
  `volume_24h` decimal(20,2) DEFAULT NULL,
  `change_24h` decimal(5,2) DEFAULT NULL,
  `trends` tinyint(2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `price_unit` varchar(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE=MyISAM
  DEFAULT CHARSET=utf8mb4
  COMMENT='nebulas market capitalization';

CREATE TABLE IF NOT EXISTS `neb_dynasty` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `block_height`      BIGINT(16) UNSIGNED NOT NULL
  COMMENT 'block height',
  `delegate`       VARCHAR(64)         NOT NULL
  COMMENT 'hex string of delegate address',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE=MyISAM
  DEFAULT CHARSET=utf8mb4
  COMMENT='nebulas dynasty';
