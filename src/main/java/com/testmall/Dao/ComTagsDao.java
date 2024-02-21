package com.testmall.Dao;

import com.testmall.Model.CommodityTags;
import com.testmall.Tools.CharsetTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComTagsDao {
    @Autowired
    JdbcTemplate jt;

    CharsetTool cstool = new CharsetTool();

    //查詢小類及大類(進DB call小類/大類,回傳data,確認影響行數不<=0)
    public List<CommodityTags> queryAll(){
        String sql = "SELECT DISTINCT CommoditySubTag, CommodityMainTag FROM commodity_tags";
        //DISTINCT是過濾，可拿掉
        try{
            return jt.query(sql, (rs, n) -> new CommodityTags(
                    rs.getString("CommoditySubTag"),
                    rs.getString("CommodityMainTag")));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //新增小類及大類(確認要新增的為空,新增,確認有改或不為空)
    public String addCommodityTag(CommodityTags tags) {
        String sql = "INSERT INTO commodity_tags(CommoditySubTag, CommodityMainTag) VALUES (?, ?)";
        try {
            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(tags.getCommoditySubTag()),
                    cstool.utf82iso(tags.getCommodityMainTag())
            );
            return "新增" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "ComTagsDao.addCommodityTag");
            return "資料庫新增失敗!";
            /*pLogIn是用來寫入日誌的功能，在這個例子中，它至少接受兩個參數：
            第一個參數 e.toString() 是異常物件的字串表示形式，
            第二個參數 "ComTagsDao.addCommoditySubTag" 則是用來標識這條日誌訊息的相關程式碼位置或行為。*/
        }
    }


    //刪除小類及大類(確認有該小類或大類,刪除,確認為空)
    // delete from commodity_tags where CommoditySubTag = " "
    public String deleteCommodityTag(String tag) {
        try {
            // 建立 SQL 語句
            String sql = "DELETE FROM commodity_tags WHERE CommoditySubTag = ?";

            int rowsAffected = jt.update(sql, cstool.utf82iso(tag));

            return "0000";
        } catch (Exception e) {
            cstool.pLogln(e.toString(), "CommodityDao.deleteCommodityTag");
            return "9999";
        }
    }





    //更新大類(找到指定大類,更新資料,確認影響行數不<=0)
    public String updateCommodityTag(CommodityTags tags) {
        String sql = "UPDATE commodity_tags SET CommodityMainTag = ? WHERE CommoditySubTag = ? ";
        try {
            int rowsAffected = jt.update(sql,
                    cstool.utf82iso(tags.getCommodityMainTag()),
                    cstool.utf82iso(tags.getCommoditySubTag())
            );
            return "更新" + rowsAffected + "筆資料!";
        }
        catch (Exception e){
            cstool.pLogln(e.toString(), "ComTagsDao.updateCommodityTag");
            return "資料庫更新失敗!";
        }
    }
}