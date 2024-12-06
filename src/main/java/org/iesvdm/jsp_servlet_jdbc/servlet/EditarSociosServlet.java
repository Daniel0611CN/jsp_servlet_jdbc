package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EditarSociosServlet", value = "/EditarSociosServlet")
public class EditarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarSocio.jsp");
        String codigoStr = request.getParameter("codigo");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        Optional<Socio> optionalSocio = UtilServlet.validaEditar(request);

        if (optionalSocio.isPresent()) {
            Socio socio = optionalSocio.get();
            this.socioDAO.update(socio);

            List<Socio> listado = this.socioDAO.getAll();

            request.setAttribute("listado", listado);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
        } else {
            request.setAttribute("error", "Error de validación!");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarSocio.jsp");
        }
        dispatcher.forward(request, response);
    }
}
