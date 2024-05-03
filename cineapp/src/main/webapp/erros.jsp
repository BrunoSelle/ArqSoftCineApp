<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%
    String msgErro = (String) request.getAttribute("msgErro");
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
                    <h4>Erro</h4>
                </div>
                <div class="card-body">
                    <p><%=msgErro%></p>
                </div>
            </div>
            <div class="mt-3"><a href="javascript:history.back();"><button type="button" class="btn btn-primary">Voltar</button></a></div>
        </div>
    </body>
</html>
