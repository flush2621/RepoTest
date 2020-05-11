package com.njit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njit.entity.StoreCompare;
import com.njit.entity.StoreNoModel;
import com.njit.mapper.StoreCompareMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storeInfo")
public class StoreCompareController {

  @Autowired
  StoreCompareMapper storeCompareMapper;

  @GetMapping("/")
  @ApiOperation(value = "获取所有门店信息")
  public List<StoreCompare> hello(){
    List<StoreCompare> storeCompares = storeCompareMapper.selectList(null);
    return storeCompares;
  }

  @GetMapping("/selectArea")
  @ApiOperation(value = "设置地区和状态搜索门店")
  @ApiImplicitParams(
      {
          @ApiImplicitParam(name = "area_no", value = "地区", dataType = "Integer", defaultValue = "51",
          paramType = "query",allowableValues = "11,51,52", allowMultiple = false),
          @ApiImplicitParam(name = "status", value = "状态", dataType = "Integer", defaultValue = "1",
          paramType = "query",allowableValues = "1,5")
      }
  )
  public List<StoreCompare> selectArea(Integer area_no, Integer[] status){
    List<StoreCompare> storeCompares = storeCompareMapper.selectList(new QueryWrapper<StoreCompare>()
      .eq("area_no", area_no)
      .in("status", status));
    return storeCompares;
  }

  @PostMapping("/selectStore")
  @ApiOperation(value = "根据老店号搜索门店")
//  @ApiImplicitParam(
//      name = "params", paramType = "body",
//      value = "{\"oldStoreNo\": 10}"
//      examples = @Example({
//      @ExampleProperty(value = "{'oldStoreNo': '10'}", mediaType = "application/json")
//      })
//  )
  public StoreCompare selectStore(@RequestBody StoreNoModel storeNoModel){
    StoreCompare store = storeCompareMapper.selectOne(new QueryWrapper<StoreCompare>()
        .eq("old_store_no", storeNoModel.getOldStoreNo()));
    return store;
  }


}
