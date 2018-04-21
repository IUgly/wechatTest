package org.redrock.dao;

import org.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StartDao {
    public Map<String, Object> rankDao(List<Object> params){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "select u.rowNo from (select openid,(@rowNum:=@rowNum+1) as rowNo from user,(select (@rowNum :=0) ) b order by number desc ) u where u.openid=?";
        try {
            return jdbc.findSimpleResult(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Map<String, Object>> userInfoDao(List<Object> params){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "select nickname,imgurl,number,share,count from user where openid = ?";
        try {
            return jdbc.findModeResult(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
