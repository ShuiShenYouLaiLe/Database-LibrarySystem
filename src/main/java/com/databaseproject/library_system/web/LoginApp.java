package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.model.Admin;
import com.databaseproject.library_system.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginApp {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/login")
    public String welcomePage() {
        return "login";
    }

    @GetMapping("/login/admin")
    public String loginAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin_login_page";
    }
}
