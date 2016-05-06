<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>场地管理</title>
    <meta name="decorator" content="main"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/sidebar.jsp">
    <jsp:param name="action" value="field"></jsp:param>
</jsp:include>
<div class="container-fluid" id="pcont">
    <div class="row">
        <div class="col-md-12">
            <div class="block-flat">
                <div class="header">
                    <h3>场地列表</h3>
                </div>


                <form:form id="searchForm" modelAttribute="reserveField" action="${ctx}/reserve/reserveField/"
                           method="post" class="breadcrumb form-search">
                    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

                    <div class="row">
                        <div class="col-sm-6 col-md-6 col-lg-6">
                            <table class="no-border">
                                <tbody class="no-border-y">
                                <tr>
                                    <td>场地名称：</td>
                                    <td><form:input path="name" htmlEscape="false" cssstyle="width:70px;" maxlength="30"
                                                    class="form-control"/></td>
                                    <td><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>


                        <div class="pull-right">
                            <a class="btn btn-success" href="${ctx}/reserve/reserveField/form">
                                <i class="fa fa-plus"></i>添加
                            </a>
                        </div>
                    </div>
                </form:form>
                <sys:message content="${message}"/>
                <div class="content">
                    <div class="table-responsive">
                        <table>
                            <thead>
                            <tr>
                                <th>场地名称</th>
                                <th>所属项目</th>
                                <th>所属场馆</th>
                                <th>是否启用</th>
                                <shiro:hasPermission name="reserve:reserveField:edit">
                                    <th>操作</th>
                                </shiro:hasPermission>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.list}" var="reserveField">
                                <tr>
                                    <td>
                                        <a href="${ctx}/reserve/reserveField/form?id=${reserveField.id}">${reserveField.name}</a>
                                    </td>
                                    <td>${reserveField.reserveProject.name}</td>
                                    <td>${reserveField.reserveVenue.name}</td>
                                    <td>
                                            ${fns:getDictLabel(reserveField.available, 'yes_no', '')}
                                    </td>
                                    <shiro:hasPermission name="reserve:reserveField:edit">
                                        <td>
                                            <a class="btn btn-primary btn-xs"
                                               href="${ctx}/reserve/reserveField/form?id=${reserveField.id}"><i
                                                    class="fa fa-pencil"></i>修改</a>
                                            <a class="btn btn-danger btn-xs"
                                               href="${ctx}/reserve/reserveField/delete?id=${reserveField.id}"
                                               onclick="return confirmb('确认要删除该场地吗？', this.href)"><i
                                                    class="fa fa-times"></i>删除</a>
                                        </td>
                                    </shiro:hasPermission>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-sm-12">

                                <div class="pull-right">
                                    <div class="dataTables_paginate paging_bs_normal">
                                        <sys:javascript_page p="${page}"></sys:javascript_page>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>