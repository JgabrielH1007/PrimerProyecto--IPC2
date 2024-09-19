<%-- 
    Document   : EditarPerfil
    Created on : 19 sept 2024, 8:08:32
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Perfil del Usuario</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/includes/Header.jsp"/>

<div class="container mt-5">
    <h2>Editar Perfil del Usuario</h2>
    
    <form method="POST" action="${pageContext.request.contextPath}/Controllers/guardarPerfil" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="foto" class="form-label">Cambiar Foto</label>
            <input type="file" class="form-control" id="foto" name="foto" accept="image/*">
        </div>

        <div class="mb-3">
            <label for="temaInteres" class="form-label">Tema de Interés</label>
            <input type="text" class="form-control" id="temaInteres" name="temaInteres">
        </div>

        <div class="mb-3">
            <label for="hobbies" class="form-label">Hobbies</label>
            <input type="text" class="form-control" id="hobbies" name="hobbies">
        </div>

        <div class="mb-3">
            <label for="gustos" class="form-label">Gustos</label>
            <input type="text" class="form-control" id="gustos" name="gustos">
        </div>

        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="4">${perfil.descripcion}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
    </form>
</div>

</body>
</html>

