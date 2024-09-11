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
    </head>
    <body>
        <h1>Crear Nuevo Usuario</h1>

        <!-- Mostrar mensaje de error si existe -->
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>

        <!-- Mostrar mensaje de éxito si existe -->
        <c:if test="${not empty mensaje}">
            <p style="color:green;">${mensaje}</p>
        </c:if>

        <form method="POST" action="${pageContext.servletContext.contextPath}/Controllers/Usuario/usuario-servlet">
            <input type="hidden" name="action" value="registro"> <!-- Define la acción como registro -->

            <label for="userName">Nombre de Usuario:</label>
            <input id="userName" name="userName" required><br><br>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br><br>

            <label for="rol">Seleccionar Rol:</label>
            <select id="rol" name="rol">
                <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                <option value="EDITOR">EDITOR</option>
                <option value="PUBLICIDAD">PUBLICIDAD</option>
                <option value="SUSCRIPTOR">SUSCRIPTOR</option>
            </select><br><br>

            <label for="cartera">Cartera con crédito:</label>
            <input id="cartera" name="cartera" type="number" min="1" required><br><br>

            <button type="submit">Crear Usuario</button>
        </form>
    </body>
</html>
