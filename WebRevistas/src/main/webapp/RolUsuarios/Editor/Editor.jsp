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
                        <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Revista/revista-servlet">
                            <div class="form-group mb-3">
                                <label for = "nombre">Nombre de la revista: </label>
                                <input id="nombre" name="nombre" maxlength="20" class="form-control" placeholder="Nombre revista" required>
                            </div>
                            
                            <div class="form-group mb-3">
                                <label for = "descripcion">Descripcion de la revista: </label>
                                <input id="descripcion" name="descripcion" maxlength="200" class="form-control" placeholder="Descripcion revista" required>
                            </div>
                            
                            <div class="form-group mb-3">
                                <label for="tipo" class="form-label">Seleccione tipo de anuncio:</label>
                                <select id="tipo" name="tipo" class="form-select" required>
                                    <option value="TEXTO">TEXTO</option>
                                    <option value="TEXTOEIMAGEN">TEXTO E IMAGEN</option>
                                    <option value="VIDEO">VIDEO</option>
                                </select>
                            </div>
                            
                            
                            
                            <button type="submit" class="btn btn-primary mt-3">Asignar</button>
                        </form>
                        <br>   
                        <br>
                        <div>
                            <h3>Costos revistas:</h3>
                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/revistas/ver_revistas">
                                <label for="revista" class="form-label">Seleccione revista:</label>
                                <select id="revista" name="revista" class="form-select" required>
                                    <c:forEach var="revista" items="${revistas}">
                                        <option value="${revista}">${revista}</option>
                                    </c:forEach>
                                </select>

                                <div class="form-group mb-3">
                                    <label for="costo">Colocar costo</label>
                                    <input id="costo" name="costo" type="number" min="1" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-primary mt-3">Asignar</button>
                            </form>
                        </div>
                        <br>
                        <br>
                        <div>
                            <h3>Reportes:</h3>
                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/Anuncios/ver-activos">
                                <label for="reportes" class="form-label">Seleccione el reporte que desee ver:</label>
                                <select id="reporte" name="reporte" class="form-select" required>
                                    <option value="GANANCIAS">REPORTE DE GANANCIAS</option>
                                    <option value="ANUNCIOS">REPORTE DE ANUNCIOS</option>
                                    <option value="POPULARREVISTAS">REPORTE 5 REVISTAS MAS POPULARES</option>
                                    <option value="COMENTARIOREVISTAS">REPORTE 5 REVISTAS MAS COMENTADAS</option>
                                </select>
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
