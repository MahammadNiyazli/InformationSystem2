package com.company.controller;

import com.company.dao.inter.UsersDaoInter;
import com.company.entity.*;
import com.company.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    UsersDaoInter usersDao;

    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(@RequestParam(value = "word", required = false) String word) {
        try {
            System.out.println("searchService="+searchService);

            List<Audio> audioList = searchService.searchAudio(word);
            List<Video> videoList = searchService.searchVideo(word);
            List<Document> documentList = searchService.searchDocument(word);
            List<Journal> journalList = searchService.searchJournal(word);
            List<Image> imageList = searchService.searchImage(word);

            ModelAndView mv = new ModelAndView("search");
            mv.addObject("audioList",audioList);
            mv.addObject("videoList",videoList);
            mv.addObject("documentList",documentList);
            mv.addObject("journalList",journalList);
            mv.addObject("imageList",imageList);
            mv.addObject("usersDao",usersDao);

            return mv;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
