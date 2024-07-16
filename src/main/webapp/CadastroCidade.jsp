<%-- 
    Document   : CadastroCidade
    Created on : 8 de jul. de 2024, 21:42:09
    Author     : tulio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro Cidade</h1>
        <form name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" />
            <input type="hidden" name="codigoCidade" value="${codigoCidade}" />
            <p><label>Cidade:</label> <input type="text" name="nomeCidade" value="${nomeCidade}" size="40" /> </p>
            <p><label>UF:</label> <input type="text" name="ufCidade" value="${ufCidade}" size="5" /> </p>
            <input type="submit" value="Salvar" name="Salvar" />
            <input type="submit" value="Cancelar" name="Cancelar" />
        </form>

        <table border="1">
            <tr>
                <th>Código</th>
                <th>Cidade</th>
                <th>Uf</th>
            </tr> 

            <c:forEach var="cidade" items="${cidades}">
                <tr>
                    <td>${cidade.codigoCidade}</td>
                    <td>${cidade.nomeCidade}</td>
                    <td>${cidade.ufCidade}</td>
                </tr>
            </c:forEach>



        </table>

    </body>
</html>
