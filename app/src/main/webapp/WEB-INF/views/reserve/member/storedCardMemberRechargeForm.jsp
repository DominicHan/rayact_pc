<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content">
    <form:form id="formBean" modelAttribute="reserveMember"
               action="${ctx}/reserve/storedCardMember/save" method="post"
               class="form-horizontal">
        <form:hidden id="id" path="id"/>
        <input id="token" type="hidden" name="token" value="${token}"/>
        <table id="contentTable" class="table table-bordered">
            <tr>
                <td>姓名:</td>
                <td>${reserveMember.name}</td>
                <td>手机号:</td>
                <td>${reserveMember.mobile}</td>
            </tr>
            <tr>
                <td>身份证:</td>
                <td>${reserveMember.sfz}</td>

                <td>地址:</td>
                <td>${reserveMember.address}</td>
            </tr>

            <tr>
                <td>性别:</td>
                <td>${reserveMember.sex}</td>

                <td>卡号:</td>
                <td>${reserveMember.cartno}</td>
            </tr>
            <tr>
                <td>余额:</td>
                <td>${reserveMember.remainder}</td>

                <td>储值卡名称:</td>
                <td>${reserveMember.storedcardSet.name}
                </td>
            </tr>

            <tr>
                <td>备注:</td>
                <td colspan="3">${reserveMember.remarks}
                </td>
            </tr>

            <tr>
                <td>充值:</td>
                <td colspan="3">
                    <input id="rechargeVolume" name="rechargeVolume" htmlEscape="false" maxlength="30" class="form-control required number"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#formBean").validate({
            submitHandler: function (form) {
                formLoding('正在提交，请稍等...');
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function (error, element) {
                formLoding('输入有误，请先更正。');
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
    });
</script>
