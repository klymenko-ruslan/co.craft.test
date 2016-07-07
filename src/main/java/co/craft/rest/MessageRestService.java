package co.craft.rest;

import co.craft.model.Message;
import co.craft.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

/**
 * Created by rklimemnko on 06.07.2016.
 */
@RestController
public class MessageRestService {

    public static final String URL_SERVICE = "message/";
    public static final String URL_GET_ALL = URL_SERVICE + "getAll";
    public static final String URL_POST = URL_SERVICE + "post";

    private MessageService messageService;

    @Autowired
    public MessageRestService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = URL_GET_ALL, method = RequestMethod.GET)
    public HttpEntity<Collection<Message>> getMessages() {
        return new HttpEntity<>(messageService.getMessages());
    }

    @RequestMapping(value = URL_POST, method = RequestMethod.POST)
    public HttpEntity<Message> postMessage(@RequestBody Message message) {
        return new HttpEntity<>(messageService.postMessage(message));
    }
}
