package com.company.controller;

import com.company.dao.inter.*;
import com.company.entity.Image;
import com.company.entity.Journal;
import com.company.entity.UserJournal;
import com.company.entity.Users;
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
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    UsersDaoInter usersDao;

    @Autowired
    JournalDaoInter journalDao;

    @Autowired
    UserJournalDaoInter userJournalDao;

    @Autowired
    StorageDaoInter storageDao;

    @Autowired
    ImageDaoInter imageDao;

    public static final String DIRJournal = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\journal\\";
    public static final String DIRImage = "D:\\InformationSystem2\\InfoSysWebApp\\src\\main\\webapp\\files\\image\\";


    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request,@RequestParam(value = "submit" , required = false) String submit){

        ModelAndView mv = new ModelAndView("journal");
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public ModelAndView index(@RequestParam(value = "submit" , required = false) String submit
            ,HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "journalId" , required = false) Integer journalId,
                      @RequestParam(value = "description" , required = false) String description){

          if(submit!=null && submit.equals("download")){

              Journal journal = journalDao.getById(journalId);
              PrintWriter out = null;
              try {
                  out = response.getWriter();

              String filename = journal.getName();
              String filepath = DIRJournal;
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

              String journalName = "";
              String imageName ="";

              try {
                  for (Part part : request.getParts()) {
                      String fileName = part.getSubmittedFileName();
                      if(fileName==null){
                          break;
                      }
                       String savePath;
                      if(fileName.endsWith("pdf")){
                           savePath = DIRJournal + fileName;
                           journalName = fileName;
                      }else{
                          savePath = DIRImage + fileName;
                          imageName = fileName;
                      }

                      part.write(savePath + File.separator );
                  }

                  Image image = new Image();
                  image.setDescription(description);
                  image.setName(imageName);
                  image.setUploadDate(new Date(new java.util.Date().getTime()));
                  image.setStorageId(storageDao.getById(2));

                  imageDao.addImage(image);

                  Journal journal = new Journal();
                  journal.setName(journalName);
                  journal.setDescription(description);
                  journal.setUploadDate(new Date(new java.util.Date().getTime()));
                  journal.setStorageId(storageDao.getById(1));
                  journal.setImageId(imageDao.findByName(imageName));

                  journalDao.addJournal(journal);

              } catch (IOException exception) {
                  exception.printStackTrace();
              } catch (ServletException e) {
                  e.printStackTrace();
              }


          }else if(submit!=null && submit.equals("save")){

              Journal journal = journalDao.getById(journalId);
              Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
              UserJournal userJournal = new UserJournal();
              userJournal.setJournalId(journal);
              userJournal.setUserId(user);

              userJournalDao.addUserJournal(userJournal);


          }else if(submit!=null && submit.equals("delete")){
               journalDao.removeJournal(journalId);

          }

         ModelAndView mv = index(request,null); //baxarsan bu koda
         return mv;

    }
}
