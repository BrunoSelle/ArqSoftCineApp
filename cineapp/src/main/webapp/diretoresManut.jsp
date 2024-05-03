<%-- 
    Document   : DiretoresManut
    Created on : 03/05/2024, 11:47:21
    Author     : Selle
--%>

<%@page import="com.cineapp.model.Diretor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%
    Diretor dir = (Diretor) request.getAttribute("diretor");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="fragmentos/headGeral.jsp" />
    </head>
    <body>
        <jsp:include page="fragmentos/navBar.jsp" />
        <div class="container">
            <form id="formDiretorManut" action='Diretores?action=gravaDiretorDB' method='POST' target='_self'>
                <input type="hidden" id="codDiretor" name="codDiretor" value="<%= dir.getCodDiretor()%>" />
                <div class="card mt-4">
                    <div class="card-header">
                        <h5>Manutenção de Diretor</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 col-xl-8">
                                <div class="form-group">
                                    <label for="nome">Nome</label>
                                    <input type="text" class="form-control shadow-none" id="nome" name="nome" placeholder="Digite o nome do diretor"
                                           value="<%= (dir.getCodDiretor()> 0 ? dir.getNome() : "")%>" required />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mt-3">
                    <input id="botaoSalvar" type="submit" class="btn btn-success" value="Salvar" />
                    <a href="Diretores?action=consultaDiretores"><button type="button" class="btn btn-danger">Cancelar</button></a>
                </div>
            </form>
        </div>
        <jsp:include page="fragmentos/toast.jsp" />
        <script type="text/javascript" src="includes/js/utils.js"></script>
        <script type="text/javascript" src="includes/js/diretoresManut.js"></script>
    </body>
</html>
