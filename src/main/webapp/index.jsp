<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Este es el login de la mejor empresa del mundo mundial">
    <title>Login page</title>
    <link rel="icon" href="img/favicon.png" type="img/future.jpg">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
  <section id="contact">
      <div class="wrapper">
        <h2>Iniciar Sesión</h2>
        <form id="loginForm" class="login-form" method="post" action="${pageContext.request.contextPath}/LoginServlet">
              <div class="input-box">
                  <input type="text" id="username" name="username" placeholder="Ingresa tu usuario" required>
              </div>
              <div class="input-box">
                  <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
              </div>
              <button type="submit" class="btn">Iniciar Sesión</button>
          </form>
          <c:if test="${param.error == '1'}">
               <p style="color:white;">Usuario o contraseña incorrectos</p>
          </c:if>
      </div>
    </section>
</body>
</html>


