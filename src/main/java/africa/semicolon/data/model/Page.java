package africa.semicolon.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class Page {
    private String id;
    private String title;
    private String body;
    private LocalDate dateCreated = LocalDate.now();

}
