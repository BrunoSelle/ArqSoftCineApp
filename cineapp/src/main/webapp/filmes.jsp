<%-- 
    Document   : filmes
    Created on : 03/05/2024, 10:58:21
    Author     : Selle
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="com.cineapp.model.Filme"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%
    List<Filme> listaFilmes = (ArrayList<Filme>) request.getAttribute("listaFilmes");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragmentos/headGeral.jsp" />
    </head>
    <body>
        <jsp:include page="fragmentos/navBar.jsp" />
        <div class="container">
            <div class="card mt-4">
                <div class="card-header">
                    <h5>Cadastro de Pessoas</h5>
                </div>
                <div class="card-body">
                    <% if (listaFilmes != null && listaFilmes.size() > 0) { %>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-sm">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nome</th>
                                    <th>Descrição</th>
                                    <th>Gênero</th>
                                    <th>Operações</th>
                                </tr>
                            </thead>

                            <tbody>
                                <% for (Filme movie : listaFilmes) {%>
                                <tr>
                                    <td><%= movie.getCodFilme()%></td>
                                    <td><%= movie.getNomeFilme()%></td>
                                    <td><%= movie.getDescricao()%></td>
                                    <td><%= movie.getGenero()%></td>
                                    <td>
                                        <a href='Filmes?action=manutencaoFilmeUpdate&codFilme=<%= movie.getCodFilme()%>' style="text-decoration: none;" data-bs-toggle="tooltip" title='Alterar Registro' >
                                            <i class="bi-pencil-square"></i>
                                        </a>&nbsp;&nbsp;
                                        <a href='#'
                                           style="text-decoration: none;" 
                                           data-bs-toggle="tooltip" 
                                           title="Excluir Registro" 
                                           onclick='mostraConfDelete(<%= movie.getCodFilme()%>);' >
                                            
                                            <i class="bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table> 
                    </div>
                    <% } else { %>
                    <p>Não existem registros de pessoas para serem exibidos</p>
                    <% }%>
                </div>
            </div>
            <div class="mt-3"><a href="Filmes?action=manutencaoFilmeInsert"><button type="button" class="btn btn-primary"><i class="bi-plus-circle"></i> Cadastrar novo Filme</button></a></div>
        </div>
        <jsp:include page="fragmentos/toolTip.jsp" />
        <jsp:include page="fragmentos/confModal.jsp" />
        <script type="text/javascript" src="includes/js/pessoas.js"></script>
    </body>
</html>
