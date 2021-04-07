<%@ page import="java.security.Principal" %>
<%@ page import="com.company.entity.Users" %>
<%@ page import="com.company.entity.Journal" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.company.dao.inter.JournalDaoInter" %>
<%@ page import="com.company.dao.impl.VideoDaoImpl" %>
<%@ page import="com.company.entity.Video" %><%--
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
    VideoDaoImpl videoDao = (VideoDaoImpl) request.getAttribute("videoDao");
    List<Video> videoList = videoDao.getAll();


%>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <%for (Video v :videoList){ %>
    <div class="col" style="margin-bottom:35px">
        <div class="card h-100" style="position:relative;">
            <div class="btn-group" style="position:absolute;left:90%; top:2%; z-index:1">
                <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fa fa-ellipsis-v" aria-hidden="true"></i>
                </button>

                <%
                    String pathVideo = v.getStorageId().getPath();
                    String videoName = v.getName();

                    String videoNameArr[] = videoName.split(" ");
                    for(int i1=0;i1<videoNameArr.length;i1++){
                        pathVideo+=videoNameArr[i1];
                        if(i1< videoNameArr.length-1){
                            pathVideo+="%20";
                        }
                    }
                    pathVideo+="#t=1";
                    System.out.println("path =" + pathVideo);
                    String cardTitle = v.getName().split("\\.")[0];
                %>

                <div class="dropdown-menu">
                    <form style="margin-bottom: -5px">
                        <div class="case">
                            <a class="dropdown-item" href=<%=pathVideo%> rel="case" target="_blank">Look</a>
                        </div>
                    </form>
                    <form method="post" action="video" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="download">Download</button>
                        <input type="hidden" name="videoId" value=<%=v.getId()%> >
                    </form>
                    <form method="post" action="video" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="save">Save</button>
                        <input type="hidden" name="videoId" value=<%=v.getId()%> >
                    </form>
                    <form method="post" action="video" style="margin-bottom: -5px">
                        <button class="dropdown-item btn btn-light" type="submit" name="submit" value="delete">Delete</button>
                        <input type="hidden" name="videoId" value=<%=v.getId()%> >
                    </form>
                </div>
            </div>


            <video class="img-fluid" controls>
                <source src=<%=pathVideo%> type="video/mp4">
            </video>
            <div class="card-body">
                <h5 class="card-title"><%=cardTitle%></h5>
                <p class="card-text"><%=v.getDescription()%></p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Tarix : <%=v.getUploadDate()%></small>
            </div>
        </div>
    </div>
    <%}%>
</div>
</body>
</html>
