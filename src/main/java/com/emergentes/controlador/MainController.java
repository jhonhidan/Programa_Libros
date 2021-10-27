/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Libro;
import com.emergentes.utiles.ConexionDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhonny
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        ArrayList<Libro> lista = new ArrayList<Libro>();
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        
        if (op.equals("list")) {
            try {
                String sql = "select * from libros";
                System.out.println(sql);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("isbm"));
                    Libro lib = new Libro();
                    lib.setId(rs.getInt("id"));
                    lib.setIsbm(rs.getString("isbm"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setCategoria(rs.getString("categoria"));
                    lista.add(lib);

                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Error al conectar" + ex.getMessage());
            }
        }
        if (op.equals("nuevo")) {
            Libro li = new Libro();
            System.out.println(li.toString());
            request.setAttribute("lib", li);
            request.getRequestDispatcher("editar.jsp").forward(request, response);

        }
        if (op.equals("editar")) {
            int id;
            id=Integer.parseInt(request.getParameter("id"));
            try {
                Libro lib1=new Libro();
                ps=conn.prepareStatement("select * from libros where id=?");
               ps.setInt(1, id);
              rs= ps.executeQuery();
              if(rs.next()){
                  lib1.setId(rs.getInt("id"));
                  lib1.setIsbm(rs.getString("isbm"));
                  lib1.setTitulo(rs.getString("titulo"));
                  lib1.setCategoria(rs.getString("categoria"));
              }
              request.setAttribute("lib", lib1);
              request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Libro li = new Libro();
            System.out.println(li.toString());
            request.setAttribute("lib", li);
            request.getRequestDispatcher("editar.jsp").forward(request, response);

        }
        if (op.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
           
            try {
                 String sql = "delete from libros where id= ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
            ps.executeUpdate();
            response.sendRedirect("MainController");
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor de ID" + id);
            String isbm = request.getParameter("isbm");
            String titulo = request.getParameter("titulo");
            String categoria = request.getParameter("categoria");
            Libro lib = new Libro();
            lib.setId(id);
            lib.setIsbm(isbm);
            lib.setTitulo(titulo);
            lib.setCategoria(categoria);
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (id == 0) {
                String sql = "insert into libros(isbm,titulo,categoria) values(?,?,?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, lib.getIsbm());
                ps.setString(2, lib.getTitulo());
                ps.setString(3, lib.getCategoria());
                ps.executeUpdate();

            } else {
                String sql1 = "update libros set isbm=?,titulo=?,categoria=? where id=?";
                ps = conn.prepareStatement(sql1);
                ps.setString(1, lib.getIsbm());
                ps.setString(2, lib.getTitulo());
                ps.setString(3, lib.getTitulo());
                ps.setInt(4, lib.getId());
                ps.executeUpdate();

            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex.getMessage());
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
