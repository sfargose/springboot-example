package com.military.content.articles.batch;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component ("cleanupTempDirectoriesTasklet")
@Scope("step")
public class CleanupTempDirectories implements Tasklet {

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
		if(tempFolder!=null){
			FileUtils.deleteDirectory(new File(imageConfigFolder+File.separator+tempFolder));
			FileUtils.deleteDirectory(new File(imageDataFolder+File.separator+tempFolder));
			FileUtils.deleteDirectory(new File(newsConfigFolder+File.separator+tempFolder));
		}
		
		return RepeatStatus.FINISHED;
	}

}
