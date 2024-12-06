package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<Socio> validaGrabar(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        //int socioID = -1;
        String nombre = null;
        int estatura = -1;
        int edad = -1;
        String localidad = null;
        try {

            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            Objects.requireNonNull(request.getParameter("nombre"));
            //CONTRACT nonBlank..
            //UTILIZO isBlank SOBRE EL PARÁMETRO DE TIPO String PARA CHEQUEAR QUE NO ES UN PARÁMETRO VACÍO "" NI CADENA TODO BLANCOS "    "
            //          |                                EN EL CASO DE QUE SEA BLANCO LO RECIBIDO, LANZO UNA EXCEPCIÓN PARA INVALIDAR EL PROCESO DE VALIDACIÓN
            //          -------------------------v                      v---------------------------------------|
            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");


            estatura = Integer.parseInt(request.getParameter("estatura"));

            edad = Integer.parseInt(request.getParameter("edad"));

            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            Objects.requireNonNull(request.getParameter("localidad"));
            //CONTRACT nonBlank
            //UTILIZO isBlank SOBRE EL PARÁMETRO DE TIPO String PARA CHEQUEAR QUE NO ES UN PARÁMETRO VACÍO "" NI CADENA TODO BLANCOS "    "
            //          |                                EN EL CASO DE QUE SEA BLANCO LO RECIBIDO, LANZO UNA EXCEPCIÓN PARA INVALIDAR EL PROCESO DE VALIDACIÓN
            //          -------------------------v                      v---------------------------------------|
            if (request.getParameter("localidad").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            localidad = request.getParameter("localidad");

            return Optional.of(new Socio(-1, nombre, estatura, edad, localidad));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }

    public static Optional<Integer> validaBorrar(HttpServletRequest request) {
        boolean valida = true;

        // Recepcion del parametro enviado por el formulario de borrar
        String codigoStr = request.getParameter("codigo");

        // Valida Parametro
        Integer codigo = null;

        try {
            // Recepcion del parametro enviado por el formulario de borrado
            codigo = Integer.parseInt(codigoStr);
            return Optional.of(codigo);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    public static Optional<Socio> validaEditar(HttpServletRequest request) {
        boolean valida = true;
        String codigoStr = request.getParameter("codigo");
        Integer codigo = null;

        String nombre = null;
        int estatura = -1;
        int edad = -1;
        String localidad = null;


        try {
            // Se supone que si el usuario se ha creado desde la web, no debería dar error, aunque validamos ya que el usuario se puede introducir
            // desde la base de datos;

            Objects.requireNonNull(request.getParameter("nombre"));

            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");

            estatura = Integer.parseInt(request.getParameter("estatura"));
            edad = Integer.parseInt(request.getParameter("edad"));

            Objects.requireNonNull(request.getParameter("localidad"));

            if (request.getParameter("localidad").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            localidad = request.getParameter("localidad");

            codigo = Integer.parseInt(codigoStr);

            if (nombre != null && estatura != -1 && edad != -1 && localidad != null) {
                return Optional.of(new Socio(codigo, nombre, estatura, edad, localidad));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }
}
