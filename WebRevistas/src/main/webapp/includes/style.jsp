<%-- 
    Document   : style
    Created on : 14 sept 2024, 1:50:01
    Author     : gabrielh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
            .sidebar {
                background-color: #f4f4f4;
                padding: 15px;
                box-sizing: border-box;
                overflow-y: auto;
                height: 100vh; /* Altura completa */
                position: relative;
            }
            .left-sidebar {
                width: 20%;
            }
            .right-sidebar {
                width: 20%;
            }
            .main-content {
                width: 60%; /* Resto del espacio */
            }
            .layout-container {
                display: flex;
                margin-top: 56px; /* Ajusta según la altura del header */
            }
            .form-box, .active-ads-box {
                flex: 1;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                margin-right: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                background-color: #f9f9f9;
            }
            .active-ads-box {
                margin-left: 10px;
            }
            .price-table {
                margin-top: 20px;
            }
            .price-table th, .price-table td {
                padding: 12px;
                text-align: center;
            }
            .form-box h3, .active-ads-box h3 {
                margin-bottom: 20px;
                font-weight: 600;
            }                  
</style>
