package com.bra.modules.reserve.web;

import com.bra.common.utils.Collections3;
import com.bra.common.utils.DateUtils;
import com.bra.common.utils.StringUtils;
import com.bra.common.web.BaseController;
import com.bra.common.web.annotation.Token;
import com.bra.modules.reserve.entity.*;
import com.bra.modules.reserve.entity.form.FieldPrice;
import com.bra.modules.reserve.entity.form.VenueGiftForm;
import com.bra.modules.reserve.service.*;
import com.bra.modules.reserve.utils.AuthorityUtils;
import com.bra.modules.reserve.utils.TimeUtils;
import com.bra.modules.sys.entity.User;
import com.bra.modules.sys.service.SystemService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 场地预定管理
 * Created by xiaobin on 16/1/5.
 */
@Controller
@RequestMapping(value = "${adminPath}/reserve/field")
public class ReserveController extends BaseController {

    //场馆
    @Autowired
    private ReserveVenueService reserveVenueService;
    //场地
    @Autowired
    private ReserveFieldService reserveFieldService;
    //会员信息
    @Autowired
    private ReserveMemberService reserveMemberService;
    @Autowired
    private ReserveFieldPriceService reserveFieldPriceService;
    @Autowired
    private ReserveVenueConsService reserveVenueConsService;
    @Autowired
    private ReserveVenueConsItemService reserveVenueConsItemService;
    //教练
    @Autowired
    private ReserveTutorService reserveTutorService;
    @Autowired
    private ReserveTutorOrderService reserveTutorOrderService;
    @Autowired
    private ReserveCommodityService reserveCommodityService;
    @Autowired
    private ReserveVenueGiftService reserveVenueGiftService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private ReserveUserService userService;


    //场地状态界面
    @RequestMapping(value = "main")
    public String main(Long t, String venueId, Model model) throws ParseException {
        //当前日期
        Date defaultDate = DateUtils.parseDate(DateUtils.getDate(), "yyyy-MM-dd");
        System.out.println("123");
        //获取可预定时间段:一周
        Map<String, Long> timeSlot = TimeUtils.getNextDaysMap(defaultDate, 7);
        model.addAttribute("timeSlot", timeSlot);
        //默认时间
        if (t != null) {
            defaultDate = new Date(t);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String consDateFormat=format.format(defaultDate);
        model.addAttribute("consDateFormat", consDateFormat);
        model.addAttribute("consDate", defaultDate);

        //获得所有场馆信息
        ReserveVenue venue = new ReserveVenue();
        venue.getSqlMap().put("dsf", AuthorityUtils.getVenueIdSql("a.id"));
        List<ReserveVenue> reserveVenueList = reserveVenueService.findList(venue);
        model.addAttribute("reserveVenueList", reserveVenueList);
        //默认场馆的场地
        if (!Collections3.isEmpty(reserveVenueList)) {
            ReserveVenue reserveVenue;
            if (StringUtils.isNoneBlank(venueId)) {
                reserveVenue = reserveVenueService.get(venueId);
            } else {
                reserveVenue = reserveVenueList.get(0);
            }
            //默认场馆
            model.addAttribute("reserveVenue", reserveVenue);
            ReserveField reserveField = new ReserveField();
            reserveField.setReserveVenue(reserveVenue);
            //上午场地价格
            List<String> timesAM=new ArrayList<>();

            timesAM.addAll(TimeUtils.getTimeSpacListValue("08:00:00", "12:30:00", 30));
            model.addAttribute("timesAM", timesAM);
            List<FieldPrice> venueFieldPriceListAM = reserveFieldPriceService.findByDate(reserveVenue.getId(), "1", defaultDate, timesAM);
            model.addAttribute("venueFieldPriceListAM", venueFieldPriceListAM);
            //下午场地价格
            List<String> timesPM = TimeUtils.getTimeSpacListValue("12:30:00", "18:30:00", 30);
            model.addAttribute("timesPM", timesPM);
            List<FieldPrice> venueFieldPriceListPM = reserveFieldPriceService.findByDate(reserveVenue.getId(), "1", defaultDate, timesPM);
            model.addAttribute("venueFieldPriceListPM", venueFieldPriceListPM);
            //晚上场地价格
            List<String> timesEvening = TimeUtils.getTimeSpacListValue("18:30:00", "00:30:00", 30);
           /* timesEvening.add("00:00-00:30");*/
            model.addAttribute("timesEvening", timesEvening);
            List<FieldPrice> venueFieldPriceListEvening = reserveFieldPriceService.findByDate(reserveVenue.getId(), "1", defaultDate, timesEvening);
            model.addAttribute("venueFieldPriceListEvening", venueFieldPriceListEvening);
        }
        return "reserve/saleField/reserveField";
    }

    //预定表单
    @RequestMapping(value = "reserveForm")
    @Token(save = true)
    public String reserveForm(Model model, String fieldId, Long date, String time, String venueId,String isHalfCourt) throws ParseException {
        ReserveField reserveField = reserveFieldService.get(fieldId);
        model.addAttribute("reserveField", reserveField);
        model.addAttribute("isHalfCourt", isHalfCourt);//是否半场

        //获取营业时间
        List<String> times = TimeUtils.getTimeSpacList("08:00:00", "00:30:00", TimeUtils.BENCHMARK);
        String consDate = DateUtils.formatDate(new Date(date), "yyyy-MM-dd");
        model.addAttribute("date", consDate);
        model.addAttribute("times", times);
        //会员
        ReserveMember reserveMember = new ReserveMember();
        model.addAttribute("memberList", reserveMemberService.findList(reserveMember));

        //教练
        model.addAttribute("tutors", reserveTutorService.findList(new ReserveTutor()));

        if (StringUtils.isNotBlank(time)) {
            String[] timeStr = time.split("-");
            model.addAttribute("startTime", timeStr[0]);
            model.addAttribute("endTime", timeStr[1]);
        }
        model.addAttribute("venueId", venueId);
        return "reserve/saleField/reserveForm";
    }

    //取消预定表单
    @RequestMapping(value = "cancelForm")
    @Token(save = true)
    public String cancelForm(Model model, String fieldId, String itemId, String time) {
        ReserveVenueConsItem item = reserveVenueConsItemService.get(itemId);
        ReserveVenueCons cons = reserveVenueConsService.get(item.getConsData().getId());
        model.addAttribute("cos", cons);
        if (StringUtils.isNoneBlank(time)) {
            String startTime = time.split("-")[0];
            String endTime = time.split("-")[1];
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
        }
        model.addAttribute("item", item);
        List<String> times = TimeUtils.getTimeSpacList("08:00:00", "00:30:00", TimeUtils.BENCHMARK);
        model.addAttribute("times", times);
        //教练订单
        List<ReserveTutorOrder> tutorOrderList = reserveTutorOrderService.findNotCancel(cons.getId(), ReserveVenueCons.MODEL_KEY);
        if (!Collections3.isEmpty(tutorOrderList)) {
            model.addAttribute("tutorOrder", tutorOrderList.get(0));
        }
        return "reserve/saleField/cancelForm";
    }

    /**
     * 取消预定
     *
     * @param itemId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "cancelReservation")
    @ResponseBody
    @Token(remove = true)
    public List<Map<String, String>> cancelReservation(String itemId, String startTime, String endTime, String tutorOrderId) {
        List<ReserveVenueConsItem> itemList = reserveVenueConsService.cancelReserve(itemId, startTime, endTime, tutorOrderId);
        return getReserveMap(itemList);
    }

    private List<Map<String, String>> getReserveMap(List<ReserveVenueConsItem> itemList) {
        List<Map<String, String>> list = Lists.newArrayList();
        if (itemList == null)
            return list;
        Map<String, String> data;
        for (ReserveVenueConsItem item : itemList) {
            String startTime = item.getStartTime();
            String endTime = item.getEndTime();
            List<String> times = TimeUtils.getTimeSpacListValue(startTime + ":00", endTime + ":00", 30);
            for (String time : times) {
                data = Maps.newConcurrentMap();
                data.put("fieldId", item.getReserveField().getId());
                data.put("time", time);
                data.put("itemId", item.getId());
                list.add(data);
            }
        }
        return list;
    }

    /**
     * 场地预定(保存)
     *
     * @param reserveVenueCons
     * @return
     */
    @RequestMapping(value = "reservation")
    @ResponseBody
    @Token(remove = true)
    public List<Map<String, String>> reservation(ReserveVenueCons reserveVenueCons) {
        boolean bool=true;//时间段是否可用
        List<ReserveVenueConsItem> itemList = reserveVenueCons.getVenueConsList();//查询预订的订单详情
        for(ReserveVenueConsItem i:itemList){//订单详情
            String startTime=i.getStartTime();
            String endTime=i.getEndTime();
            startTime=TimeUtils.earlyMorningFormat(startTime);
            endTime=TimeUtils.earlyMorningFormat(endTime);
            ReserveField field=i.getReserveField();//场地
            Date consDate=reserveVenueCons.getConsDate();//日期
            //查询该场地，当天的已预订时间
            ReserveVenueConsItem reserveVenueConsItem=new  ReserveVenueConsItem();
            reserveVenueConsItem.setReserveField(field);
            reserveVenueConsItem.setConsDate(consDate);
            List<ReserveVenueConsItem> list = reserveVenueConsItemService.findRelationList(reserveVenueConsItem);//查询该场地在预订date的预订状态
            for(ReserveVenueConsItem item:list){
                String start=item.getStartTime();//已预订开始时间
                String end=item.getEndTime();//已预订结束时间
                start=TimeUtils.earlyMorningFormat(start);
                end=TimeUtils.earlyMorningFormat(end);
                //第一个判断条件 假设 预订时间为15:30-16:00 已预订时间为15:30-16:30 startTime=start=15:30 startTime=start不可预订
                //第二个判断条件 假设 预订时间为15:30-16:00  已预订时间为15:00-15:30 startTime=end=15:30 时间段正好错开 可预订
                if(startTime.compareTo(start)>=0 && startTime.compareTo(end)<0){//预订开始时间 介于 已预订区间
                    bool=false;
                    break;
                }
                //第一个判断条件 假设 预订时间为15:30-16:00  已预订时间为16:00-16:30 endTime=stat=16:00 时间段正好错开 可预订
                //第二个判断条件 假设 预订时间为15:30-16:30  已预订时间为16:00-16:30 endTime=end=16:30 不可预订
                if(endTime.compareTo(start)>0 && endTime.compareTo(end)<=0){//预订结束时间 介于 已预订区间
                    bool=false;
                    break;
                }
                if(startTime.compareTo(start)<0 && endTime.compareTo(end)>0){// 预订区间 包含 已预订区间
                    bool=false;
                    break;
                }
            }
        }
        List<Map<String, String>> list=new ArrayList<>();
        Map<String, String> map = Maps.newConcurrentMap();
        if(bool){
            reserveVenueCons.setReserveType(ReserveVenueCons.RESERVATION);//预定
            reserveVenueConsService.save(reserveVenueCons);//保存预订信息

            Map<String, String> data;
            for (ReserveVenueConsItem item : itemList) {
                String startTime = item.getStartTime();
                String endTime = item.getEndTime();
                List<String> times = TimeUtils.getTimeSpacListValue(startTime + ":00", endTime + ":00", 30);
                for (String time : times) {
                    data = Maps.newConcurrentMap();
                    data.put("fieldId", item.getReserveField().getId());
                    data.put("time", time);
                    data.put("itemId", item.getId());
                    data.put("bool","1");
                    list.add(data);
                }
            }
        }else{
            map.put("bool","0");
            list.add(map);
        }
        return list;
    }

    /**
     * 结算订单的表单
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "settlementForm")
    @Token(save = true)
    public String settlementForm(String itemId, Model model) {
        //操作类型(1:已预定,2:锁场,3:已取消,4:已结算)
        ReserveVenueConsItem consItem = reserveVenueConsItemService.get(itemId);//获得预订详情
        ReserveVenueConsItem search = new ReserveVenueConsItem();
        search.setConsData(consItem.getConsData());
        ReserveVenueCons order = reserveVenueConsService.get(consItem.getConsData().getId());//获得订单

        //教练订单
        List<ReserveTutorOrder> tutorOrderList = reserveTutorOrderService.findNotCancel(order.getId(), ReserveVenueCons.MODEL_KEY);
        if (!Collections3.isEmpty(tutorOrderList)) {
            model.addAttribute("tutorOrder", tutorOrderList.get(0));
        }
        List<ReserveVenueConsItem> itemList = reserveVenueConsItemService.findList(search);
        model.addAttribute("itemList", itemList);
        User user=new User();
        user.setUserType("2");//用户类型(1:超级管理员；2:场馆管理员；3：场地管理员；4：收银；5：财务)
        List<User> authUserList=userService.findList(user);
        model.addAttribute("authUserList", authUserList);
        model.addAttribute("itemList", itemList);

        model.addAttribute("order", order);
        //赠品
        model.addAttribute("giftList", reserveVenueGiftService.findList(new ReserveVenueGift(order.getId(), ReserveVenueCons.MODEL_KEY)));
        return "reserve/saleField/settlementDetailForm";
        //return "reserve/saleField/settlementForm";
    }
    /**
     * 结算订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "saveSettlement")
    @ResponseBody
    @Token(remove = true)
    public List<Map<String, String>> saveSettlement(String id,String payType,String authUserId,Double discountPrice,Double consPrice) {

        ReserveVenueCons venueCons = reserveVenueConsService.saveConsOrder(id,payType,authUserId,discountPrice,consPrice);
        List<Map<String, String>> list = getReserveMap(venueCons.getVenueConsList());
        return list;
    }

    //订单详情
    @RequestMapping(value = "details")
    public String details(String itemId, Model model) {
        ReserveVenueConsItem consItem = reserveVenueConsItemService.get(itemId);
        ReserveVenueConsItem search = new ReserveVenueConsItem();
        //consItem.getConsData().setReserveType("3");

        search.setConsData(consItem.getConsData());
        ReserveVenueCons cons = reserveVenueConsService.get(consItem.getConsData().getId());
        model.addAttribute("cos", cons);
        List<ReserveVenueConsItem> itemList = reserveVenueConsItemService.findList(search);
        model.addAttribute("itemList", itemList);
      /*  List<String> times = TimeUtils.getTimeSpacList("09:00:00", "23:00:00", TimeUtils.BENCHMARK);*/
        model.addAttribute("tutorOrderList", reserveTutorOrderService.findNotCancel(cons.getId(), ReserveVenueCons.MODEL_KEY));
      /*  model.addAttribute("times", times);*/
        //赠品
        model.addAttribute("giftList", reserveVenueGiftService.findList(new ReserveVenueGift(cons.getId(), ReserveVenueCons.MODEL_KEY)));
        return "reserve/saleField/details";
    }

    //退款
    @RequestMapping(value = "refund")
    public String refund(String itemId, Model model) {
       /* ReserveVenueConsItem consItem = reserveVenueConsItemService.get(itemId);
        ReserveVenueConsItem search = new ReserveVenueConsItem();
        consItem.getConsData().setReserveType("3");

        search.setConsData(consItem.getConsData());
        ReserveVenueCons cons = reserveVenueConsService.get(consItem.getConsData().getId());
        model.addAttribute("cos", cons);
        List<ReserveVenueConsItem> itemList = reserveVenueConsItemService.findList(search);
        model.addAttribute("itemList", itemList);
        List<String> times = TimeUtils.getTimeSpacList("09:00:00", "23:00:00", TimeUtils.BENCHMARK);
        model.addAttribute("tutorOrderList", reserveTutorOrderService.findNotCancel(cons.getId(), ReserveVenueCons.MODEL_KEY));
        model.addAttribute("times", times);*/
        return "reserve/saleField/details";
    }

    //赠品
    @RequestMapping(value = "gift")
    @Token(save = true)
    public String gift(String itemId, Model model) {
        ReserveVenueConsItem consItem = reserveVenueConsItemService.get(itemId);
        ReserveVenueConsItem search = new ReserveVenueConsItem();
        consItem.getConsData().setReserveType("3");

        search.setConsData(consItem.getConsData());
        ReserveVenueCons cons = reserveVenueConsService.get(consItem.getConsData().getId());
        model.addAttribute("cos", cons);
        //商品
        model.addAttribute("giftList", reserveCommodityService.findList(new ReserveCommodity()));
        return "reserve/saleField/giftForm";
    }

    //保存赠品
    @RequestMapping(value = "saveGift")
    @ResponseBody
    @Token(remove = true)
    public String saveGift(VenueGiftForm giftForm) {
        List<ReserveVenueGift> giftList = giftForm.getGiftList();
        for(ReserveVenueGift gift:giftList){
            int giftNum=gift.getNum();
            ReserveCommodity commodity=gift.getGift();
            commodity=reserveCommodityService.get(commodity);
            int storeNum=commodity.getRepertoryNum();
            if(giftNum>storeNum){
                return "fail";
            }
        }

        reserveVenueGiftService.saveVenueList(giftForm, ReserveVenueCons.MODEL_KEY);
        return "success";
    }
    //检测授权码
    @RequestMapping(value = "checkUserAuth")
    @ResponseBody
    public User checkUserAuth(String userId,String authPassword) {
        User user = systemService.checkUserAuth(userId,authPassword);
        return user;
    }

    @RequestMapping(value = "checkTime")
    @ResponseBody
    public String checkTime(String startTime,String endTime) {
        startTime=TimeUtils.earlyMorningFormat(startTime);
        endTime=TimeUtils.earlyMorningFormat(endTime);
        if(startTime.compareTo(endTime)<0) {
            return "1";
        }else{
            return "0";
        }
    }
}
