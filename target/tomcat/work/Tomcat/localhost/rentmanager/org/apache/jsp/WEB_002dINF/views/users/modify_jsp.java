/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2024-04-07 16:01:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class modify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1707747245720L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1707747245653L));
    _jspx_dependants.put("/WEB-INF/views/common/head.jsp", Long.valueOf(1707747245685L));
    _jspx_dependants.put("/WEB-INF/views/common/sidebar.jsp", Long.valueOf(1707747245780L));
    _jspx_dependants.put("/WEB-INF/views/common/js_imports.jsp", Long.valueOf(1707747245750L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <title>Rent Manager</title>\n");
      out.write("    <!-- Tell the browser to be responsive to screen width -->\n");
      out.write("    <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\n");
      out.write("    <!-- Bootstrap 3.3.7 -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/bootstrap/dist/css/bootstrap.min.css\">\n");
      out.write("    <!-- Font Awesome -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/font-awesome/css/font-awesome.min.css\">\n");
      out.write("    <!-- Ionicons -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/Ionicons/css/ionicons.min.css\">\n");
      out.write("    <!-- Theme style -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/AdminLTE.min.css\">\n");
      out.write("    <!-- AdminLTE Skins. Choose a skin from the css/skins\n");
      out.write("         folder instead of downloading all of them to reduce the load. -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/skins/_all-skins.min.css\">\n");
      out.write("\n");
      out.write("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->\n");
      out.write("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("    <!--[if lt IE 9]>\n");
      out.write("    <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n");
      out.write("    <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n");
      out.write("    <![endif]-->\n");
      out.write("\n");
      out.write("    <!-- Google Font -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic\">\n");
      out.write("</head>");
      out.write("\r\n");
      out.write("<body class=\"hold-transition skin-blue sidebar-mini\">\r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("    ");
      out.write("<header class=\"main-header\">\n");
      out.write("    <!-- Logo -->\n");
      out.write("    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/home\" class=\"logo\">\n");
      out.write("        <!-- mini logo for sidebar mini 50x50 pixels -->\n");
      out.write("        <span class=\"logo-mini\"><b>R</b>M</span>\n");
      out.write("        <!-- logo for regular state and mobile devices -->\n");
      out.write("        <span class=\"logo-lg\"><b>Rent</b> Manager</span>\n");
      out.write("    </a>\n");
      out.write("    <!-- Header Navbar: style can be found in header.less -->\n");
      out.write("    <nav class=\"navbar navbar-static-top\">\n");
      out.write("        <!-- Sidebar toggle button-->\n");
      out.write("        <a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">\n");
      out.write("            <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("            <span class=\"icon-bar\"></span>\n");
      out.write("        </a>\n");
      out.write("    </nav>\n");
      out.write("</header>");
      out.write("\r\n");
      out.write("    <!-- Left side column. contains the logo and sidebar -->\r\n");
      out.write("    ");
      out.write("<!-- Left side column. contains the logo and sidebar -->\n");
      out.write("<aside class=\"main-sidebar\">\n");
      out.write("    <!-- sidebar: style can be found in sidebar.less -->\n");
      out.write("    <section class=\"sidebar\">\n");
      out.write("        <!-- /.search form -->\n");
      out.write("        <!-- sidebar menu: : style can be found in sidebar.less -->\n");
      out.write("        <ul class=\"sidebar-menu\" data-widget=\"tree\">\n");
      out.write("            <li class=\"header\">NAVIGATION</li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/users\">\n");
      out.write("                    <i class=\"fa fa-user\"></i> <span>Utilisateurs</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/cars\">\n");
      out.write("                    <i class=\"fa fa-car\"></i> <span>Voitures</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/rents\">\n");
      out.write("                    <i class=\"fa fa-pencil\"></i> <span>Reservations</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </section>\n");
      out.write("    <!-- /.sidebar -->\n");
      out.write("</aside>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Content Wrapper. Contains page content -->\r\n");
      out.write("    <div class=\"content-wrapper\">\r\n");
      out.write("        <!-- Content Header (Page header) -->\r\n");
      out.write("        <section class=\"content-header\">\r\n");
      out.write("            <h1>\r\n");
      out.write("                Modification de l'utilisateur\r\n");
      out.write("            </h1>\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("        <!-- Main content -->\r\n");
      out.write("        <section class=\"content\">\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"col-md-12\">\r\n");
      out.write("                    <!-- Horizontal Form -->\r\n");
      out.write("                    <div class=\"box\">\r\n");
      out.write("                        <!-- form start -->\r\n");
      out.write("                        <form class=\"form-horizontal\" method=\"post\">\r\n");
      out.write("                            <div class=\"box-body\">\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label for=\"last_name\" class=\"col-sm-2 control-label\">Nom</label>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" id=\"last_name\" name=\"last_name\" placeholder=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.nom}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" onblur=\"validateForm()\" required>\r\n");
      out.write("                                        <div id=\"ErrorMessageNom\" class=\"text-danger\" style=\"display: none;\">\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label for=\"first_name\" class=\"col-sm-2 control-label\">Prenom</label>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" id=\"first_name\" name=\"first_name\" placeholder=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.prenom}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" onblur=\"validateForm()\" required>\r\n");
      out.write("                                        <div id=\"ErrorMessagePrenom\" class=\"text-danger\" style=\"display: none;\">\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label for=\"email\" class=\"col-sm-2 control-label\">Email</label>\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" placeholder=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" onblur=\"validateForm()\" required>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class=\"form-group\">\r\n");
      out.write("                                    <label for=\"Naissance\" class=\"col-sm-2 control-label\">Date de Naissance</label>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"col-sm-10\">\r\n");
      out.write("                                        <input type=\"text\" class=\"form-control\" id=\"Naissance\" name=\"Naissance\" placeholder=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${client.naissance}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" required\r\n");
      out.write("                                               data-inputmask=\"'alias': 'dd/mm/yyyy'\" data-mask onblur=\"validateForm()\">\r\n");
      out.write("                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div id=\"ageErrorMessage\" class=\"col-sm-offset-2 col-sm-10 text-danger\"\r\n");
      out.write("                                     style=\"display: none;\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div id=\"DatesErrorMessage3\" class=\"col-sm-offset-2 col-sm-10 text-danger\"\r\n");
      out.write("                                     style=\"display: none;\">\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- /.box-body -->\r\n");
      out.write("                            <div class=\"box-footer\">\r\n");
      out.write("                                <button id=\"addButton\" type=\"submit\" class=\"btn btn-info pull-right\" disabled>Modifier</button>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- /.box-footer -->\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!-- /.box -->\r\n");
      out.write("                </div>\r\n");
      out.write("                <!-- /.col -->\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("        <!-- /.content -->\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    ");
      out.write("<footer class=\"main-footer\">\n");
      out.write("    <div class=\"pull-right hidden-xs\">\n");
      out.write("        <b>Version</b> 1.0\n");
      out.write("    </div>\n");
      out.write("</footer>\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<!-- ./wrapper -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery 3 -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/jquery/dist/jquery.min.js\"></script>\n");
      out.write("<!-- Bootstrap 3.3.7 -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>\n");
      out.write("<!-- FastClick -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/bower_components/fastclick/lib/fastclick.js\"></script>\n");
      out.write("<!-- AdminLTE App -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/adminlte.min.js\"></script>\n");
      out.write("<!-- AdminLTE for demo purposes -->\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/demo.js\"></script>");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/plugins/input-mask/jquery.inputmask.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/plugins/input-mask/jquery.inputmask.date.extensions.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/plugins/input-mask/jquery.inputmask.extensions.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    $(function () {\r\n");
      out.write("        $('[data-mask]').inputmask()\r\n");
      out.write("    });\r\n");
      out.write("</script>\r\n");
      out.write("<script>\r\n");
      out.write("    function transformDateFormat(DateInput) {\r\n");
      out.write("        return DateInput.replace(/^(\\d{2})\\/(\\d{2})\\/(\\d{4})$/, '$2/$1/$3');\r\n");
      out.write("    }\r\n");
      out.write("    function isValidDateFormat(dateString) {\r\n");
      out.write("        const regex = /^(0[1-9]|[1-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/\\d{4}$/;\r\n");
      out.write("        return regex.test(dateString);\r\n");
      out.write("    }\r\n");
      out.write("    function validateForm() {\r\n");
      out.write("        var formValableAge;\r\n");
      out.write("        var formValableMail;\r\n");
      out.write("        var formValableNom;\r\n");
      out.write("        var formValablePrenom;\r\n");
      out.write("        var formValable = false;\r\n");
      out.write("        var nomImput = document.getElementById(\"last_name\").value;\r\n");
      out.write("        var prenomImput = document.getElementById(\"first_name\").value;\r\n");
      out.write("        var errorMessageNom = document.getElementById(\"ErrorMessageNom\");\r\n");
      out.write("        var errorMessagePrenom = document.getElementById(\"ErrorMessagePrenom\");\r\n");
      out.write("        if (nomImput.length < 3 ) {\r\n");
      out.write("            errorMessageNom.innerHTML = \"Le nom doit contenir au moins 3 caract\\xE8res\";\r\n");
      out.write("            errorMessageNom.style.display = \"block\";\r\n");
      out.write("            errorMessageNom.style.color = \"red\";\r\n");
      out.write("            formValableNom = false;\r\n");
      out.write("        } else {\r\n");
      out.write("            errorMessageNom.style.display = \"none\";\r\n");
      out.write("            formValableNom = true;\r\n");
      out.write("        }\r\n");
      out.write("        if (prenomImput.length < 3 && prenomImput.length > 0) {\r\n");
      out.write("            errorMessagePrenom.innerHTML = \"Le pr\\xE9nom doit contenir au moins 3 caract\\xE8res\";\r\n");
      out.write("            errorMessagePrenom.style.display = \"block\";\r\n");
      out.write("            errorMessagePrenom.style.color = \"red\";\r\n");
      out.write("            formValablePrenom = false;\r\n");
      out.write("        } else {\r\n");
      out.write("            errorMessagePrenom.style.display = \"none\";\r\n");
      out.write("            formValablePrenom = true;\r\n");
      out.write("        }\r\n");
      out.write("        const birthdateInput = document.getElementById(\"Naissance\").value;\r\n");
      out.write("        const birthdateString = birthdateInput.toString();\r\n");
      out.write("        const transformedDate = transformDateFormat(birthdateString);\r\n");
      out.write("        const birthdate = new Date(transformedDate);\r\n");
      out.write("        const today = new Date();\r\n");
      out.write("        let age = today.getFullYear() - birthdate.getFullYear();\r\n");
      out.write("        const monthDiff = today.getMonth() - birthdate.getMonth();\r\n");
      out.write("        if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthdate.getDate())) {\r\n");
      out.write("            age--;\r\n");
      out.write("        }\r\n");
      out.write("        var errorMessage = document.getElementById(\"ageErrorMessage\");\r\n");
      out.write("        if (age < 18) {\r\n");
      out.write("            errorMessage.innerHTML = \"Vous devez avoir au moins 18 ans pour cr\\xE9er un compte.\";\r\n");
      out.write("            errorMessage.style.display = \"block\";\r\n");
      out.write("            errorMessage.style.color = \"red\";\r\n");
      out.write("            formValableAge = false;\r\n");
      out.write("        } else {\r\n");
      out.write("            errorMessage.style.display = \"none\";\r\n");
      out.write("            formValableAge = true;\r\n");
      out.write("        }\r\n");
      out.write("        let formValable3;\r\n");
      out.write("        const errorMessageDate3 = document.getElementById(\"DatesErrorMessage3\");\r\n");
      out.write("        if (birthdateInput.trim() !== \"\") {\r\n");
      out.write("            if (!isValidDateFormat(birthdateInput)) {\r\n");
      out.write("                errorMessageDate3.innerHTML = \"Veuillez entrer une date au format dd/mm/yyyy.\";\r\n");
      out.write("                errorMessageDate3.style.display = \"block\";\r\n");
      out.write("                errorMessageDate3.style.color = \"red\";\r\n");
      out.write("                formValable3 = false;\r\n");
      out.write("            } else {\r\n");
      out.write("                errorMessageDate3.style.display = \"none\";\r\n");
      out.write("                formValable3 = true;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        if (formValableAge && formValableNom && formValablePrenom && formValable3) {\r\n");
      out.write("            formValable = true;\r\n");
      out.write("        }\r\n");
      out.write("        const addButton = document.getElementById(\"addButton\");\r\n");
      out.write("        addButton.disabled = !formValable;\r\n");
      out.write("        return formValable;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/views/users/modify.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("client");
    // /WEB-INF/views/users/modify.jsp(6,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/views/users/modify.jsp(6,0) '${requestScope.client}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${requestScope.client}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }
}
