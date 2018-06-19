package com.zq.shop.web.service.impl;

import com.google.common.collect.Maps;
import com.zq.core.restful.ServerResponse;
import com.zq.shop.web.bean.Shipping;
import com.zq.shop.web.common.Const;
import com.zq.shop.web.mappers.IDMapper;
import com.zq.shop.web.mappers.ShippingMapper;
import com.zq.shop.web.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author 张迁-zhangqian
 * @Data 2018/4/22 下午8:01
 * @Package com.zq.shop.web.service.impl
 **/


@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {


    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private IDMapper idMapper;

    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        shipping.setId(idMapper.findId(Const.IDType.SHIPPING_ID));
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByIdAndUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }


    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.findByUserIdAndId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        List<Shipping> byUserId = shippingMapper.findByUserIdAndIsDefault(userId);
        for (Shipping ship : byUserId) {
            ship.setIsDefault(0);
            shippingMapper.updateByShipping(ship);
        }
        shipping.setIsDefault(1);
        shippingMapper.updateByShipping(shipping);
        return ServerResponse.createBySuccess("更新地址成功", shipping);
    }


    public ServerResponse<List<Shipping>> list(Integer userId, int pageNum, int pageSize) {

        List<Shipping> shippingList = shippingMapper.findByUserId(userId);
        return ServerResponse.createBySuccess(shippingList);
    }
}
