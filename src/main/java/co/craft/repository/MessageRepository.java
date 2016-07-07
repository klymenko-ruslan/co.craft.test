package co.craft.repository;

import co.craft.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rklimemnko on 06.07.2016.
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {}