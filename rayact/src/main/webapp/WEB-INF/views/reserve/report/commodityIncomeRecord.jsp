<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="main"/>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/modules/reserve/css/field.css?id=7862256"/>
    <title>会员收入统计</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/sidebar.jsp">
    <jsp:param name="action" value="commodityIncomeRecord"></jsp:param>
</jsp:include>
<div class="container-fluid" id="pcont">
    <div class="row">
        <div class="col-md-12">
            <div class="block-flat">
                <div class="header">
                    <h3>商品收入统计</h3>
                </div>
                <form:form id="searchForm" modelAttribute="reserveCardStatements"
                           action="${ctx}/reserve/reserveCardStatements/listByCommodityType"
                           method="post" class="breadcrumb form-search">
                    <div class="row">
                        <div class="col-sm-10 col-md-10 col-lg-10">
                            <table class="no-border">
                                <tbody class="no-border-y">
                                <tr>
                                    <td colspan="3">
                                        <div class="tab-tit-first">
                                            <ul>
                                                <a href="javascript:queryToday()"><li>当天</li></a>
                                                <a href="javascript:queryMonth()"><li>当月</li></a>
                                                <a href="javascript:queryYear()"><li>当年</li></a>
                                            </ul>
                                        </div>
                                    </td>
                                    <td>时间:</td>
                                    <td>
                                        <input value="<fmt:formatDate  pattern="yyyy-MM-dd" value="${reserveCardStatements.startDate}"/>"
                                               name="startDate" id="startDate" type="text" readonly="readonly"
                                               maxlength="20"
                                               style="width: 90px;height: 30px;"
                                               class="input-medium Wdate "
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>至
                                        <input value="<fmt:formatDate  pattern="yyyy-MM-dd" value="${reserveCardStatements.endDate}"/>"
                                               name="endDate" id="endDate" type="text" readonly="readonly"
                                               maxlength="20"
                                               style="width: 90px;height: 30px;"
                                               class="input-medium Wdate "
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                                    </td>
                                    <td><input id="btnSubmit" class="btn btn-primary" type="submit"
                                               value="查询"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </form:form>
                <sys:msg content="${message}"/>
                <div class="content">
                    <div class="table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th>商品类型</th>
                                <th>销售金额</th>
                                <th>销售比例</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page}" var="commodityIncomeRecord">
                                <tr>
                                    <td>
                                            ${commodityIncomeRecord.commodityTypeName}
                                    </td>

                                    <td>
                                            ${commodityIncomeRecord.saleAmount}
                                    </td>
                                    <td>
                                            ${commodityIncomeRecord.saleRate}%
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctxStatic}/modules/reserve/js/commodity_income_record.js" type="text/javascript"></script>
</body>
</html>
