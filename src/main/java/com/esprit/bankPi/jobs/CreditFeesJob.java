package com.esprit.bankPi.jobs;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
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
import com.esprit.bankPi.model.credit.Credit;
import com.esprit.bankPi.repository.CompteRepository;
import com.esprit.bankPi.repository.credit.CreditRepository;

@Component
public class CreditFeesJob {
	
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Lazy
	CreditRepository creditRepository;
	@Autowired
	@Lazy
	CompteRepository compteRepository;
	@Autowired
	JobLauncher jobLauncher;


	@Bean
	private Job distractCreditFeesJob() {
		return jobBuilderFactory
				.get("Credit job")
				.start(distractCreditFeesStep())
				.build();
	}
    
	@Bean
	private Step distractCreditFeesStep() {
		return stepBuilderFactory
				.get("Distract credit fees Job")
				.<Credit, Compte>chunk(3)
				.reader(getCreditItemReader())
				.processor(creditIteamProcessor())
				.writer(creditIteamWriter())
				.build();
	}

	@Bean
	@StepScope
	private RepositoryItemWriter<Compte> creditIteamWriter() {
		return new RepositoryItemWriterBuilder<Compte>()
				.repository(compteRepository)
				.methodName("save")
				.build();
	}

	@Bean
	@StepScope
	private RepositoryItemReader<Credit> getCreditItemReader() {
        Map<String, Direction> sorts = new HashMap<>();
        sorts.put("id", Direction.ASC);
		return new RepositoryItemReaderBuilder<Credit>()
				.name("ItemReader")
				.repository(creditRepository)
				.methodName("findAll")
				.sorts(sorts)
				.build();
	}

	@Bean
	@StepScope
	private ItemProcessor<Credit, Compte> creditIteamProcessor() {
		return new ItemProcessor<Credit, Compte>() {

			@Override
			public Compte process(Credit item) throws Exception {
				int creditTerm = item.getCreditRequest().getCreditTerm();

				long daysBetween = ChronoUnit.MONTHS.between(item.getCreationDate(), LocalDate.now());

				Compte c = item.getAccount();

				if (creditTerm > Integer.getInteger(Long.toString(daysBetween))) {
					c.setSolde(c.getSolde() - item.getCreditFees());
					return c;
				}
				return c;
			}

		};
	}
    
	@Scheduled(cron = "0 0 8 1 1/1 *")
	public void creditJobRunner() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters jobParameter = new JobParametersBuilder().addLong("Time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(distractCreditFeesJob(), jobParameter);
	}


}
