package co.craft.util;

import co.craft.model.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rklimemnko on 07.07.2016.
 */
public class TestUtil {

    public static List<Message> generateMessages() {
        return Arrays.asList(new Message.MessageBuilder()
                        .addId(1)
                        .addUserId(1)
                        .addTitle("test1")
                        .addBody("test1")
                        .build(),
                new Message.MessageBuilder()
                        .addId(2)
                        .addUserId(2)
                        .addTitle("test2")
                        .addBody("test2")
                        .build());
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
