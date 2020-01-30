package com.stygar.taxi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {


    @RequestMapping("/admin")
    public String adminPage() {        
        return "admin"; 
    }
    
    @RequestMapping("/login")
    public String admin() {        
        return "login"; 
    }
    
    @RequestMapping("/home/taxi")
    public String home() {        
        return "/home/taxi"; 
    }
    
    
    
    

    //Obsluga bledow
    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();
        model.addAttribute("message", message);
        return "error";
    }

}
