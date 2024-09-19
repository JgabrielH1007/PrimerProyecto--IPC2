<%-- 
    Document   : Header
    Created on : 11 sept 2024, 21:25:30
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.122.0">
        <title>Headers · Bootstrap v5.3</title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

        <style>
            .navbar-custom {
                background-color: #A9A9A9;
            }
            .navbar-brand, .nav-link {
                color: #fff; /* Color de la letra negro */
            }
            .navbar-brand:hover, .nav-link:hover {
                color: #000; /* Color en hover */
            }
            .btn-custom {
                margin-right: 10px; /* Separar los botones */
                color: #000;
                border-color: #000;
            }
            .btn-custom:hover {
                background-color: #000;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">REVISTAS</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    
                        <li class="nav-item"><a  class="btn btn-custom" href="${pageContext.servletContext.contextPath}/Controllers/mostrar-perfil">Perfil</a></li>
                       
                        <li class="nav-item"><a class="btn btn-custom" href="${pageContext.servletContext.contextPath}/Controllers/Usuario/cerrarSesion-servlet">Cerrar Sesión</a></li>
                    
                </div>
            </div>
        </nav>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

