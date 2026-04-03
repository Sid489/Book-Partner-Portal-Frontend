package com.cg.controller;

import com.cg.dto.BestSellingBookDTO;
import com.cg.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/authors")
    public String showAuthors(Model model) {
        try {
            model.addAttribute("authors", apiService.getAuthorsWithBooksAndPublishers());
            return "authors";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "authors";
        }
    @GetMapping("/best-selling")
    public String getBestSellingBooks(Model model) {

        List<BestSellingBookDTO> list = apiService.getBestSellingBooks();
        model.addAttribute("books", list);

        return "best-selling";
    }
}