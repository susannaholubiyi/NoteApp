package africa.semicolon.data.repositories;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PageRepository extends MongoRepository<Page, String> {
}
