<%-- 
    Document   : RevistaSuscrita
    Created on : 15 sept 2024, 3:15:08
    Author     : gabrielh
--%>

<%@page import="java.util.List"%>
<%@page import="Revista.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Suscripción</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-kenU1KFdBIe4zVF0sG1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous">
        <style>
            .liked {
                color: red;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/includes/Header.jsp"/>

        <!-- Contenedor de detalles de suscripción -->
        <div class="container mt-4">
            <h2><%= ((Revista) request.getAttribute("revista")).getNombre() %></h2>
            <div class="card mb-3">
                <div class="card-body">
                    <p><strong>Descripción:</strong> <%= ((Revista) request.getAttribute("revista")).getDescripcion() %></p>
                    <p><strong>Categoría:</strong> <%= ((Revista) request.getAttribute("revista")).getCategoria() %></p>
                    <p><strong>Etiquetas:</strong> <%= ((Revista) request.getAttribute("revista")).getEtiquetas() %></p>
                    <p><strong>Autor:</strong> <%= ((Revista) request.getAttribute("revista")).getAutor() %></p>
                    <p><strong>Cantidad de Me Gusta:</strong> <span id="likesCount"><%= ((Revista) request.getAttribute("revista")).getCantidadMeGusta() %></span></p>

                    <!-- Botón Me Gusta -->
                    <button id="likeButton" class="btn btn-primary" onclick="toggleLike()">Me gusta</button>
                </div>
            </div>

            <!-- Apartado de Comentarios -->
            <div class="mb-3">
                <h3>Deja tu comentario</h3>
                <form action="comentarRevista" method="post">
                    <input type="hidden" name="nombre" value="<%= ((Revista) request.getAttribute("revista")).getNombre() %>">
                    <div class="mb-3">
                        <textarea name="comentario" class="form-control" rows="4" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar comentario</button>
                </form>
            </div>

            <h3>Capítulos</h3>
            <ul class="list-group">
                <%
                    List<Capitulo> capitulos = (List<Capitulo>) request.getAttribute("capitulos");
                    if (capitulos != null && !capitulos.isEmpty()) {
                        for (Capitulo capitulo : capitulos) {
                %>
                    <li class="list-group-item">
                        <a href="<%= capitulo.getPdfUrl() %>" target="_blank"><%= capitulo.getTitulo() %></a>
                    </li>
                <% 
                        }
                    } else {
                %>
                    <p>No hay capítulos disponibles.</p>
                <% 
                    }
                %>
            </ul>
        </div>

        <!-- Bootstrap JS Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-cc4d2E4pAEJz1nA5Kqa8D4p9xJwPi8/xn6Io6uy1Bh/N1zz9O+z1Xk4I5y3tmR4" crossorigin="anonymous"></script>
        <script>
            function toggleLike() {
                var button = document.getElementById('likeButton');
                var likesCount = document.getElementById('likesCount');
                var liked = button.classList.contains('liked');

                if (liked) {
                    button.textContent = 'Me gusta';
                    button.classList.remove('liked');
                    likesCount.textContent = parseInt(likesCount.textContent) - 1;
                } else {
                    button.textContent = 'Ya no me gusta';
                    button.classList.add('liked');
                    likesCount.textContent = parseInt(likesCount.textContent) + 1;
                }
            }
        </script>
    </body>
</html>

