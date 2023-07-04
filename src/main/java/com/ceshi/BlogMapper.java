package com.ceshi;
import myclass.user;

import java.util.List;
import java.util.Map;

//定义mapper接口
public interface BlogMapper {


    public user selectuser(Map<String,String> map);
    public List<user> selectall();
    public user insertuser(Map<String,String> map);
}
