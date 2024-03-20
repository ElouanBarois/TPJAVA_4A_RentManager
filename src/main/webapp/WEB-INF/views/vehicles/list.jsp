<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Voitures
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/cars/create">Ajouter</a>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Marque</th>
                                    <th>Modele</th>
                                    <th>Nombre de places</th>
                                    <th>Action</th>
                                </tr>
                                <tr>
                                <c:forEach items="${vehicles}" var="vehicle">
                                    <td>${vehicle.id}.</td>
                                    <td>${vehicle.constructeur}</td>
                                    <td>${vehicle.modele}</td>
                                    <td>${vehicle.nbPlaces}</td>
                                    <td style="display: flex; align-items: center;">
                                        <a class="btn btn-primary " href="${pageContext.request.contextPath}/cars/details?id=${vehicle.id}" style="margin-right: 5px;">
                                            <i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success " href="${pageContext.request.contextPath}/cars/modify?id=${vehicle.id}" style="margin-right: 5px;">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <form method="post" action="${pageContext.request.contextPath}/cars">
                                            <input type="hidden" name="id" value="${vehicle.id}">
                                            <button type="submit" class="btn btn-danger" style="margin-right: 5px;">
                                                <i class="fa fa-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
