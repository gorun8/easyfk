CREATE TABLE IF NOT EXISTS `catalina_session` (
  `SESSION_ID` varchar(60) NOT NULL,
  `SESSION_SIZE` decimal(20,0) default NULL,
  `SESSION_INFO` longblob,
  `IS_VALID` char(1) default NULL,
  `MAX_IDLE` decimal(20,0) default NULL,
  `LAST_ACCESSED` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`SESSION_ID`),
  KEY `CTLN_SSSN_TXSTMP` (`LAST_UPDATED_TX_STAMP`),
  KEY `CTLN_SSSN_TXCRTS` (`CREATED_TX_STAMP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;