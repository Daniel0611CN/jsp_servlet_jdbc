<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <h2>Introduzca los datos del nuevo socio:</h2>
    <form method="post" action="GrabarSociosServlet">

      Nombre <input type="text" name="nombre"/></br>
      Estatura <input type="text" name="estatura"/></br>
      Edad <input type="text" name="edad"/></br>
      Localidad <input type="text" name="localidad"/></br>
      <input type="submit" value="Aceptar">
    </form>

  <%
//                                v---- RECOGER MENSAJE DE ERROR DEL ÁMBITO request
    String error = (String) request.getAttribute("error");
//           v---- SI ESTÁ PRESENTE INFORMAR DEL ERROR
    if (error != null) {
      %>
      <div style="color: red"><%=error%></div>
    <%
      }
      // EL EXTRA SERÍA IMPLEMENTAR AQUÍ UNA LÓGICA DEPENDIENDO DE QUE BOTÓN DEL LISTADO SOCIOS PULSE EL USUARIO, Y REALIZAR LA ACCIÓN CORRESPONDIENTE
      // SI ES CREAR - SERÍA EL FORMULARIO DE ARRIBA, SI ES BORRAR, SERIA SOLAMENTE REDIRIGIR A OTRA PÁGINA, Y SI ES EDITAR, SERÍA OTRO FORMULARIO PARECIDO;
    %>

  </body>
</html>