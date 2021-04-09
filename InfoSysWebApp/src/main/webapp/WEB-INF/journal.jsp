<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.impl.JournalDaoImpl" %>
<%@ page import="com.company.dao.inter.UsersDaoInter" %><%--
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
    JournalDaoInter journalDao = (JournalDaoInter) request.getAttribute("journalDao");
    List<Journal> journalList = journalDao.getAll();

    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
%>

<%if(user.getRole().equals("ADMIN")){%>
<div class="row">
    <button class="btn btn-secondary mx-3 mb-3" data-toggle="modal" data-target="#uploadJournal" name="uploadJournal" style="width:100%">Upload <i class="fas fa-upload"></i></button>
</div>
<%}%>

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
                    <%if(user.getRole().equals("ADMIN")){%>
                    <form method="post" action="journal" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="Delete">Delete</button>
                        <input type="hidden" name="journalId" value=<%=j.getId()%> >
                    </form>
                    <%}%>
                </div>
            </div>


            <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=j.getDescription()%> (.pdf)</p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=j.getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>

<div class="modal fade" id="uploadJournal" tabindex="-1" role="dialog" aria-labelledby="UploadModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="UploadModalLabel">Upload</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="journal" method="POST" enctype="multipart/form-data" >
                <div class="modal-body">
                    <div class="form-group ">
                        <label for="chAudio">Choose journal:</label>
                        <input type="file" class="form-control" name="chFile"  id="chAudio" multiple >
                    </div>

                    <div class="form-group ">
                        <label for="chImage">Choose image:</label>
                        <input type="file" class="form-control" name="chImage"  id="chImage" multiple >
                    </div>

                    <div class="form-group ">
                        <label for="desc"> Description:</label>
                        <input type="text"  class="form-control" name="description"  id="desc">
                    </div>
                </div>
                <div class="modal-footer">

                    <button type="button" class="btn btn-success" data-dismiss="modal">close</button>
                    <button type="submit" name="submit" value="upload" class="btn btn-danger" >upload</button>

                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
