create table if not exists nat_record
(
  id        INTEGER,
  address   varchar(64) not null,
  block     INTEGER,
  txHash    varchar(128),
  amount    varchar(64) default '',
  source    INTEGER,
  timestamp TIMESTAMP,
  createdAt TIMESTAMP default current_timestamp
);

create index nat_record_address_index
  on nat_record (address);

create unique index nat_record_id_uindex
  on nat_record (id);

create index nat_record_source_index
  on nat_record (source);

create index nat_record_txHash_index
  on nat_record (txHash);

create index nat_record_block_index
  on nat_record (block);