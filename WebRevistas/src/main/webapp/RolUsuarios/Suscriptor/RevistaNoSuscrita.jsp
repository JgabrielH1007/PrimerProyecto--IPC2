<%-- 
    Document   : RevistaNoSuscrita
    Created on : 16 sept 2024, 23:46:52
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Revista No Suscrita - ${revista.nombre}</title>
    <!-- Bootstrap CSS -->
    <jsp:include page="/includes/resources.jsp"/>
    <jsp:include page="/includes/style.jsp"/>
</head>
<body>
    <jsp:include page="/includes/Header.jsp"/>
    <div class="layout-container container-fluid">
        <jsp:include page="/includes/leftSide.jsp"/>

        <main>
            <section class="section">
                <div class="container">
                    <div class="card">
                        <div class="card-body">
                            <!-- Información de la Revista -->
                            <h4>Acceso Denegado</h4>
                            <p>No tienes una suscripción activa para la revista <strong>${revista.nombre}</strong>.</p>
                            
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Información de la Revista</h5>
                                    <p><strong>Descripción:</strong> ${revista.descripcion}</p>
                                    <p><strong>Categoría:</strong> ${revista.categoria}</p>
                                    <p><strong>Etiquetas:</strong>
                                        <c:choose>
                                            <c:when test="${not empty revista.etiquetas}">
                                                <c:forEach var="etiqueta" items="${revista.etiquetas}">
                                                    <c:out value="${etiqueta}"/>
                                                    <c:if test="${not empty etiqueta}">
                                                        <c:if test="${not empty etiqueta}">
                                                            <c:out value=", "/>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                No disponibles
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p><strong>Autor:</strong> ${revista.autor}</p>
                                    
                                    <c:choose>
                                        <c:when test="${revista.suscripciones}">
                                            <p>Para acceder al contenido completo, por favor suscríbete a la revista.</p>
                                            <form action="${pageContext.request.contextPath}/Controllers/revistas/suscribir" method="post">
                                                <input type="hidden" name="nombreRevista" value="${revista.nombre}">
                                                <div class="form-group mb-3">
                                                    <label for="fecha-suscripcion">Fecha de suscripción:</label>
                                                    <input type="date" id="fecha-suscripcion" name="fecha-suscripcion" class="form-control" required>
                                                </div>
                                                <button type="submit" class="btn btn-primary mt-3">Suscribirse</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <p>La suscripción a esta revista no está habilitada.</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <jsp:include page="/includes/rightSide.jsp"/>
    </div>
</body>
</html>



