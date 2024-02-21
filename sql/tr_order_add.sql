CREATE TRIGGER tr_order_add ON orderlists AFTER INSERT AS
DECLARE @odrComId BIGINT
DECLARE @odrQty INT
DECLARE @comQty INT
SET NOCOUNT ON
SELECT @odrComId = OrderCommodityID, @odrQty = OrderQty FROM INSERTED
SELECT @comQty = CommodityQty FROM commodities WHERE CommodityID = @odrComId
UPDATE commodities SET CommodityQty = (@comQty - @odrQty) WHERE CommodityID = @odrComId