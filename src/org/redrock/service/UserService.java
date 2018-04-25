package org.redrock.service;

import org.redrock.bean.Rank;
import org.redrock.helper.DatabaseHelper;

import org.redrock.bean.User;
import java.util.List;
import java.util.Map;

public class UserService {

    public List<User> getRankList(){
        String sql = "select t.nickname,t.imgurl,(select count(s.score)+1 from (select s.score,count(s.score) from user s group by score order by score desc) s where s.score>t.score) rank from user t order by t.score desc limit 0,50";
        return DatabaseHelper.queryEntityList(User.class, sql);
    }

    public User getSimpleRank(String openid){
        String sql = "select u.rowNo from (select openid,(@rowNum:=@rowNum+1) as rowNo from user,(select (@rowNum :=0) ) b order by score desc ) u where u.openid=?";
        return DatabaseHelper.queryEntity(User.class, sql, openid);
    }

    public User getUser(String openid){
        String sql = "select nickname,imgurl,score,share,count from user where openid = ?";
        return DatabaseHelper.queryEntity(User.class, sql, openid);
    }


    public boolean createUser(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(User.class, fieldMap);
    }

    public boolean updateScore(String openid, Map<String, Object> fieldMap){
        return DatabaseHelper.updateEntity(User.class, openid, fieldMap);
    }
}
