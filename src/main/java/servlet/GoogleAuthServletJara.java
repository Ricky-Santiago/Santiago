/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.JwtUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet(name = "GoogleAuthServletJara", urlPatterns = {"/googleauthservletjara"})
public class GoogleAuthServletJara extends HttpServlet {

    private static final String CLIENT_ID = "437492465701-vrv2rst7vvoku44hfvcf44lipve3bii8.apps.googleusercontent.com";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GoogleAuthServletJara</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GoogleAuthServletJara at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Leer token del cuerpo de la petición
            String googleToken = request.getReader().readLine().split(":")[1].replace("\"", "").replace("}", "").trim();

            // 2. Verificar token de Google (método alternativo sin librerías de Google)
            if (!verifyGoogleToken(googleToken)) {
                throw new Exception("Token de Google inválido");
            }

            // 3. Obtener email del token (sin tocar la BD)
            String email = extractEmailFromGoogleToken(googleToken);

            // 4. Generar JWT con tu clase existente
            String jwt = JwtUtil.generarToken(email);

            // 5. Responder con el JWT
            response.setContentType("application/json");
            response.getWriter().print("{\"success\":true, \"jwt\":\"" + jwt + "\"}");

        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().print("{\"success\":false, \"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private boolean verifyGoogleToken(String googleToken) throws IOException {
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + googleToken;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        
        try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            String audience = json.get("aud").getAsString();
            return audience.equals(CLIENT_ID);
        }
    }

    private String extractEmailFromGoogleToken(String googleToken) throws IOException {
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + googleToken;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        
        try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            return json.get("email").getAsString();
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para autenticación con Google";
    }
}