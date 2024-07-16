/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Registros extends HttpServlet {

    private Connection con;
    private Statement set;

    public void init(ServletConfig cfg) {
        String url = "jdbc:mysql://localhost:3306/registro4iv9";
        String userName = "root";
        String password = "D19J29s1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Conexión no exitosa");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Método no permitido");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nom = request.getParameter("nombre");
            String appat = request.getParameter("appat");
            String apmat = request.getParameter("apmat");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String correo = request.getParameter("correo");

            try {
                String q = "INSERT INTO mregistro (nom_usu, appat_usu, apmat_usu, edad_usu, email_usu) VALUES ('"
                        + nom + "', '" + appat + "', '" + apmat + "', " + edad + ", '" + correo + "')";
                set.executeUpdate(q);
                System.out.println("Registro exitoso en la tabla");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Servlet Registro</title></head>");
                out.println("<body>");
                out.println("<h1>Registro Exitoso</h1>");
                out.println("<br>Tu nombre es: " + nom);
                out.println("<br>Tu apellido paterno es: " + appat);
                out.println("<br>Tu apellido materno es: " + apmat);
                out.println("<br>Tu edad es: " + edad);
                out.println("<br>Tu email es: " + correo);
                out.println("<br><br>");
                out.println("<a href='index.html'>Regresar al Menú Principal</a>");
                out.println("</body>");
                out.println("</html>");
            } catch (Exception e) {
                System.out.println("Error al registrar en la tabla");
                e.printStackTrace();

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Registro Fallido</title></head>");
                out.println("<body>");
                out.println("<h1>Registro No Exitoso, ocurrió un error</h1>");
                out.println("<a href='index.html'>Regresar al Menú Principal</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    public void destroy() {
        try {
            if (set != null) set.close();
            if (con != null) con.close();
            System.out.println("Conexión cerrada");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
