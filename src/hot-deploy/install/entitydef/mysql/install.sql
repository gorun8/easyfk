/*
 *版本号 
 */
CREATE TABLE IF NOT EXISTS `version`(
  `ID` int(64) NOT NULL auto_increment,
  `VERSION_CODE` int(11) default NULL,
  `VERSION` varchar(255) default NULL,
  `LAST_UPDATE` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `version`(`VERSION_CODE`,`VERSION`,`LAST_UPDATE`) VALUES ('1', '1.0', now());
