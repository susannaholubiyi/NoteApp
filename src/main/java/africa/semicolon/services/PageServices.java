package africa.semicolon.services;

import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.*;

import java.util.List;

public interface PageServices {
    Page createPage(CreatePageRequest createPageRequest);

    Page editPage(EditPageRequest editPageRequest);

    ViewPageResponse viewOneParticularPageWith(ViewPageRequest viewPageRequest);

    DeletePageResponse deletePage(DeletePageRequest deletePageRequest);

    List<Page> viewAllPages();
}
