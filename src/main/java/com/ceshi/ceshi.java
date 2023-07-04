package com.ceshi;
import myclass.user;
import org.apache.ibatis.session.SqlSession;
import poms.pom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ceshi
{

    private pom p = new pom();
    private SqlSession ss = p.getsqlSession();
    BlogMapper mapper = ss.getMapper(BlogMapper.class);
    public  boolean zhuce(String username,String password,ceshi c)
    {
        Map<String,String> map = new HashMap<>();
        map.put("user",username);
        boolean bool;
        //如果当前用户名存在返回-1
        if(c.mapper.selectuser(map)!=null) bool = false;
        else
        {
            //查看当前表大小
            List<user> list = c.mapper.selectall();
            int size = list.size();
            size++;
            map.put("password",password);
            map.put("uid", String.valueOf(size));
            c.mapper.insertuser(map);
            bool = true;
        }
        c.ss.commit();
        c.ss.close();
        return  bool;
    }
    public boolean denglu(String user,String password,ceshi ceshi)
    {
        Map<String,String> map = new HashMap<>();
        map.put("user",user);
        user usr = mapper.selectuser(map);
        boolean bool = false;
        if(usr!=null&&usr.getPassword().compareTo(password)==0) bool = true;
        ceshi.ss.commit();
        ceshi.ss.close();
        return  bool;
    }

    public static void main(String[] args) {
        ceshi ce = new ceshi();
        boolean b = ce.denglu("tian","dsdada",ce);
        System.out.println(b);
    }
}
