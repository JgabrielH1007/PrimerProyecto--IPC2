<%-- 
    Document   : resultados_busqueda
    Created on : 15 sept 2024, 2:57:15
    Author     : gabrielh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Revista.Revista"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Resultados de Búsqueda</title>
        <!-- Bootstrap CSS -->
        <jsp:include page="/includes/resources.jsp"/>
        <jsp:include page="/includes/style.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/Header.jsp"/>
        <div class="layout-container container-fluid">

        <jsp:include page="/includes/leftSide.jsp"/>

            <main>
                <section class ="section">
                    <div class = "container">
                        <c:forEach items="${revistas}" var="revista">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${revistas}.nombreRevista</h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.descripcion}</h6>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.categoria}</h6>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.etiquetas}</h6>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.cantidadMegusta}</h6>
                                    <a href="${pageContext.servletContext.contextPath}/mvc/revistas/revistas-servlet?nombre=${revista.nombre}" class="card-link">Ver</a>
                                    <a href="#" class ="car-link">Suscribirse</a>
                                </div>
                            </div>
                        </c:forEach>        
                    </div>
                </section>
            </main>
            <jsp:include page="/includes/rightSide.jsp"/>
        </div>
    </body>
</html>

