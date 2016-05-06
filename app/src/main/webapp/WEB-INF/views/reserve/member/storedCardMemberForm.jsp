<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>储值卡会员添加</title>
    <meta name="decorator" content="main"/>

</head>
<body>
<jsp:include page="/WEB-INF/views/include/sidebar.jsp">
    <jsp:param name="action" value="storedcardMember"></jsp:param>
</jsp:include>
<div class="cl-mcont" id="pcont">
    <div class="row">
        <div class="col-md-12">
            <div class="block-flat">
                <div class="header">
                    <h3>储值卡会员添加</h3>
                </div>
                <div class="content">
                    <div class="tab-container">
                        <div class="form-horizontal group-border-dashed">
                            <form:form id="inputForm" modelAttribute="reserveMember"
                                       action="${ctx}/reserve/storedCardMember/save" method="post"
                                       class="form-horizontal" onsubmit="return checkForm()">
                                <form:hidden id="id" path="id"/>
                                <input type="hidden" name="token" value="${token}"/>
                                <sys:message content="${message}"/>
                                <table id="contentTable" class="table table-bordered">
                                    <tr>
                                        <td>姓名:</td>
                                        <td><form:input path="name" htmlEscape="false" maxlength="30"
                                                        class="required form-control"/>
                                            <span class="help-inline"><font color="red">*</font> </span></td>
                                        <td>手机号:</td>
                                        <td>
                                            <form:input id="mobile" path="mobile" htmlEscape="false" maxlength="20"
                                                        class="form-control phone required"/>
                                            <span class="help-inline"><font color="red">*</font> </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>身份证:</td>
                                        <td>
                                            <form:input id="sfz" path="sfz" htmlEscape="false" maxlength="18"
                                                        class="form-control " />
                                        </td>

                                        <td>地址:</td>
                                        <td>
                                            <form:input path="address" htmlEscape="false" maxlength="100"
                                                        class="form-control "/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>性别:</td>
                                        <td>
                                            <form:radiobuttons path="sex" items="${fns:getDictList('sex')}"
                                                               itemLabel="label" itemValue="value"
                                                               htmlEscape="false"/>
                                        </td>

                                        <td>卡号:</td>
                                        <td>
                                            <form:input id="cardno" path="cartno" htmlEscape="false" maxlength="20"
                                                        class="form-control required"/>
                                            <span class="help-inline"><font color="red">*</font> </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>余额:</td>
                                        <td>
                                            <form:input path="remainder" htmlEscape="false" maxlength="20"
                                                        class="form-control "/>
                                        </td>

                                        <td>储值卡名称:</td>
                                        <td>
                                            <sys:select cssClass="input-xlarge" name="storedcardSet.id"
                                                        items="${storedcardSetList}"
                                                        value="${storedcardSet}" itemLabel="name"
                                                        itemValue="id"></sys:select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>备注:</td>
                                        <td colspan="3">
                                            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255"
                                                           class="form-control"/>
                                        </td>
                                    </tr>
                                </table>
                                <div>
                                    <shiro:hasPermission name="reserve:reserveMember:edit"><input id="btnSubmit"
                                                                                                  class="btn btn-primary"
                                                                                                  type="submit"
                                                                                                  value="保 存"/>&nbsp;</shiro:hasPermission>
                                    <input id="btnCancel" class="btn" type="button" value="返 回"
                                           onclick="history.go(-1)"/>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctxStatic}/modules/reserve/js/reserve_member_form.js" type="text/javascript"></script>
</body>
</html>