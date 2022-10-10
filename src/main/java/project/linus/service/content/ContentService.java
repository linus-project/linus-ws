package project.linus.service.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.content.Content;
import project.linus.repository.content.ContentRepository;
import project.linus.util.exception.ContentException;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    public Optional<Content> getContent(Integer idContent){
        return contentRepository.findById(idContent);
    }

    public List<Content> getContentByTitle(String contentTitle){
        return contentRepository.findByContentTitleContains(contentTitle);
    }

    public Content createContent(Content content){
        if(verifyIfContentTitleExists(content)){
            contentRepository.save(content);
            return content;
        }
        throw new ContentException();
    }

    public boolean verifyIfContentTitleExists(Content content){
        return contentRepository.findByContentTitle(content.getContentTitle()) == null;
    }

}
