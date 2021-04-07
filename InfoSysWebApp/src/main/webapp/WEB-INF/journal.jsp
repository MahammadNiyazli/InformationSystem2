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

    <script>

        $(".case").live('click', function(){
            $.fancybox({
                openEffect: 'elastic',
                closeEffect: 'elastic'
            });
        });

    </script>

</head>
<body>

<%
    JournalDaoImpl journalDao = (JournalDaoImpl) request.getAttribute("journalDao");
    List<Journal> journalList = journalDao.getAll();


%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (Journal j :journalList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathJournal = j.getStorageId().getPath();
                    String journalName = j.getName();

                    String path = j.getImageId().getStorageId().getPath();
                    String name = j.getImageId().getName();
                    String nameArr[] = name.split(" ");
                    String journalNameArr[] = journalName.split(" ");
                    for(int i=0;i<nameArr.length;i++){
                        pathJournal+=journalNameArr[i];
                        path+=nameArr[i];
                        if(i< nameArr.length-1){
                            path+="%20";
                            pathJournal+="%20";
                        }
                    }
                    System.out.println("path =" + path);
                    String cardTitle = j.getName().split("\\.")[0];
                %>

                <div class="dropdown-menu">
                    <form style="margin-bottom: -5px">
                        <div class="case">
                        <a class="dropdown-item" href=<%=pathJournal%> rel="case" target="_blank">Look</a>
                        </div>
                    </form>
                    <form method="post" action="journal" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Download">Download</button>
                        <input type="hidden" name="journalId" value=<%=j.getId()%> >
                    </form>
                    <form method="post" action="journal" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Save">Save</button>
                        <input type="hidden" name="journalId" value=<%=j.getId()%> >
                    </form>
                    <form method="post" action="journal" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Delete">Delete</button>
                        <input type="hidden" name="journalId" value=<%=j.getId()%> >
                    </form>
                </div>
            </div>


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
