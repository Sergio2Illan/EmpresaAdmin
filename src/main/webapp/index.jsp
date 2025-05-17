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
  <div class="main-container ">
    <div class="main-container-opacity"></div>
    <div class="container">
      <div class="login-image">
        <div class="img-opacity">
        </div>
        <img src="img/fot.jpg" alt="Foto de la empresa más guay del mundo.">
      </div>
      <div class="login-container">
          <form id="loginForm" class="login-form" method="post" action="${pageContext.request.contextPath}/LoginServlet">
              <h2>Iniciar Sesión</h2>
              <div class="input-group">
                  <label for="username">Usuario</label>
                  <input type="text" id="username" name="username" placeholder="Ingresa tu usuario" required>
              </div>
              <div class="input-group">
                  <label for="password">Contraseña</label>
                  <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
              </div>
              <button type="submit" class="btn">Iniciar Sesión</button>
          </form>

          <div id="spinner-animation">
            <!-- div class="spinner"></!-->
            <c:if test="${param.error == '1'}">
               <p style="color:red;">Usuario o contraseña incorrectos</p>
            </c:if>
          </div>
        </div>
        
    </div>
  </div>

</body>
</html>
