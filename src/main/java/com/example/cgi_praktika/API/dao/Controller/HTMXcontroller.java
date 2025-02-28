package com.example.cgi_praktika.API.dao.Controller;
//import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping("/")
//@RequiredArgsConstructor
public class HTMXcontroller {
    @RequestMapping
    public String index() {
        return "main";
    }

}
