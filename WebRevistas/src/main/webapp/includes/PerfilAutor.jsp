<%-- 
    Document   : PerfilAutor
    Created on : 19 sept 2024, 12:19:40
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
    <title>Perfil del Usuario</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/includes/Header.jsp"/>

<div class="container mt-5">
    <h2>Perfil del Autor</h2>

    <div class="row">
        <div class="col-md-4">
            <h4>Foto</h4>
            <c:choose>
                <c:when test="${perfil.fotoBase64 != null}">
                    <img src="data:image/jpeg;base64,${perfil.fotoBase64}" alt="Foto de Perfil" class="img-fluid rounded-circle"/>
                </c:when>
                <c:otherwise>
                    <p>No hay foto disponible</p>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-8">
            <h4>Tema de Interés</h4>
            <p>${perfil.temasInteres}</p>
            
            <h4>Hobbies</h4>
            <p>${perfil.hobbies}</p>
            
            <h4>Gustos</h4>
            <p>${perfil.gustos}</p>
            
            <h4>Descripción</h4>
            <p>${perfil.descripcion}</p>
        </div>
    </div>


    </div>
</div>

</body>
</html>

