/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Eliminar extends HttpServlet {

    private Connection con;

    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/registro4iv9";
        String userName = "root";
        String password = "D19J29s1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Conexión no exitosa");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userId = request.getParameter("userId");

            if (userId != null && !userId.isEmpty()) {
                String query = "DELETE FROM mregistro WHERE id_usu = " + userId;
                try (Statement stmt = con.createStatement()) {
                    int rowsAffected = stmt.executeUpdate(query);
                    if (rowsAffected > 0) {
                        out.println("<p>Usuario con ID " + userId + " eliminado con éxito.</p>");
                    } else {
                        out.println("<p>No se encontró un usuario con ID " + userId + ".</p>");
                    }
                } catch (SQLException e) {
                    out.println("<p>Error al eliminar el usuario: " + e.getMessage() + "</p>");
                }
            } else {
                out.println("<p>ID de usuario no proporcionado.</p>");
            }

            out.println("<a href='index.html'>Regresar al Menú Principal</a>");
        }
    }

    @Override
    public void destroy() {
        try {
            if (con != null) con.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
