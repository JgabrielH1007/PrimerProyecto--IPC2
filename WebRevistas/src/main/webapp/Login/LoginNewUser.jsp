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
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action = "${pageContext.servletContext.contextPath}/Controllers/Usuario/usuario-servlet" >
            <label for="userName">Nombre usuario:</label>
            <input id="userName" name="userName"/>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"/>
            <br>
            <label for="rol">Seleccionar rol:</label>
            <select id="rol" name="rol">
                <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                <option value="EDITOR">EDITOR</option>
                <option value="PUBLICIDAD">PUBLICIDAD</option>
                <option value="SUSCRIPTOR">SUSCRIPTOR</option>
            </select>
            <br>
            <label for="cartera">Cartera con credito: </label>
            <input id="cartera" name="cartera" type="number" min="1" />
            <br>
            <button>Crear solicitud</button>
        </form>
    </body>
</html>
