<%-- 
    Document   : Diretores
    Created on : 03/05/2024, 11:47:21
    Author     : Selle
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@page import="com.cineapp.model.Diretor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%
    List<Diretor> listaDiretores = (ArrayList<Diretor>) request.getAttribute("listaDiretores");
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
                    <h5>Cadastro de Diretores</h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-sm">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nome</th>
                                    <th>Operações</th>
                                </tr>
                            </thead>

                            <tbody>
                                <% for (Diretor dir : listaDiretores) {%>
                                <tr>
                                    <td><%= dir.getCodDiretor()%></td>
                                    <td><%= dir.getNome()%></td>
                                    <td>
                                        <a href='Diretores?action=manutencaoDiretorUpdate&codDiretor=<%= dir.getCodDiretor()%>' style="text-decoration: none;" data-bs-toggle="tooltip" title='Alterar Registro' >
                                            <i class="bi-pencil-square"></i>
                                        </a>&nbsp;&nbsp;
                                        <a href='#'
                                           style="text-decoration: none;" 
                                           data-bs-toggle="tooltip" 
                                           title="Excluir Registro" 
                                           onclick='mostraConfDelete(<%= dir.getCodDiretor()%>);'>
                                           <i class="bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table> 
                    </div>
                </div>
            </div>
            <div class="mt-3"><a href="Diretores?action=manutencaoDiretoresInsert"><button type="button" class="btn btn-primary"><i class="bi-plus-circle"></i> Novo Diretor</button></a></div>
        </div>
        <jsp:include page="fragmentos/toolTip.jsp" />
        <jsp:include page="fragmentos/confModal.jsp" />
        <script type="text/javascript" src="includes/js/diretores.js"></script>
    </body>
</html>
