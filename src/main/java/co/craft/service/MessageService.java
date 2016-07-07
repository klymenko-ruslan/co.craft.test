package co.craft.service;

import co.craft.model.Message;
import co.craft.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rklimemnko on 06.07.2016.
 */
@Service
public class MessageService {

    public static final String URI = "https://jsonplaceholder.typicode.com/posts/";

    private static HttpEntity<String> requestEntity;
    static {
        HttpHeaders headers = new HttpHeaders();
        final String CHROME_USER_AGENT = "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0";
        headers.set("User-Agent",CHROME_USER_AGENT);
        requestEntity = new HttpEntity<>(headers);
    }

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Collection<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message postMessage(Message message) {
        return messageRepository.save(getThirdPartyApiMessage(message));
    }

    private Message getThirdPartyApiMessage(Message message) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(URI + message.getId(),
                                     HttpMethod.GET,
                                     requestEntity,
                                     Message.class)
                           .getBody();
    }
}
