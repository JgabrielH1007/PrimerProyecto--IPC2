<%-- 
    Document   : resultados_busqueda
    Created on : 15 sept 2024, 2:57:15
    Author     : gabrielh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>SUSCRIPTOR</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-kenU1KFdBIe4zVF0sG1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous">
    <jsp:include page="/includes/style.jsp"/>
</head>
<body>
    <jsp:include page="/includes/Header.jsp"/>

    <!-- Contenedor de todo el layout -->
    <div class="layout-container container-fluid">
        <!-- Incluye la barra lateral izquierda -->
        <jsp:include page="/includes/leftSide.jsp"/>

        <!-- Contenido principal -->
        <main class="main-content">
            <section class="form-container mt-4">
                <div class="container">
                    <h2>Buscar Revistas</h2>
                    <form id="search-form" method="POST" action="${pageContext.request.contextPath}/Controllers/Revista/search">
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <select id="search-category" name="category" class="form-select">
                                    <jsp:include page="/includes/category.jsp"/>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <jsp:include page="/includes/tags.jsp"/>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </form>

                    <br>

                    <!-- Botón para mostrar revistas suscritas -->
                    <form action="${pageContext.request.contextPath}/Controllers/revistas/listar-suscripciones" method="get">
                        <button type="submit" class="btn btn-primary">Mostrar Revistas Suscritas</button>
                    </form>

                    <!-- Lista de revistas suscritas -->
                    <c:if test="${not empty revistas}">
                        <h2>Revistas a las que estás suscrito</h2>
                        <c:forEach items="${revistas}" var="revista">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">${revista.nombre}</h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revista.descripcion}</h6>
                                    <p><strong>Categoría:</strong> ${revista.categoria}</p>
                                    <p><strong>Etiquetas:</strong> 
                                        <c:choose>
                                            <c:when test="${not empty revista.etiquetas}">
                                                <c:forEach items="${revista.etiquetas}" var="etiqueta" varStatus="status">
                                                    <c:out value="${etiqueta}"/>
                                                    <c:if test="${status.last == false}">
                                                        , 
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>No disponibles</c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p><strong>Autor:</strong> ${revista.autor}</p>
                                    <p><strong>Cantidad me gusta:</strong> ${revista.cantidadMegusta}</p>
                                    <a href="${pageContext.servletContext.contextPath}/Controllers/revistas/ver-contenido?nombre=${revista.nombre}" class="btn btn-primary">Ver Contenido</a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </section>
        </main>

        <jsp:include page="/includes/rightSide.jsp"/>
    </div>

</body>
</html>




