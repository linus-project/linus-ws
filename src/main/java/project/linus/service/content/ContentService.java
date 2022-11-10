package project.linus.service.content;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.content.Content;
import project.linus.model.content.ContentManager;
import project.linus.model.login.AdminLogin;
import project.linus.repository.content.ContentRepository;
import project.linus.service.login.LoginService;
import project.linus.util.exception.GenericException;
import project.linus.util.generic.ObjectList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    LoginService loginService;

    private final Logger logger = LoggerFactory.logger(ContentService.class);

    public Optional<Content> getContent(Integer idContent) {
        return contentRepository.findById(idContent);
    }

    public List<Content> getContentByTitle(String contentTitle) {
        if (contentTitle == null || contentTitle.equals("")){
            return contentRepository.findAll();
        }
        return contentRepository.findByContentTitleContains(contentTitle);
    }

    public List<Content> getContentByLevel(Integer level) {
        return contentRepository.findByFkLevel(level);
    }

    public Content createContent(ContentManager contentManager) {
        Content content = new Content();

        AdminLogin admin = new AdminLogin(
                contentManager.getUsername(),
                contentManager.getPassword(),
                contentManager.getAdminkey());

        if (!verifyIfContentTitleExists(contentManager.getContentTitle())) {
            boolean isFkDistroValid = (contentManager.getFkDistro() == null || contentManager.getFkDistro() >= 1);
            boolean isFkLevelValid = (contentManager.getFkLevel() >= 1 && contentManager.getFkLevel() <= 3);

            if (isFkDistroValid && isFkLevelValid && loginService.login(admin) != null){
                content.setContentTitle(contentManager.getContentTitle());
                content.setContent(contentManager.getContentTitle());
                content.setFkDistro(contentManager.getFkDistro());
                content.setFkLevel(contentManager.getFkLevel());
                contentRepository.save(content);
                return content;
            }
        }
        throw new GenericException();
    }

    public Content editContent(ContentManager contentManager) {
        Content content = contentRepository.findByIdContent(contentManager.getIdContent());

        AdminLogin admin = new AdminLogin(
                contentManager.getUsername(),
                contentManager.getPassword(),
                contentManager.getAdminkey());

        if (content != null && loginService.login(admin) != null) {
            if (contentManager.getContentTitle() != null ||
                    !contentManager.getContentTitle().equals("")) {
                content.setContentTitle(contentManager.getContentTitle());
            }
            if (contentManager.getContent() != null ||
                    !contentManager.getContent().equals("")
            ) {
                content.setContent(contentManager.getContent());
            }
            if (contentManager.getFkDistro() != 0) {
                content.setFkDistro(contentManager.getFkDistro());
            }
            if (contentManager.getFkLevel() != null ||
                    contentManager.getFkLevel() != 0) {
                content.setFkLevel(contentManager.getFkLevel());
            }
            contentRepository.save(content);
            return content;
        }
        throw new GenericException();
    }

    public Content deleteContent(ContentManager contentManager) {
        Content content = contentRepository.findByIdContent(contentManager.getIdContent());

        AdminLogin admin = new AdminLogin(
                contentManager.getUsername(),
                contentManager.getPassword(),
                contentManager.getAdminkey());

        if (content != null && loginService.login(admin) != null) {
            contentRepository.delete(content);
            return content;
        }
        throw new GenericException();
    }

    public ObjectList<Content> exportContent(String fileTitle, String contentTitle, Integer listSize) {
        FileWriter file = null;
        Formatter formatter = null;
        fileTitle += ".csv";
        ObjectList<Content> contentList = new ObjectList(listSize);

        int index = 0;

        for (Content content : contentRepository.findByContentTitleContains(contentTitle)) {
            contentList.add(content);
            if (index == listSize - 1) break;
            index++;
        }

        try {
            file = new FileWriter(fileTitle);
            formatter = new Formatter(file);
        } catch (IOException error) {
            logger.info("[ERROR] - exportContent: " + error);
        }

        try {
            contentList = bubbleSortByLevel(contentList);
            formatter.format("TITULO;CONTEUDO;DISTRO;NIVEL\n");
            for (index = 0; index < contentList.getSize(); index++) {
                Content content = contentList.getElement(index);
                formatter.format(
                        "%s;%s;%s;%s\n",
                        content.getContentTitle(),
                        content.getContent(),
                        content.getFkDistro(),
                        content.getFkLevel()
                );
            }
        } catch (FormatterClosedException error) {
            logger.info("[ERROR] - exportContent: " + error);
        } finally {
            formatter.close();
            try {
                file.close();
            } catch (IOException error) {
                logger.info("[ERROR] - exportContent: " + error);
            }
        }
        logger.info("The file " + fileTitle + " has been exported successfully!");
        return contentList;
    }

    public boolean verifyIfContentTitleExists(String contentTitle) {
        return contentRepository.findByContentTitle(contentTitle) != null;
    }

    public ObjectList<Content> bubbleSortByLevel(ObjectList<Content> contentList) {
        for (int firstIndex = 0; firstIndex < contentList.getSize(); firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < contentList.getSize(); secondIndex++) {
                if (
                        contentList.getElement(firstIndex).getFkLevel() >
                                contentList.getElement(secondIndex).getFkLevel()
                ) {
                    Content contentAux = contentList.getElement(firstIndex);
                    contentList.setElement(contentList.getElement(secondIndex), firstIndex);
                    contentList.setElement(contentAux, secondIndex);
                }
            }
        }
        return contentList;
    }

}
