<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.impl.DocumentDaoImpl" %>
<%@ page import="com.company.entity.Document" %>
<%@ page import="com.company.dao.inter.DocumentDaoInter" %>
<%@ page import="com.company.dao.inter.UsersDaoInter" %><%--
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
    DocumentDaoInter documentDao = (DocumentDaoInter) request.getAttribute("documentDao");
    List<Document> documentList = documentDao.getAll();

    UsersDaoInter usersDao = (UsersDaoInter) request.getAttribute("usersDao");
    Users user = usersDao.findByEmail(request.getUserPrincipal().getName());

%>

<%if(user.getRole().equals("ADMIN")){%>
<div class="row">
    <button class="btn btn-secondary mx-3 mb-3" data-toggle="modal" data-target="#uploadDocument" name="upload" style="width:100%">Upload <i class="fas fa-upload"></i></button>
</div>
<%}%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (Document d :documentList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathDocument = d.getStorageId().getPath();
                    String documentName = d.getName();

                    String path = d.getImageId().getStorageId().getPath();
                    String name = d.getImageId().getName();
                    String nameArr[] = name.split(" ");
                    String documentNameArr[] = documentName.split(" ");
                    for(int i=0;i<nameArr.length;i++){
                        pathDocument+=documentNameArr[i];
                        path+=nameArr[i];
                        if(i< nameArr.length-1){
                            path+="%20";
                            pathDocument+="%20";
                        }
                    }
                    System.out.println("path =" + path);
                    System.out.println("pathDocument="+pathDocument);
                    String cardTitle = d.getName().split("\\.")[0];
                %>

                <div class="dropdown-menu dropdown-menu-right">
                    <form style="margin-bottom: -5px">
                            <a class="dropdown-item" href=<%=pathDocument%>>Look</a>
                    </form>
                    <form method="post" action="document" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                        <input type="hidden" name="documentId" value=<%=d.getId()%> >
                    </form>
                    <form method="post" action="document" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="save">Save</button>
                        <input type="hidden" name="documentId" value=<%=d.getId()%> >
                    </form>
                    <%if(user.getRole().equals("ADMIN")){%>
                    <form method="post" action="document" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                        <input type="hidden" name="documentId" value=<%=d.getId()%> >
                    </form>
                    <%}%>
                </div>
            </div>


            <img class="card-img-top" style="width:100%" src=<%=path%> alt="..." >
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=d.getDescription()%> (.docx)</p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=d.getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>

<div class="modal fade" id="uploadDocument" tabindex="-1" role="dialog" aria-labelledby="UploadModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="UploadModalLabel">Upload</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="document" method="POST" enctype="multipart/form-data" >
                <div class="modal-body">
                    <div class="form-group ">
                        <label for="chAudio">Choose document:</label>
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
