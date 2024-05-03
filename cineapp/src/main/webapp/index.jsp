<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
                    <h4>Cadastros</h4>
                </div>
                <div class="card-body">
                    <ul>
                        <li><a href="Filmes?action=consultaFilmes">Filmes</a></li>
                        <li><a href="Diretores?action=consultaDiretores">Diretores</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
