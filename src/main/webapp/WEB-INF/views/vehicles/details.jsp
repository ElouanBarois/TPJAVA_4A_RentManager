<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<c:set var="vehicle" value="${requestScope.vehicle}" />
<c:set var="clients" value="${requestScope.clients}" />
<c:set var="reservations" value="${requestScope.reservations}" />
<c:set var="reservationsR" value="${requestScope.reservationsR}" />
<c:set var="nbClient" value="${requestScope.nbClient}" />
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center" >${vehicle.constructeur} ${vehicle.modele} ( Places: ${vehicle.nbPlaces})</h3>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Reservation(s)</b> <a class="pull-right">${reservations.size()}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Client(s)</b> <a class="pull-right">${nbClient}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
                            <li><a href="#users" data-toggle="tab">Clients</a></li>
                        </ul>
                        <div class="tab-content">

                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Client</th>
                                            <th>Date de debut</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <tr>
                                            <c:forEach items="${reservationsR}" var="reservationsR">
                                            <td>${reservationsR.id}.</td>
                                            <td>${reservationsR.clientNom}</td>
                                            <td>${reservationsR.debut}</td>
                                            <td>${reservationsR.fin}</td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="users">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th style=>email</th>
                                        </tr>
                                        <tr>
                                            <c:forEach items="${clients}" var="client">
                                            <td>${client.id}.</td>
                                            <td>${client.nom}</td>
                                            <td>${client.prenom}</td>
                                            <td>${client.email}</td>
                                        </tr>
                                        </c:forEach>

                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>