package com.oybek.webapp.controller;

import com.oybek.webapp.ReadoutsDao;
import com.oybek.webapp.entities.Readout;
import com.oybek.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    ReadoutsDao readoutsDao;

    @Autowired
    UserService userService;
   
    @RequestMapping(value="/")
    public String home(){
        return "home";
    }
   
    @RequestMapping(value="/admin")
    public ModelAndView admin() {
        List<Readout> list = readoutsDao.getReadouts();
        return new ModelAndView("admin","readouts", list);
    }
   
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }
   
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }

    /* It provides list of employees in model object */
    @RequestMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("readout", new Readout());
        model.addAttribute("readouts", readoutsDao.getReadouts(principal.getName()));
        model.addAttribute("devTypes", readoutsDao.getAvailabeTypes());
        return "user";
    }

    @PostMapping("/readout")
    public RedirectView submitReadout(@ModelAttribute Readout readout, Principal principal) {
        // add to readout the authorized user's name
        readout.setUserlogin(userService.findByLogin(principal.getName()));
        // save readout to db
        readoutsDao.save(readout);
        return new RedirectView("user");
    }
}
