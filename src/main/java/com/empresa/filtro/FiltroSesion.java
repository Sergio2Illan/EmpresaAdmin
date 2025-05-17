package com.empresa.filtro;

import com.empresa.util.Conexion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FiltroSesion implements Filter {
    private static final Logger logger = LogManager.getLogger(Conexion.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización si hiciera falta
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("Iniciando FiltroSesion");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession sesion = req.getSession(false);
        String uri = req.getRequestURI();
        logger.info("FiltroSesion: URI accedida = " + uri);

        // Permitir el acceso libre a recursos estáticos y páginas públicas
        if (uri.endsWith("index.jsp") ||
                uri.equals(req.getContextPath() + "/") ||  // acceso directo a la raíz
                uri.contains("LoginServlet") ||
                uri.endsWith(".css") ||
                uri.endsWith(".js") ||
                uri.endsWith(".jpg") ||
                uri.endsWith(".png") ||
                uri.endsWith(".ico")) {
            logger.info("Ejecutando FiltroSesion, usuario entra en publico");
            chain.doFilter(request, response);
            return;
        }

        // Si hay sesión activa y usuario logueado, dejar pasar
        if (sesion != null && sesion.getAttribute("usuario") != null) {
            logger.info("Ejecutando FiltroSesion, usuario actual");
            chain.doFilter(request, response);
        } else {
            // Si no hay sesión, redirigir al login
            logger.info("Ejecutando FiltroSesion, usuario invalido");
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    @Override
    public void destroy() {
        // Limpieza si es necesaria
    }
}
