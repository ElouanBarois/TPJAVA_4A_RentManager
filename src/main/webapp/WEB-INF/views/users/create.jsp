<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp" %>
<body class="hold-transition skin-blue sidebar-mini">
<c:set var="emails" value="${requestScope.emails}"/>
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name"
                                               placeholder="Nom" onblur="validateForm()" required>
                                        <div id="ErrorMessageNom" class="text-danger" style="display: none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name"
                                               placeholder="Prenom" onblur="validateForm()" required>
                                        <div id="ErrorMessagePrenom" class="text-danger" style="display: none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email"
                                               placeholder="Email" onblur="validateForm()" required>
                                        <div id="emailErrorMessage" class="text-danger" style="display: none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Naissance" class="col-sm-2 control-label">Date de Naissance</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="Naissance" name="Naissance" required
                                               data-inputmask="'alias': 'dd/mm/yyyy'" data-mask
                                               onblur="validateForm()">
                                    </div>
                                </div>
                                <div id="ageErrorMessage" class="col-sm-offset-2 col-sm-10 text-danger"
                                     style="display: none;">
                                </div>
                                <div id="DatesErrorMessage3" class="col-sm-offset-2 col-sm-10 text-danger"
                                     style="display: none;">
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button id="addButton" type="submit" class="btn btn-info pull-right" disabled>Ajouter
                                </button>
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
        var formValableAge;
        var formValableMail;
        var formValableNom;
        var formValablePrenom;
        var formValable = false;
        var nomImput = document.getElementById("last_name").value;
        var prenomImput = document.getElementById("first_name").value;
        var errorMessageNom = document.getElementById("ErrorMessageNom");
        var errorMessagePrenom = document.getElementById("ErrorMessagePrenom");
        if (nomImput.length < 3 ) {
            errorMessageNom.innerHTML = "Le nom doit contenir au moins 3 caract\xE8res";
            errorMessageNom.style.display = "block";
            errorMessageNom.style.color = "red";
            formValableNom = false;
        } else {
            errorMessageNom.style.display = "none";
            formValableNom = true;
        }
        if (prenomImput.length < 3 && prenomImput.length > 0) {
            errorMessagePrenom.innerHTML = "Le pr\xE9nom doit contenir au moins 3 caract\xE8res";
            errorMessagePrenom.style.display = "block";
            errorMessagePrenom.style.color = "red";
            formValablePrenom = false;
        } else {
            errorMessagePrenom.style.display = "none";
            formValablePrenom = true;
        }
        const birthdateInput = document.getElementById("Naissance").value;
        const birthdateString = birthdateInput.toString();
        const transformedDate = transformDateFormat(birthdateString);
        const birthdate = new Date(transformedDate);
        const today = new Date();
        let age = today.getFullYear() - birthdate.getFullYear();
        const monthDiff = today.getMonth() - birthdate.getMonth();
        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthdate.getDate())) {
            age--;
        }
        var errorMessage = document.getElementById("ageErrorMessage");
        if (age < 18) {
            errorMessage.innerHTML = "Vous devez avoir au moins 18 ans pour cr\xE9er un compte.";
            errorMessage.style.display = "block";
            errorMessage.style.color = "red";
            formValableAge = false;
        } else {
            errorMessage.style.display = "none";
            formValableAge = true;
        }
        let formValable3;
        const errorMessageDate3 = document.getElementById("DatesErrorMessage3");
        if (birthdateInput.trim() !== "") {
            if (!isValidDateFormat(birthdateInput)) {
                errorMessageDate3.innerHTML = "Veuillez entrer une date au format dd/mm/yyyy.";
                errorMessageDate3.style.display = "block";
                errorMessageDate3.style.color = "red";
                formValable3 = false;
            } else {
                errorMessageDate3.style.display = "none";
                formValable3 = true;
            }
        }
        const emailInput = document.getElementById("email").value;
        var emailsList = ${emails};
        const emailErrorMessage = document.getElementById("emailErrorMessage");

        if (emailsList.indexOf(emailInput) !== -1) {
            emailErrorMessage.innerHTML = "Email d\xE9j\xE0 utilis\xE9.";
            emailErrorMessage.style.display = "block";
            emailErrorMessage.style.color = "red";
            formValableMail = false;
        } else {
            emailErrorMessage.style.display = "none";
            formValableMail = true;
        }

        if (formValableAge && formValableMail && formValableNom && formValablePrenom && formValable3) {
            formValable = true;
        }
        const addButton = document.getElementById("addButton");
        addButton.disabled = !formValable;
        return formValable;
    }


</script>
</body>
</html>
