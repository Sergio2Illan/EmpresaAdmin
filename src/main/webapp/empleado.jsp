<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page session="true" %>
<% String usuario = (String) session.getAttribute("usuario"); if (usuario ==
null) { response.sendRedirect("../index.html"); return; } %> <%@ page
contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Gestión de empleados de la empresa" />
    <title>Gestión de Empleados</title>
    <link rel="icon" href="img/favicon.png" type="img/future.jpg" />
    <link rel="stylesheet" href="css/empelado.css" />
  </head>
  <body>
    <header class="header">
      <!-- Usuario y botón de logout -->
      <div class="text-end mb-3">
        <span class="me-3" style="color: white">Usuario: <strong>${sessionScope.usuario}</strong></span
        >
        <a
          href="${pageContext.request.contextPath}/LogoutServlet"
          class="btn btn-danger btn-sm"
          >Cerrar sesión</a>
      </div>
    </header>
    <section id="contact">
      <div class="wrapper">
        <h2>Gestión de Empleados</h2>

        <!-- Formulario -->
        <form
          id="empleadoForm"
          class="login-form"
          method="post"
          action="${pageContext.request.contextPath}/EmpleadoServlet"
        >
          <div class="input-box">
            <input type="number" name="id" placeholder="ID" />
          </div>
          <div class="input-box">
            <input type="text" name="nombre" placeholder="Nombre" />
          </div>
          <div class="input-box">
            <input type="text" name="email" placeholder="Email" />
          </div>
          <div class="input-box">
            <input type="text" name="puesto" placeholder="Puesto" />
          </div>
          <div class="input-box">
            <input
              type="number"
              step="0.01"
              name="salario"
              placeholder="Salario"
            />
          </div>

          <!-- Botones de acción -->
          <div class="botones">
            <button type="submit" name="accion" value="alta" class="btn">
              Alta
            </button>
            <button type="submit" name="accion" value="baja" class="btn">
              Baja
            </button>
            <button type="submit" name="accion" value="modificar" class="btn">
              Modificar
            </button>
            <button type="submit" name="accion" value="buscarPorId" class="btn">
              Buscar por ID
            </button>
            <button type="submit" name="accion" value="buscarTodos" class="btn">
              Buscar Todos
            </button>
          </div>
        </form>

        <!-- Mensaje de error -->
        <c:if test="${param.error == '1'}">
          <p style="color: white">Error en la operación</p>
        </c:if>
      </div>
    </section>

    <section id="result">
      <!-- Zona de resultados -->
      <div id="resultados" style="margin-top: 20px">
        <h3>Resultados</h3>

        <!-- Mostrar un solo empleado -->
        <c:if test="${not empty empleado}">
          <p><strong>ID:</strong> ${empleado.id}</p>
          <p><strong>Nombre:</strong> ${empleado.nombre}</p>
          <p><strong>Email:</strong> ${empleado.email}</p>
          <p><strong>Puesto:</strong> ${empleado.puesto}</p>
          <p><strong>Salario:</strong> ${empleado.salario}</p>
        </c:if>

        <!-- Mostrar lista de empleados -->
        <c:if test="${not empty listaEmpleados}">
          <table
            style="
              width: 100%;
              margin-top: 15px;
              border-collapse: collapse;
              background: white;
            "
          >
            <thead>
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
        </c:if>
      </div>
    </section>
  </body>
</html>
