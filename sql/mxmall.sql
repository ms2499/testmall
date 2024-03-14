set catalog mallcat;
set schema mallsch;

DROP TRIGGER tr_order_add;
DROP TABLE IF EXISTS orderlists;
DROP TABLE IF EXISTS userorders;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS userinfo;
DROP TABLE IF EXISTS commodities;
DROP TABLE IF EXISTS commodity_tags;
DROP TABLE IF EXISTS manager;

CREATE TABLE commodity_tags (								/*商品種類表*/
	CommoditySubTag VARCHAR(20) NOT NULL PRIMARY KEY,		/*小類*/
	CommodityMainTag VARCHAR(20) NOT NULL 					/*大類*/
);

CREATE INDEX IX_main_tags ON commodity_tags(CommodityMainTag);

--GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1)欄位會自動增加
CREATE TABLE commodities (						/*商品表*/
	CommodityID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY, /*商品編號*/
	CommodityName VARCHAR(30),					/*商品名稱*/
	CommodityQty INT,							/*庫存數量*/
	CommodityPrice INT,							/*價格*/
	CommodityTag VARCHAR(20),					/*商品分類*/
	CommodityImgPath VARCHAR(200),				/*圖片位址*/
	CommodityDetail VARCHAR(500),				/*商品詳細資訊*/
	CommoditySaleFlag NUMERIC(1),				/*是否銷售中*/
	CommodityDiscount NUMERIC(1),				/*是否有折扣*/
	CommodityDisRate NUMERIC(1),				/*幾折*/
	FOREIGN KEY (CommodityTag) REFERENCES commodity_tags(CommoditySubTag) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX IX_commodities_tag ON commodities(CommodityTag);
CREATE INDEX IX_commodities_name ON commodities(CommodityName);
CREATE INDEX IX_commodities_sale ON commodities(CommoditySaleFlag);
CREATE INDEX IX_commodities_discount ON commodities(CommodityDiscount);

CREATE TABLE userinfo (									/*使用者表*/
	UserAccount VARCHAR(30) NOT NULL PRIMARY KEY,		/*帳號*/
	UserPassword CHAR(32) DEFAULT '' NOT NULL,			/*密碼*/
	UserSalt CHAR(60) DEFAULT '' NOT NULL,				/*鹽值*/
	UserName VARCHAR(20),								/*姓名*/
	UserPhone VARCHAR(20),								/*電話*/
	UserEmail VARCHAR(50),								/*email*/
	UserAddress VARCHAR(100),							/*通訊地址*/
	UserMsg VARCHAR(200)        						/*訊息*/
);

CREATE TABLE carts (									/*購物車表*/
	CartSeq BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,    /*購物車流水號*/
	CartAccount VARCHAR(30) NOT NULL,					/*帳號*/
	CartCommodityID BIGINT NOT NULL,					/*商品編號*/
	CartQty INT,										/*購買數量*/
	FOREIGN KEY (CartAccount) REFERENCES userinfo(UserAccount) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (CartCommodityID) REFERENCES commodities(CommodityID) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX IX_carts_account ON carts(CartAccount);

CREATE TABLE userorders (								/*訂單表*/
	OrderNo BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,	/*訂單編號*/
	OrderAccount VARCHAR(30) NOT NULL,					/*帳號*/
	OrderDate TIMESTAMP,								/*日期*/
	OrderTotal BIGINT									/*總金額*/
--  FOREIGN KEY (OrderAccount) REFERENCES userinfo(UserAccount) ON UPDATE CASCADE ON DELETE CASCADE
);

--CREATE INDEX IX_orders_account ON userorders(OrderAccount);

CREATE TABLE orderlists (								/*訂單商品資訊*/
	OrderSeq BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY, /*訂單流水號*/
	OrderNo BIGINT NOT NULL,							/*訂單編號*/
	OrderCommodityID BIGINT NOT NULL,					/*商品編號*/
	OrderQty INT,										/*數量*/	
	OrderPrice BIGINT,									/*總金額*/
	OrderReturn NUMERIC(1),								/*此商品是否退貨*/
	FOREIGN KEY (OrderCommodityID) REFERENCES commodities(CommodityID) ON UPDATE CASCADE ON DELETE RESTRICT
--  FOREIGN KEY (OrderNo) REFERENCES userorders(OrderNo) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE INDEX IX_orderlist_no ON orderlists(OrderNo);

CREATE TABLE manager (								/*後台管理者表*/
	ManID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,   /*ID*/
	ManAccount VARCHAR(30) NOT NULL UNIQUE,			/*帳號*/
	ManPassword CHAR(32) DEFAULT '' NOT NULL,		/*密碼*/
	ManSalt CHAR(60) DEFAULT '' NOT NULL,			/*鹽質*/
	ManName VARCHAR(20),							/*姓名*/
	ManPhone VARCHAR(20),							/*電話*/
	ManEmail VARCHAR(50),							/*email*/
	ManAddress VARCHAR(100),						/*通訊地址*/
	ManMsg VARCHAR(200)								/*備註*/
);

CREATE INDEX IX_manager_account ON manager(ManAccount);

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag) 
VALUES ('水果', '食品');

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag)
VALUES ('肉品', '食品');

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag)
VALUES ('乳製品', '食品');

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag)
VALUES ('蔬菜', '食品');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('西瓜', 100, 100, '水果', 'image/product1.jpg', '西瓜 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('洋蔥', 100, 100, '蔬菜', 'image/product2.jpg', '洋蔥 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('雞肉', 100, 100, '肉品', 'image/product3.jpg', '雞肉 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('高麗菜', 100, 100, '蔬菜', 'image/product4.jpg', '高麗菜 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('馬鈴薯', 100, 100, '蔬菜', 'image/product5.jpg', '馬鈴薯 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('酪梨', 100, 100, '水果', 'image/product6.jpg', '酪梨 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('紅蘿蔔', 100, 100, '蔬菜', 'image/product7.jpg', '紅蘿蔔 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('檸檬', 100, 100, '水果', 'image/product8.jpg', '檸檬 Detail...', 1, 1, 9);

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail, CommoditySaleFlag, CommodityDiscount, CommodityDisRate)
VALUES ('牛奶', 100, 100, '乳製品', 'image/categories3.jpg', '牛奶 Detail...', 1, 0, 0);

INSERT INTO userinfo (UserAccount, UserPassword, UserSalt, UserName, UserPhone, UserEmail, UserAddress, UserMsg)
VALUES ('user1', '37a88aa07f54340f3eaa457e4591850b', 'a5b62ab042f343189495c2d94285c149', '測試員', '01234567890', 'user1@mail', '台北', 'User message...');

--INSERT INTO carts (CartAccount, CartCommodityID, CartQty)
--VALUES ('user1', 1, 2);

--INSERT INTO userorders (OrderAccount, OrderDate, OrderTotal)
--VALUES ('user1', TIMESTAMP '2023-12-13 15:32:31.00', 50);

--INSERT INTO orderlists (OrderNo, OrderCommodityID, OrderQty, OrderPrice, OrderReturn)
--VALUES (1, 1, 2, 200, 0);

--INSERT INTO orderlists (OrderNo, OrderCommodityID, OrderQty, OrderPrice, OrderReturn)
--VALUES (1, 2, 1, 10, 0);

INSERT INTO manager (ManAccount, ManPassword, ManSalt, ManName, ManPhone, ManEmail, ManAddress, ManMsg)
VALUES ('admin', 'fc0f0078f09b07fe5fd45428d6aaf12f', '9bf0684484d740a0b03eb84218d84b82', '管理員', '0912345678', 'admin@gmail.com', '台北市', 'cc');