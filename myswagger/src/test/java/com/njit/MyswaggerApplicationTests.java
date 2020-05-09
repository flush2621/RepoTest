package com.njit;

import com.njit.entity.StoreCompare;
import com.njit.mapper.StoreCompareMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyswaggerApplicationTests {

    @Autowired
    StoreCompareMapper storeCompareMapper;
    @Test
    void contextLoads() {
        List<StoreCompare> storeCompares = storeCompareMapper.selectList(null);
        storeCompares.forEach(System.out::println);
    }

}
