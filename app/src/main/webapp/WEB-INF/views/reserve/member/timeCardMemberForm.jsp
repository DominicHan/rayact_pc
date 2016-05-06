<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>次卡会员添加</title>
    <meta name="decorator" content="main"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/sidebar.jsp">
    <jsp:param name="action" value="timecardMember"></jsp:param>
</jsp:include>
<div class="cl-mcont" id="pcont">
    <div class="row">
        <div class="col-md-12">
            <div class="block-flat">
                <div class="header">
                    <h3>次卡会员添加</h3>
                </div>
                <div class="content">
                    <div class="tab-container">
                        <div class="form-horizontal group-border-dashed">
                            <form:form id="inputForm" modelAttribute="reserveMember" action="${ctx}/reserve/timeCardMember/save" method="post"
                                       onsubmit="return checkForm()" class="form-horizontal">
                                <form:hidden id="id" path="id"/>
                                <input type="hidden" name="token" value="${token}"/>
                                <sys:message content="${message}"/>
                                <table id="contentTable" class="table table-bordered">
                                    <tr>
                                        <td>姓名:</td>
                                        <td><form:input path="name" htmlEscape="false" maxlength="30" class="form-control required "/>
                                            <span class="help-inline"><font color="red">*</font> </span></td>
                                        <td>手机号:</td>
                                        <td>
                                            <form:input id="mobile" path="mobile" htmlEscape="false" maxlength="20" class="form-control required"/>
                                            <span class="help-inline"><font color="red">*</font> </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>身份证:</td>
                                        <td>
                                            <form:input id="sfz" path="sfz" htmlEscape="false" maxlength="18" class="form-control "/>
                                        </td>

                                        <td>地址:</td>
                                        <td>
                                            <form:input path="address" htmlEscape="false" maxlength="100" class="form-control "/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>性别:</td>
                                        <td>
                                            <form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value"
                                                               htmlEscape="false"/>
                                        </td>

                                        <td>卡号:</td>
                                        <td>
                                            <form:input id="cardno" path="cartno" htmlEscape="false" maxlength="20" class="form-control required"/>
                                            <span class="help-inline"><font color="red">*</font> </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>次卡名称:</td>
                                        <td colspan="3">
                                            <sys:select cssClass="input-xlarge" name="timecardSet.id" items="${timecardSetList}"
                                                        value="${timecardSet}" itemLabel="name" itemValue="id"></sys:select>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td>卡号截止日期:</td>
                                        <td>
                                            <input name="validityend" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                                                   value="<fmt:formatDate value="${reserveMember.validityend}" pattern="yyyy-MM-dd"/>"
                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                                        </td>

                                        <td>剩余次数:</td>
                                        <td>
                                            <form:input path="residue" htmlEscape="false" maxlength="11" class="form-control "/>&nbsp;次
                                        </td>
                                    </tr>


                                    <tr>
                                        <td>备注:</td>
                                        <td colspan="3">
                                            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
                                        </td>
                                    </tr>
                                </table>

                                <div class="form-actions">
                                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
                                    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
                                </div>
                            </form:form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxStatic}/modules/reserve/js/validate.js"></script>
<script src="${ctxStatic}/modules/reserve/js/reserve_member_form.js" type="text/javascript"></script>
</body>
</html>