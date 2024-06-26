package africa.semicolon.data.repositories;

import africa.semicolon.data.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {
    boolean existsByNoteName(String username);

    Optional<Note> findByNoteName(String strip);
}
