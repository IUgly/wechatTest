package org.redrock.dao;

import org.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.*;

public class RankDao {
    public static List<Map<String, Object>> rankList(){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(0);
        params.add(50);
        String sql = "select t.nickname,t.imgurl,(select count(s.number)+1 from (select s.number,count(s.number) from user s group by number order by number desc) s where s.number>t.number) rank from user t order by t.number desc limit ?,?";
        try {
            return jdbc.findModeResult(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
