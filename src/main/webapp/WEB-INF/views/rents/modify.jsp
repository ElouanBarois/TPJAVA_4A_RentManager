<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<c:set var="reservation" value="${requestScope.reservation}" />
<c:set var="reservationDTO" value="${requestScope.reservationDTO}" />

<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Modification de la reservation
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->

                        <!-- ATTENTION SE RÉFERER AU FORMULAIRE DE CREATION DE RESERVATION -->

                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="car" class="col-sm-2 control-label">Voiture: </label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="car" name="car" required>
                                            <option value="">Select Vehicule</option>
                                            <c:forEach items="${vehicles}" var="car">
                                                <option value="${car.id}" <c:if test="${car.constructeur eq reservationDTO.vehicleManufacturer && car.modele eq reservationDTO.vehicleModele}">selected</c:if>>${car.constructeur} ${car.modele}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client: </label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="client" name="client" required>
                                            <option value="">Select Client</option>
                                            <c:forEach items="${clients}" var="client">
                                                <%-- <option value="${client.id}">${client.nom} ${client.prenom}</option> --%>
                                                <option value="${client.id}" <c:if test="${client.nom eq reservationDTO.clientNom && client.prenom eq reservationDTO.clientPrenom}">selected</c:if>>${client.nom} ${client.prenom}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="begin" class="col-sm-2 control-label">Date de debut: </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="begin" name="begin" required
                                               data-inputmask="'alias': 'dd/mm/yyyy'" data-mask value="${reservationDTO.debut}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end" class="col-sm-2 control-label">Date de fin: </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="end" name="end" required
                                               data-inputmask="'alias': 'dd/mm/yyyy'" data-mask value="${reservationDTO.fin}" onblur="validateForm()">
                                    </div>
                                </div>
                                <div id="DatesErrorMessage" class="col-sm-offset-2 col-sm-10 text-danger"
                                     style="display: none;">
                                </div>
                                <div id="DatesErrorMessage2" class="col-sm-offset-2 col-sm-10 text-danger"
                                     style="display: none;">
                                </div>
                                <div id="DatesErrorMessage3" class="col-sm-offset-2 col-sm-10 text-danger"
                                     style="display: none;">
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
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
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
<script>
    function transformDateFormat(DateInput) {
        return DateInput.replace(/^(\d{2})\/(\d{2})\/(\d{4})$/, '$2/$1/$3');
    }
    function isValidDateFormat(dateString) {
        const regex = /^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/\d{4}$/;
        return regex.test(dateString);
    }

    function validateForm() {
        const beginDateInput = document.getElementById("begin").value;
        const endDateInput = document.getElementById("end").value;
        const beginDateString = beginDateInput.toString();
        const endDateString = endDateInput.toString();
        const transformedBeginDate = transformDateFormat(beginDateString);
        const transformedEndDate = transformDateFormat(endDateString)
        let formValable3;
        const errorMessageDate3 = document.getElementById("DatesErrorMessage3");
        if (!isValidDateFormat(beginDateInput) || !isValidDateFormat(endDateInput)) {
            errorMessageDate3.innerHTML = "Veuillez entrer une date au format dd/mm/yyyy.";
            errorMessageDate3.style.display = "block";
            errorMessageDate3.style.color = "red";
            formValable3= false;
        } else{
            errorMessageDate3.style.display = "none";
            formValable3 =true;
        }
        const beginDate = new Date(transformedBeginDate);
        const endDate = new Date(transformedEndDate);
        const errorMessageDate = document.getElementById("DatesErrorMessage");
        const errorMessageDate2 = document.getElementById("DatesErrorMessage2");

        let formValable1;
        let formValable2;
        let formValable = false;
        if (beginDate > endDate) {
            errorMessageDate.innerHTML = "La date de fin doit \xEAtre ult\xE9rieure \xE0 celle du d\xE9but."
            errorMessageDate.style.display = "block";
            errorMessageDate.style.color = "red"
            formValable1 = false;
        } else {
            errorMessageDate.style.display = "none";
            formValable1 = true;
        }
        const timeDifference = endDate.getTime() - beginDate.getTime();
        const daysDifference = timeDifference / (1000 * 3600 * 24);
        if (daysDifference > 7) {
            errorMessageDate2.innerHTML = "La r\xE9servation ne peut pas durer plus de 7 jours."
            errorMessageDate2.style.display = "block";
            errorMessageDate2.style.color = "red"
            formValable2 = false;
        } else {
            errorMessageDate2.style.display = "none";
            formValable2 = true;
        }
        if (formValable1 && formValable2 && formValable3) {
            formValable = true
        }
        const addButton = document.getElementById("addButton");
        addButton.disabled = !formValable;
        return formValable;
    }
</script>
</body>
</html>