package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.boot.model.Student;
import ru.geekbrains.boot.services.StudentService;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping // GET http://localhost:8189/app/students
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_score") Integer minScore,
                          @RequestParam(required = false, name = "max_score") Integer maxScore
    ) {
        model.addAttribute("students", studentService.findAll(minScore, maxScore));
        return "students";
    }

    @GetMapping("/test")
    @ResponseBody
    public Student getById(@RequestParam Long id){
        return studentService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable Long id) {
        studentService.deleteBydId(id);
        return "redirect:/students"; // [http://localhost:8189/app]/students
    }
}
