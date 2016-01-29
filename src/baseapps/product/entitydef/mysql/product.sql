/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */
 CREATE TABLE  IF NOT EXISTS  `product` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_TYPE_ID` varchar(20) default NULL,
  `PRODUCT_TMP_ID` varchar(20) default NULL,
  `PAPER_ID` varchar(20) default NULL,
  `PUBLISHED` char(1) default NULL,
  `SALEABLE` char(1) default NULL,
  `STAR_COUNT` decimal(20,0) default NULL,
  `PRIMARY_PRODUCT_CATEGORY_ID` varchar(20) default NULL,
  `MANUFACTURER_PARTY_ID` varchar(20) default NULL,
  `FACILITY_ID` varchar(20) default NULL,
  `INTRODUCTION_DATE` datetime default NULL,
  `RELEASE_DATE` datetime default NULL,
  `SUPPORT_DISCONTINUATION_DATE` datetime default NULL,
  `SALES_DISCONTINUATION_DATE` datetime default NULL,
  `SALES_DISC_WHEN_NOT_AVAIL` char(1) default NULL,
  `INTERNAL_NAME` varchar(255) default NULL,
  `BRAND_NAME` varchar(100) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `PRODUCT_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LONG_DESCRIPTION` longtext,
  `BOOK_DESCRIPTION` longtext,
  `PRICE_DETAIL_TEXT` varchar(255) default NULL,
  `SMALL_IMAGE_URL` varchar(255) default NULL,
  `MEDIUM_IMAGE_URL` varchar(255) default NULL,
  `LARGE_IMAGE_URL` varchar(255) default NULL,
  `DETAIL_IMAGE_URL` varchar(255) default NULL,
  `ORIGINAL_IMAGE_URL` varchar(255) default NULL,
  `DETAIL_SCREEN` varchar(255) default NULL,
  `INVENTORY_MESSAGE` varchar(255) default NULL,
  `REQUIRE_INVENTORY` char(1) default NULL,
  `QUANTITY_UOM_ID` varchar(20) default NULL,
  `QUANTITY_INCLUDED` decimal(18,6) default NULL,
  `PIECES_INCLUDED` decimal(20,0) default NULL,
  `REQUIRE_AMOUNT` char(1) default NULL,
  `FIXED_AMOUNT` decimal(18,2) default NULL,
  `AMOUNT_UOM_TYPE_ID` varchar(20) default NULL,
  `WEIGHT_UOM_ID` varchar(20) default NULL,
  `WEIGHT` decimal(18,6) default NULL,
  `PRODUCT_WEIGHT` decimal(18,6) default NULL,
  `HEIGHT_UOM_ID` varchar(20) default NULL,
  `PRODUCT_HEIGHT` decimal(18,6) default NULL,
  `SHIPPING_HEIGHT` decimal(18,6) default NULL,
  `WIDTH_UOM_ID` varchar(20) default NULL,
  `PRODUCT_WIDTH` decimal(18,6) default NULL,
  `SHIPPING_WIDTH` decimal(18,6) default NULL,
  `DEPTH_UOM_ID` varchar(20) default NULL,
  `PRODUCT_DEPTH` decimal(18,6) default NULL,
  `SHIPPING_DEPTH` decimal(18,6) default NULL,
  `DIAMETER_UOM_ID` varchar(20) default NULL,
  `PRODUCT_DIAMETER` decimal(18,6) default NULL,
  `PRODUCT_RATING` decimal(18,6) default NULL,
  `RATING_TYPE_ENUM` varchar(20) default NULL,
  `RETURNABLE` char(1) default NULL,
  `TAXABLE` char(1) default NULL,
  `CHARGE_SHIPPING` char(1) default NULL,
  `AUTO_CREATE_KEYWORDS` char(1) default NULL,
  `INCLUDE_IN_PROMOTIONS` char(1) default NULL,
  `IS_VIRTUAL` char(1) default NULL,
  `IS_VARIANT` char(1) default NULL,
  `VIRTUAL_VARIANT_METHOD_ENUM` varchar(20) default NULL,
  `ORIGIN_GEO_ID` varchar(20) default NULL,
  `REQUIREMENT_METHOD_ENUM_ID` varchar(20) default NULL,
  `BILL_OF_MATERIAL_LEVEL` decimal(20,0) default NULL,
  `RESERV_MAX_PERSONS` decimal(18,6) default NULL,
  `RESERV2ND_P_P_PERC` decimal(18,6) default NULL,
  `RESERV_NTH_P_P_PERC` decimal(18,6) default NULL,
  `CONFIG_ID` varchar(20) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_DATE` datetime default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `IN_SHIPPING_BOX` char(1) default NULL,
  `DEFAULT_SHIPMENT_BOX_TYPE_ID` varchar(20) default NULL,
  `LOT_ID_FILLED_IN` varchar(255) default NULL,
  `ORDER_DECIMAL_QUANTITY` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  `TEACHER_PARTY_ID` varchar(250) default NULL,
  PRIMARY KEY  (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_assoc
-- ----------------------------

CREATE TABLE `product_assoc` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_ID_TO` varchar(20) NOT NULL,
  `PRODUCT_ASSOC_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `REASON` varchar(255) default NULL,
  `QUANTITY` decimal(18,6) default NULL,
  `SCRAP_FACTOR` decimal(18,6) default NULL,
  `INSTRUCTION` varchar(255) default NULL,
  `ROUTING_WORK_EFFORT_ID` varchar(20) default NULL,
  `ESTIMATE_CALC_METHOD` varchar(20) default NULL,
  `RECURRENCE_INFO_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PRODUCT_ID_TO`,`PRODUCT_ASSOC_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_assoc_type
-- ----------------------------
CREATE TABLE `product_assoc_type` (
  `PRODUCT_ASSOC_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ASSOC_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
CREATE TABLE `product_attribute` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `ATRR_ITEM_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) default NULL,
  `ATTR_VALUE` varchar(255) default NULL,
  `ATTR_TYPE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`ATRR_ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_attribute_item
-- ----------------------------
CREATE TABLE `product_attribute_item` (
  `MODEL_ID` varchar(20) default NULL,
  `ATRR_ITEM_ID` varchar(20) NOT NULL,
  `ATRR_ITEM_TYPE_ID` varchar(20) default NULL,
  `ATRR_ITEM_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ATRR_ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_attribute_model
-- ----------------------------

CREATE TABLE `product_attribute_model` (
  `MODEL_ID` varchar(20) NOT NULL,
  `MODEL_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`MODEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_attribute_option
-- ----------------------------

CREATE TABLE `product_attribute_option` (
  `ATRR_ITEM_ID` varchar(20) default NULL,
  `ATTR_OPTION_ID` varchar(20) NOT NULL,
  `ATTR_OPTION_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ATTR_OPTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_average_cost
-- ----------------------------

CREATE TABLE `product_average_cost` (
  `PRODUCT_AVERAGE_COST_TYPE_ID` varchar(20) NOT NULL,
  `ORGANIZATION_PARTY_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `FACILITY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `AVERAGE_COST` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_AVERAGE_COST_TYPE_ID`,`ORGANIZATION_PARTY_ID`,`PRODUCT_ID`,`FACILITY_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_average_cost_type
-- ----------------------------

CREATE TABLE `product_average_cost_type` (
  `PRODUCT_AVERAGE_COST_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_AVERAGE_COST_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_calculated_info
-- ----------------------------

CREATE TABLE `product_calculated_info` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `TOTAL_QUANTITY_ORDERED` decimal(18,6) default NULL,
  `TOTAL_TIMES_VIEWED` decimal(20,0) default NULL,
  `AVERAGE_CUSTOMER_RATING` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------

CREATE TABLE `product_category` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `PRODUCT_CATEGORY_TYPE_ID` varchar(20) default NULL,
  `PRIMARY_PARENT_CATEGORY_ID` varchar(20) default NULL,
  `CATEGORY_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LONG_DESCRIPTION` longtext,
  `CATEGORY_IMAGE_URL` varchar(255) default NULL,
  `LINK_ONE_IMAGE_URL` varchar(255) default NULL,
  `LINK_TWO_IMAGE_URL` varchar(255) default NULL,
  `DETAIL_SCREEN` varchar(255) default NULL,
  `SHOW_IN_SELECT` char(1) default NULL,
  `CATEGORY_LEVEL` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_attribute
-- ----------------------------

CREATE TABLE `product_category_attribute` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `ATTR_VALUE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_content
-- ----------------------------

CREATE TABLE `product_category_content` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `CONTENT_ID` varchar(20) NOT NULL,
  `PROD_CAT_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PURCHASE_FROM_DATE` datetime default NULL,
  `PURCHASE_THRU_DATE` datetime default NULL,
  `USE_COUNT_LIMIT` decimal(20,0) default NULL,
  `USE_DAYS_LIMIT` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`CONTENT_ID`,`PROD_CAT_CONTENT_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_content_type
-- ----------------------------

CREATE TABLE `product_category_content_type` (
  `PROD_CAT_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PROD_CAT_CONTENT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_gl_account
-- ----------------------------
CREATE TABLE `product_category_gl_account` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `ORGANIZATION_PARTY_ID` varchar(20) NOT NULL,
  `GL_ACCOUNT_TYPE_ID` varchar(20) NOT NULL,
  `GL_ACCOUNT_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`ORGANIZATION_PARTY_ID`,`GL_ACCOUNT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_link
-- ----------------------------

CREATE TABLE `product_category_link` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `LINK_SEQ_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `COMMENTS` varchar(255) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `TITLE_TEXT` varchar(255) default NULL,
  `DETAIL_TEXT` longtext,
  `IMAGE_URL` varchar(255) default NULL,
  `IMAGE_TWO_URL` varchar(255) default NULL,
  `LINK_TYPE_ENUM_ID` varchar(20) default NULL,
  `LINK_INFO` varchar(255) default NULL,
  `DETAIL_SUB_SCREEN` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`LINK_SEQ_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_member
-- ----------------------------

CREATE TABLE `product_category_member` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `COMMENTS` varchar(255) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `QUANTITY` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`PRODUCT_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_role
-- ----------------------------

CREATE TABLE `product_category_role` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `COMMENTS` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`PARTY_ID`,`ROLE_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_rollup
-- ----------------------------

CREATE TABLE `product_category_rollup` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `PARENT_PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`PARENT_PRODUCT_CATEGORY_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_type
-- ----------------------------

CREATE TABLE `product_category_type` (
  `PRODUCT_CATEGORY_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_category_type_attr
-- ----------------------------

CREATE TABLE `product_category_type_attr` (
  `PRODUCT_CATEGORY_TYPE_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_TYPE_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config
-- ----------------------------
CREATE TABLE `product_config` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `CONFIG_ITEM_ID` varchar(20) NOT NULL,
  `SEQUENCE_NUM` decimal(20,0) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LONG_DESCRIPTION` longtext,
  `CONFIG_TYPE_ID` varchar(20) default NULL,
  `DEFAULT_CONFIG_OPTION_ID` varchar(20) default NULL,
  `THRU_DATE` datetime default NULL,
  `IS_MANDATORY` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`CONFIG_ITEM_ID`,`SEQUENCE_NUM`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config_config
-- ----------------------------
CREATE TABLE `product_config_config` (
  `CONFIG_ID` varchar(20) NOT NULL,
  `CONFIG_ITEM_ID` varchar(20) NOT NULL,
  `SEQUENCE_NUM` decimal(20,0) NOT NULL,
  `CONFIG_OPTION_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONFIG_ID`,`CONFIG_ITEM_ID`,`CONFIG_OPTION_ID`,`SEQUENCE_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config_item
-- ----------------------------
CREATE TABLE `product_config_item` (
  `CONFIG_ITEM_ID` varchar(20) NOT NULL,
  `CONFIG_ITEM_TYPE_ID` varchar(20) default NULL,
  `CONFIG_ITEM_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LONG_DESCRIPTION` longtext,
  `IMAGE_URL` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONFIG_ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config_option
-- ----------------------------

CREATE TABLE `product_config_option` (
  `CONFIG_ITEM_ID` varchar(20) NOT NULL,
  `CONFIG_OPTION_ID` varchar(20) NOT NULL,
  `CONFIG_OPTION_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONFIG_ITEM_ID`,`CONFIG_OPTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config_option_iactn
-- ----------------------------

CREATE TABLE `product_config_option_iactn` (
  `CONFIG_ITEM_ID` varchar(20) NOT NULL,
  `CONFIG_OPTION_ID` varchar(20) NOT NULL,
  `CONFIG_ITEM_ID_TO` varchar(20) NOT NULL,
  `CONFIG_OPTION_ID_TO` varchar(20) NOT NULL,
  `SEQUENCE_NUM` decimal(20,0) NOT NULL,
  `CONFIG_IACTN_TYPE_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONFIG_ITEM_ID`,`CONFIG_OPTION_ID`,`CONFIG_ITEM_ID_TO`,`CONFIG_OPTION_ID_TO`,`SEQUENCE_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config_product
-- ----------------------------
CREATE TABLE `product_config_product` (
  `CONFIG_ITEM_ID` varchar(20) NOT NULL,
  `CONFIG_OPTION_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `QUANTITY` decimal(18,6) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONFIG_ITEM_ID`,`CONFIG_OPTION_ID`,`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_config_stats
-- ----------------------------
CREATE TABLE `product_config_stats` (
  `CONFIG_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `NUM_OF_CONFS` decimal(20,0) default NULL,
  `CONFIG_TYPE_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONFIG_ID`,`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_content
-- ----------------------------
CREATE TABLE `product_content` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `CONTENT_ID` varchar(20) NOT NULL,
  `PRODUCT_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PURCHASE_FROM_DATE` datetime default NULL,
  `PURCHASE_THRU_DATE` datetime default NULL,
  `USE_COUNT_LIMIT` decimal(20,0) default NULL,
  `USE_TIME` decimal(20,0) default NULL,
  `USE_TIME_UOM_ID` varchar(20) default NULL,
  `USE_ROLE_TYPE_ID` varchar(20) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`CONTENT_ID`,`PRODUCT_CONTENT_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_content_type
-- ----------------------------
CREATE TABLE `product_content_type` (
  `PRODUCT_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CONTENT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_cost_component_calc
-- ----------------------------

CREATE TABLE `product_cost_component_calc` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `COST_COMPONENT_TYPE_ID` varchar(20) NOT NULL,
  `COST_COMPONENT_CALC_ID` varchar(20) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`COST_COMPONENT_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_facility
-- ----------------------------
CREATE TABLE `product_facility` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `FACILITY_ID` varchar(20) NOT NULL,
  `MINIMUM_STOCK` decimal(18,6) default NULL,
  `REORDER_QUANTITY` decimal(18,6) default NULL,
  `DAYS_TO_SHIP` decimal(20,0) default NULL,
  `LAST_INVENTORY_COUNT` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`FACILITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_facility_location
-- ----------------------------
CREATE TABLE `product_facility_location` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `FACILITY_ID` varchar(20) NOT NULL,
  `LOCATION_SEQ_ID` varchar(20) NOT NULL,
  `MINIMUM_STOCK` decimal(18,6) default NULL,
  `MOVE_QUANTITY` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`FACILITY_ID`,`LOCATION_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature
-- ----------------------------
CREATE TABLE `product_feature` (
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_TYPE_ID` varchar(20) default NULL,
  `PRODUCT_FEATURE_CATEGORY_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `UOM_ID` varchar(20) default NULL,
  `NUMBER_SPECIFIED` decimal(18,6) default NULL,
  `DEFAULT_AMOUNT` decimal(18,2) default NULL,
  `DEFAULT_SEQUENCE_NUM` decimal(20,0) default NULL,
  `ABBREV` varchar(20) default NULL,
  `ID_CODE` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_ID`),
  KEY `PROD_FEAT_CATEGORY` (`PRODUCT_FEATURE_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_appl
-- ----------------------------
CREATE TABLE `product_feature_appl` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_APPL_TYPE_ID` varchar(20) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `AMOUNT` decimal(18,2) default NULL,
  `RECURRING_AMOUNT` decimal(18,2) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PRODUCT_FEATURE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_appl_attr
-- ----------------------------
CREATE TABLE `product_feature_appl_attr` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `ATTR_VALUE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PRODUCT_FEATURE_ID`,`FROM_DATE`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_appl_type
-- ----------------------------
CREATE TABLE `product_feature_appl_type` (
  `PRODUCT_FEATURE_APPL_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_APPL_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_cat_grp_appl
-- ----------------------------
CREATE TABLE `product_feature_cat_grp_appl` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_GROUP_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`PRODUCT_FEATURE_GROUP_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_category
-- ----------------------------
CREATE TABLE `product_feature_category` (
  `PRODUCT_FEATURE_CATEGORY_ID` varchar(20) NOT NULL,
  `PARENT_CATEGORY_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_category_appl
-- ----------------------------
CREATE TABLE `product_feature_category_appl` (
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_CATEGORY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_CATEGORY_ID`,`PRODUCT_FEATURE_CATEGORY_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_data_resource
-- ----------------------------
CREATE TABLE `product_feature_data_resource` (
  `DATA_RESOURCE_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`DATA_RESOURCE_ID`,`PRODUCT_FEATURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_group
-- ----------------------------
CREATE TABLE `product_feature_group` (
  `PRODUCT_FEATURE_GROUP_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_group_appl
-- ----------------------------
CREATE TABLE `product_feature_group_appl` (
  `PRODUCT_FEATURE_GROUP_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_CATEGORY_ID` varchar(20) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_GROUP_ID`,`PRODUCT_FEATURE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_iactn
-- ----------------------------
CREATE TABLE `product_feature_iactn` (
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_ID_TO` varchar(20) NOT NULL,
  `PRODUCT_FEATURE_IACTN_TYPE_ID` varchar(20) default NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_ID`,`PRODUCT_FEATURE_ID_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_iactn_type
-- ----------------------------
CREATE TABLE `product_feature_iactn_type` (
  `PRODUCT_FEATURE_IACTN_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_IACTN_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_price
-- ----------------------------
CREATE TABLE `product_feature_price` (
  `PRODUCT_FEATURE_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_TYPE_ID` varchar(20) NOT NULL,
  `CURRENCY_UOM_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PRICE` decimal(18,3) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_DATE` datetime default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_ID`,`PRODUCT_PRICE_TYPE_ID`,`CURRENCY_UOM_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_feature_type
-- ----------------------------
CREATE TABLE `product_feature_type` (
  `PRODUCT_FEATURE_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_FEATURE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_geo
-- ----------------------------
CREATE TABLE `product_geo` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `GEO_ID` varchar(20) NOT NULL,
  `PRODUCT_GEO_ENUM_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`GEO_ID`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_gl_account
-- ----------------------------
DROP TABLE IF EXISTS `product_gl_account`;
CREATE TABLE `product_gl_account` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `ORGANIZATION_PARTY_ID` varchar(20) NOT NULL,
  `GL_ACCOUNT_TYPE_ID` varchar(20) NOT NULL,
  `GL_ACCOUNT_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`ORGANIZATION_PARTY_ID`,`GL_ACCOUNT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_group_order
-- ----------------------------
CREATE TABLE `product_group_order` (
  `GROUP_ORDER_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `REQ_ORDER_QTY` decimal(18,6) default NULL,
  `SOLD_ORDER_QTY` decimal(18,6) default NULL,
  `JOB_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`GROUP_ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_keyword
-- ----------------------------
CREATE TABLE `product_keyword` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `KEYWORD` varchar(60) NOT NULL,
  `RELEVANCY_WEIGHT` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`KEYWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_keyword_new
-- ----------------------------
CREATE TABLE `product_keyword_new` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `KEYWORD` varchar(60) NOT NULL,
  `KEYWORD_TYPE_ID` varchar(20) NOT NULL,
  `RELEVANCY_WEIGHT` decimal(20,0) default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`KEYWORD`,`KEYWORD_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_keyword_result
-- ----------------------------
CREATE TABLE `product_keyword_result` (
  `PRODUCT_KEYWORD_RESULT_ID` varchar(20) NOT NULL,
  `VISIT_ID` varchar(20) default NULL,
  `PRODUCT_CATEGORY_ID` varchar(20) default NULL,
  `SEARCH_STRING` varchar(60) default NULL,
  `INTRA_KEYWORD_OPERATOR` varchar(10) default NULL,
  `ANY_PREFIX` char(1) default NULL,
  `ANY_SUFFIX` char(1) default NULL,
  `REMOVE_STEMS` char(1) default NULL,
  `NUM_RESULTS` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_KEYWORD_RESULT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_maint
-- ----------------------------
CREATE TABLE `product_maint` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_MAINT_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_MAINT_TYPE_ID` varchar(20) default NULL,
  `MAINT_NAME` varchar(100) default NULL,
  `MAINT_TEMPLATE_WORK_EFFORT_ID` varchar(20) default NULL,
  `INTERVAL_QUANTITY` decimal(18,6) default NULL,
  `INTERVAL_UOM_ID` varchar(20) default NULL,
  `INTERVAL_METER_TYPE_ID` varchar(20) default NULL,
  `REPEAT_COUNT` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PRODUCT_MAINT_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_maint_type
-- ----------------------------
CREATE TABLE `product_maint_type` (
  `PRODUCT_MAINT_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_MAINT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_manufacturing_rule
-- ----------------------------
CREATE TABLE `product_manufacturing_rule` (
  `RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `PRODUCT_ID_FOR` varchar(20) default NULL,
  `PRODUCT_ID_IN` varchar(20) default NULL,
  `RULE_SEQ_ID` varchar(20) default NULL,
  `FROM_DATE` datetime default NULL,
  `PRODUCT_ID_IN_SUBST` varchar(20) default NULL,
  `PRODUCT_FEATURE` varchar(20) default NULL,
  `RULE_OPERATOR` varchar(20) default NULL,
  `QUANTITY` double default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`RULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_meter
-- ----------------------------
CREATE TABLE `product_meter` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_METER_TYPE_ID` varchar(20) NOT NULL,
  `METER_UOM_ID` varchar(20) default NULL,
  `METER_NAME` varchar(100) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PRODUCT_METER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_meter_type
-- ----------------------------
CREATE TABLE `product_meter_type` (
  `PRODUCT_METER_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `DEFAULT_UOM_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_METER_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_order_item
-- ----------------------------
CREATE TABLE `product_order_item` (
  `ORDER_ID` varchar(20) NOT NULL,
  `ORDER_ITEM_SEQ_ID` varchar(20) NOT NULL,
  `ENGAGEMENT_ID` varchar(20) NOT NULL,
  `ENGAGEMENT_ITEM_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ORDER_ID`,`ORDER_ITEM_SEQ_ID`,`ENGAGEMENT_ID`,`ENGAGEMENT_ITEM_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_payment_method_type
-- ----------------------------
CREATE TABLE `product_payment_method_type` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PAYMENT_METHOD_TYPE_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_PURPOSE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PAYMENT_METHOD_TYPE_ID`,`PRODUCT_PRICE_PURPOSE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price
-- ----------------------------
CREATE TABLE `product_price` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_TYPE_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_PURPOSE_ID` varchar(20) NOT NULL,
  `CURRENCY_UOM_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_GROUP_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PRICE` decimal(18,3) default NULL,
  `TERM_UOM_ID` varchar(20) default NULL,
  `CUSTOM_PRICE_CALC_SERVICE` varchar(20) default NULL,
  `PRICE_WITHOUT_TAX` decimal(18,3) default NULL,
  `PRICE_WITH_TAX` decimal(18,3) default NULL,
  `TAX_AMOUNT` decimal(18,3) default NULL,
  `TAX_PERCENTAGE` decimal(18,6) default NULL,
  `TAX_AUTH_PARTY_ID` varchar(20) default NULL,
  `TAX_AUTH_GEO_ID` varchar(20) default NULL,
  `TAX_IN_PRICE` char(1) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_DATE` datetime default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PRODUCT_PRICE_TYPE_ID`,`PRODUCT_PRICE_PURPOSE_ID`,`CURRENCY_UOM_ID`,`PRODUCT_STORE_GROUP_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_action
-- ----------------------------
CREATE TABLE `product_price_action` (
  `PRODUCT_PRICE_RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_ACTION_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_ACTION_TYPE_ID` varchar(20) default NULL,
  `AMOUNT` decimal(18,6) default NULL,
  `RATE_CODE` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_RULE_ID`,`PRODUCT_PRICE_ACTION_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_action_type
-- ----------------------------
CREATE TABLE `product_price_action_type` (
  `PRODUCT_PRICE_ACTION_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_ACTION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_auto_notice
-- ----------------------------
CREATE TABLE `product_price_auto_notice` (
  `PRODUCT_PRICE_NOTICE_ID` varchar(20) NOT NULL,
  `FACILITY_ID` varchar(20) default NULL,
  `RUN_DATE` datetime default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_NOTICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_change
-- ----------------------------
CREATE TABLE `product_price_change` (
  `PRODUCT_PRICE_CHANGE_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `PRODUCT_PRICE_TYPE_ID` varchar(20) default NULL,
  `PRODUCT_PRICE_PURPOSE_ID` varchar(20) default NULL,
  `CURRENCY_UOM_ID` varchar(20) default NULL,
  `PRODUCT_STORE_GROUP_ID` varchar(20) default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `PRICE` decimal(18,2) default NULL,
  `OLD_PRICE` decimal(18,2) default NULL,
  `CHANGED_DATE` datetime default NULL,
  `CHANGED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_CHANGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_cond
-- ----------------------------
CREATE TABLE `product_price_cond` (
  `PRODUCT_PRICE_RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_PRICE_COND_SEQ_ID` varchar(20) NOT NULL,
  `INPUT_PARAM_ENUM_ID` varchar(20) default NULL,
  `OPERATOR_ENUM_ID` varchar(20) default NULL,
  `COND_VALUE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_RULE_ID`,`PRODUCT_PRICE_COND_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for product_price_purpose
-- ----------------------------
CREATE TABLE `product_price_purpose` (
  `PRODUCT_PRICE_PURPOSE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_PURPOSE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_rule
-- ----------------------------
CREATE TABLE `product_price_rule` (
  `PRODUCT_PRICE_RULE_ID` varchar(20) NOT NULL,
  `RULE_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `IS_SALE` char(1) default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_RULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_price_type
-- ----------------------------
CREATE TABLE `product_price_type` (
  `PRODUCT_PRICE_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PRICE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo
-- ----------------------------
CREATE TABLE `product_promo` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `PROMO_NAME` varchar(100) default NULL,
  `PROMO_TEXT` varchar(255) default NULL,
  `USER_ENTERED` char(1) default NULL,
  `SHOW_TO_CUSTOMER` char(1) default NULL,
  `REQUIRE_CODE` char(1) default NULL,
  `USE_LIMIT_PER_ORDER` decimal(20,0) default NULL,
  `USE_LIMIT_PER_CUSTOMER` decimal(20,0) default NULL,
  `USE_LIMIT_PER_PROMOTION` decimal(20,0) default NULL,
  `BILLBACK_FACTOR` decimal(18,6) default NULL,
  `OVERRIDE_ORG_PARTY_ID` varchar(20) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_DATE` datetime default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_action
-- ----------------------------
CREATE TABLE `product_promo_action` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ACTION_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ACTION_ENUM_ID` varchar(20) default NULL,
  `ORDER_ADJUSTMENT_TYPE_ID` varchar(20) default NULL,
  `SERVICE_NAME` varchar(255) default NULL,
  `QUANTITY` decimal(18,6) default NULL,
  `AMOUNT` decimal(18,6) default NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `PARTY_ID` varchar(20) default NULL,
  `USE_CART_QUANTITY` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`,`PRODUCT_PROMO_RULE_ID`,`PRODUCT_PROMO_ACTION_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_category
-- ----------------------------
CREATE TABLE `product_promo_category` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ACTION_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_COND_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_CATEGORY_ID` varchar(20) NOT NULL,
  `AND_GROUP_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_APPL_ENUM_ID` varchar(20) default NULL,
  `INCLUDE_SUB_CATEGORIES` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`,`PRODUCT_PROMO_RULE_ID`,`PRODUCT_PROMO_ACTION_SEQ_ID`,`PRODUCT_PROMO_COND_SEQ_ID`,`PRODUCT_CATEGORY_ID`,`AND_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_code
-- ----------------------------
CREATE TABLE `product_promo_code` (
  `PRODUCT_PROMO_CODE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ID` varchar(20) default NULL,
  `USER_ENTERED` char(1) default NULL,
  `REQUIRE_EMAIL_OR_PARTY` char(1) default NULL,
  `USE_LIMIT_PER_CODE` decimal(20,0) default NULL,
  `USE_LIMIT_PER_CUSTOMER` decimal(20,0) default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_DATE` datetime default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_CODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_code_email
-- ----------------------------
CREATE TABLE `product_promo_code_email` (
  `PRODUCT_PROMO_CODE_ID` varchar(20) NOT NULL,
  `EMAIL_ADDRESS` varchar(255) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_CODE_ID`,`EMAIL_ADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_code_party
-- ----------------------------
CREATE TABLE `product_promo_code_party` (
  `PRODUCT_PROMO_CODE_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_CODE_ID`,`PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_cond
-- ----------------------------
CREATE TABLE `product_promo_cond` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_COND_SEQ_ID` varchar(20) NOT NULL,
  `INPUT_PARAM_ENUM_ID` varchar(20) default NULL,
  `OPERATOR_ENUM_ID` varchar(20) default NULL,
  `COND_VALUE` varchar(255) default NULL,
  `OTHER_VALUE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`,`PRODUCT_PROMO_RULE_ID`,`PRODUCT_PROMO_COND_SEQ_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_content
-- ----------------------------
CREATE TABLE `product_promo_content` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `CONTENT_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`,`CONTENT_ID`,`PRODUCT_PROMO_CONTENT_TYPE_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_product
-- ----------------------------
CREATE TABLE `product_promo_product` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_RULE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ACTION_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_COND_SEQ_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_APPL_ENUM_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`,`PRODUCT_PROMO_RULE_ID`,`PRODUCT_PROMO_ACTION_SEQ_ID`,`PRODUCT_PROMO_COND_SEQ_ID`,`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_rule
-- ----------------------------
CREATE TABLE `product_promo_rule` (
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_RULE_ID` varchar(20) NOT NULL,
  `RULE_NAME` varchar(100) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_PROMO_ID`,`PRODUCT_PROMO_RULE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_promo_use
-- ----------------------------
CREATE TABLE `product_promo_use` (
  `ORDER_ID` varchar(20) NOT NULL,
  `PROMO_SEQUENCE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ID` varchar(20) default NULL,
  `PRODUCT_PROMO_CODE_ID` varchar(20) default NULL,
  `PARTY_ID` varchar(20) default NULL,
  `TOTAL_DISCOUNT_AMOUNT` decimal(18,2) default NULL,
  `QUANTITY_LEFT_IN_ACTIONS` decimal(18,6) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ORDER_ID`,`PROMO_SEQUENCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_question
-- ----------------------------
CREATE TABLE `product_question` (
  `QUESTION_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `TITLE` varchar(100) default NULL,
  `QCLASS` char(1) default NULL,
  `QTYPE` char(1) default NULL,
  `OPTIONS` varchar(255) default NULL,
  `RESULT` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`QUESTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_question_result
-- ----------------------------
CREATE TABLE `product_question_result` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `QCLASS` char(1) NOT NULL,
  `STUDENT` varchar(20) NOT NULL,
  `CLASS_ID` varchar(20) default NULL,
  `QUESTION_COUNT` decimal(20,0) default NULL,
  `QUESTION_RIGHT` decimal(20,0) default NULL,
  `ANWSER_DATA` longtext,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`QCLASS`,`STUDENT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_review
-- ----------------------------
CREATE TABLE `product_review` (
  `PRODUCT_REVIEW_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_ID` varchar(20) default NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `USER_LOGIN_ID` varchar(250) default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `POSTED_ANONYMOUS` char(1) default NULL,
  `POSTED_DATE_TIME` datetime default NULL,
  `PRODUCT_RATING` decimal(18,6) default NULL,
  `PRODUCT_REVIEW` longtext,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_REVIEW_ID`),
  KEY `PROD_REVIEW_PRDSTR` (`PRODUCT_STORE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_role
-- ----------------------------
CREATE TABLE `product_role` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`PARTY_ID`,`ROLE_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_search_constraint
-- ----------------------------
CREATE TABLE `product_search_constraint` (
  `PRODUCT_SEARCH_RESULT_ID` varchar(20) NOT NULL,
  `CONSTRAINT_SEQ_ID` varchar(20) NOT NULL,
  `CONSTRAINT_NAME` varchar(255) default NULL,
  `INFO_STRING` varchar(255) default NULL,
  `INCLUDE_SUB_CATEGORIES` char(1) default NULL,
  `IS_AND` char(1) default NULL,
  `ANY_PREFIX` char(1) default NULL,
  `ANY_SUFFIX` char(1) default NULL,
  `REMOVE_STEMS` char(1) default NULL,
  `LOW_VALUE` varchar(60) default NULL,
  `HIGH_VALUE` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_SEARCH_RESULT_ID`,`CONSTRAINT_SEQ_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_search_result
-- ----------------------------
CREATE TABLE `product_search_result` (
  `PRODUCT_SEARCH_RESULT_ID` varchar(20) NOT NULL,
  `VISIT_ID` varchar(20) default NULL,
  `ORDER_BY_NAME` varchar(255) default NULL,
  `IS_ASCENDING` char(1) default NULL,
  `NUM_RESULTS` decimal(20,0) default NULL,
  `SECONDS_TOTAL` double default NULL,
  `SEARCH_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_SEARCH_RESULT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_star_user_login
-- ----------------------------
CREATE TABLE `product_star_user_login` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `USER_LOGIN_ID` varchar(250) NOT NULL,
  `INFOS` varchar(100) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`USER_LOGIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store
-- ----------------------------
CREATE TABLE `product_store` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `PRIMARY_STORE_GROUP_ID` varchar(20) default NULL,
  `STORE_NAME` varchar(100) default NULL,
  `COMPANY_NAME` varchar(100) default NULL,
  `TITLE` varchar(100) default NULL,
  `SUBTITLE` varchar(255) default NULL,
  `PAY_TO_PARTY_ID` varchar(20) default NULL,
  `DAYS_TO_CANCEL_NON_PAY` decimal(20,0) default NULL,
  `MANUAL_AUTH_IS_CAPTURE` char(1) default NULL,
  `PRORATE_SHIPPING` char(1) default NULL,
  `PRORATE_TAXES` char(1) default NULL,
  `VIEW_CART_ON_ADD` char(1) default NULL,
  `AUTO_SAVE_CART` char(1) default NULL,
  `AUTO_APPROVE_REVIEWS` char(1) default NULL,
  `IS_DEMO_STORE` char(1) default NULL,
  `IS_IMMEDIATELY_FULFILLED` char(1) default NULL,
  `INVENTORY_FACILITY_ID` varchar(20) default NULL,
  `ONE_INVENTORY_FACILITY` char(1) default NULL,
  `CHECK_INVENTORY` char(1) default NULL,
  `RESERVE_INVENTORY` char(1) default NULL,
  `RESERVE_ORDER_ENUM_ID` varchar(20) default NULL,
  `REQUIRE_INVENTORY` char(1) default NULL,
  `BALANCE_RES_ON_ORDER_CREATION` char(1) default NULL,
  `REQUIREMENT_METHOD_ENUM_ID` varchar(20) default NULL,
  `ORDER_NUMBER_PREFIX` varchar(60) default NULL,
  `DEFAULT_LOCALE_STRING` varchar(10) default NULL,
  `DEFAULT_CURRENCY_UOM_ID` varchar(20) default NULL,
  `DEFAULT_SALES_CHANNEL_ENUM_ID` varchar(20) default NULL,
  `ALLOW_PASSWORD` char(1) default NULL,
  `DEFAULT_PASSWORD` varchar(255) default NULL,
  `EXPLODE_ORDER_ITEMS` char(1) default NULL,
  `CHECK_GC_BALANCE` char(1) default NULL,
  `RETRY_FAILED_AUTHS` char(1) default NULL,
  `HEADER_APPROVED_STATUS` varchar(20) default NULL,
  `ITEM_APPROVED_STATUS` varchar(20) default NULL,
  `DIGITAL_ITEM_APPROVED_STATUS` varchar(20) default NULL,
  `HEADER_DECLINED_STATUS` varchar(20) default NULL,
  `ITEM_DECLINED_STATUS` varchar(20) default NULL,
  `HEADER_CANCEL_STATUS` varchar(20) default NULL,
  `ITEM_CANCEL_STATUS` varchar(20) default NULL,
  `AUTH_DECLINED_MESSAGE` varchar(255) default NULL,
  `AUTH_FRAUD_MESSAGE` varchar(255) default NULL,
  `AUTH_ERROR_MESSAGE` varchar(255) default NULL,
  `VISUAL_THEME_ID` varchar(20) default NULL,
  `STORE_CREDIT_ACCOUNT_ENUM_ID` varchar(20) default NULL,
  `USE_PRIMARY_EMAIL_USERNAME` char(1) default NULL,
  `REQUIRE_CUSTOMER_ROLE` char(1) default NULL,
  `AUTO_INVOICE_DIGITAL_ITEMS` char(1) default NULL,
  `REQ_SHIP_ADDR_FOR_DIG_ITEMS` char(1) default NULL,
  `SHOW_CHECKOUT_GIFT_OPTIONS` char(1) default NULL,
  `SELECT_PAYMENT_TYPE_PER_ITEM` char(1) default NULL,
  `SHOW_PRICES_WITH_VAT_TAX` char(1) default NULL,
  `SHOW_TAX_IS_EXEMPT` char(1) default NULL,
  `VAT_TAX_AUTH_GEO_ID` varchar(20) default NULL,
  `VAT_TAX_AUTH_PARTY_ID` varchar(20) default NULL,
  `ENABLE_AUTO_SUGGESTION_LIST` char(1) default NULL,
  `ENABLE_DIG_PROD_UPLOAD` char(1) default NULL,
  `PROD_SEARCH_EXCLUDE_VARIANTS` char(1) default NULL,
  `DIG_PROD_UPLOAD_CATEGORY_ID` varchar(20) default NULL,
  `AUTO_ORDER_CC_TRY_EXP` char(1) default NULL,
  `AUTO_ORDER_CC_TRY_OTHER_CARDS` char(1) default NULL,
  `AUTO_ORDER_CC_TRY_LATER_NSF` char(1) default NULL,
  `AUTO_ORDER_CC_TRY_LATER_MAX` decimal(20,0) default NULL,
  `STORE_CREDIT_VALID_DAYS` decimal(20,0) default NULL,
  `AUTO_APPROVE_INVOICE` char(1) default NULL,
  `AUTO_APPROVE_ORDER` char(1) default NULL,
  `SHIP_IF_CAPTURE_FAILS` char(1) default NULL,
  `SET_OWNER_UPON_ISSUANCE` char(1) default NULL,
  `REQ_RETURN_INVENTORY_RECEIVE` char(1) default NULL,
  `ADD_TO_CART_REMOVE_INCOMPAT` char(1) default NULL,
  `ADD_TO_CART_REPLACE_UPSELL` char(1) default NULL,
  `SPLIT_PAY_PREF_PER_SHP_GRP` char(1) default NULL,
  `MANAGED_BY_LOT` char(1) default NULL,
  `SHOW_OUT_OF_STOCK_PRODUCTS` char(1) default NULL,
  `ORDER_DECIMAL_QUANTITY` char(1) default NULL,
  `STYLE_SHEET` varchar(255) default NULL,
  `HEADER_LOGO` varchar(255) default NULL,
  `HEADER_MIDDLE_BACKGROUND` varchar(255) default NULL,
  `HEADER_RIGHT_BACKGROUND` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_catalog
-- ----------------------------
CREATE TABLE `product_store_catalog` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `PROD_CATALOG_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`PROD_CATALOG_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_email_setting
-- ----------------------------
CREATE TABLE `product_store_email_setting` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `EMAIL_TYPE` varchar(20) NOT NULL,
  `BODY_SCREEN_LOCATION` varchar(255) default NULL,
  `XSLFO_ATTACH_SCREEN_LOCATION` varchar(255) default NULL,
  `FROM_ADDRESS` varchar(255) default NULL,
  `CC_ADDRESS` varchar(255) default NULL,
  `BCC_ADDRESS` varchar(255) default NULL,
  `SUBJECT` varchar(255) default NULL,
  `CONTENT_TYPE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`EMAIL_TYPE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_facility
-- ----------------------------
CREATE TABLE `product_store_facility` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `FACILITY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`FACILITY_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_fin_act_setting
-- ----------------------------
CREATE TABLE `product_store_fin_act_setting` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `FIN_ACCOUNT_TYPE_ID` varchar(20) NOT NULL,
  `REQUIRE_PIN_CODE` char(1) default NULL,
  `VALIDATE_G_C_FIN_ACCT` char(1) default NULL,
  `ACCOUNT_CODE_LENGTH` decimal(20,0) default NULL,
  `PIN_CODE_LENGTH` decimal(20,0) default NULL,
  `ACCOUNT_VALID_DAYS` decimal(20,0) default NULL,
  `AUTH_VALID_DAYS` decimal(20,0) default NULL,
  `PURCHASE_SURVEY_ID` varchar(20) default NULL,
  `PURCH_SURVEY_SEND_TO` varchar(20) default NULL,
  `PURCH_SURVEY_COPY_ME` varchar(20) default NULL,
  `ALLOW_AUTH_TO_NEGATIVE` char(1) default NULL,
  `MIN_BALANCE` decimal(18,2) default NULL,
  `REPLENISH_THRESHOLD` decimal(18,2) default NULL,
  `REPLENISH_METHOD_ENUM_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`FIN_ACCOUNT_TYPE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_group
-- ----------------------------
CREATE TABLE `product_store_group` (
  `PRODUCT_STORE_GROUP_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_GROUP_TYPE_ID` varchar(20) default NULL,
  `PRIMARY_PARENT_GROUP_ID` varchar(20) default NULL,
  `PRODUCT_STORE_GROUP_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_group_member
-- ----------------------------
CREATE TABLE `product_store_group_member` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_GROUP_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`PRODUCT_STORE_GROUP_ID`,`FROM_DATE`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_group_role
-- ----------------------------
CREATE TABLE `product_store_group_role` (
  `PRODUCT_STORE_GROUP_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_GROUP_ID`,`PARTY_ID`,`ROLE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_group_rollup
-- ----------------------------
CREATE TABLE `product_store_group_rollup` (
  `PRODUCT_STORE_GROUP_ID` varchar(20) NOT NULL,
  `PARENT_GROUP_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_GROUP_ID`,`PARENT_GROUP_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_group_type
-- ----------------------------
CREATE TABLE `product_store_group_type` (
  `PRODUCT_STORE_GROUP_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_GROUP_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_keyword_ovrd
-- ----------------------------
CREATE TABLE `product_store_keyword_ovrd` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `KEYWORD` varchar(60) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `TARGET` varchar(255) default NULL,
  `TARGET_TYPE_ENUM_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`KEYWORD`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_payment_setting
-- ----------------------------
CREATE TABLE `product_store_payment_setting` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `PAYMENT_METHOD_TYPE_ID` varchar(20) NOT NULL,
  `PAYMENT_SERVICE_TYPE_ENUM_ID` varchar(20) NOT NULL,
  `PAYMENT_SERVICE` varchar(255) default NULL,
  `PAYMENT_CUSTOM_METHOD_ID` varchar(20) default NULL,
  `PAYMENT_GATEWAY_CONFIG_ID` varchar(20) default NULL,
  `PAYMENT_PROPERTIES_PATH` varchar(255) default NULL,
  `APPLY_TO_ALL_PRODUCTS` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`PAYMENT_METHOD_TYPE_ID`,`PAYMENT_SERVICE_TYPE_ENUM_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_promo_appl
-- ----------------------------
CREATE TABLE `product_store_promo_appl` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `PRODUCT_PROMO_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `MANUAL_ONLY` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`PRODUCT_PROMO_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_role
-- ----------------------------
CREATE TABLE `product_store_role` (
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`ROLE_TYPE_ID`,`PRODUCT_STORE_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_shipment_meth
-- ----------------------------
CREATE TABLE `product_store_shipment_meth` (
  `PRODUCT_STORE_SHIP_METH_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_ID` varchar(20) default NULL,
  `SHIPMENT_METHOD_TYPE_ID` varchar(20) default NULL,
  `PARTY_ID` varchar(20) default NULL,
  `ROLE_TYPE_ID` varchar(20) default NULL,
  `COMPANY_PARTY_ID` varchar(20) default NULL,
  `MIN_WEIGHT` decimal(18,6) default NULL,
  `MAX_WEIGHT` decimal(18,6) default NULL,
  `MIN_SIZE` decimal(18,6) default NULL,
  `MAX_SIZE` decimal(18,6) default NULL,
  `MIN_TOTAL` decimal(18,2) default NULL,
  `MAX_TOTAL` decimal(18,2) default NULL,
  `ALLOW_USPS_ADDR` char(1) default NULL,
  `REQUIRE_USPS_ADDR` char(1) default NULL,
  `ALLOW_COMPANY_ADDR` char(1) default NULL,
  `REQUIRE_COMPANY_ADDR` char(1) default NULL,
  `INCLUDE_NO_CHARGE_ITEMS` char(1) default NULL,
  `INCLUDE_FEATURE_GROUP` varchar(20) default NULL,
  `EXCLUDE_FEATURE_GROUP` varchar(20) default NULL,
  `INCLUDE_GEO_ID` varchar(20) default NULL,
  `EXCLUDE_GEO_ID` varchar(20) default NULL,
  `SERVICE_NAME` varchar(255) default NULL,
  `CONFIG_PROPS` varchar(255) default NULL,
  `SHIPMENT_CUSTOM_METHOD_ID` varchar(20) default NULL,
  `SHIPMENT_GATEWAY_CONFIG_ID` varchar(20) default NULL,
  `SEQUENCE_NUMBER` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_SHIP_METH_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_survey_appl
-- ----------------------------
CREATE TABLE `product_store_survey_appl` (
  `PRODUCT_STORE_SURVEY_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_ID` varchar(20) default NULL,
  `SURVEY_APPL_TYPE_ID` varchar(20) default NULL,
  `GROUP_NAME` varchar(100) default NULL,
  `SURVEY_ID` varchar(20) default NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `PRODUCT_CATEGORY_ID` varchar(20) default NULL,
  `FROM_DATE` datetime default NULL,
  `THRU_DATE` datetime default NULL,
  `SURVEY_TEMPLATE` varchar(255) default NULL,
  `RESULT_TEMPLATE` varchar(255) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_SURVEY_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_vendor_payment
-- ----------------------------
CREATE TABLE `product_store_vendor_payment` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `VENDOR_PARTY_ID` varchar(20) NOT NULL,
  `PAYMENT_METHOD_TYPE_ID` varchar(20) NOT NULL,
  `CREDIT_CARD_ENUM_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`VENDOR_PARTY_ID`,`PAYMENT_METHOD_TYPE_ID`,`CREDIT_CARD_ENUM_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_store_vendor_shipment
-- ----------------------------
CREATE TABLE `product_store_vendor_shipment` (
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `VENDOR_PARTY_ID` varchar(20) NOT NULL,
  `SHIPMENT_METHOD_TYPE_ID` varchar(20) NOT NULL,
  `CARRIER_PARTY_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_STORE_ID`,`VENDOR_PARTY_ID`,`SHIPMENT_METHOD_TYPE_ID`,`CARRIER_PARTY_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_subscription_resource
-- ----------------------------
CREATE TABLE `product_subscription_resource` (
  `PRODUCT_ID` varchar(20) NOT NULL,
  `SUBSCRIPTION_RESOURCE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PURCHASE_FROM_DATE` datetime default NULL,
  `PURCHASE_THRU_DATE` datetime default NULL,
  `MAX_LIFE_TIME` decimal(20,0) default NULL,
  `MAX_LIFE_TIME_UOM_ID` varchar(20) default NULL,
  `AVAILABLE_TIME` decimal(20,0) default NULL,
  `AVAILABLE_TIME_UOM_ID` varchar(20) default NULL,
  `USE_COUNT_LIMIT` decimal(20,0) default NULL,
  `USE_TIME` decimal(20,0) default NULL,
  `USE_TIME_UOM_ID` varchar(20) default NULL,
  `USE_ROLE_TYPE_ID` varchar(20) default NULL,
  `AUTOMATIC_EXTEND` char(1) default NULL,
  `CANCL_AUTM_EXT_TIME` decimal(20,0) default NULL,
  `CANCL_AUTM_EXT_TIME_UOM_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_ID`,`SUBSCRIPTION_RESOURCE_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_template
-- ----------------------------
CREATE TABLE `product_template` (
  `PRODUCT_TMP_ID` varchar(20) NOT NULL,
  `TEMPLATE_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `PUBLISHED` char(1) default NULL,
  `PRODUCT_TYPE_ID` varchar(100) default NULL,
  `ATTRIBUTE_MODEL_ID` varchar(20) default NULL,
  `STAND_FEATURE_GROUP_ID` varchar(20) default NULL,
  `CHOISE_FEATURE_GROUP_ID` varchar(20) default NULL,
  `CONFIG_ITEM_ID` varchar(20) default NULL,
  `IS_VIRTUAL` char(1) default NULL,
  `IS_VARIANT` char(1) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_TMP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
CREATE TABLE `product_type` (
  `PRODUCT_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `IS_PHYSICAL` char(1) default NULL,
  `IS_DIGITAL` char(1) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_type_attr
-- ----------------------------
CREATE TABLE `product_type_attr` (
  `PRODUCT_TYPE_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PRODUCT_TYPE_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
