$(document).ready(function () {
    $('.md-trigger').modalEffects();
    //-------预定---------
    $(".table-chang tbody td").on('dblclick', function () {
        if (!$(this).hasClass("access")) {
            return;
        }
        var fieldId = $(this).attr("data-field");
        var time = $(this).attr("data-time");
        var price = $(this).attr("data-price");
        var isHalfCourt = $(this).attr("data-isHalfCourt");
        if (price == null || price == "" || price == undefined) {
            errorLoding("抱歉，该时间段价格尚未设定");
            return;
        }
        var date = consDate;//日期
        jQuery.postItems({
            url: ctx + '/reserve/field/reserveForm',
            data: {fieldId: fieldId, time: time, date: date, venueId: venueId, isHalfCourt: isHalfCourt},
            success: function (result) {
                $("#reserveForm").html(result);
                $("#reserveDialog").click();
                $("#reserveForm .select2").select2({
                    width: '100%'
                });
                $('#reserveForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    });
    //保存预订数据
    $("#saveBtn").on('click', function () {
        var userName = $('#userName').val();
        var consMobile = $("#consMobile").val();
        var frequency = $("#frequency").val();
        var endDate = $("#endDate").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        if (startTime == endTime) {
            formLoding('开始时间应小于结束时间');
            return false;
        }

        $.postItems({
            url: ctx + '/reserve/field/checkTime',
            data: {startTime: startTime, endTime: endTime},
            success: function (values) {
                if (values == "0") {
                    formLoding('开始时间应小于结束时间');
                    return false;
                }
            }
        });

        if (userName == '') {
            formLoding('请输入预定人姓名');
            return false;
        }
      /*  if (consMobile == '') {
            formLoding('请输入预定人手机');
            return false;
        }*/
        if (frequency == '2' || frequency == '3') {
            if (endDate == '') {
                formLoding('结束时间不能为空');
                return false;
            }
        }
        var data = $("#reserveFormBean").serializeArray();
        $.postItems({
            url: ctx + '/reserve/field/reservation',
            data: data,
            success: function (values) {
                if (values) {
                    $.each(values, function (index, item) {
                        if (item.bool == "0") {
                            formLoding('该时间段不可使用!');
                        }
                        else {
                            formLoding('订单预定成功!');
                            location.reload(true);
                           /* $(".table-chang tbody td").each(function (index) {
                                var $this = $(this);
                                var fieldId = $this.attr("data-field");
                                var time = $this.attr("data-time");
                                $.each(values, function (index, item) {
                                    if (item.fieldId == fieldId && time == item.time) {
                                        /!*$this.removeClass("access");
                                         $this.attr("status", "1");
                                         $this.attr("data-item", item.itemId);
                                         $this.text(userName);*!/
                                        location.reload(true);
                                    }
                                });
                            });*/
                        }
                    });
                }
            }
        });
        $("#closeBtn").click();
    });
    //-------取消预定---------
    function cancelReserve(t) {
        var itemId = t.attr("data-item");
        var field = t.attr("data-field");
        var time = t.attr("data-time");
        jQuery.postItems({
            url: ctx + '/reserve/field/cancelForm',
            data: {fieldId: field, time: time, itemId: itemId, venueId: venueId},
            success: function (result) {
                $("#cancelForm").html(result);
                $("#cancelBtn").click();
                $("#cancelForm .select2").select2({
                    width: '100%'
                });
                $('#cancelForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    }


    //赠品
    function gift(t) {
        var itemId = t.attr("data-item");
        jQuery.postItems({
            url: ctx + '/reserve/field/gift',
            data: {itemId: itemId},
            success: function (result) {
                $("#giftForm").html(result);
                $("#giftBtn").click();
                $("#giftForm .select2").select2({
                    width: '100%'
                });
                $('#giftForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    }

    //申请优惠
    function applycut(t) {
        var itemId = t.attr("data-item");
        jQuery.postItems({
            url: ctx + '/reserve/field/applyCut',
            data: {itemId: itemId},
            success: function (result) {
                $("#applyCutForm").html(result);
                $("#applyCutBtn").click();
                $("#applyCutForm .select2").select2({
                    width: '100%'
                });
                $('#applyCutForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    }

    //保存赠品
    $("#saveGiftBtn").on('click', function () {
        var data = $("#giftFormBean").serializeArray();
        var length = $("#giftTable tr").length;
        if (length < 1) {
            formLoding('请选择赠品!');
            return;
        }
        $.postItems({
            url: ctx + '/reserve/field/saveGift',
            data: data,
            success: function (values) {
                if (values == "success") {
                    successLoding('保存赠品成功!');
                    location.reload();
                }
                if (values == "fail") {
                    errorLoding("库存量不足！")
                }
            }
        });
    });

    //保存优惠申请
    $("#saveApplyCutBtn").on('click', function () {
        var data = $("#applyCutFormBean").serializeArray();
        var applyer = $("#userId").val();
        if(!applyer){
            formLoding('请选择通知人!');
            return;
        }
        $.postItems({
            url: ctx + '/reserve/field/saveApplyCut',
            data: data,
            success: function (values) {
                if (values == "success") {
                    successLoding('发送通知成功!');
                    location.reload();
                }
                if (values == "fail") {
                    errorLoding("发送通知失败！")
                }
            }

        });
    });


    //查看详情
    function details(t) {
        var itemId = t.attr("data-item");
        jQuery.postItems({
            url: ctx + '/reserve/field/details',
            data: {itemId: itemId},
            success: function (result) {
                $("#detailsForm").html(result);
                $("#detailsBtn").click();
                $("#detailsForm .select2").select2({
                    width: '100%'
                });
                $('#detailsForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    }

    //退款
    function refund(t) {
        var itemId = t.attr("data-item");
        jQuery.postItems({
            url: ctx + '/reserve/field/refund',
            data: {itemId: itemId},
            success: function (result) {
                $("#detailsForm").html(result);
                $("#detailsBtn").click();
                $("#detailsForm .select2").select2({
                    width: '100%'
                });
                $('#detailsForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    }

    //右击 结账
    function settlement(t) {
        $("#settlementDetailForm").html("");
        var itemId = t.attr("data-item");
        jQuery.postItems({
            url: ctx + '/reserve/field/settlementForm',
            data: {itemId: itemId},
            success: function (result) {
                $("#settlementForm").html(result);
                $("#settlementBtn").click();
                $("#settlementForm .select2").select2({
                    width: '100%'
                });
                $('#settlementForm .icheck').iCheck({
                    checkboxClass: 'icheckbox_square-blue checkbox',
                    radioClass: 'iradio_square-blue'
                });
            }
        });
    }
    //确认结账
    $("#saveSettlementBtn").on('click', function () {

        var shouldPrice = $("#shouldPrice").val();
        var discountPrice = $("#discountPrice").val();
        if(isNaN(discountPrice)){
            errorLoding("优惠金额必须为数字！");
            return;
        }
        if (eval(discountPrice) > eval(shouldPrice)) {
            errorLoding("优惠金额不能大于应收金额！");
            return;
        }
       /* 以上为数据验证*/
        var consPrice=shouldPrice-discountPrice;
        if(isNaN(consPrice)){
            errorLoding("结算金额必须为数字！");
            return;
        }
        if(eval(consPrice) < 0){
            errorLoding("结账金额不能小于0！");
            return;
        }
        var authUserId=$("#authUser").val();
        var id=$("#id").val();
        var token=$("#token").val();
        var payType = $("#payType2 input:radio:checked").val();
        if(payType=="" || payType==null || payType==undefined){
            errorLoding("请选择支付类型！");
            return;
        }
      /*  var data = $("#settlementFormBean").serializeArray();*/
        $.postItems({
            url: ctx + '/reserve/field/saveSettlement?random='+Math.random(),
            data: {
                token:token,
                id:id,
                payType:payType,
                authUserId:authUserId,
                discountPrice:discountPrice,
                consPrice:consPrice
            },
            success: function (values) {
                if (values != null) {
                    formLoding('保存结账单据成功!');
                    location.reload();
                } else {
                    formLoding('保存结账单据出错!');
                }
            }
        });
    });

    //取消预定
    $("#saveCancelBtn").on('click', function () {
        var data = $("#cancelFormBean").serializeArray();
        $.postItems({
            url: ctx + '/reserve/field/cancelReservation',
            data: data,
            success: function (values) {
                if (values) {
                    formLoding('取消预定成功!');
                    location.reload();
                }
            }
        });
    });


    var accessMenuData = [[{
        text: "预定",
        func: function () {
            $(this).dblclick();
        }
    }]];
    var reserveMenuData = [[{
        text: "取消订单",
        func: function () {
            cancelReserve($(this));
        }
    }], [{
        text: "申请优惠",
        func: function () {
            applycut($(this));
        }
    }],[{
        text: "预定详情",
        func: function () {
            details($(this));
        }
    }], [{
        text: "赠品",
        func: function () {
            gift($(this));
        }
    }], [{
        text: "结账",
        func: function () {
            settlement($(this));
        }
    }]
    ];
    var checkoutMenuData = [[{
        text: "查看详情",
        func: function () {
            details($(this));
        }
    }]];
    //mouserover
    $(".reserveTd").unbind("mouserover");
    $(".reserveTd").on('mousedown', function () {
        if ($(this).hasClass("access")) {//预定
            $(this).smartMenu(accessMenuData, {name: "access"});
        } else if ($(this).hasClass("red")) {//已经结账
            $(this).smartMenu(checkoutMenuData, {name: "checkout"});
        }
        else {//取消预定
            $(this).smartMenu(reserveMenuData, {name: "reserve"});
        }
    });
});
//修改价格的连接
function changePrice() {
    $("#button_userPwd").show();
}
//修改结算价格
function editPrice() {
    var discountPrice = $("#discountPrice").val();
    if(isNaN(discountPrice)){
        errorLoding("优惠金额必须为数字！");
        return;
    }
    var shouldPrice = $("#shouldPrice").val();

    var consPrice = shouldPrice - discountPrice;
    if (consPrice < 0) {
        errorLoding("优惠金额不能大于应收金额！");
        return;
    }
    $("#consPrice").val(consPrice);
}
function checkAuthorization() {
    var userId=$("#authUser").val();
    var authPassword = $("#authPassword").val();
    jQuery.postItems({
        url: ctx + '/reserve/field/checkUserAuth',
        data: {userId:userId,authPassword: authPassword},
        success: function (result) {
            if (result != null && result.id != null) {
                successLoding("授权码正确!");
                $("#discountPriceDiv").show();
            } else {
                errorLoding("授权码不正确!");
            }
        }
    });
}