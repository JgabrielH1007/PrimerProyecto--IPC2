<%-- 
    Document   : Ingresar
    Created on : 10 sept 2024, 1:42:36
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="/includes/resources.jsp"/>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous">

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">

        <style>
          .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: auto;
          }

          .form-signin .checkbox {
            font-weight: 400;
          }

          .form-signin .form-floating:focus-within {
            z-index: 2;
          }

          .icono-ingresar {
            font-size: 4rem;
            color: #2F4F4F; /* Puedes ajustar el color */
          }
        </style>
    </head>
    <body>
        <main class="form-signin w-100 m-auto text-center">
            <section class="container mt-5">
                
               
                <i class="bi bi-person-check icono-ingresar mb-3"></i>
                
                <h1 class="h3 mb-3 fw-normal">INGRESAR</h1>

                <!-- Mostrar mensaje de error si existe -->
                <c:if test="${not empty error}">
                    <p class="text-danger">${error}</p>
                </c:if>

                <!-- Mostrar mensaje de éxito si existe -->
                <c:if test="${not empty mensaje}">
                    <p class="text-success">${mensaje}</p>
                </c:if>

                <!-- Formulario de login -->
                <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Usuario/usuario-servlet">
                    <input type="hidden" name="action" value="login">

                    <div class="form-floating">
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="User Name" required>
                        <label for="userName">User Name</label>
                    </div>

                    <div class="form-floating mt-2">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                        <label for="password">Password</label>
                    </div>

                    <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Login</button>
                </form>

                <!-- Formulario para crear una cuenta nueva -->
                <form action="${pageContext.servletContext.contextPath}/Login/LoginNewUser.jsp" method="get">
                    <br>
                    <input type="submit" class=" w-100 btn btn-outline-secondary " value="Crear Cuenta">
                </form>

            </section>
        </main>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+Z0I5q1iZn7fDaWfhp8Psr4lgv0g" crossorigin="anonymous"></script>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>





