package com.bra.modules.reserve.entity.form;


import com.bra.common.persistence.SaasEntity;
import com.bra.modules.reserve.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 商品收入统计Entity
 *
 * @author jiangxingqi
 * @version 2015-12-29
 */
public class ReserveCommodityIntervalReport extends SaasEntity<ReserveCommodityIntervalReport> {

    private static final long serialVersionUID = 1L;

    private Double bill;//消费金额

    private Double  storedCardBill;// 储值卡

    private Double  cashBill;//现金

    private Double  bankCardBill;//银行卡

    private Double  weiXinBill;//微信

    private Double  personalWeiXinBill;//（个人）微信

    private Double  aliPayBill;//支付宝

    private Double  personalAliPayBill;//（个人）支付宝


    private Double  dueBill;// 欠账

    private Double  otherBill;// 其它

    private ReserveVenue reserveVenue;//场馆

    private ReserveCommodity reserveCommodity;//商品

    private ReserveCommodityType reserveCommodityType;//商品类型



    private Date startDate;//开始日期

    private Date endDate;//结束日期

    private List<ReserveCommodityDayReport> dayReportList;//日报表


    public ReserveCommodity getReserveCommodity() {
        return reserveCommodity;
    }

    public void setReserveCommodity(ReserveCommodity reserveCommodity) {
        this.reserveCommodity = reserveCommodity;
    }

    public ReserveCommodityType getReserveCommodityType() {
        return reserveCommodityType;
    }

    public void setReserveCommodityType(ReserveCommodityType reserveCommodityType) {
        this.reserveCommodityType = reserveCommodityType;
    }

    public Double getStoredCardBill() {
        return storedCardBill;
    }

    public void setStoredCardBill(Double storedCardBill) {
        this.storedCardBill = storedCardBill;
    }

    public Double getCashBill() {
        return cashBill;
    }

    public void setCashBill(Double cashBill) {
        this.cashBill = cashBill;
    }

    public Double getBankCardBill() {
        return bankCardBill;
    }

    public void setBankCardBill(Double bankCardBill) {
        this.bankCardBill = bankCardBill;
    }

    public Double getWeiXinBill() {
        return weiXinBill;
    }

    public void setWeiXinBill(Double weiXinBill) {
        this.weiXinBill = weiXinBill;
    }

    public Double getAliPayBill() {
        return aliPayBill;
    }

    public void setAliPayBill(Double aliPayBill) {
        this.aliPayBill = aliPayBill;
    }

    public Double getDueBill() {
        return dueBill;
    }

    public void setDueBill(Double dueBill) {
        this.dueBill = dueBill;
    }

    public Double getOtherBill() {
        return otherBill;
    }

    public void setOtherBill(Double otherBill) {
        this.otherBill = otherBill;
    }

    public ReserveVenue getReserveVenue() {
        return reserveVenue;
    }

    public void setReserveVenue(ReserveVenue reserveVenue) {
        this.reserveVenue = reserveVenue;
    }

    public List<ReserveCommodityDayReport> getDayReportList() {
        return dayReportList;
    }

    public void setDayReportList(List<ReserveCommodityDayReport> dayReportList) {
        this.dayReportList = dayReportList;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public Double getPersonalWeiXinBill() {
        return personalWeiXinBill;
    }

    public void setPersonalWeiXinBill(Double personalWeiXinBill) {
        this.personalWeiXinBill = personalWeiXinBill;
    }

    public Double getPersonalAliPayBill() {
        return personalAliPayBill;
    }

    public void setPersonalAliPayBill(Double personalAliPayBill) {
        this.personalAliPayBill = personalAliPayBill;
    }

}