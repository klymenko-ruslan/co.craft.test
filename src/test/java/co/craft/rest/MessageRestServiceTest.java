package co.craft.rest;

import co.craft.ApplicationInitializer;
import co.craft.model.Message;
import co.craft.service.MessageService;
import co.craft.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.nio.charset.Charset;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationInitializer.class)
@WebAppConfiguration
public class MessageRestServiceTest {

	private MockMvc mockMvc;

	@Mock
	private MessageService messageServiceMock = Mockito.mock(MessageService.class);

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new MessageRestService(messageServiceMock))
								 .build();
	}

	@Test
	public void testGetAll() throws Exception {
		List<Message> generatedMessages = TestUtil.generateMessages();
		when(messageServiceMock.getMessages()).thenReturn(generatedMessages);

		mockMvc.perform(get("/" + MessageRestService.URL_GET_ALL))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
						MediaType.APPLICATION_JSON.getSubtype(),
						Charset.forName("utf8"))))
				.andExpect(jsonPath("$", hasSize(generatedMessages.size())))
				.andExpect(jsonPath("$[0].id", is(Math.toIntExact(generatedMessages.get(0).getId()))))
				.andExpect(jsonPath("$[0].userId", is(Math.toIntExact((int) generatedMessages.get(0).getUserId()))))
				.andExpect(jsonPath("$[0].title", is(generatedMessages.get(0).getTitle())))
				.andExpect(jsonPath("$[0].body", is(generatedMessages.get(0).getBody())))
				.andExpect(jsonPath("$[1].id", is(Math.toIntExact((int) generatedMessages.get(1).getId()))))
				.andExpect(jsonPath("$[1].userId", is(Math.toIntExact((int) generatedMessages.get(1).getUserId()))))
				.andExpect(jsonPath("$[1].title", is(generatedMessages.get(1).getTitle())))
				.andExpect(jsonPath("$[1].body", is(generatedMessages.get(1).getBody())));

		verify(messageServiceMock, times(1)).getMessages();
		verifyNoMoreInteractions(messageServiceMock);
	}

	@Test
	public void testPost() throws Exception {
		Message generatedMessage = TestUtil.generateMessages().get(0);
		when(messageServiceMock.postMessage(any(Message.class))).thenReturn(generatedMessage);

		mockMvc.perform(post("/" + MessageRestService.URL_POST)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(generatedMessage)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
						MediaType.APPLICATION_JSON.getSubtype(),
						Charset.forName("utf8"))))
				.andExpect(jsonPath("$.id", is((int)generatedMessage.getId())))
				.andExpect(jsonPath("$.userId", is((int)generatedMessage.getUserId())))
				.andExpect(jsonPath("$.title", is(generatedMessage.getTitle())))
				.andExpect(jsonPath("$.body", is(generatedMessage.getBody())));

		verify(messageServiceMock, times(1)).postMessage(any(Message.class));
		verifyNoMoreInteractions(messageServiceMock);
	}

}
