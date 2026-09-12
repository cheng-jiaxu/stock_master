package com.ming;

import com.ming.stock.pojo.mapper.StockBusinessMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestAll {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StockBusinessMapper stockBusinessMapper;
    /**
     * 测试密码加密
     */
    @Test
    public void testPwd(){
        String pwd = "123456";
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);
        /**
         * matches()匹配铭文密码和加密后密码是否匹配
         */
        boolean flag = passwordEncoder.matches(pwd,"$2a$10$IWrigGynRJCeogQDcLMjLenXdssrQH.GNPqW..S807uhEEoMRkq5u");
        System.out.println(flag);
    }

    @Test
    public void test01(){
        stockBusinessMapper.getStockIds();
    }

}
