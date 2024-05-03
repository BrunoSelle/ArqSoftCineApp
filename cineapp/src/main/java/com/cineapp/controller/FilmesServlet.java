package com.cineapp.controller;

import com.cineapp.database.FilmeDB;
import com.cineapp.model.Filme;
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
 * @author gmass
 */
public class FilmesServlet extends HttpServlet {

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
                case "consultafilmes":
                    consultaFilmes(request, response);
                    break;
                case "manutencaofilmeinsert":
                    manutencaoFilmeInsert(request, response);
                    break;
                case "manutencaofilmeupdate":
                    manutencaoFilmeUpdate(request, response);
                    break;
                case "gravafilmedb":
                    gravaFilmeDB(request, response);
                    break;
                case "excluifilmedb":
                    excluiFilmeDB(request, response);
                    break;
                default:
                    // seta o atributo com a mensagem de erro para o jsp exibir
                    request.setAttribute("msgErro", "Uso indevido do Servlet");
                    // Direcionar para o jsp dos erros
                    request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
            }
        }
    }

    private void consultaFilmes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filme> listaFilmes = new ArrayList<Filme>();

        try {
            listaFilmes = FilmeDB.getFilmes();
        } catch (SQLException e) {
            System.out.println("ERRO: Ao trazer a lista de filmes do Banco de Dados!");
        }
        request.setAttribute("listaFilmes", listaFilmes);

        // Direcionar para o jsp
        request.getServletContext().getRequestDispatcher("/filmes.jsp").forward(request, response);
    }

    private void manutencaoFilmeInsert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Filme movie = new Filme();

        request.setAttribute("movie", movie);
        // Direcionar para o jsp
        request.getServletContext().getRequestDispatcher("/filmesManut.jsp").forward(request, response);
    }

    private void manutencaoFilmeUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgErro = "";

        // Recebe e valida codFilme
        String codFilme = request.getParameter("codFilme");
        if (codFilme == null || codFilme.equalsIgnoreCase("")) {
            msgErro = "Erro ao editar registro: o filme não foi encontrado";
        }

        if (msgErro.equalsIgnoreCase("")) {

            try {
                int codigo = Integer.parseInt(codFilme);
                Filme movie = FilmeDB.getFilmeByCodigo(codigo);
                request.setAttribute("movie", movie);
                // Direcionar para o jsp
                request.getServletContext().getRequestDispatcher("/filmesManut.jsp").forward(request, response);
            } catch (SQLException e) {
                msgErro = e.getMessage();
            }
        }

        if (msgErro.equalsIgnoreCase("")) {
            // Caso não exista erro, direciona para a consulta
            consultaFilmes(request, response);
        } else {
            // Se existe erro direciona para a página de erros
            // seta o atributo com a mensagem de erro para o jsp exibir
            request.setAttribute("msgErro", msgErro);

            // Direcionar para o jsp dos erros
            request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
        }
    }

    private void gravaFilmeDB(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgErro = "";
        String teste = "";

        
        // Recebe e valida codFilme
        teste = request.getParameter("codFilme");
        int codFilme = 0;
        if (teste == null || teste.equalsIgnoreCase("")) {
            msgErro = "Campo codFilme deve ser enviado no request";
        } else {
            try {
                codFilme = Integer.parseInt(teste);
            } catch (Exception e) {
                msgErro = "Campo codPessoa inválido";
            }
        }

        // Recebe e valida nome
        String nome = request.getParameter("nome").toUpperCase();
        if (msgErro.equalsIgnoreCase("")) {
            if (nome == null || nome.equalsIgnoreCase("")) {
                msgErro = "Informar o nome";
            }
        }

        // Recebe e valida descricao
        String descricao = request.getParameter("descricao");
        if (msgErro.equalsIgnoreCase("")) {
            if (descricao == null || descricao.equalsIgnoreCase("")) {
                msgErro = "Informar a descrição do filme";
            }
        }

        // Recebe e valida genero
        String genero = request.getParameter("genero").toUpperCase();
        
        if (msgErro.equalsIgnoreCase("")) {
            // Cria um objeto pessoa para passar para gravar no banco
            Filme movie = new Filme();

            movie.setCodFilme(codFilme);
            movie.setNomeFilme(nome);
            movie.setDescricao(descricao);
            movie.setGenero(genero);


            try {
                if (codFilme <= 0) {
                    FilmeDB.insertFilmeDB(movie);
                } else {
                    FilmeDB.updateFilmeDB(movie);
                }
            } catch (SQLException e) {
                msgErro = e.getMessage();
            }

        }

        if (msgErro.equalsIgnoreCase("")) {
            // Caso não exista erro, direciona para a consulta
            consultaFilmes(request, response);
        } else {
            // Se existe erro direciona para a página de erros
            // seta o atributo com a mensagem de erro para o jsp exibir
            request.setAttribute("msgErro", msgErro);

            // Direcionar para o jsp dos erros
            request.getServletContext().getRequestDispatcher("/erros.jsp").forward(request, response);
        }
    }

   
    private void excluiFilmeDB(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgErro = "";

        // Recebe e valida codFilme
        String codFilme = request.getParameter("codFilme");
        if (codFilme == null || codFilme.equalsIgnoreCase("")) {
            msgErro = "codFilme deve ser informado";
        }

        if (msgErro.equalsIgnoreCase("")) {

            try {
                int codigo = Integer.parseInt(codFilme);
                FilmeDB.deleteFilmeDB(codigo);
            } catch (SQLException e) {
                msgErro = e.getMessage();
            }

        }

        if (msgErro.equalsIgnoreCase("")) {
            // Caso não exista erro, direciona para a consulta
            consultaFilmes(request, response);
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
