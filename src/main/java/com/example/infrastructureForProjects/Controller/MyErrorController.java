package com.example.infrastructureForProjects.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/pageNotFound", method = { RequestMethod.GET, RequestMethod.POST })
    public String pageNotFound() {
        return "pageNotFound";
    }

    @RequestMapping(value = "/error")
    public String error() { return "pageNotFound"; }

    @Override
    public String getErrorPath() {
        return null;
    }
}

