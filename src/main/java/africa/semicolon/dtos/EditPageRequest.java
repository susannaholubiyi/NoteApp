package africa.semicolon.dtos;

import lombok.Data;

@Data
public class EditPageRequest {
    private String noteName;
    private String pageId;
    private String newTitle;
    private String newBody;

}
