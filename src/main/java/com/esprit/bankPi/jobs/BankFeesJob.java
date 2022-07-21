package com.esprit.bankPi.jobs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.esprit.bankPi.data.Compte;
import com.esprit.bankPi.repository.CompteRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableBatchProcessing
public class BankFeesJob {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Lazy
	CompteRepository compteRepository;
	@Autowired
	JobLauncher jobLauncher;


	@Bean
	public Job distractBankFeesJob() {
		return jobBuilderFactory
				.get("Bank fees Job")
				.start(distractBankFeesStep())
				.build();
	}
    
	@Bean
	private Step distractBankFeesStep() {
		return stepBuilderFactory
				.get("Distract Bank fees Job")
				.<Compte, Compte>chunk(3)
				.reader(getItemReader())
				.processor(iteamProcessor())
				.writer(iteamWriter())
				.build();
	}

	@Bean
	private RepositoryItemWriter<Compte> iteamWriter() {
		log.info("Writing is starting");
		return new RepositoryItemWriterBuilder<Compte>()
				.repository(compteRepository)
				.methodName("save")
				.build();
	}

	@Bean
	private RepositoryItemReader<Compte> getItemReader() {
		log.info("Reading is starting");

        Map<String, Direction> sorts = new HashMap<>();
        sorts.put("numeroCompte", Direction.ASC);
		return new RepositoryItemReaderBuilder<Compte>()
				.name("ItemReader")
				.repository(compteRepository)
				.methodName("findAll")
				.sorts(sorts)
				.build();
	}

    @Bean
	private CompteProcess iteamProcessor() {
		return new CompteProcess();
	}
    
	@Scheduled(cron = "0 0 8 1 1/1 *")
	public void jobRunner() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameter = new JobParametersBuilder().addLong("Time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(distractBankFeesJob(), jobParameter);
	}

}
