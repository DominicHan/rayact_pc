package com.bra.modules.reserve.listener.venue;

import com.bra.common.utils.Collections3;
import com.bra.common.utils.StringUtils;
import com.bra.modules.reserve.entity.ReserveVenueCons;
import com.bra.modules.reserve.event.main.MainControlerEvent;
import com.bra.modules.reserve.service.ReserveVenueConsService;
import com.bra.modules.reserve.service.ReserveVenueOrderService;
import com.bra.modules.reserve.utils.AuthorityUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaobin on 16/1/25.
 */
@Component
public class VenueSaleChartListener {

    @Autowired
    private ReserveVenueConsService reserveVenueConsService;
    @Autowired
    private ReserveVenueOrderService reserveVenueOrderService;

    protected int getOrder() {
        return 0;
    }

    private void chartData(Map<String, Object> data, List<String> venueIds) {
        ReserveVenueCons venueCons = new ReserveVenueCons();
        Map<String,Object> search = Maps.newConcurrentMap();
        search.put("dsf", AuthorityUtils.getVenueIds(venueIds));
        search.put("day","1");
        venueCons.setSqlMap(search);
        List<Map<String,Object>> venueConsList = reserveVenueConsService.sellOfChart(venueCons);

        List<String> dateList = Lists.newArrayList();
        List<String> volumeList = Lists.newArrayList();
        List<String> venueListJson = Lists.newArrayList();

        for (Map<String,Object> map : venueConsList) {
            dateList.add("'" + map.get("updateDate") + "'");
            Object updateDate = map.get("updateDate");
            if(updateDate!=null){
                venueListJson.add(updateDate.toString());
            }
            Object orderPrice=map.get("orderPrice");
            if(orderPrice!=null){
                volumeList.add(orderPrice.toString());
            }
        }

        data.put("venueListJson",venueListJson);
        data.put("fieldChartMapX", StringUtils.join(dateList, ","));
        data.put("fieldChartMapY", StringUtils.join(volumeList, ","));
    }

    @EventListener
    protected void onEvent(MainControlerEvent event) {
        Map<String, Object> data = event.getMainBean().getMap();
        List<String> venueIds = (List<String>) data.get("venueIds");
        if (Collections3.isEmpty(venueIds)) {
            return;
        }
        chartData(data, venueIds);
        loadData(data, venueIds);
    }

    private void loadData(Map<String, Object> data, List<String> venueIds) {
        //当天得交易额
        ReserveVenueCons venueCons = new ReserveVenueCons();
        Map<String,Object> search = Maps.newConcurrentMap();
        search.put("dsf", AuthorityUtils.getVenueIds(venueIds));
        search.put("day","1");
        venueCons.setSqlMap(search);
        List<Map<String,Object>> venueConsList = reserveVenueConsService.sellOfChart(venueCons);
        Double todayPrice = 0D;
        for (Map<String,Object> cons : venueConsList) {
            Object orderPrice=cons.get("orderPrice");
            if(orderPrice!=null){
                todayPrice += NumberUtils.toDouble(orderPrice.toString());
            }
        }
        data.put("fieldTodayPrice", todayPrice);

        //当月的
        BigDecimal monthPrice = reserveVenueConsService.sellMonthOfChart(venueCons);
        if(monthPrice==null){
            monthPrice=BigDecimal.ZERO;
        }
        monthPrice=monthPrice.setScale(1, BigDecimal.ROUND_HALF_UP);
        data.put("fieldMonthPrice", monthPrice);

    }
}
