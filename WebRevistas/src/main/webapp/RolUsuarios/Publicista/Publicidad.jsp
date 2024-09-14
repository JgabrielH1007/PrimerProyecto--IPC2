<%-- 
    Document   : Publicidad
    Created on : 11 sept 2024, 3:01:42
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PUBLICISTA</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-kenU1KFdBIe4zVF0sG1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous">
    <jsp:include page="/includes/style.jsp"/>
</head>
<body>
    <!-- Incluye el header -->
        <jsp:include page="/includes/Header.jsp"/>

        <!-- Contenedor de todo el layout -->
        <div class="layout-container container-fluid">
            <!-- Incluye la barra lateral izquierda -->
            <jsp:include page="/includes/leftSide.jsp"/>

            <!-- Contenido principal -->
            <main class="main-content">
                <section class="form-container">
                    <!-- Formulario para comprar anuncio -->
                    <div class="form-box">
                        <h3>Comprar Anuncio</h3>
                        <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Usuario/usuario-servlet">
                            <!-- Seleccionar tipo de anuncio -->
                            <div class="form-group mb-3">
                                <label for="anuncio" class="form-label">Seleccione tipo de anuncio:</label>
                                <select id="anuncio" name="anuncio" class="form-select" required>
                                    <option value="TEXTO">TEXTO</option>
                                    <option value="TEXTOEIMAGEN">TEXTO E IMAGEN</option>
                                    <option value="VIDEO">VIDEO</option>
                                </select>
                            </div>

                            <!-- Seleccionar duración del anuncio -->
                            <div class="form-group mb-3">
                                <label for="tiempo" class="form-label">Seleccione duración del anuncio:</label>
                                <select id="tiempo" name="tiempo" class="form-select" required>
                                    <option value="1 DIA">1 DÍA</option>
                                    <option value="3 DIAS">3 DÍAS</option>
                                    <option value="1 SEMANA">1 SEMANA</option>
                                    <option value="2 SEMANAS">2 SEMANAS</option>
                                </select>
                            </div>

                            <!-- Botón para comprar anuncio -->
                            <button type="submit" class="btn btn-primary w-100 py-2">Comprar</button>
                        </form>

                        <!-- Tabla de precios por tipo de anuncio y duración -->
                        <table class="table table-bordered price-table mt-4">
                            <thead class="table-light">
                                <tr>
                                    <th>Tipo de Anuncio</th>
                                    <th>1 Día</th>
                                    <th>3 Días</th>
                                    <th>1 Semana</th>
                                    <th>2 Semanas</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Texto</td>
                                    <td>$10</td>
                                    <td>$25</td>
                                    <td>$50</td>
                                    <td>$90</td>
                                </tr>
                                <tr>
                                    <td>Texto e Imagen</td>
                                    <td>$20</td>
                                    <td>$50</td>
                                    <td>$90</td>
                                    <td>$170</td>
                                </tr>
                                <tr>
                                    <td>Video</td>
                                    <td>$30</td>
                                    <td>$70</td>
                                    <td>$120</td>
                                    <td>$220</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!-- Sección para ver anuncios activos -->
                    <div class="active-ads-box">
                        <h3>Anuncios Activos</h3>
                        <p>Aquí podrás ver los anuncios activos:</p>
                        <form method="GET" action="${pageContext.servletContext.contextPath}/Controllers/Anuncios/ver-activos">
                            <button type="submit" class="btn btn-secondary w-100 py-2">Ver Anuncios Activos</button>
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




