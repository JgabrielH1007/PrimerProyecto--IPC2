<%-- 
    Document   : Editor
    Created on : 11 sept 2024, 3:01:26
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDITOR</title>
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
                <section class="form-container">
                    <div class="form-box">
                        <h3>Publicar revista: </h3>
                        <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Revista/revista-servlet" enctype="multipart/form-data">
                            <div class="form-group mb-3">
                                <label for="nombre">Nombre de la revista: </label>
                                <input id="nombre" name="nombre" maxlength="20" class="form-control" placeholder="Nombre revista" required>
                            </div>

                            <div class="form-group mb-3">
                                <label for="descripcion">Descripción de la revista: </label>
                                <input id="descripcion" name="descripcion" maxlength="200" class="form-control" placeholder="Descripción revista" required>
                            </div>

                            <div class="form-group mb-3">
                                <label for="categoria" class="form-label">Seleccione categoría:</label>
                                <select id="categoria" name="categoria" class="form-select" required>
                                    <jsp:include page="/includes/category.jsp"/>
                                </select>
                            </div>

                            <div class="form-group mb-3">
                                <label>Etiquetas:</label>
                                <jsp:include page = "/includes/tags.jsp"/>
                            </div>

                            <div class="form-group mb-3">
                                <label for="fecha-publicacion">Fecha de publicación:</label>
                                <input type="date" id="fecha-publicacion" name="fecha-publicacion" class="form-control" required>
                            </div>

                            <div class="form-group mb-3">
                                <label for="comentarios">Permitir comentarios:</label>
                                <input type="checkbox" id="comentarios" name="comentarios" value="true">
                                <label for="comentarios">Sí, permitir comentarios</label>
                            </div>

                            <div class="form-group mb-3">
                                <label for="me_gusta">Permitir "Me gusta":</label>
                                <input type="checkbox" id="me_gusta" name="me_gusta" value="true">
                                <label for="me_gusta">Sí, permitir "Me gusta"</label>
                            </div>

                            <div class="form-group mb-3">
                                <label for="pdf">Subir archivo PDF (opcional):</label>
                                <input type="file" id="pdf" name="pdf" class="form-control" accept=".pdf">
                            </div>

                            <button type="submit" class="btn btn-primary mt-3">Asignar</button>
                        </form>


                        <br>   
                        <br>
                        <div>
                            <h3>Subir capitulo revista:</h3>
                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/revistas/ver_revistas">
                                <label for="revista" class="form-label">Seleccione revista:</label>
                                <select id="revista" name="revista" class="form-select" required>
                                    <c:forEach var="revista" items="${revistas}">
                                        <option value="${revista}">${revista}</option>
                                    </c:forEach>
                                </select>

                                <div class="form-group mb-3">
                                    <label for="pdf">Subir archivo PDF (opcional):</label>
                                    <input type="file" id="pdf" name="pdf" class="form-control" accept=".pdf">
                                </div>
                                <button type="submit" class="btn btn-primary mt-3">Subir</button>
                            </form>
                        </div>
                        <br>
                        <br>
                        <div>
                            <h3>Reportes:</h3>
                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/Anuncios/ver-activos">
                                <label for="reportes" class="form-label">Seleccione el reporte que desee ver:</label>
                                <select id="reporte" name="reporte" class="form-select" required>
                                    <option value="COMENTARIOS">REPORTE DE COMENTARIOS</option>
                                    <option value="SUSCRIPCIONES">REPORTE DE SUSCRIPCIONES</option>
                                    <option value="MEGUSTAS">REPORTE 5 REVISTAS MAS GUSTADAS</option>
                                </select>
                                <br>
                                
                                
                                <button type="submit" class="btn btn-primary mt-3">Visualizar reporte</button>
                            </form>          
                        </div>
                        <br>
                        <br>
                        <div>
                            <h3>Habilitar comentarios, me gustas y suscripciones:</h3>
                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/Anuncios/ver-activos">
                                <label for="revista" class="form-label">Seleccione revista:</label>
                                <select id="revista" name="revista" class="form-select" required>
                                    <c:forEach var="revista" items="${revistas}">
                                        <option value="${revista}">${revista}</option>
                                    </c:forEach>
                                </select>
                                <br>
                                <div class="form-group mb-3">
                                    <label for="comentarios">Permitir comentarios:</label>
                                    <input type="checkbox" id="comentarios" name="comentarios" value="true">
                                    <label for="comentarios">Si</label>
                                </div>

                                <div class="form-group mb-3">
                                    <label for="me_gusta">Permitir "Me gusta":</label>
                                    <input type="checkbox" id="me_gusta" name="me_gusta" value="true">
                                    <label for="me_gusta">Si</label>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="suscripciones">Permitir suscripciones:</label>
                                    <input type="checkbox" id="suscripciones" name="suscripciones" value="true">
                                    <label for="suscripciones">Si</label>
                                </div>
                                <br>
                                <button type="submit" class="btn btn-primary mt-3">Visualizar reporte</button>
                            </form>          
                        </div>
                </section>
            </main>

            <!-- Incluye la barra lateral derecha -->
            <jsp:include page="/includes/rightSide.jsp"/>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0sG1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous"></script>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
