<%-- 
    Document   : RevistaSuscrita
    Created on : 15 sept 2024, 3:15:08
    Author     : gabrielh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.io.InputStream"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Suscripción - ${revista.nombre}</title>
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
                                <label>Nombre revista:</label><h5 class="card-title">${revista.nombre}</h5>
                                <label>Descripcion: </label><h6 class="card-subtitle mb-2 text-body-secondary">${revista.descripcion}</h6>
                                <label>Categoria: </label><h6 class="card-subtitle mb-2 text-body-secondary">${revista.categoria}</h6>
                                <label>Etiquetas: </label><h6 class="card-subtitle mb-2 text-body-secondary">${revista.etiquetas}</h6>
                                <label>Autor: </label><h6 class="card-subtitle mb-2 text-body-secondary">${revista.autor}</h6>
                                <label>Cantidad me gustas: </label><h6 class="card-subtitle mb-2 text-body-secondary">${revista.cantidadMegusta}</h6>

                                <h5>Capítulos:</h5>
                                <ul>
                                    <c:forEach var="capitulo" items="${capitulos}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/Controllers/revistas/descargar-pdf?nombre=${revista.nombre}&noCapitulo=${capitulo.numeroCapitulo}">
                                                ${capitulo.nombreArchivo}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger">
                                        ${error}
                                    </div>
                                </c:if>

                                <!-- Formulario para dejar un comentario -->
                                    <c:if test="${revista.comentario}">
                                        <form action="${pageContext.request.contextPath}/Controllers/revistas/comentar" method="post">
                                            <input type="hidden" name="nombreRevista" value="${revista.nombre}">
                                            <textarea name="comentario" rows="4" cols="50" placeholder="Deja tu comentario aquí..."></textarea>
                                            <button type="submit" class="btn btn-primary mt-3">Enviar Comentario</button>
                                        </form>
                                    </c:if>
                                    
                                    <!-- Formulario para Me Gusta -->
                                    <c:if test="${revista.megusta}">
                                        <form action="${pageContext.request.contextPath}/Controllers/revistas/me-gusta" method="post">
                                            <input type="hidden" name="nombreRevista" value="${revista.nombre}">
                                            <c:choose>
                                                <c:when test="${leGusta}">
                                                    <button type="submit" class="btn btn-danger mt-3">Ya no Me Gusta</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" class="btn btn-primary mt-3">Me Gusta</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </c:if>
                                    <br>
                                    <label>Autor: </label>
                                        <h6 class="card-subtitle mb-2 text-body-secondary">
                                            ${revista.autor}
                                            <!-- Enlace para ver el perfil del autor -->
                                            <a href="${pageContext.request.contextPath}/Controllers/mostrar-perfilAutor?autor=${revista.autor}" class="btn btn-link">Ver Perfil</a>
                                        </h6>
                                    <br>               
                                <!-- Sección para mostrar los comentarios -->
                                <h5>Comentarios:</h5>
                                <ul>
                                    <c:forEach var="comentario" items="${comentarios}">
                                        <li>
                                            <strong>${comentario.userName}:</strong> ${comentario.contenido}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </section>
            </main>

            <jsp:include page="/includes/rightSide.jsp"/>
        </div>
    </body>
</html>


