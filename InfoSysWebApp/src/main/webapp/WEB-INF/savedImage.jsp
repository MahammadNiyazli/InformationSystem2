<%@ page import="com.company.dao.inter.UsersDaoInter" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.UserAudio" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.entity.UserImage" %><%--
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


    List<UserImage> userImageList = user.getUserImageList();
%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (UserImage ui :userImageList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathImage = ui.getImageId().getStorageId().getPath();
                    String imageName = ui.getImageId().getName();

                    String imageNameArr[] = imageName.split(" ");
                    for(int i1=0;i1<imageNameArr.length;i1++){
                        pathImage+=imageNameArr[i1];
                        if(i1< imageNameArr.length-1){
                            pathImage+="%20";
                        }
                    }
                    System.out.println("path =" + pathImage);
                    String cardTitle = ui.getImageId().getName().split("\\.")[0];
                %>

                <div class="dropdown-menu dropdown-menu-right">
                    <form style="margin-bottom: -5px">
                        <div class="case">
                            <a class="dropdown-item" href=<%=pathImage%> rel="case" target="_blank">Look</a>
                        </div>
                    </form>
                    <form method="post" action="image" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                        <input type="hidden" name="imageId" value=<%=ui.getImageId().getId()%> >
                    </form>
                </div>
            </div>


            <img class="card-img-top" style="width:100%" src=<%=pathImage%> alt="..." >
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=ui.getImageId().getDescription()%> (.png)</p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=ui.getImageId().getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>


</body>
</html>
