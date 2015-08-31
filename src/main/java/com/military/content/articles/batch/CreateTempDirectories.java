package com.military.content.articles.batch;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component ("createTempDirectoriesTasklet")
@Scope("step")
public class CreateTempDirectories implements Tasklet {

	@Value("#{jobParameters['tempFolder']}")
	private String tempFolder;
	
	@Value("${data.image.configs.folder}")
	private String imageConfigFolder;
	
	@Value("${data.image.files.folder}")
	private String imageDataFolder;
	
	@Value("${data.news.folder}")
	private String newsConfigFolder;
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		//create parent directories(need to create only once)
		new File(newsConfigFolder).mkdirs();
		new File(imageConfigFolder).mkdirs();
		new File(imageDataFolder).mkdirs();
		
		//create temp folder where current execution files will be stored
		if(tempFolder!=null){
			new File(imageConfigFolder+File.separator+tempFolder).mkdir();
			new File(imageDataFolder+File.separator+tempFolder).mkdir();
			new File(newsConfigFolder+File.separator+tempFolder).mkdir();
		}
		
		return RepeatStatus.FINISHED;
	}

}
