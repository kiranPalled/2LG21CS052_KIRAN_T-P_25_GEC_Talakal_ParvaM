package com.example.studentcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.studentcrud.model.Employee;
import com.example.studentcrud.service.StudentService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/users")
public class EmployeeController {

    @Autowired
    private EmployeeService EmployeeService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("Employee", EmployeeService.listAll());
        return "Employee/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("Employee", new Employee());
        return "Employee/create";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee stu) {
        EmployeeService.saveEmployee(stu);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        EmployeeService.deleteEmployee(id);
        return "redirect:/users/list";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("Employee", EmployeeService.getEmployee(id));
        return "Employee/edit";

    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee stu) {
        stu.setId(id);
        EmployeeService.saveEmployee(stu);
        return "redirect:/users/list";
    }
}