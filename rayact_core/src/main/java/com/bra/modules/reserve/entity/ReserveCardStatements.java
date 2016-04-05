package com.bra.modules.reserve.entity;

import com.bra.common.persistence.SaasEntity;

import java.util.Date;

/**
 * reserveEntity
 * @author jiangxingqi
 * @version 2016-01-16
 */
public class ReserveCardStatements extends SaasEntity<ReserveCardStatements> {
	
	private static final long serialVersionUID = 1L;
	private ReserveMember reserveMember;	// 会员编号外键
	private String transactionType;  //交易类型 (1：储值卡充值，2：退费，3：商品消费 4:超级管理员修改余额 5：销户退还用户的金额 6：销户违约金;7:次卡充值,8:场地收入，9：次卡消费)
	private Double transactionVolume;		// 交易额
	private String payType; //充值类型(1:储值卡，2:现金,3:银行卡,4:微信,5:支付宝,6:其它，7：次卡)

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public ReserveCardStatements() {
		super();
	}

	public ReserveCardStatements(String id){
		super(id);
	}

	public ReserveMember getReserveMember() {
		return reserveMember;
	}

	public void setReserveMember(ReserveMember reserveMember) {
		this.reserveMember = reserveMember;
	}

	public Double getTransactionVolume() {
		return transactionVolume;
	}

	public void setTransactionVolume(Double transactionVolume) {
		this.transactionVolume = transactionVolume;
	}

	//---------------------------------------------------------
	private Date startDate;
	private Date endDate;
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

	//--------------------------------------
	//交易发生场馆
	private ReserveVenue venue;
	public void setVenue(ReserveVenue venue){
		this.venue = venue;
	}
	public ReserveVenue getVenue(){
		return venue;
	}
}