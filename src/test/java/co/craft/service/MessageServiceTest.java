package co.craft.service;

import co.craft.ApplicationInitializer;
import co.craft.model.Message;
import co.craft.repository.MessageRepository;
import co.craft.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationInitializer.class)
@WebAppConfiguration
public class MessageServiceTest {

    @Mock
    MessageRepository messageRepositoryMock = Mockito.mock(MessageRepository.class);


    @Test
    public void testGetMessages() {
        when(messageRepositoryMock.findAll()).thenReturn(TestUtil.generateMessages());

        MessageService messageService = new MessageService(messageRepositoryMock);

        Assert.assertEquals(TestUtil.generateMessages(), messageService.getMessages());
    }

    @Test
    public void testPostMessage() {
        Message generatedMessage = TestUtil.generateMessages().get(0);
        when(messageRepositoryMock.save(any(Message.class))).thenReturn(generatedMessage);

        MessageService messageService = new MessageService(messageRepositoryMock);

        Assert.assertEquals(generatedMessage,
                                      messageService.postMessage(
                                              new Message.MessageBuilder()
                                                         .addId(1)
                                                         .build()));
    }
}
