package co.craft.controller;

import co.craft.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rklimemnko on 06.07.2016.
 */
@Controller
public class MainController {

    public static final String URL_MAIN = "/";
    public static final String PAGE_MAIN = "main";
    public static final String ATTRIBUTE_MESSAGES = "messages";

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    private MessageService messageService;

    @RequestMapping(URL_MAIN)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(PAGE_MAIN);
        modelAndView.addObject(ATTRIBUTE_MESSAGES, messageService.getMessages());
        return modelAndView;
    }
}
