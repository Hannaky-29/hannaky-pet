<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hannaky Pet | Mascotas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilos.css">
</head>
<body>
<div class="layout">
    <!-- Inclusión del menú lateral con ruta absoluta -->
    <jsp:include page="/WEB-INF/vistas/sidebar.jsp" />

    <main>
        <header class="header">
            <div>
                <p class="eyebrow">DIRECTORIO VETERINARIO</p>
                <h1>Gestión de mascotas</h1>
                <p class="muted">Registra y administra la ficha básica de cada paciente.</p>
            </div>
            <span class="user">Hannaky Pet · Administración</span>
        </header>
        
        <!-- Formulario de registro de mascotas -->
        <section class="card" style="margin-bottom:20px;">
            <div class="card-head"><h2>Registrar nueva mascota</h2></div>
            <form method="post" action="${pageContext.request.contextPath}/mascotas">
                <input type="hidden" name="accion" value="registrar">
                <label for="nombreMascota">Nombre de la mascota <em>*</em></label>
                <input id="nombreMascota" name="nombreMascota" required placeholder="Ej. Luna">
                <div class="fields">
                    <div><label for="especie">Especie <em>*</em></label><input id="especie" name="especie" required placeholder="Ej. Felino"></div>
                    <div><label for="raza">Raza</label><input id="raza" name="raza" placeholder="Ej. Criolla"></div>
                </div>
                <label for="edad">Edad (años) <em>*</em></label>
                <input id="edad" name="edad" type="number" min="0" required placeholder="Ej. 13">
                <div class="buttons"><button class="btn primary" type="submit">Guardar mascota</button></div>
            </form>
        </section>
        
        <!-- Tabla de mascotas registradas -->
        <section class="card table-card">
            <div class="card-head"><h2>Mascotas registradas</h2><span class="tag"><c:out value="${total}"/> pacientes</span></div>
            <div class="table-wrap">
                <table>
                    <thead><tr><th>ID</th><th>Nombre</th><th>Especie</th><th>Raza</th><th>Edad</th><th>Acciones</th></tr></thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${empty mascotas}">
                            <tr><td colspan="6" class="empty"><b>No hay mascotas registradas</b></td></tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="m" items="${mascotas}">
                                <tr>
                                    <td>#<c:out value="${m.idMascota}"/></td>
                                    <td><b><c:out value="${m.nombreMascota}"/></b></td>
                                    <td><c:out value="${m.especie}"/></td>
                                    <td><c:out value="${m.raza}"/></td>
                                    <td><c:out value="${m.edad}"/> años</td>
                                    <td>
                                        <form method="post" action="${pageContext.request.contextPath}/mascotas" style="margin:0;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="id" value="<c:out value='${m.idMascota}'/>">
                                            <button type="submit" style="color:#bd4855;background:none;border:0;cursor:pointer;font-weight:800;">Eliminar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</div>
</body></html>