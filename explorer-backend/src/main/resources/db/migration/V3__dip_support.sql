create table if not exists explorer_test.neb_dip_award
(
  id           int auto_increment primary key,
  contract     varchar(40)                         not null
  comment '中奖合约地址',
  creator      varchar(40)                         not null
  comment '中奖合约的创建者',
  award        bigint default '0'                  null
  comment '奖金',
  tx_hash      varchar(64)                         null
  comment '发放奖金的交易Hash',
  tx_timestamp timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP
  comment '发放奖金的交易时间',
  year         int                                 null
  comment '年份',
  week         int                                 null
  comment 'week of year (第几周的奖励)',
  constraint neb_dip_award_id_uindex unique (id),
  index neb_dip_award_value_index (award),
  index neb_dip_award_week_year_index (week, year)
) default charset='utf8' comment 'DIP发奖记录';


