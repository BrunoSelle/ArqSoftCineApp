<%-- 
    Document   : filmes
    Created on : 03/05/2024, 11:16:21
    Author     : Selle
--%>

<%@page import="com.cineapp.model.Filme"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%
    Filme movie = (Filme) request.getAttribute("movie");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragmentos/headGeral.jsp" />
    </head>
    <body>
        <jsp:include page="fragmentos/navBar.jsp" />
        <div class="container">
            <form id="formFilmeManut" action='Filmes?action=gravaFilmeDB' method='POST' target='_self'>
                <input type="hidden" id="codFilme" name="codFilme" value="<%= movie.getCodFilme()%>" />
                <div class="card mt-4">
                    <div class="card-header">
                        <h5>Manutenção de Pessoa</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 col-xl-8">
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control shadow-none" id="nome" name="nome" placeholder="Digite o nome do filme"
                                           value="<%= (movie.getCodFilme() > 0 ? movie.getNomeFilme() : "") %>" required />
                                </div>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="row">
                                <div class="col-10 col-md-5 col-lg-3">
                                    <div class="form-group">
                                        <label for="descricao">Descrição</label>
                                        <input type="text" class="form-control shadow-none" id="descricao" name="descricao" placeholder="Digite a descrição do filme"
                                           value="<%= (movie.getCodFilme() > 0 ? movie.getDescricao(): "") %>" required />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="row">
                                <div class="col-10 col-md-5 col-lg-3">
                                    <div class="form-group">
                                        <label for="genero">Gênero</label>
                                        <input type="text" class="form-control shadow-none" id="genero" name="genero" placeholder="Digite a descrição do filme"
                                               value="<%= (movie.getCodFilme() > 0 ? movie.getGenero() : "") %>" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mt-3">
                    <input id="botaoSalvar" type="submit" class="btn btn-success" value="Salvar" />
                    <a href="Filmes?action=consultaFilmes"><button type="button" class="btn btn-danger">Cancelar</button></a>
                </div>
            </form>
        </div>
        <jsp:include page="fragmentos/toast.jsp" />
        <script type="text/javascript" src="includes/js/utils.js"></script>
        <script type="text/javascript" src="includes/js/filmesManut.js"></script>
    </body>
</html>
