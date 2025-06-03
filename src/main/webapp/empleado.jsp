<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../index.html");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <meta name="description" content="Gestión de empleados de la empresa" />
  <title>Gestión de Empleados</title>
  <link rel="icon" href="img/favicon.png" type="image/png" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/empelado.css" />
</head>
<body class="bg-light">

<div class="container mt-4">
  <!-- Header con usuario y logout -->
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2 class="text-primary">Gestión de Empleados</h2>
    <div>
      <span class="me-3 text-dark">Usuario: <strong>${sessionScope.usuario}</strong></span>
      <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-sm btn-danger">Cerrar sesión</a>
    </div>
  </div>

  <!-- Formulario de empleados -->
  <form method="post" action="${pageContext.request.contextPath}/EmpleadoServlet" class="card p-4 shadow-sm">
    <div class="row mb-3">
      <div class="col-md-2">
        <input type="number" name="id" min="0" class="form-control" placeholder="ID" />
      </div>
      <div class="col-md-2">
        <input type="text" name="nombre" class="form-control" placeholder="Nombre" />
      </div>
      <div class="col-md-3">
        <input type="text" name="email" class="form-control" placeholder="Email" />
      </div>
      <div class="col-md-2">
        <input type="text" name="puesto" class="form-control" placeholder="Puesto" />
      </div>
      <div class="col-md-3">
        <input type="number" step="0.01" name="salario" class="form-control" placeholder="Salario" />
      </div>
    </div>

    <!-- Botones de acción -->
    <div class="d-flex flex-wrap gap-2">
      <button type="submit" name="accion" value="alta" class="btn btn-success">Alta</button>
      <button type="submit" name="accion" value="baja" class="btn btn-danger">Baja</button>
      <button type="submit" name="accion" value="modificar" class="btn btn-warning">Modificar</button>
      <button type="submit" name="accion" value="buscarPorId" class="btn btn-info text-white">Buscar por ID</button>
      <button type="submit" name="accion" value="buscarTodos" class="btn btn-primary">Buscar Todos</button>
    </div>
  </form>

  <!-- Mensaje de error -->
  <c:if test="${param.error == '1'}">
    <div class="alert alert-danger mt-3">Error en la operación</div>
  </c:if>

  <!-- Mensaje de error -->
  <c:if test="${param.success == '0'}">
    <div class="alert alert-danger mt-3">Error en la operación</div>
  </c:if>
  <!-- Mensaje de OK -->
    <c:if test="${param.success > 0}">
      <div class="alert alert-info mt-3">Operación realizada con éxito.</div>
    </c:if>

  <!-- Resultados -->
  <div class="mt-5">
    <!-- h4>Resultados </!-->

    <!-- Resultado único -->
    <c:if test="${not empty empleado}">
      <div class="card p-3 mb-4">
        <p><strong>ID:</strong> ${empleado.id}</p>
        <p><strong>Nombre:</strong> ${empleado.nombre}</p>
        <p><strong>Email:</strong> ${empleado.email}</p>
        <p><strong>Puesto:</strong> ${empleado.puesto}</p>
        <p><strong>Salario:</strong> ${empleado.salario}</p>
      </div>
    </c:if>

    <!-- Tabla de empleados -->
    <c:if test="${not empty listaEmpleados}">
      <div class="table-responsive">
        <table class="table table-bordered table-hover table-striped bg-white">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Email</th>
              <th>Puesto</th>
              <th>Salario</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="emp" items="${listaEmpleados}">
              <tr>
                <td>${emp.id}</td>
                <td>${emp.nombre}</td>
                <td>${emp.email}</td>
                <td>${emp.puesto}</td>
                <td>${emp.salario}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:if>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
