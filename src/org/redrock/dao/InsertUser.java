package org.redrock.dao;

import org.redrock.utils.JDBC;

import java.sql.SQLException;
import java.util.List;

public class InsertUser {
    public static boolean InsertUser(List<Object> params) throws SQLException {
        JDBC jdbc = new JDBC();
        jdbc.getConnection();
        String sql = "insert into user" +
                "(openid,nickname,imgurl) values" +
                "(?,?,?)";
        return jdbc.updateByPreparedStatement(sql, params);
    }
}
