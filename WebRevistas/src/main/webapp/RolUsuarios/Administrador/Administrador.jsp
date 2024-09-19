<%-- 
    Document   : Administrador
    Created on : 11 sept 2024, 3:01:54
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ADMINISTRADOR</title>
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
                <section class="form-container">
                    <!-- Formulario para valor anuncio -->
                    <div class="form-box">
                        <c:if test="${not empty mensaje}">
                            <p class="text-success">${mensaje}</p>
                        </c:if>
                        <h3>Precio Anuncios:</h3>
                        <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Usuario/AsignarValorAnuncioServlet">
                            <!-- Seleccionar tipo de anuncio -->
                            <div class="form-group mb-3">
                                <label for="tipo" class="form-label">Seleccione tipo de anuncio:</label>
                                <select id="tipo" name="tipo" class="form-select" required>
                                    <option value="TEXTO">TEXTO</option>
                                    <option value="TEXTOEIMAGEN">TEXTO E IMAGEN</option>
                                    <option value="VIDEO">VIDEO</option>
                                </select>
                            </div>

                            <!-- Seleccionar duración del anuncio -->
                            <div class="form-group mb-3">
                                <label for="tiempo" class="form-label">Seleccione duración del anuncio:</label>
                                <select id="tiempo" name="tiempo" class="form-select" required>
                                    <option value="1DIA">1 DÍA</option>
                                    <option value="3DIAS">3 DÍAS</option>
                                    <option value="1SEMANA">1 SEMANA</option>
                                    <option value="2SEMANAS">2 SEMANAS</option>
                                </select>
                            </div>
                                                      
                            <div class="form-group mb-3">
                                <label for="precio">Colocar precio</label>
                                <input id="precio" name="precio" type="number" min="1" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-primary mt-3">Asignar</button>
                        </form>
                        <br>   
                        <br>
                        <div>
                            <h3>Costos revistas no asignado:</h3>
                            <!-- Etiqueta para mostrar el costo global sugerido -->
                            <p><strong>Costo sugerido:</strong> 100</p>
                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/revistas/asignar_costo">
                                <label for="revista" class="form-label">Seleccione revista:</label>
                                <select id="revista" name="revista" class="form-select" required>
                                    <c:forEach var="revista" items="${revistasSinCosto}">
                                        <option value="${revista}">${revista}</option>
                                    </c:forEach>
                                </select>

                                <div class="form-group mb-3">
                                    <label for="costo">Colocar costo</label>
                                    <!-- Campo de entrada con el valor predeterminado de 100 -->
                                    <input id="costo" name="costo" type="number" min="1" value="100" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-primary mt-3">Asignar</button>
                            </form>
                        </div>

                        <br>

                        <!-- Sección para asignar costo a todas las revistas -->
                        <div>
                            <h2>Asignar Costo a Todas las Revistas</h2>

                            <!-- Mensaje de éxito o error -->
                            <c:if test="${not empty mensaje}">
                                <div class="alert alert-info">${mensaje}</div>
                            </c:if>

                            <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/revistas/asignar_costo">
                                <label for="revistas" class="form-label">Seleccione revista:</label>
                                <select id="revistas" name="revistas" class="form-select" required>
                                    <c:forEach var="revista" items="${todasLasRevistas}">
                                        <option value="${revista}">${revista}</option>
                                    </c:forEach>
                                </select>

                                <div class="form-group mt-3">
                                    <label for="costo">Costo</label>
                                    <!-- Campo de entrada con el valor predeterminado de 100 -->
                                    <input id="costo" name="costo" type="number" min="1" value="100" class="form-control" required>
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
