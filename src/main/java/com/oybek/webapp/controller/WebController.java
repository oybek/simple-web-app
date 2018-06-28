package com.oybek.webapp.controller;

import com.oybek.webapp.entities.Readout;
import com.oybek.webapp.service.interfaces.ReadoutService;
import com.oybek.webapp.service.interfaces.TypeService;
import com.oybek.webapp.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class WebController {

    // Представляет из себя сервис для получения и сохранения информации по записям показаний приборов пользователя
    private ReadoutService readoutService;

    // Представляет информацию о типах устройств по которым можно снимать показания
    private TypeService typeService;

    // Предоставляет информацию по пользователям в системе
    private UserService userService;

    public WebController(UserService userService, ReadoutService readoutService, TypeService typeService) {
        this.userService = userService;
        this.readoutService = readoutService;
        this.typeService = typeService;
    }

    // Главная страница
    @RequestMapping(value="/")
    public String home(){
        return "home";
    }

    // Страница админа
    @RequestMapping(value="/admin")
    public ModelAndView admin() {
        // TODO: getall
        List<Readout> list = null; //readoutService.findByUser();
        return new ModelAndView("admin","readouts", list);
    }

    // Страница для авторизации
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    // В случае ошибок перенаправляем пользователя сюда
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }

    // Страница пользователя, доступна только пользователю
    @RequestMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("readout", new Readout());
        model.addAttribute("readouts", readoutService.findByUser(userService.findByLogin(principal.getName())));
        model.addAttribute("devTypes", typeService.findAll());
        return "user";
    }

    // Запрос для сохранения показаний
    @PostMapping("/readout")
    public RedirectView submitReadout(@ModelAttribute Readout readout, Principal principal) {
        // add to readout the authorized user's name
        readout.setUserlogin(userService.findByLogin(principal.getName()));
        // save readout to db
        readoutService.save(readout);
        return new RedirectView("user");
    }
}
