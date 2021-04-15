package com.company.controller;

import com.company.dao.inter.*;
import com.company.entity.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
import java.util.ArrayList;
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
        mv.addObject("journalDao",journalDao);
        return mv;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public void index(@RequestParam(value = "submit" , required = false) String submit
            ,HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "journalId" , required = false) Integer journalId,
                      @RequestParam(value = "description" , required = false) String description){

        System.out.println("submit = "+submit);

          if(submit!=null && submit.equals("Download")){

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
                  journal.setRepetedWords(findMostRepetedWords(DIRJournal + journalName));

                  Thread.sleep(4000);

                  journalDao.addJournal(journal);

              } catch (IOException exception) {
                  exception.printStackTrace();
              } catch (ServletException e) {
                  e.printStackTrace();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } catch (Exception exception) {
                  exception.printStackTrace();
              }


          }else if(submit!=null && submit.equals("Save")){

              Journal journal = journalDao.getById(journalId);
              Users user = usersDao.findByEmail(request.getUserPrincipal().getName());
              UserJournal userJournal = new UserJournal();
              userJournal.setJournalId(journal);
              userJournal.setUserId(user);

              userJournalDao.addUserJournal(userJournal);


          }else if(submit!=null && submit.equals("Delete")){
              Journal journal = journalDao.getById(journalId);
              String fileName = journal.getName();
              String path = DIRJournal;
              File myFile = new File(path+fileName);
              myFile.delete();
               journalDao.removeJournal(journalId);

          }

        try {
            response.sendRedirect("base");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }



    public String findMostRepetedWords(String path) throws Exception {
        File myFile = null;
        String myword = "";
        int count1 = 0, maxCount1 = 0;
        List<String> mywords = new ArrayList<String>();
        XWPFWordExtractor myextractor = null;
        try {

            myFile = new File(path);

            PDDocument myDocument = PDDocument.load(myFile);

            PDFTextStripper myPdfStripper = new PDFTextStripper();

            String myfileData = myPdfStripper.getText(myDocument);




            String string[] = myfileData.toLowerCase().split(" ");

            for (String s : string) {
                mywords.add(s);
                System.out.println(s);
            }


            for (int i = 0; i < mywords.size(); i++) {
                count1 = 1;

                for (int j = i + 1; j < mywords.size(); j++) {
                    if (mywords.get(i).equals(mywords.get(j))) {
                        count1++;
                    }
                }

                if (count1 > maxCount1) {
                    maxCount1 = count1;
                    myword = mywords.get(i);
                }
            }
            myDocument.close();
        }catch (Exception exep) {
            exep.printStackTrace();
        }

        return myword;
    }
}
