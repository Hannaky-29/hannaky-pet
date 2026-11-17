<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hannaky Pet | Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilos.css">
</head>
<body>
<div class="layout">
    <!-- Menú lateral unificado -->
    <jsp:include page="/WEB-INF/vistas/sidebar.jsp" />

    <main>
        <header class="header">
            <div>
                <p class="eyebrow">BIENVENIDO</p>
                <h1>Panel de inicio</h1>
                <p class="muted">Selecciona el módulo que deseas administrar.</p>
            </div>
            <span class="user">Hannaky Pet · Administración</span>
        </header>

        <section class="stats">
            <article>
                <small>MÓDULO 1</small>
                <strong>Pacientes</strong>
                <span>Gestión de mascotas</span>
            </article>
            <article>
                <small>MÓDULO 2</small>
                <strong>Vacunas</strong>
                <span>Calendario de vacunas</span>
            </article>
        </section>

        <section class="columns">
            <article class="card">
                <div class="card-head">
                    <div>
                        <p class="eyebrow">PACIENTES</p>
                        <h2>Gestión de mascotas</h2>
                    </div>
                    <span class="tag">Módulo web</span>
                </div>
                <p class="muted">Registra, consulta, edita y elimina las fichas de los pacientes veterinarios.</p>
                <div class="buttons" style="justify-content:flex-start; margin-top:15px;">
                    <a class="btn primary" href="${pageContext.request.contextPath}/mascotas">Ir al módulo</a>
                </div>
            </article>

            <article class="card">
                <div class="card-head">
                    <div>
                        <p class="eyebrow">VACUNACIÓN</p>
                        <h2>Calendario de vacunas</h2>
                    </div>
                    <span class="tag">Módulo web</span>
                </div>
                <p class="muted">Administra las vacunas aplicadas y programa las próximas dosis de cada mascota.</p>
                <div class="buttons" style="justify-content:flex-start; margin-top:15px;">
                    <a class="btn primary" href="${pageContext.request.contextPath}/vacunas">Ir al módulo</a>
                </div>
            </article>
        </section>
    </main>
</div>
</body>
</html>