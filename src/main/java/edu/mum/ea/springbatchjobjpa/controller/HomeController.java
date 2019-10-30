package edu.mum.ea.springbatchjobjpa.controller;

import edu.mum.ea.springbatchjobjpa.domain.Student;
import edu.mum.ea.springbatchjobjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = {"/","/admin"})
    public String home(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "admin";
    }

    //for displaying the data in view using Rest Template
//    @GetMapping("/start")
//    public String admin(Model model){
//        ResponseEntity<List<Student>> response = restTemplate.exchange("http://localhost:8080/process",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
//                });
//        System.out.println(response.getBody());
//        model.addAttribute("students", response.getBody());
//        return "admin";
//
//    }

}
