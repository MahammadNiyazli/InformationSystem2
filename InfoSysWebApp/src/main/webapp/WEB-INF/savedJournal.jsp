<%@ page import="com.company.dao.inter.UsersDaoInter" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.UserJournal" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 4/4/2021
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());


    List<UserJournal> userJournalList = user.getUserJournalList();


%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (UserJournal uj :userJournalList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathJournal = uj.getJournalId().getStorageId().getPath();
                    String journalName = uj.getJournalId().getName();

                    String path = uj.getJournalId().getImageId().getStorageId().getPath();
                    String name = uj.getJournalId().getImageId().getName();
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
                    String cardTitle = uj.getJournalId().getName().split("\\.")[0];
                %>

                <div class="dropdown-menu dropdown-menu-right">
                    <form style="margin-bottom: -5px">
                        <div class="case">
                            <a class="dropdown-item" href=<%=pathJournal%> rel="case" target="_blank">Look</a>
                        </div>
                    </form>
                    <form method="post" action="journal" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Download">Download</button>
                        <input type="hidden" name="journalId" value=<%=uj.getJournalId().getId()%> >
                    </form>
                </div>
            </div>


            <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=uj.getJournalId().getDescription()%> (.pdf)</p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=uj.getJournalId().getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>

</body>
</html>
