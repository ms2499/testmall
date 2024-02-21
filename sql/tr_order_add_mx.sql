set catalog mallcat;
set schema mallsch;

DROP TRIGGER tr_order_add;

CREATE TRIGGER tr_order_add
AFTER INSERT ON orderlists
REFERENCING NEW AS newodr
FOR EACH ROW
UPDATE commodities SET CommodityQty = (
(SELECT CommodityQty FROM commodities WHERE CommodityID = newodr.OrderCommodityID) -
newodr.OrderQty) WHERE CommodityID = newodr.OrderCommodityID;