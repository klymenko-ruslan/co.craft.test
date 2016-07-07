package co.craft.controller;

import co.craft.ApplicationInitializer;
import co.craft.service.MessageService;
import co.craft.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationInitializer.class)
@WebAppConfiguration
public class MainControllerTest {

	private MockMvc mockMvc;

	@Mock
	private MessageService messageServiceMock = Mockito.mock(MessageService.class);

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new MainController(messageServiceMock))
								 .build();
	}

	@Test
	public void testIndex() throws Exception {
		when(messageServiceMock.getMessages()).thenReturn(TestUtil.generateMessages());

		mockMvc.perform(get(MainController.URL_MAIN))
				.andExpect(status().isOk())
				.andExpect(view().name(MainController.PAGE_MAIN))
				.andExpect(forwardedUrl(MainController.PAGE_MAIN))
				.andExpect(model().attribute(MainController.ATTRIBUTE_MESSAGES, hasSize(2)))
				.andExpect(model().attribute(MainController.ATTRIBUTE_MESSAGES, hasItem(
						allOf(
								hasProperty("id", is(1L)),
								hasProperty("userId", is(1L)),
								hasProperty("title", is("test1")),
								hasProperty("body", is("test1"))
						)
				)))
				.andExpect(model().attribute(MainController.ATTRIBUTE_MESSAGES, hasItem(
						allOf(
								hasProperty("id", is(2L)),
								hasProperty("userId", is(2L)),
								hasProperty("title", is("test2")),
								hasProperty("body", is("test2"))
						)
				)));

		verify(messageServiceMock, times(1)).getMessages();
		verifyNoMoreInteractions(messageServiceMock);
	}


}
