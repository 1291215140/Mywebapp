package poms;

import com.ceshi.BlogMapper;
import com.ceshi.ceshi;
import myclass.query;
import myclass.user;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pom2 implements Runnable
{
    String url;
    pom pom = new pom();
    SqlSession ss = null;
    BlogMapper mapper = null;
    query query = null;
    //最好意图即希望能找到
    public boolean bool = true;
    String  user;
    String  password;
    //这个用来登录
    public pom2(String url,query query,String user,String password) {
        this.url = url;
        this.ss = pom.getsqlSession(url);
        this.mapper = this.ss.getMapper(BlogMapper.class);
        this.query = query;
        this.user = user;
        this.password = password;
    }
    //这个用来注册,只在大的数据库中注册
    public pom2(String user, String password) {
        this.url = "mybaties-mysql-config.xml";
        this.ss = pom.getsqlSession(url);
        this.mapper = this.ss.getMapper(BlogMapper.class);
        this.user = user;
        this.password = password;
    }

    public  boolean zhuce()
    {
        Map<String,String> map = new HashMap<>();
        map.put("user",user);
        boolean bool;
        //如果当前用户名存在返回false
        if(mapper.selectuser(map)!=null) bool = false;
        else
        {
            int size = mapper.selectmaxuid();
            size++;
            map.put("password",password);
            map.put("uid", String.valueOf(size));
            map.put("frequecy", String.valueOf(size));
            mapper.insertuser(map);
            bool = true;
        }
        ss.commit();
        ss.close();
        return  bool;
    }
    //设置一个返回变量
    public user usr = null;
    public boolean denglu()
    {
        Map<String,String> map = new HashMap<>();
        map.put("user",user);
        this.usr = mapper.selectuser(map);
        this.query.set();
        boolean bool = false;
        if(usr!=null&&usr.getPassword().compareTo(password)==0)
        {
            bool = true;
        }
        return  bool;
    }
    public void run() {

        pom2 p = new pom2(this.url,this.query,this.user,this.password);
        this.bool = p.denglu();
        this.usr = p.usr;
        return;
    }
}