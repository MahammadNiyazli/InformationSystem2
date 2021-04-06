<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/3/2021
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    Users user = (Users) request.getAttribute("user");

%>
<h1>Salam qaqa image</h1>

<form method="POST" action="journal" >
    <input type="hidden" name="journalId" value="2">
    <input type="submit" name="submit" value="delete">
</form>
</body>
</html>
