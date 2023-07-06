package com.ceshi;
import myclass.query;
import myclass.user;
import org.apache.ibatis.session.SqlSession;
import poms.pom;
import poms.pom2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ceshi
{

    public boolean  panduan(String username,String password) throws InterruptedException {
        query query = new query();
        pom2 p1 = new pom2("mybaties-mysql-config.xml", query,  username, password);
        pom2 p2 = new pom2("mybaties-sqlite-config.xml", query, username, password);

        Thread thread1 = new Thread(p1);
        Thread thread2 = new Thread(p2);
        thread1.start();
        thread2.start();
        int i = 0;
        //只要有一个完成就返回
        while(thread2.getState()!=Thread.State.TERMINATED && thread1.getState()!=Thread.State.TERMINATED);
        //若是快表查完发现没有就等会慢表
        if(p2.usr==null)
        {
            while(thread1.getState()!=Thread.State.TERMINATED );
            return p1.bool;
        }
        //若是快表存在该条数据，则返回该条数据结果，但是也有可能慢的数据库返回的快所以是与
        return p2.bool&&p1.bool;
    }


}
