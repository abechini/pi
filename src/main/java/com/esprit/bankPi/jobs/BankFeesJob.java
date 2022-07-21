package com.esprit.bankPi.jobs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort.Direction;
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


	@Bean
	public Job myfirstJob() {
		return jobBuilderFactory
				.get("Bank fees Jon")
				.start(myFirstStep())
				.build();
	}
    
	@Bean
	private Step myFirstStep() {
		return stepBuilderFactory
				.get("Distract Bank fees Job")
				.<Compte, Compte>chunk(3)
				.reader(getItemReader())
				.processor(iteamProcessor())
				.writer(iteamWriter())
				.build();
	}

	@Bean
	@StepScope
	private RepositoryItemWriter<Compte> iteamWriter() {
		log.info("Start Writing");
		return new RepositoryItemWriterBuilder<Compte>()
				.repository(compteRepository)
				.methodName("save")
				.build();
	}

	@Bean
	@StepScope
	private RepositoryItemReader<Compte> getItemReader() {
        Map<String, Direction> sorts = new HashMap<>();
		log.info("Start Reading");
        sorts.put("numeroCompte", Direction.ASC);
		return new RepositoryItemReaderBuilder<Compte>()
				.name("ItemReader")
				.repository(compteRepository)
				.methodName("findAll")
				.sorts(sorts)
				.build();
	}

    @Bean
    @StepScope
	private ItemProcessor<Compte, Compte> iteamProcessor() {
		log.info("In the processor");
		return new CompteProcess();
	}

}
