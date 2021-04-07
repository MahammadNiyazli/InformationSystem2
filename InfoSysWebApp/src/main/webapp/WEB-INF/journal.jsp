<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.impl.JournalDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/3/2021
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>Journal</title>
</head>
<body>

<%
    JournalDaoImpl journalDao = (JournalDaoImpl) request.getAttribute("journalDao");
    List<Journal> journalList = journalDao.getAll();


%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (Journal j :journalList){ %>
    <div class="col">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>
                <div class="dropdown-menu">
                    <form style="margin-bottom: -5px">
                        <a class="dropdown-item" href="#">Look</a>
                    </form>
                    <form class="" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit">Download</button>
                    </form>
                    <form class="" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit">Save</button>
                    </form>
                    <form class="" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit">Delete</button>
                    </form>
                </div>
            </div>

            <%
                String path = j.getImageId().getStorageId().getPath();
                String name = j.getImageId().getName();
                String nameArr[] = name.split(" ");
                for(int i=0;i<nameArr.length;i++){
                    path+=nameArr[i];
                    if(i< nameArr.length-1){
                        path+="%20";
                    }
                }
                System.out.println("path =" + path);
              String cardTitle = j.getName().split("\\.")[0];
            %>

            <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=j.getDescription()%></p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=j.getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>

</body>
</html>
