# Host: 127.0.0.1  (Version 5.5.44)
# Date: 2017-04-15 14:36:27
# Generator: MySQL-Front 6.0  (Build 1.122)


#
# Structure for table "cli"
#

DROP TABLE IF EXISTS `cli`;
CREATE TABLE `cli` (
  `cli_id` varchar(12) NOT NULL DEFAULT '' COMMENT '客户会员编号',
  `cname` varchar(16) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `ctel` varchar(16) NOT NULL DEFAULT '' COMMENT '客户联系电话',
  `cage` int(3) DEFAULT '0' COMMENT '客户年龄',
  `csex` varchar(1) DEFAULT '' COMMENT '客户性别',
  PRIMARY KEY (`cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

#
# Data for table "cli"
#

INSERT INTO `cli` VALUES ('015261860000','wch','15261860000',20,'男'),('015261861236','张榕容','15261861236',23,'女'),('015261861506','吕斌','15261861506',33,'女'),('015261863036','吴薇薇','15261863036',33,'女'),('015261863326','顾云天','15261863326',41,'男'),('015261866395','张志勇','15261866395',36,'男'),('015261867917','陆明羽','15261867917',33,'女'),('015261868695','李慧慧','15261868695',26,'女'),('015261869876','胡一涛','15261869876',48,'男');

#
# Structure for table "dep"
#

DROP TABLE IF EXISTS `dep`;
CREATE TABLE `dep` (
  `dep_id` varchar(2) NOT NULL DEFAULT '' COMMENT '部门号',
  `dname` varchar(16) NOT NULL DEFAULT '' COMMENT '部门名称',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

#
# Data for table "dep"
#

INSERT INTO `dep` VALUES ('01','总经理办公室'),('02','研发部'),('03','生产部'),('04','销售部'),('05','财务部');

#
# Structure for table "emp"
#

DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `emp_id` varchar(16) NOT NULL DEFAULT '' COMMENT '工号',
  `ename` varchar(16) NOT NULL DEFAULT '' COMMENT '姓名',
  `eposition` varchar(16) NOT NULL DEFAULT '' COMMENT '员工职位',
  `etel` varchar(16) NOT NULL DEFAULT '' COMMENT '员工联系电话',
  `eage` int(3) NOT NULL DEFAULT '0' COMMENT '员工年龄',
  `esex` varchar(1) NOT NULL DEFAULT '' COMMENT '员工性别',
  `dep_id` varchar(3) NOT NULL DEFAULT '' COMMENT '部门号',
  `hiredate` varchar(10) DEFAULT '0000-00-00' COMMENT '入职日期',
  `grade` int(1) NOT NULL DEFAULT '0' COMMENT '管理级别号',
  `income` float(10,2) DEFAULT '0.00' COMMENT '月薪',
  `password` varchar(16) NOT NULL DEFAULT '123456' COMMENT '员工登录密码',
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

#
# Data for table "emp"
#

INSERT INTO `emp` VALUES ('101201409062','林凌期','总经理','15261868888',36,'男','01','2014-09-06',1,12628.83,'123456'),('202201409066','胡小天','经理','15261861968',32,'男','02','2014-09-06',2,7232.45,'123456'),('203201409069','张栋梁','经理','15261867067',30,'男','03','2014-09-06',2,6986.63,'123456'),('204201409061','宋佳佳','经理','15261867693',29,'女','04','2014-09-06',2,6483.79,'123456'),('205201409064','杨睿','经理','15261868857',32,'女','05','2014-09-06',2,6668.56,'123456'),('301201411120','李涵','职员','15261864225',25,'女','01','2014-11-12',3,0.00,'123456'),('301201411302','苏见信','职员','15261869965',31,'男','01','2014-11-30',3,0.00,'123456'),('301201412011','张冬','职员','15261869632',22,'男','01','2014-12-01',3,4563.30,'123456'),('3022014123110','钟志远','职员','15261868896',21,'男','02','2014-12-31',3,0.00,'123456'),('303201412050','董晓燕','职员','15261869763',23,'女','03','2014-12-05',3,0.00,'123456'),('304201410318','顾玮玮','职员','15261865696',27,'女','04','2014-10-31',3,0.00,'123456'),('305201411256','陆羽微','职员','15261863126',29,'女','05','2014-11-25',3,0.00,'123456');

#
# Structure for table "grade"
#

DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade` int(1) NOT NULL AUTO_INCREMENT COMMENT '管理级别号',
  `gname` varchar(16) NOT NULL DEFAULT '' COMMENT '级别名称',
  PRIMARY KEY (`grade`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='管理级别表';

#
# Data for table "grade"
#

INSERT INTO `grade` VALUES (1,'总经理'),(2,'部门经理'),(3,'员工');

#
# Structure for table "miss"
#

DROP TABLE IF EXISTS `miss`;
CREATE TABLE `miss` (
  `Id` int(1) NOT NULL AUTO_INCREMENT COMMENT '任务编号',
  `mission` text COMMENT '任务内容',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='任务内容表';

#
# Data for table "miss"
#

INSERT INTO `miss` VALUES (1,'1.力争在年底前完成目标。\n2.年终奖金为3个月工资。\n3.各部门商讨元旦值班表，并及时上报。\n4.年会定于12.30晚19.00，请所有员工准\n时参加。\n                                                ——总经办');

#
# Structure for table "quarter1"
#

DROP TABLE IF EXISTS `quarter1`;
CREATE TABLE `quarter1` (
  `goods` varchar(20) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "quarter1"
#

INSERT INTO `quarter1` VALUES ('商品1',500),('商品2',495),('商品3',560),('商品4',430),('商品5',482),('商品6',492);

#
# Structure for table "quarter2"
#

DROP TABLE IF EXISTS `quarter2`;
CREATE TABLE `quarter2` (
  `goods` varchar(20) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "quarter2"
#

INSERT INTO `quarter2` VALUES ('商品1',505),('商品2',600),('商品3',450),('商品4',480),('商品5',550),('商品6',430);

#
# Structure for table "quarter3"
#

DROP TABLE IF EXISTS `quarter3`;
CREATE TABLE `quarter3` (
  `goods` varchar(20) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "quarter3"
#

INSERT INTO `quarter3` VALUES ('商品1',520),('商品2',490),('商品3',482),('商品4',466),('商品5',530),('商品6',484);

#
# Structure for table "quarter4"
#

DROP TABLE IF EXISTS `quarter4`;
CREATE TABLE `quarter4` (
  `goods` varchar(20) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "quarter4"
#

INSERT INTO `quarter4` VALUES ('商品1',540),('商品2',530),('商品3',510),('商品4',500),('商品5',480),('商品6',460);
