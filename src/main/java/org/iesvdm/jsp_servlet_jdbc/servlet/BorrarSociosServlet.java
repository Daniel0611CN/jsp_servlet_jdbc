package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(name = "BorrarSociosServlet", value = "/BorrarSociosServlet")
public class BorrarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        Optional<Integer> optionalCodigo = UtilServlet.validaBorrar(request);

        if (optionalCodigo.isPresent()) {
            int codigo = optionalCodigo.get();

            // Parametro Valido
            socioDAO.delete(codigo);

            // SI QUISIERA REDIRECCION DEL LADO DEL NAVEGADOR
            // response.sendRedirect("ListarSociosServlet");

            // QUIERO REDIRECCION INTERNA
            List<Socio> listado = this.socioDAO.getAll();
            request.setAttribute("listado", listado);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
            dispatcher.forward(request, response);

        } else {
            // Parametro no valido
            response.sendRedirect("ListarSociosServlet?err-cod=1");
        }
    }
}
