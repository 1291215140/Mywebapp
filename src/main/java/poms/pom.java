package poms;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//建立一个工具类
public class pom {


    public SqlSession getsqlSession(String url)
    {
        // 读取配置文件mybatis-config.xml
        InputStream config = null;
        SqlSession ss = null;
        try {
            config = Resources.getResourceAsStream(url);
            // 根据配置文件构建SqlSessionFactory
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
            // 通过SqlSessionFactory创建SqlSession
            ss = ssf.openSession();

        } catch (IOException e) {
            System.out.println("报错了");
        }
        return  ss;
    }

}
