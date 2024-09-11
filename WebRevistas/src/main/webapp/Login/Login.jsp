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
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>

        <!-- Mostrar mensaje de error si existe -->
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>

        <!-- Mostrar mensaje de éxito si existe -->
        <c:if test="${not empty mensaje}">
            <p style="color:green;">${mensaje}</p>
        </c:if>

        <!-- Formulario de login -->
        <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Usuario/usuario-servlet">
            <input type="hidden" name="action" value="login"> <!-- Define la acción como login -->

            <label for="userName">User Name:</label>
            <input type="text" id="userName" name="userName" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <button type="submit">Login</button>
        </form>

        <!-- Formulario para crear una cuenta nueva -->
        <form action="${pageContext.servletContext.contextPath}/Login/LoginNewUser.jsp" method="get">
            <input type="submit" value="Crear Cuenta">
        </form>
    </body>
</html>



