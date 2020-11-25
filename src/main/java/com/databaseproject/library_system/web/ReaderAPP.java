package com.databaseproject.library_system.web;

import com.databaseproject.library_system.model.Admin;
import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReaderAPP {
    @Autowired
    private  ReaderService readerService;

    @GetMapping("/login")
    public String welcomePage() {
        return "login";
    }

    @GetMapping("/login/admin")
    public String loginAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin_login_page";
    }

    @GetMapping("/admin/page")
    public String checkAdmin(Admin admin){
        if (admin == null || admin.getName().isEmpty() || admin.getPassword().isEmpty()) return "admin_login_page";
        if (admin.getName().equals("root") && admin.getPassword().equals("12345678")) return "admin_op_page";
        return "admin_login_page";
    }

    @GetMapping("/reader")
    public String findReader(@RequestParam long reader_Id, Model model) {
        Reader reader = readerService.getReaderById(reader_Id);
        if (reader == null) return "login";
        model.addAttribute("readerId", reader.getReader_id());
        return "reader_menu";
    }
}
