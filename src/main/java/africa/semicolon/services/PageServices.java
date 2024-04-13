package africa.semicolon.services;

import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.CreatePageResponse;
import africa.semicolon.dtos.CreatePageRequest;

public interface PageServices {
    Page createPage(CreatePageRequest createPageRequest);
}
