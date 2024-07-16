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
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;

public class Consult extends HttpServlet {

    private Connection con;
    private Statement set;

    @Override
    public void init(ServletConfig cfg) {
        String url = "jdbc:mysql://localhost:3306/registro4iv9";
        String userName = "root";
        String password = "D19J29s1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Conexión no exitosa");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Tabla del Registro de Usuarios</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("table { border-collapse: collapse; width: 100%; margin-top: 20px; }");
            out.println("th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("a { display: inline-block; padding: 10px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; margin-top: 20px; }");
            out.println("a:hover { background-color: #45a049; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Tabla del Registro de Usuarios</h1>");

            String query = "SELECT * FROM mregistro"; 
            try (ResultSet rs = set.executeQuery(query)) {
                if (!rs.isBeforeFirst()) { // Verifica si el ResultSet está vacío
                    out.println("<p>No hay registros disponibles.</p>");
                } else {
                    out.println("<table>");
                    out.println("<thead><tr><th>ID</th><th>Nombre</th><th>Apellido Paterno</th><th>Apellido Materno</th><th>Edad</th><th>Email</th></tr></thead>");
                    out.println("<tbody>");

                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getInt("id_usu") + "</td>"); // Cambia a 'id_usu' según tu tabla
                        out.println("<td>" + rs.getString("nom_usu") + "</td>");
                        out.println("<td>" + rs.getString("appat_usu") + "</td>");
                        out.println("<td>" + rs.getString("apmat_usu") + "</td>");
                        out.println("<td>" + rs.getInt("edad_usu") + "</td>");
                        out.println("<td>" + rs.getString("email_usu") + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</tbody>");
                    out.println("</table>");
                }
            } catch (SQLException e) {
                out.println("<p>Error al acceder a la base de datos: " + e.getMessage() + "</p>");
                e.printStackTrace();
            } catch (Exception e) {
                out.println("<p>Se ha producido un error inesperado: " + e.getMessage() + "</p>");
                e.printStackTrace();
            }

            out.println("<a href='index.html'>Regresar al Menú Principal</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            if (set != null) set.close();
            if (con != null) con.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
