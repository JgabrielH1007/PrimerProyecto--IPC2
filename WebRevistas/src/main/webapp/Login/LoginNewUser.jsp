<%-- 
    Document   : LoginNewUser
    Created on : 9 sept 2024, 17:08:32
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <jsp:include page="/includes/resources.jsp"/>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous">
        
        <style>
            body, html {
                height: 100%;
            }

            .form-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form-box {
                width: 100%;
                max-width: 400px;
                padding: 20px;
                background-color: #f9f9f9;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <main class="form-container">
            <section class="form-box">
                <div class="container">
                    <h1 class="text-center mb-4">Crear Nuevo Usuario</h1>

                    <!-- Mostrar mensaje de error si existe -->
                    <c:if test="${not empty error}">
                        <p class="text-danger">${error}</p>
                    </c:if>

                    <!-- Mostrar mensaje de éxito si existe -->
                    <c:if test="${not empty mensaje}">
                        <p class="text-success">${mensaje}</p>
                    </c:if>
                    
                    <!-- Formulario de registro -->
                    <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Usuario/usuario-servlet">
                        <input type="hidden" name="action" value="registro">

                        <div class="form-group mb-3">
                            <label for="userName">Nombre de Usuario:</label>
                            <input id="userName" name="userName" maxlength="20" class="form-control" placeholder="Nombre de usuario" required>
                        </div>
                        
                        <div class="form-group mb-3">
                            <label for="password">Contraseña:</label>
                            <input type="password" id="password" name="password" maxlength="20" class="form-control" required>
                        </div>

                        <div class="form-group mb-3">
                            <label for="rol">Seleccionar Rol:</label>
                            <select id="rol" name="rol" class="form-control" required>
                                <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                                <option value="EDITOR">EDITOR</option>
                                <option value="PUBLICIDAD">PUBLICIDAD</option>
                                <option value="SUSCRIPTOR">SUSCRIPTOR</option>
                            </select>
                        </div>

                        <div class="form-group mb-3">
                            <label for="cartera">Salario:</label>
                            <input id="cartera" name="cartera" type="number" min="1" class="form-control" required>
                        </div>

                        <button type="submit" class="btn btn-success w-100">Crear Usuario</button>
                    </form>
                </div>
            </section>
        </main>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous"></script>
    </body>
</html>

