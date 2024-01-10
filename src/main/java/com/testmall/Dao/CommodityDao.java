package com.testmall.Dao;

//SQLMX資料庫編碼是ISO-8859-1，所有String資料需先使用cstool.iso2utf8方法進行轉碼，傳送到前台才不會亂碼
//前台傳到後台也要先從UTF8轉ISO-8859-1

import com.testmall.Model.Commodity;
import com.testmall.Tools.CharsetTool;
import com.testmall.properties.CustomProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.Charset;
import java.util.List;

@Repository
public class CommodityDao {
    @Autowired
    JdbcTemplate jt;
    CharsetTool cstool = new CharsetTool();

    public List<Commodity> queryAll(){
        String sql = "SELECT * FROM commodities;";

        try{
            List<Commodity> comList =
                jt.query(sql, (rs, n) -> new Commodity(rs.getLong("CommodityID"),
                    rs.getString("CommodityName"),
                    rs.getInt("CommodityQty"),
                    rs.getLong("CommodityPrice"),
                    rs.getString("CommodityTag"),
                    rs.getString("CommodityImgPath"),
                    rs.getString("CommodityDetail"),
                    rs.getByte("CommoditySaleFlag"),
                    rs.getByte("CommodityDiscount"),
                    rs.getByte("CommodityDisRate")));
            for (Commodity c:comList) {
                cstool.pLog(c.getCommodityName()+" ");
                cstool.pLog(c.getCommodityTag()+" ");
                cstool.pLog(c.getCommodityImgPath()+" ");
                cstool.pLogln(c.getCommodityDetail());
            }
            return comList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Commodity queryById(Long id){
        String sql = "SELECT * FROM commodities WHERE CommodityID = " + id + ";";

        try{
            return jt.queryForObject(sql, (rs, n) -> new Commodity(rs.getLong("CommodityID"),
                    rs.getString("CommodityName"),
                    rs.getInt("CommodityQty"),
                    rs.getLong("CommodityPrice"),
                    rs.getString("CommodityTag"),
                    rs.getString("CommodityImgPath"),
                    rs.getString("CommodityDetail"),
                    rs.getByte("CommoditySaleFlag"),
                    rs.getByte("CommodityDiscount"),
                    rs.getByte("CommodityDisRate")));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Commodity> queryByTag(String tag){
        String sql = "SELECT * FROM commodities WHERE CommodityTag = '" + cstool.utf82iso(tag) + "';";

        try{
            List<Commodity> comList =
                    jt.query(sql, (rs, n) -> new Commodity(rs.getLong("CommodityID"),
                            rs.getString("CommodityName"),
                            rs.getInt("CommodityQty"),
                            rs.getLong("CommodityPrice"),
                            rs.getString("CommodityTag"),
                            rs.getString("CommodityImgPath"),
                            rs.getString("CommodityDetail"),
                            rs.getByte("CommoditySaleFlag"),
                            rs.getByte("CommodityDiscount"),
                            rs.getByte("CommodityDisRate")));
            for (Commodity c:comList) {
                cstool.pLog(c.getCommodityName()+" ");
                cstool.pLog(c.getCommodityTag()+" ");
                cstool.pLog(c.getCommodityImgPath()+" ");
                cstool.pLogln(c.getCommodityDetail());
            }
            return comList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
