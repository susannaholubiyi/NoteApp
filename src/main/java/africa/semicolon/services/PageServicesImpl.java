package africa.semicolon.services;

import africa.semicolon.data.model.Page;
import africa.semicolon.data.repositories.PageRepository;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static africa.semicolon.utils.Helpers.validateIfEmpty;
import static africa.semicolon.utils.Helpers.validateIfNull;

@Service
public class PageServicesImpl implements PageServices {
    @Autowired
    private PageRepository pageRepository;

    @Override
    public Page createPage(CreatePageRequest createPageRequest) {
        validateIfEmpty(createPageRequest.getTitle());
        validateIfEmpty(createPageRequest.getBody());
        validateIfEmpty(createPageRequest.getNoteName());
        validateIfNull(createPageRequest.getNoteName());
        validateIfNull(createPageRequest.getTitle());
        validateIfNull(createPageRequest.getBody());
        Page newPage = Mappers.mapCreatePage(createPageRequest);
        pageRepository.save(newPage);

        return newPage;
    }

}
