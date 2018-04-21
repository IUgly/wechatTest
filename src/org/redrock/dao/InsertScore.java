package org.redrock.dao;

import org.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.List;

public class InsertScore {
    public int InsertScore(List<Object> params){
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "update user set number=? where openid = ?";
        try {
            jdbc.updateByPreparedStatement(sql, params);
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
    }
}
