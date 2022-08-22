package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.Goodstag;
import com.yoyo.yoyomall.service.GoodstagService;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/goodstag")
@CrossOrigin  //跨域
public class GoodstagController {

    @Autowired
    private GoodstagService goodstagService;

    /**
     * @param id 通过id查询商品标签
     * @return
     */
    @GetMapping("/findById")
    public R findById(String id){
        Goodstag goodstag = goodstagService.findById(id);
        if(goodstag!=null){
            return  R.ok().data("goodstag",goodstag);
        }
        return R.error();
    }

    /**
     * @param name 添加商品标签
     * @return
     */
    @RequestMapping("/add")
    public  R addGooodsTag(String name){
        try {
            Goodstag goodstag = new Goodstag();
            goodstag.setName(name);
            goodstagService.insert(goodstag);
            return  R.ok().msg("插入商品标签成功");
        }catch (Exception e){
            return  R.error().msg("插入商品标签失败");
        }
    }

    /**通过id删除
     * @param id
     * @return
     */
   @RequestMapping("/delete")
    public  R deleteById(String id){
       try {
           goodstagService.deleteById(id);
           return  R.ok().msg("删除商品标签成功");
       }catch (Exception e){
           return  R.error().msg("删除商品标签出错");
       }
   }

    /**通过名称查询
     * @param name
     * @return
     */
    @RequestMapping("/findByName")
    public  R findByName(String name){
        try {
            Goodstag goodstag = goodstagService.findByName(name);
            if(goodstag==null){
                return R.error().msg("商品标签不存在");
            }
            return  R.ok().msg("查询商品标签成功").data("goodsTag",goodstag);
        }catch (Exception e){
             return  R.error().msg("查询商品标签出错");
    }
}
@RequestMapping("/updateById")
    public  R updateById(String id,String name){
        Goodstag goodstag = new Goodstag();
        goodstag.setId(id);
        goodstag.setName(name);
        try {
            goodstagService.update(goodstag);
            return  R.ok().msg("修改商品标签成功");
        }catch (Exception e){
            return R.error().msg("修改商品标签失败");
        }

}
@RequestMapping("/getAll")
    public  R getAll(){
        List<Goodstag> goodstagList = goodstagService.getAll();
        try {
            return  R.ok().msg("获取商品标签列表成功").data("goodstagList",goodstagList);
        }catch (Exception e){
            return  R.error().msg("获取标签列表失败");
        }
}

}

