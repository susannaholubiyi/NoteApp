package africa.semicolon.data.repositories;

import africa.semicolon.data.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Notes, String> {
}
