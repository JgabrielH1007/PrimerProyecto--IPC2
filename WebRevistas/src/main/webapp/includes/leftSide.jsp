<%-- 
    Document   : leftSide
    Created on : 12 sept 2024, 1:45:23
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="sidebar left-sidebar">
    <h2>Barra Lateral Izquierda</h2>
    <div id="text-content" class="content-item" style="display: none;">
        <p>Este es un ejemplo de texto en la barra lateral izquierda.</p>
    </div>
    <div id="image-content" class="content-item" style="display: none;">
        <img src="ruta_imagen.jpg" alt="Imagen de ejemplo" style="width: 100%;">
    </div>
    <div id="video-content" class="content-item" style="display: none;">
        <video controls style="width: 100%;">
            <source src="ruta_video.mp4" type="video/mp4">
            Tu navegador no soporta video HTML5.
        </video>
    </div>
</div>

<script>
    // Seleccionar aleatoriamente un elemento para mostrar en la barra lateral izquierda
    window.onload = function() {
        const leftItems = document.querySelectorAll('.content-item');
        const leftRandomIndex = Math.floor(Math.random() * leftItems.length); // Obtener índice aleatorio para la izquierda
        leftItems[leftRandomIndex].style.display = 'block'; // Mostrar el elemento seleccionado en la izquierda

        // Seleccionar aleatoriamente un elemento para mostrar en la barra lateral derecha
        const rightItems = document.querySelectorAll('.right-content-item');
        const rightRandomIndex = Math.floor(Math.random() * rightItems.length); // Obtener índice aleatorio para la derecha
        rightItems[rightRandomIndex].style.display = 'block'; // Mostrar el elemento seleccionado en la derecha
    };
</script>