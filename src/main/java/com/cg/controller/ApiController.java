package com.cg.controller;

import com.cg.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    // Show form page
    @GetMapping("/employees-form")
    public String showForm() {
        return "employees-form";
    }

    // Handle form submit
    @GetMapping("/employees")
    public String getEmployees(@RequestParam String pubId, Model model) {

        try {
            var data = apiService.getEmployees(pubId);
            model.addAttribute("data", data);
            return "employees-table";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "employees-form"; // go back to form page
        }
    }
}