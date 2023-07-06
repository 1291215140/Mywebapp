package myclass;

import java.util.List;

public class query {
    boolean bool = false;
    //将当前方法锁住
    public synchronized void set()
    {
        //如果是表示一个已经返回结果集了
        if(bool)return;
        else
        {
            this.bool = true;
            return;
        }
    }

    public boolean isBool() {
        return bool;
    }

}
