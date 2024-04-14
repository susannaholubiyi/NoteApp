package africa.semicolon.services;

import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.EditPageRequest;
import africa.semicolon.dtos.ViewPageRequest;
import africa.semicolon.dtos.ViewPageResponse;

import java.util.List;

public interface PageServices {
    Page createPage(CreatePageRequest createPageRequest);

    Page editPage(EditPageRequest editPageRequest);

    ViewPageResponse viewOneParticularPageWith(ViewPageRequest viewPageRequest);

    List<Page> viewAllPages();
}
