package com.military.content.articles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.military.content.articles.batch.ImportArticlesJob;

@Controller
public class ArticlesImportController {

	@Autowired
	private ImportArticlesJob job;
	
	@RequestMapping("/import.do")
    public ModelAndView showForm() {
		job.launchJob();
        return new ModelAndView("import");
    }
			
}
