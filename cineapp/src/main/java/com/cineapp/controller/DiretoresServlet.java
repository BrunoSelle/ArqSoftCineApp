package com.cineapp.controller;

import com.cineapp.database.DiretorDB;
import com.cineapp.model.Diretor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Selle
 */
public class DiretoresServlet extends HttpServlet {

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

        String action = request.getParameter("action").toLowerCase();

        if (action != null) {
            switch (action) {
                case "consultadiretores":
                    consultaDiretores(request, response);
                    break;
                case "manutencaodiretoresinsert":
                    manutencaoDiretoresInsert(request, response);
                    break;
                case "manutencaodiretorupdate":
                    manutencaoDiretorUpdate(request, response);
                    break;
                case "gravadiretordb":
                    gravaDiretorDB(request, response);
                    break;
                case "excluidiretordb":
                    excluiDiretorDB(request, response);
                    break;
                default:
                    // seta o atributo com a mensagem de erro para o jsp exibir
                    request.setAttribute("msgErro", "Uso indevido do Servlet");
                    // Direcionar para o jsp dos erros
                    request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
            }
        }

    }

    private void consultaDiretores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Diretor> listaDiretores = new ArrayList<Diretor>();
        try {
            listaDiretores = DiretorDB.getDiretores();
        } catch (SQLException e) {
            System.out.println("ERRO: Ao carregar a lista de produtos do banco!");
        }
        request.setAttribute("listaDiretores", listaDiretores);

        // Direcionar para o jsp
        request.getServletContext().getRequestDispatcher("/diretores.jsp").forward(request, response);
    }

    private void manutencaoDiretoresInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Diretor dir = new Diretor();

        request.setAttribute("diretor", dir);
        // Direcionar para o jsp
        request.getServletContext().getRequestDispatcher("/diretoresManut.jsp").forward(request, response);
    }

    private void manutencaoDiretorUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgErro = "";

        // Recebe e valida codDiretor
        String codDiretor = request.getParameter("codDiretor");
        if (codDiretor == null || codDiretor.equalsIgnoreCase("")) {
            msgErro = "codDiretor deve ser informado no request";
        }

        if (msgErro.equalsIgnoreCase("")) {

            try {
                int codigo = Integer.parseInt(codDiretor);
                Diretor dir = DiretorDB.getDiretorByCodigo(codigo);
                request.setAttribute("diretor", dir);
                // Direcionar para o jsp
                request.getServletContext().getRequestDispatcher("/diretoresManut.jsp").forward(request, response);
            } catch (SQLException e) {
                msgErro = e.getMessage();
            }
        }

        if (msgErro.equalsIgnoreCase("")) {
            // Caso não exista erro, direciona para a consulta
            consultaDiretores(request, response);
        } else {
            // Se existe erro direciona para a página de erros
            // seta o atributo com a mensagem de erro para o jsp exibir
            request.setAttribute("msgErro", msgErro);

            // Direcionar para o jsp dos erros
            request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
        }
    }

    private void gravaDiretorDB(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgErro = "";
        String teste = "";
        
        // Recebe e valida codProduto
        teste = request.getParameter("codDiretor");
        int codDiretor = 0;
        if (teste == null || teste.equalsIgnoreCase("")) {
            msgErro = "Campo codDiretor deve ser enviado no request";
        } else {
            try {
                codDiretor = Integer.parseInt(teste);
            } catch (Exception e) {
                msgErro = "Campo codProduto inválido";
            }
        }

        // Recebe e valida nome
        String nome = request.getParameter("nome");
        if (msgErro.equalsIgnoreCase("")) {
            if (nome == null || nome.equalsIgnoreCase("")) {
                msgErro = "Informar a descrição";
            }
        }


        if (msgErro.equalsIgnoreCase("")) {
            // Cria um objeto produto para passar para gravar no banco
            Diretor dir = new Diretor();

            dir.setCodDiretor(codDiretor);
            dir.setNome(nome);

            try {
                if (codDiretor <= 0) {
                    DiretorDB.insertDiretorDB(dir);
                } else {
                    DiretorDB.updateDiretorDB(dir);
                }
            } catch (SQLException e) {
                msgErro = e.getMessage();
            }

        }

        if (msgErro.equalsIgnoreCase("")) {
            // Caso não exista erro, direciona para a consulta
            consultaDiretores(request, response);
        } else {
            // Se existe erro direciona para a página de erros
            // seta o atributo com a mensagem de erro para o jsp exibir
            request.setAttribute("msgErro", msgErro);

            // Direcionar para o jsp dos erros
            request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
        }
    }

    
    private void excluiDiretorDB(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgErro = "";

        // Recebe e valida codDiretor
        String codDiretor = request.getParameter("codDiretor");
        if (codDiretor == null || codDiretor.equalsIgnoreCase("")) {
            msgErro = "codDiretor deve ser informado";
        }

        if (msgErro.equalsIgnoreCase("")) {

            try {
                int codigo = Integer.parseInt(codDiretor);
                DiretorDB.deleteDiretorDB(codigo);
            } catch (SQLException e) {
                msgErro = e.getMessage();
            }

        }

        if (msgErro.equalsIgnoreCase("")) {
            // Caso não exista erro, direciona para a consulta
            consultaDiretores(request, response);
        } else {
            // Se existe erro direciona para a página de erros
            // seta o atributo com a mensagem de erro para o jsp exibir
            request.setAttribute("msgErro", msgErro);

            // Direcionar para o jsp dos erros
            request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
