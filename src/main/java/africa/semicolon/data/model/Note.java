package africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Note {
    private String noteName;
    private String password;
    private String id;
    private boolean isLocked = true;
    private List<Page> pages = new ArrayList<>();
}
