package com.bra.modules.reserve.entity.form;


import com.bra.common.persistence.SaasEntity;

import java.util.Date;
import java.util.List;

/**
 * 场馆收入统计Entity
 *
 * @author jiangxingqi
 * @version 2015-12-29
 */
public class ReserveVenueIncomeIntervalReport extends SaasEntity<ReserveVenueIncomeIntervalReport> {

    private static final long serialVersionUID = 1L;

    private Double bill;//消费金额

    private Double  storedCardBill;// 储值卡

    private Double  cashBill;//现金

    private Double  bankCardBill;//银行卡

    private Double  weiXinBill;//微信

    private Double  personalWeiXinBill;//个人微信

    private Double  aliPayBill;//支付宝

    private Double  personalAliPayBill;//个人微信

    private Double  dueBill;// 欠账

    private Double  otherBill;// 其它

    private List<ReserveVenueProjectIntervalReport> projectIntervalReports;

    private Date startDate;//开始日期

    private Date endDate;//结束日期

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


    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public List<ReserveVenueProjectIntervalReport> getProjectIntervalReports() {
        return projectIntervalReports;
    }

    public void setProjectIntervalReports(List<ReserveVenueProjectIntervalReport> projectIntervalReports) {
        this.projectIntervalReports = projectIntervalReports;
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