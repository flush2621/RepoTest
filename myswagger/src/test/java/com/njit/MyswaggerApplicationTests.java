package com.njit;

import com.njit.entity.StoreCompare;
import com.njit.mapper.StoreCompareMapper;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Function;

@SpringBootTest
class MyswaggerApplicationTests {

    @Autowired
    StoreCompareMapper storeCompareMapper;
    @Test
    void contextLoads() {
        List<StoreCompare> storeCompares = storeCompareMapper.selectList(null);
        storeCompares.forEach(System.out::println);
    }
    @Test
    void testFunc(){
        Function<String, Integer> func1 = x -> x.length();
        Function<Integer, Integer> func2 = x -> x + 1;
        Integer i1 = func1.andThen(func2).apply("liupeng");
        Integer i2 = func2.compose(func1).apply("liupeng");

        System.out.println("i1:"+i1+" i2:"+i2);
    }

}
