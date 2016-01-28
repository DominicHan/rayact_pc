package com.bra.modules.reserve.entity;

import com.bra.common.persistence.SaasEntity;

/**
 * 赠品
 * Created by xiaobin on 16/1/27.
 */
public class ReserveVenueGift extends SaasEntity<ReserveVenueGift> {

    private ReserveCommodity gift;

    private String modelId;

    private String modelKey;//field:场地,visitor:人次

    private Integer num;//数量

    public ReserveCommodity getGift() {
        return gift;
    }

    public void setGift(ReserveCommodity gift) {
        this.gift = gift;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
