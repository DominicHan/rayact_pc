package com.bra.modules.reserve.entity.form;


import com.bra.common.persistence.SaasEntity;
import com.bra.modules.reserve.entity.ReserveField;
import com.bra.modules.reserve.entity.ReserveProject;
import com.bra.modules.reserve.entity.ReserveVenue;

import java.util.Date;
import java.util.List;

/**
 * 场馆 项目 场地 收入统计Entity
 *
 * @author jiangxingqi
 * @version 2015-12-29
 */
public class ReserveVenueProjectFieldIntervalReport extends SaasEntity<ReserveVenueProjectFieldIntervalReport> {

    private static final long serialVersionUID = 1L;

    private Double bill;//消费金额

    private Double  storedCardBill;// 储值卡

    private Double  cashBill;//现金

    private Double  bankCardBill;//银行卡

    private Double  weiXinBill;//微信

    private Double  personalWeiXinBill;//微信(个人) 9

    private Double  aliPayBill;//支付宝

    private Double  personalAliPayBill;//支付宝个人 10

    private Double  otherBill;// 优惠券

    private Double  dueBill;// 欠账

    private ReserveField reserveField;//场地

    private ReserveVenue reserveVenue;//场馆

    private ReserveProject reserveProject;//项目

    private List<ReserveVenueProjectFieldDayReport> dayReports;//日报表

    private Date startDate;//开始日期

    private Date endDate;//结束日期


    public List<ReserveVenueProjectFieldDayReport> getDayReports() {
        return dayReports;
    }

    public void setDayReports(List<ReserveVenueProjectFieldDayReport> dayReports) {
        this.dayReports = dayReports;
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

    public ReserveField getReserveField() {
        return reserveField;
    }

    public void setReserveField(ReserveField reserveField) {
        this.reserveField = reserveField;
    }

    public ReserveVenue getReserveVenue() {
        return reserveVenue;
    }

    public void setReserveVenue(ReserveVenue reserveVenue) {
        this.reserveVenue = reserveVenue;
    }

    public ReserveProject getReserveProject() {
        return reserveProject;
    }

    public void setReserveProject(ReserveProject reserveProject) {
        this.reserveProject = reserveProject;
    }


    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
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

    public Double getPersonalWeiXinBill() {
        return personalWeiXinBill;
    }

    public void setPersonalWeiXinBill(Double personalWeiXinBill) {
        this.personalWeiXinBill = personalWeiXinBill;
    }

    public Double getAliPayBill() {
        return aliPayBill;
    }

    public void setAliPayBill(Double aliPayBill) {
        this.aliPayBill = aliPayBill;
    }

    public Double getPersonalAliPayBill() {
        return personalAliPayBill;
    }

    public void setPersonalAliPayBill(Double personalAliPayBill) {
        this.personalAliPayBill = personalAliPayBill;
    }

    public Double getOtherBill() {
        return otherBill;
    }

    public void setOtherBill(Double otherBill) {
        this.otherBill = otherBill;
    }

    public Double getDueBill() {
        return dueBill;
    }

    public void setDueBill(Double dueBill) {
        this.dueBill = dueBill;
    }
}