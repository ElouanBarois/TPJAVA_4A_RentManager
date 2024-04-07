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
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <!-- Le  type de methode http qui sera appel� lors de action submit du formulaire -->
                        <!-- est d�crit an l'attribut "method" de la balise forme -->
                        <!-- action indique � quel "cible" sera envoyr la requ�te, ici notre Servlet qui sera bind sur -->
                        <!-- /vehicles/create -->
                        <form class="form-horizontal" method="post" onsubmit="return validateForm()">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="manufacturer" class="col-sm-2 control-label">Marque</label>

									<!-- Pour r�up�rer la valeur rentr�e dans un champ input de cette jsp au niveau de votre servlet -->
									<!-- vous devez passer par les methodes getParameter de l'objet request, est sp�cifiant la valeur -->
									<!-- de l'attribut "name" de l'input -->
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="Marque" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="modele" class="col-sm-2 control-label">Modele</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="modele" name="modele" placeholder="Modele" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="seats" class="col-sm-2 control-label">Nombre de places</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="seats" name="seats" placeholder="Nombre de places" required onblur="validateForm()">
                                    </div>
                                </div>
                                <div id="seatsErrorMessage" class="col-sm-offset-2 col-sm-10 text-danger" style="display: none;"></div>


                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button id="addButton" type="submit" class="btn btn-info pull-right">Ajouter</button>
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
<script>
    function validateForm() {
        var formValable = false;
        var seatsInput = document.getElementById("seats").value;
        var seats = parseInt(seatsInput);
        var errorMessage = document.getElementById("seatsErrorMessage");
        if (seatsInput !== "") {
            if (seats < 2 || seats > 9) {
                errorMessage.innerHTML = "Le nombre de places doit etre compris entre 2 et 9.";
                errorMessage.style.display = "block";
                errorMessage.style.color = "red";
                formValable = false;

            } else {
                errorMessage.style.display = "none";
                formValable = true;
            }
        }else{
            errorMessage.innerHTML = "Le nombre de places doit etre compris entre 2 et 9.";
            errorMessage.style.display = "block";
            errorMessage.style.color = "red";
            formValable = false;
        }


        var addButton = document.getElementById("addButton");
        addButton.disabled = !formValable;
        return formValable;
    }
</script>
</body>
</html>
