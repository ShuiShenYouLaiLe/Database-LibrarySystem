package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Branch;
import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BranchApp {
    @Autowired
    BranchService branchService;

    @GetMapping("/branches")
    public String getAllBranch(Model model) {
        List<Branch> branches = branchService.getAll();
        model.addAttribute("branches", branches);
        return "branches";
    }
}
