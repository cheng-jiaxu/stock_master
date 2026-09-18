package com.ming;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ming.stock.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TesrEasyExcel {
    public List<User> init(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("chjx"+i).setAddress("home"+i).setAge(18+i).setBirthday(new Date());
            users.add(user);
        }
        return users;
    }

    @Test
    public void Test01(){
        List<User> users = init();
        EasyExcel.write("/Users/jingjing/test.xlsx",User.class).sheet("用户信息").doWrite(users);

    }
    @Test
    public void Test02(){
        ArrayList<User> users = new ArrayList<>();
        EasyExcel.read("/Users/jingjing/test.xlsx", User.class, new AnalysisEventListener<User>() {
            @Override
            public void invoke(User o, AnalysisContext context) {
                System.out.println(o);
                users.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("完成...");

            }
        }).sheet().doRead();
        System.out.println(users);
    }
}
