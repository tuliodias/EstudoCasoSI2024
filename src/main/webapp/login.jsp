<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form id="cadastroForm" name="cadastro" method="post"  action="${pageContext.request.contextPath}${URL_BASE}/LoginControlador">
        <p>
            <label>Username:</label>
            <input type="text" name="username" required>
        </p>
        <p>
            <label>Password:</label>
            <input type="password" name="password" required>
        </p>
        <p>
            <input type="submit" value="Login">
        </p>
           <input type="hidden" name="opcao" value="login" />
    </form>
        <p>  <a href="${pageContext.request.contextPath}/CadastroUsuario.jsp">Cadastrar Usuário</a> </p>
        
    <c:if test="${not empty mensagem}">
        <p>${mensagem}</p>
    </c:if>
</body>
</html>