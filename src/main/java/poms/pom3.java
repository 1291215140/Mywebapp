package poms;

import com.ceshi.BlogMapper;
import myclass.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pom3 implements  Runnable{
    @Override
    public void run() {
        pom pom = new pom();
        SqlSession ss1 = pom.getsqlSession("mybaties-mysql-config.xml");
        SqlSession ss2 = pom.getsqlSession("mybaties-sqlite-config.xml");
        HashMap<String,String> map = new HashMap<>();
        //作为将两个数据库合并的依据
        //开工！！！！！！！
        BlogMapper mapper1 = ss1.getMapper(BlogMapper.class);
        List<user> listmysql = mapper1.selectalluser();
        int mysqlsize = listmysql.size();
        BlogMapper mapper2 = ss2.getMapper(BlogMapper.class);
        List<user> listsqlite = mapper2.selectalluser();
        int sqlitesize = listsqlite.size();
        //如果小的数据库没有占满
        int min = listmysql.get(0).getFrequency();
        user minuser;
        while(sqlitesize++<100&&mysqlsize--!=0)
        {
            minuser = listmysql.get(0);
            for (user user:listmysql)
            {
                if(minuser.getFrequency()<user.getFrequency())
                {
                    minuser = user;
                }
            }
            listmysql.remove(minuser);
            boolean bool = true;
            for(user user:listsqlite)
            {
                if(user.getUid()==minuser.getUid())
                {
                    bool = false;
                    break;
                }
            }
            if(bool)
            {
                map.put("uid", String.valueOf(minuser.getUid()));
                map.put("user",minuser.getUser());
                map.put("password",minuser.getPassword());
                map.put("frequecy", String.valueOf(minuser.getFrequency()));
                mapper2.insertuser(map);
                if(minuser.getFrequency()>0)mapper1.updatefrequecy(map);
            }

        }
    }
}
