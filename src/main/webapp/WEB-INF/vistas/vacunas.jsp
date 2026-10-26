<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hannaky Pet | Vacunas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilos.css">
</head>
<body>
<div class="layout">
    <aside class="sidebar">
        <a class="brand" href="${pageContext.request.contextPath}/vacunas"><span class="logo">H</span><span><b>Hannaky Pet</b><small>Salud y bienestar animal</small></span></a>
        <p class="side-label">MÓDULO PRINCIPAL</p>
        <a class="nav active" href="${pageContext.request.contextPath}/vacunas">Calendario de vacunas</a>
        <div class="side-bottom"><strong>Cuidado responsable</strong><p>Registra y consulta las vacunas de tus mascotas desde un solo lugar.</p></div>
    </aside>
    <main>
        <header class="header"><div><p class="eyebrow">CONTROL VETERINARIO</p><h1>Calendario de vacunas</h1><p class="muted">Administra las aplicaciones y próximas dosis de cada mascota.</p></div><span class="user">Hannaky Pet · Administración</span></header>
        <c:if test="${not empty param.mensaje}"><div class="notice"><c:choose><c:when test="${param.mensaje eq 'registrada'}">La vacuna se registró correctamente.</c:when><c:when test="${param.mensaje eq 'actualizada'}">La vacuna se actualizó correctamente.</c:when><c:when test="${param.mensaje eq 'eliminada'}">La vacuna se eliminó correctamente.</c:when></c:choose></div></c:if>
        <c:if test="${not empty param.error}"><div class="error"><c:choose><c:when test="${param.error eq 'campos'}">Completa los campos obligatorios.</c:when><c:when test="${param.error eq 'nombre'}">El nombre de la vacuna no puede superar 120 caracteres.</c:when><c:when test="${param.error eq 'fecha'}">Revisa las fechas ingresadas.</c:when><c:when test="${param.error eq 'eliminar'}">No fue posible eliminar el registro.</c:when><c:when test="${param.error eq 'guardar'}">No fue posible guardar. Verifica la conexión y los datos.</c:when><c:otherwise>Ocurrió un error al procesar la solicitud.</c:otherwise></c:choose></div></c:if>
        <section class="stats"><article><small>REGISTROS MOSTRADOS</small><strong><c:out value="${total}"/></strong><span>Vacunas en el listado actual</span></article><article><small>SEGUIMIENTO</small><strong class="status">Al día</strong><span>Consulta las fechas de próximas dosis</span></article></section>
        <section class="columns">
            <article class="card">
                <div class="card-head"><div><p class="eyebrow"><c:choose><c:when test="${not empty editar}">EDICIÓN</c:when><c:otherwise>NUEVO REGISTRO</c:otherwise></c:choose></p><h2><c:choose><c:when test="${not empty editar}">Actualizar vacuna</c:when><c:otherwise>Registrar vacuna</c:otherwise></c:choose></h2></div><span class="tag">Datos de vacunación</span></div>
                <form method="post" action="${pageContext.request.contextPath}/vacunas">
                    <input type="hidden" name="accion" value="${not empty editar ? 'actualizar' : 'registrar'}">
                    <c:if test="${not empty editar}"><input type="hidden" name="idVacuna" value="<c:out value='${editar.idVacuna}'/>"/></c:if>
                    <label for="idMascota">ID de la mascota <em>*</em></label><input id="idMascota" name="idMascota" type="number" min="1" required value="<c:out value='${editar.idMascota}'/>" placeholder="Ej. 1" <c:if test="${not empty editar}">readonly</c:if>>
                    <label for="nombreVacuna">Nombre de la vacuna <em>*</em></label><input id="nombreVacuna" name="nombreVacuna" maxlength="120" required value="<c:out value='${editar.nombreVacuna}'/>" placeholder="Ej. Rabia">
                    <div class="fields"><div><label for="fechaAplicacion">Fecha de aplicación <em>*</em></label><input id="fechaAplicacion" name="fechaAplicacion" type="date" required value="<c:out value='${editar.fechaAplicacion}'/>"/></div><div><label for="fechaProximaDosis">Próxima dosis</label><input id="fechaProximaDosis" name="fechaProximaDosis" type="date" value="<c:out value='${editar.fechaProximaDosis}'/>"/></div></div>
                    <div class="buttons"><c:if test="${not empty editar}"><a class="btn secondary" href="${pageContext.request.contextPath}/vacunas">Cancelar</a></c:if><button class="btn primary" type="submit"><c:choose><c:when test="${not empty editar}">Guardar cambios</c:when><c:otherwise>Registrar vacuna</c:otherwise></c:choose></button></div>
                </form>
            </article>
            <article class="card guide"><p class="eyebrow">GUÍA RÁPIDA</p><h2>Un registro claro ayuda a cuidar mejor</h2><p class="muted">Utiliza el ID de mascota que corresponde a su registro en la base de datos.</p><div class="guide-row"><b>01</b><div><strong>Identifica la mascota</strong><p>Ingresa su ID para asociar correctamente la vacuna.</p></div></div><div class="guide-row"><b>02</b><div><strong>Registra la aplicación</strong><p>El nombre y la fecha de aplicación son obligatorios.</p></div></div><div class="guide-row"><b>03</b><div><strong>Programa el seguimiento</strong><p>La fecha de próxima dosis es opcional.</p></div></div><div class="guide-foot">Los cambios se guardan en la base de datos existente.</div></article>
        </section>
        <section class="card table-card"><div class="card-head"><div><p class="eyebrow">HISTORIAL</p><h2>Vacunas registradas</h2><p class="muted">Consulta, filtra o administra los registros.</p></div><span class="tag"><c:out value="${total}"/> registros</span></div>
            <form class="filter" method="get" action="${pageContext.request.contextPath}/vacunas"><label for="filtro">Filtrar por ID de mascota</label><div class="filter-line"><input id="filtro" name="idMascota" type="number" min="1" value="<c:out value='${filtroMascota}'/>" placeholder="Todos los registros"><button class="btn primary" type="submit">Buscar</button><a class="btn secondary" href="${pageContext.request.contextPath}/vacunas">Ver todos</a></div></form>
            <div class="table-wrap"><table><thead><tr><th>ID vacuna</th><th>ID mascota</th><th>Vacuna</th><th>Aplicación</th><th>Próxima dosis</th><th>Acciones</th></tr></thead><tbody>
            <c:choose><c:when test="${empty vacunas}"><tr><td colspan="6" class="empty"><b>No hay vacunas para mostrar</b><span>Registra una vacuna o cambia el filtro de búsqueda.</span></td></tr></c:when><c:otherwise><c:forEach var="v" items="${vacunas}"><tr><td>#<c:out value="${v.idVacuna}"/></td><td><span class="species">#<c:out value="${v.idMascota}"/></span></td><td><b><c:out value="${v.nombreVacuna}"/></b></td><td><c:out value="${v.fechaAplicacion}"/></td><td><c:choose><c:when test="${not empty v.fechaProximaDosis}"><c:out value="${v.fechaProximaDosis}"/></c:when><c:otherwise><span class="muted">No definida</span></c:otherwise></c:choose></td><td><div class="actions"><a href="${pageContext.request.contextPath}/vacunas?accion=editar&amp;id=${v.idVacuna}">Editar</a><form method="post" action="${pageContext.request.contextPath}/vacunas" onsubmit="return confirm('¿Seguro que deseas eliminar esta vacuna?');"><input type="hidden" name="accion" value="eliminar"><input type="hidden" name="id" value="<c:out value='${v.idVacuna}'/>"><button type="submit">Eliminar</button></form></div></td></tr></c:forEach></c:otherwise></c:choose>
            </tbody></table></div>
        </section><footer>Hannaky Pet · Módulo de vacunación</footer>
    </main>
</div>
</body></html>
