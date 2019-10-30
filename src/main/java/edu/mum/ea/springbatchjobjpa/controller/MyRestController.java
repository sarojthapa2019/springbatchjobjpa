package edu.mum.ea.springbatchjobjpa.controller;

import edu.mum.ea.springbatchjobjpa.domain.Student;
import edu.mum.ea.springbatchjobjpa.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("executeManualJob")
    private Job job;



    @GetMapping(value = "/process", produces = "application/json")
    public RedirectView trigger(Model model) throws Exception {
        try{
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters).getExitStatus().getExitCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView("/admin");
    }
}
