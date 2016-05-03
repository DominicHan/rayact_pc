<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="main"/>
    <title>商品入库记录管理</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/sidebar.jsp">
    <jsp:param name="action" value="reserveCommodityStorageLog"></jsp:param>
</jsp:include>
<div class="container-fluid" id="pcont">
    <div class="row">
        <div class="col-md-12">
            <div class="block-flat">
                <div class="header">
                    <h3>商品入库记录列表</h3>
                </div>
                <form:form id="searchForm" modelAttribute="reserveCommodityStorageLog"
                           action="${ctx}/reserve/reserveCommodityStorageLog/list"
                           method="post">

                    <div class="row col-lg-12 col-sm-12 breadcrumb form-search" style="margin-left:0px; margin-right:0px;">
                        <div class="form-group col-lg-3 col-sm-5">
                            <label class="control-label" for="venue">场馆：</label>
                            <sys:select cssClass="input-large" name="reserveVenue.id" id="venue"
                                        cssStyle="width:50%"
                                        value="${query.reserveVenue.id}"
                                        items="${venues}" itemLabel="name" itemValue="id"
                                        defaultLabel="----请选择-----"
                                        defaultValue=""></sys:select>
                        </div>

                        <div class="form-group col-lg-4 col-sm-6">
                            <div class="col-lg-6 col-sm-6 ">
                                <input id="startDate" name="startDate" type="text"
                                       value="<fmt:formatDate  pattern="yyyy-MM-dd" value="${query.startDate}"/>"
                                       maxlength="20"
                                       class="input-medium form-control Wdate"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                            </div>
                            <div class="col-lg-6 col-sm-6 ">
                                <input value="<fmt:formatDate  pattern="yyyy-MM-dd" value="${query.endDate}"/>"
                                       name="endDate" id="endDate" type="text"
                                       maxlength="20"
                                       class="input-medium form-control Wdate "
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                            </div>
                        </div>
                        <div class="form-group col-lg-2 col-sm-2">
                            <input id="btnSubmit" class="btn btn-primary" type="submit"
                                   value="查询"/>
                        </div>
                    </div>
                    <sys:message content="${message}"/>
                    <div class="content">
                        <div class="table-responsive">
                            <table>
                                <thead>
                                <tr>
                                    <th>场馆</th>
                                    <th>商品</th>
                                    <th>入库箱数</th>
                                    <th>入库量</th>
                                    <th>入库前库存量</th>
                                    <th>入库后库存量</th>
                                    <th>入库单箱价格</th>
                                    <th>入库单价</th>
                                    <th>操作人</th>
                                    <th>备注</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.list}" var="reserveCommodityStorageLog">
                                    <tr>
                                        <td>
                                                ${reserveCommodityStorageLog.reserveVenue.name}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.reserveCommodity.name}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.boxNum}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.num}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.beforeNum}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.afterNum}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.boxPrice}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.price}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.createBy.name}
                                        </td>
                                        <td>
                                                ${reserveCommodityStorageLog.remarks}
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${reserveCommodityStorageLog.createDate}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <div class="row">
                                <div class="col-sm-12">

                                    <div class="pull-right">
                                        <div class="dataTables_paginate paging_bs_normal">
                                            <sys:javascript_page p="${page}" formId="searchForm"></sys:javascript_page>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#btnExport").click(function () {
        $("#searchForm").attr("action", "${ctx}/reserve/reserveCommodityStorageLog/listExport");
        $("#searchForm").submit();
        $("#searchForm").attr("action", "${ctx}/reserve/reserveCommodityStorageLog/list");
    });
</script>
</body>
</html>
