package edu.mum.ea.springbatchjobjpa.config;

import edu.mum.ea.springbatchjobjpa.domain.Student;
import edu.mum.ea.springbatchjobjpa.repository.StudentRepository;
import edu.mum.ea.springbatchjobjpa.utility.StudentProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    EntityManagerFactory emf;
    @Autowired
    JobCompletionNotificationListener listener;


    @Bean
    public FlatFileItemReader<Student> reader(){
        return new FlatFileItemReaderBuilder<Student>()
                .name("StudentReader")
                .resource(new ClassPathResource("student-data.csv"))
                .delimited()
                .names(new String[]{"firstName", "lastName", "cgpa", "birthDate"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Student>(){
            {
                setTargetType(Student.class);
            }
        })
                .build();
    }
    @Bean
    public StudentProcessor processor(){
        return new StudentProcessor();
    }

    @Bean
    public JpaItemWriter<Student> writer(){
       JpaItemWriter<Student> jpaItemWriter = new JpaItemWriter<>();
       jpaItemWriter.setEntityManagerFactory(emf);
       return jpaItemWriter;
    }
    @Bean(name="executeManualJob")
    public Job importJob() {
        return jobBuilderFactory.get("executeManualJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Student, Student> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
