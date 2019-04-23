DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '�û���',
  `password` varchar(64) DEFAULT NULL COMMENT '����',
  `nickname` varchar(64) DEFAULT NULL COMMENT '�ǳ�',
  `phone` varchar(64) DEFAULT NULL COMMENT '�ֻ�����',
  `status` int(1) DEFAULT NULL COMMENT '�ʺ�����״̬:0->���ã�1->����',
  `create_time` datetime DEFAULT NULL COMMENT 'ע��ʱ��',
  `icon` varchar(500) DEFAULT NULL COMMENT 'ͷ��',
  `gender` int(1) DEFAULT NULL COMMENT '�Ա�0->δ֪��1->�У�2->Ů',
  `birthday` date DEFAULT NULL COMMENT '����',
  `city` varchar(64) DEFAULT NULL COMMENT '���ڳ���',
  `job` varchar(100) DEFAULT NULL COMMENT 'ְҵ',
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '����ǩ��',
  `integration` int(11) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='��Ա��';


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '��Ʒ��������',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '������',
  `description` text COMMENT '��Ʒ����',
  `pic` varchar(255) DEFAULT NULL,
  `ISBN` varchar(64) NOT NULL COMMENT '����',
  `delete_status` int(1) DEFAULT NULL COMMENT 'ɾ��״̬��0->δɾ����1->��ɾ��',
  `publish_status` int(1) DEFAULT NULL COMMENT '�ϼ�״̬��0->�¼ܣ�1->�ϼ�',
  `new_status` int(1) DEFAULT NULL COMMENT '��Ʒ״̬:0->������Ʒ��1->��Ʒ',

  `sort` int(11) DEFAULT NULL COMMENT '����',
  `sale` int(11) DEFAULT NULL COMMENT '����',
  `price` decimal(10,2) DEFAULT NULL,


  `original_price` decimal(10,2) DEFAULT NULL COMMENT '�г���',
  `stock` int(11) DEFAULT NULL COMMENT '���',
  `low_stock` int(11) DEFAULT NULL COMMENT '���Ԥ��ֵ',


  `keywords` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `detail_title` varchar(255) DEFAULT NULL,
  `detail_desc` text,


  `gift_point` int(11) DEFAULT '0' COMMENT '���͵Ļ���',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='��Ʒ��Ϣ';





DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `product_count` int(11) DEFAULT NULL,
  `nav_status` int(1) DEFAULT NULL COMMENT '�Ƿ���ʾ�ڵ�������0->����ʾ��1->��ʾ',
  `show_status` int(1) DEFAULT NULL COMMENT '��ʾ״̬��0->����ʾ��1->��ʾ',
  `sort` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT 'ͼ��',
  `keywords` varchar(255) DEFAULT NULL,
  `description` text COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='��Ʒ����';


DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sub_title` varchar(255) DEFAULT NULL,
  `pic` varbinary(500) DEFAULT NULL COMMENT 'չʾͼƬ',
  `sort` int(11) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='�Ƽ�ר��';


INSERT INTO `recommend` VALUES ('1', '����', '����', null, null, '1');
INSERT INTO `recommend` VALUES ('2', '����������ʿ', '����������ʿ', null, null, null);
INSERT INTO `recommend` VALUES ('3', '����', null, null, null, null);
INSERT INTO `recommend` VALUES ('4', '�˼�ʧ��', null, null, null, null);


DROP TABLE IF EXISTS `recommend_product_relation`;
CREATE TABLE `recommend_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recommend_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='�Ƽ�ר�����鼮��ϵ��';


DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `pic` varchar(500) DEFAULT NULL COMMENT 'ר����ͼ',
  `product_count` int(11) DEFAULT NULL COMMENT '������Ʒ����',
  `create_time` datetime DEFAULT NULL,
  `collect_count` int(11) DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL COMMENT '��ʾ״̬��0->����ʾ��1->��ʾ',
  `content` text,
  `category_name` varchar(200) DEFAULT NULL COMMENT 'ר���������',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='ר���';



DROP TABLE IF EXISTS `subject_product_relation`;
CREATE TABLE `subject_product_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='ר����Ʒ��ϵ��';



DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `ISBN` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL COMMENT '��������',
  `price` decimal(10,2) DEFAULT NULL COMMENT '��ӵ����ﳵ�ļ۸�',
  `product_pic` varchar(1000) DEFAULT NULL COMMENT '��Ʒ��ͼ',
  `product_name` varchar(500) DEFAULT NULL COMMENT '��Ʒ����',
  `member_nickname` varchar(500) DEFAULT NULL COMMENT '��Ա�ǳ�',
  `create_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `modify_date` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  `delete_status` int(1) DEFAULT '0' COMMENT '�Ƿ�ɾ��',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '��Ʒ����',
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='���ﳵ��';

DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_name` varchar(200) DEFAULT NULL COMMENT '��ַ����',
  `send_status` int(1) DEFAULT NULL COMMENT 'Ĭ�Ϸ�����ַ��0->��1->��',
  `receive_status` int(1) DEFAULT NULL COMMENT '�Ƿ�Ĭ���ջ���ַ��0->��1->��',
  `name` varchar(64) DEFAULT NULL COMMENT '�շ���������',
  `phone` varchar(64) DEFAULT NULL COMMENT '�ջ��˵绰',
  `province` varchar(64) DEFAULT NULL COMMENT 'ʡ/ֱϽ��',
  `city` varchar(64) DEFAULT NULL COMMENT '��',
  `region` varchar(64) DEFAULT NULL COMMENT '��',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '��ϸ��ַ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='�û��ջ���ַ��';

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `user_id` bigint(20) NOT NULL,
  `order_sn` varchar(64) DEFAULT NULL COMMENT '�������',
  `create_time` datetime DEFAULT NULL COMMENT '�ύʱ��',
  `user_username` varchar(64) DEFAULT NULL COMMENT '�û��ʺ�',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '�����ܽ��',
  `status` int(1) DEFAULT NULL COMMENT '����״̬��0->�����1->��������2->�ѷ�����3->����ɣ�4->�ѹرգ�5->��Ч����',

  `delivery_sn` varchar(64) DEFAULT NULL COMMENT '��������',
  `integration` int(11) DEFAULT NULL COMMENT '���Ի�õĻ���',

  `receiver_name` varchar(100) NOT NULL COMMENT '�ջ�������',
  `receiver_phone` varchar(32) NOT NULL COMMENT '�ջ��˵绰',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '�ջ����ʱ�',
  `receiver_province` varchar(32) DEFAULT NULL COMMENT 'ʡ��/ֱϽ��',
  `receiver_city` varchar(32) DEFAULT NULL COMMENT '����',
  `receiver_region` varchar(32) DEFAULT NULL COMMENT '��',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '��ϸ��ַ',
  `note` varchar(500) DEFAULT NULL COMMENT '������ע',


  `confirm_status` int(1) DEFAULT NULL COMMENT 'ȷ���ջ�״̬��0->δȷ�ϣ�1->��ȷ��',
  `delete_status` int(1) NOT NULL DEFAULT '0' COMMENT 'ɾ��״̬��0->δɾ����1->��ɾ��',

  `payment_time` datetime DEFAULT NULL COMMENT '֧��ʱ��',
  `delivery_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `receive_time` datetime DEFAULT NULL COMMENT 'ȷ���ջ�ʱ��',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='������';





DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '����id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '�������',
  `product_id` bigint(20) DEFAULT NULL,
  `product_pic` varchar(500) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `ISBN` varchar(64) DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '���ۼ۸�',
  `product_quantity` int(11) DEFAULT NULL COMMENT '��������',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '��Ʒ����id',


  `gift_integration` int(11) DEFAULT '0',
  `gift_growth` int(11) DEFAULT '0',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='����������������Ʒ';





DROP TABLE IF EXISTS `order_return_apply`;
CREATE TABLE `order_return_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '����id',
  `company_address_id` bigint(20) DEFAULT NULL COMMENT '�ջ���ַ��id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '�˻���Ʒid',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '�������',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `user_username` varchar(64) DEFAULT NULL COMMENT '��Ա�û���',
  `return_amount` decimal(10,2) DEFAULT NULL COMMENT '�˿���',
  `return_name` varchar(100) DEFAULT NULL COMMENT '�˻�������',
  `return_phone` varchar(100) DEFAULT NULL COMMENT '�˻��˵绰',
  `status` int(1) DEFAULT NULL COMMENT '����״̬��0->������1->�˻��У�2->����ɣ�3->�Ѿܾ�',
  `handle_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `product_pic` varchar(500) DEFAULT NULL COMMENT '��ƷͼƬ',
  `product_name` varchar(200) DEFAULT NULL COMMENT '��Ʒ����',

  `product_count` int(11) DEFAULT NULL COMMENT '�˻�����',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '��Ʒ����',
  `product_real_price` decimal(10,2) DEFAULT NULL COMMENT '��Ʒʵ��֧������',
  `reason` varchar(200) DEFAULT NULL COMMENT 'ԭ��',
  `description` varchar(500) DEFAULT NULL COMMENT '����',
  `proof_pics` varchar(1000) DEFAULT NULL COMMENT 'ƾ֤ͼƬ���Զ��Ÿ���',
  `handle_note` varchar(500) DEFAULT NULL COMMENT '����ע',
  `handle_man` varchar(100) DEFAULT NULL COMMENT '������Ա',
  `receive_man` varchar(100) DEFAULT NULL COMMENT '�ջ���',
  `receive_time` datetime DEFAULT NULL COMMENT '�ջ�ʱ��',
  `receive_note` varchar(500) DEFAULT NULL COMMENT '�ջ���ע',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='�����˻�����';




DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `user_nick_name` varchar(255) DEFAULT NULL,
 `user_icon` varchar(255) DEFAULT NULL COMMENT '�����û�ͷ��',
  `product_name` varchar(255) DEFAULT NULL,
  `star` int(3) DEFAULT NULL COMMENT '����������0->5',

  `create_time` datetime DEFAULT NULL,
  `show_status` int(1) DEFAULT NULL,

  `content` text,
  `pics` varchar(1000) DEFAULT NULL COMMENT '�ϴ�ͼƬ��ַ���Զ��Ÿ���',

  `user_ip` varchar(64) DEFAULT NULL COMMENT '���۵�ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��Ʒ���۱�';






DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT 'ͷ��',
  `email` varchar(100) DEFAULT NULL COMMENT '����',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '�ǳ�',
  `note` varchar(500) DEFAULT NULL COMMENT '��ע��Ϣ',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `login_time` datetime DEFAULT NULL COMMENT '����¼ʱ��',
  `status` int(1) DEFAULT '1' COMMENT '�ʺ�����״̬��0->���ã�1->����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='��̨�û���';



DROP TABLE IF EXISTS `admin_login_log`;
CREATE TABLE `admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '�������¼����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='��̨�û���¼��־��';




DROP TABLE IF EXISTS `admin_permission_relation`;
CREATE TABLE `admin_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��̨�û���Ȩ�޹�ϵ��(����ɫ�ж����Ȩ������ļӼ�Ȩ��)';



DROP TABLE IF EXISTS `admin_role_relation`;
CREATE TABLE `admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='��̨�û��ͽ�ɫ��ϵ��';




