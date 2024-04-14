package africa.semicolon.services;

import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.EditPageRequest;

import java.util.List;

public interface PageServices {
    Page createPage(CreatePageRequest createPageRequest);

    Page editPage(EditPageRequest editPageRequest);

    List<Page> viewAllPages();
}
