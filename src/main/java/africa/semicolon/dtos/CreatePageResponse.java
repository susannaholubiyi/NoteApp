package africa.semicolon.dtos;

import lombok.Data;

@Data
public class CreatePageResponse {
    private String title;
    private String body;
    private String dateCreated;
    private String pageId;
}
