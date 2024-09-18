<%-- 
    Document   : Suscriptor
    Created on : 11 sept 2024, 3:02:21
    Author     : gabrielh
--%>

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
                        <form id="search-form" method="GET" action="${pageContext.request.contextPath}/search">
                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <input type="text" id="search-name" name="name" class="form-control" placeholder="Buscar por nombre">
                                </div>
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
                    </div>
                                <br>
                    <div class = "container">
                        <h2>Suscripciones</h2>
                        <c:forEach items="${revistas}" var="revista">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${revistas.nombreRevista}</h5>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.descripcion}</h6>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.categoria}</h6>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.etiquetas}</h6>
                                    <h6 class="card-subtitle mb-2 text-body-secondary">${revistas.cantidadMegusta}</h6>
                                    <a href="${pageContext.servletContext.contextPath}/mvc/revistas/revistas-servlet?nombre=${revista.nombre}" class="card-link">Ver</a>
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


