package com.company.controller;

import com.company.dao.inter.*;
import com.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    UsersDaoInter usersDao;

    @Autowired
    ImageDaoInter imageDao;

    @Autowired
    UserImageDaoInter userImageDao;

    @Autowired
    StorageDaoInter storageDao;


    public static final String DIRImage = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\image\\";


    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request,@RequestParam(value = "submit" , required = false) String submit){

        ModelAndView mv = new ModelAndView("image");
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void index(@RequestParam(value = "submit" , required = false) String submit
            ,HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "imageId" , required = false) Integer imageId,
                      @RequestParam(value = "description" , required = false) String description){

          if(submit!=null && submit.equals("download")){

              Image image = imageDao.getById(imageId);
              PrintWriter out = null;
              try {
                  out = response.getWriter();

              String filename = image.getName();
              String filepath = DIRImage;
              response.setContentType("APPLICATION/OCTET-STREAM");
              response.setHeader("Content-Disposition", "attachment; filename=\""
                      + filename + "\"");

              FileInputStream fileInputStream = new FileInputStream(filepath
                      + filename);

              int i;
              while( (i = fileInputStream.read()) != -1 )
              {
                  out.write(i);
              }
              fileInputStream.close();
              out.close();



              } catch (IOException exception) {
            exception.printStackTrace();
             }

          }else if(submit!=null && submit.equals("upload")){

              String imageName ="";

              try {
                  for (Part part : request.getParts()) {
                      String fileName = part.getSubmittedFileName();
                      if(fileName==null){
                          break;
                      }


                          String savePath = DIRImage + fileName;
                          imageName = fileName;
                          Image image = new Image();
                          image.setDescription(description);
                          image.setName(imageName);
                          image.setUploadDate(new Date(new java.util.Date().getTime()));
                          image.setStorageId(storageDao.getById(2));
                          imageDao.addImage(image);
                          part.write(savePath + File.separator );
                  }


              } catch (IOException exception) {
                  exception.printStackTrace();
              } catch (ServletException e) {
                  e.printStackTrace();
              }


          }else if(submit!=null && submit.equals("save")){

              Image image = imageDao.getById(imageId);
              Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
              UserImage userImage = new UserImage();
              userImage.setImageId(image);
              userImage.setUserId(user);

              userImageDao.addUserImage(userImage);


          }else if(submit!=null && submit.equals("delete")){
               imageDao.removeImage(imageId);

          }

        try {
            response.sendRedirect("base");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
