package pers.johnx.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * @BelongsProject: SpringDataRedisDemo
 * @BelongsPackage: pers.johnx.test
 * @Author: Administrator
 * @Description: 测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-redis.xml")
public class RTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setValue1(){
        redisTemplate.boundValueOps("name").set("youjiuye");
    }
    @Test
    public void getValue(){
        String str = (String) redisTemplate.boundValueOps("name").get();
        System.out.println(str);
    }
    @Test
    public void deleteValue1(){
        redisTemplate.delete("name");;
    }


    /**
     * 存入值
     */
    @Test
    public void setValue2(){
        redisTemplate.boundSetOps("nameset").add("曹操");
        redisTemplate.boundSetOps("nameset").add("刘备");
        redisTemplate.boundSetOps("nameset").add("孙权");
    }

    /**
     * 提取值
     */
    @Test
    public void getValue2(){
        Set members = redisTemplate.boundSetOps("nameset").members();
        System.out.println(members);
    }

    /**
     * 删除集合中的某一个值
     */
    @Test
    public void deleteValue2(){
        redisTemplate.boundSetOps("nameset").remove("孙权");
    }

    /**
     * 删除整个集合
     */
    @Test
    public void deleteAllValue2(){
        redisTemplate.delete("nameset");
    }


    /**
     * 右压栈：后添加的对象排在后边
     */
    @Test
    public void testSetValue3(){
        redisTemplate.boundListOps("namelist1").rightPush("刘备");
        redisTemplate.boundListOps("namelist1").rightPush("关羽");
        redisTemplate.boundListOps("namelist1").rightPush("张飞");
    }

    /**
     * 显示右压栈集合
     */
    @Test
    public void testGetValue3(){
        List list = redisTemplate.boundListOps("namelist1").range(0, 10);
        System.out.println(list);
    }

}
