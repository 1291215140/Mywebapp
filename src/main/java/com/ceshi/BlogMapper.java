package com.ceshi;
import myclass.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//定义mapper接口
public interface BlogMapper {


    public user selectuser(Map<String,String> map);
    public int selectmaxuid();
    public user insertuser(Map<String,String> map);
    public List<user> selectalluser();
    public void updatefrequecy(Map<String,String> map);
}
