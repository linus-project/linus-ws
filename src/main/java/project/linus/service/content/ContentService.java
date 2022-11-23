package project.linus.service.content;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.content.Content;
import project.linus.model.content.ContentManager;
import project.linus.model.content.UserFavoriteContent;
import project.linus.model.login.AdminLogin;
import project.linus.repository.content.ContentRepository;
import project.linus.repository.content.UserFavoriteContentRepository;
import project.linus.repository.user.UserRepository;
import project.linus.service.login.LoginService;
import project.linus.util.exception.GenericException;
import project.linus.util.generic.ObjectList;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    UserFavoriteContentRepository userFavoriteContentRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    private String exportUrl = "~/Documents/git-projects/linus-ws/target/";

    private final Logger logger = LoggerFactory.logger(ContentService.class);

    public Optional<Content> getContent(Integer idContent) {
        return contentRepository.findById(idContent);
    }

    public List<Content> getContentByTitle(String contentTitle) {
        return contentRepository.findByContentTitleContains(contentTitle);
    }

    public List<Content> getContentByLevel(Integer level) {
        return contentRepository.findByFkLevel(level);
    }

    public List<Content> getContentByFkDistro(Integer fkDistro) {
        return contentRepository.findByFkDistro(fkDistro);
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

            if (isFkDistroValid && isFkLevelValid && loginService.login(admin) != null) {
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

    public List<Content> getFavoriteContentByLevel(Integer idUser, Integer level) {
        List<UserFavoriteContent> userFavoriteContentList = userFavoriteContentRepository.findByContentLevel(level);
        List<Content> contentList = new ArrayList<>();
        for (UserFavoriteContent favoriteContent : userFavoriteContentList) {
            contentList.add(contentRepository.findByIdContent(favoriteContent.getFkContent()));
        }
        return contentList;
    }

    public Content favoriteContent(UserFavoriteContent userFavoriteContent) {
        Content content = contentRepository.findByIdContent(userFavoriteContent.getFkContent());
        boolean login = userRepository.findByIdUser(userFavoriteContent.getFkUser()).isPresent();

        if (content != null && login) {
            boolean isFavorited = userFavoriteContentRepository.findByFkUserAndFkContent(
                    userFavoriteContent.getFkUser(),
                    userFavoriteContent.getFkContent()
            ).isPresent();
            if(isFavorited) {
                UserFavoriteContent userFavoritedContent = userFavoriteContentRepository.findByFkUserAndFkContent(
                        userFavoriteContent.getFkUser(),
                        userFavoriteContent.getFkContent()
                ).get();
                userFavoriteContentRepository.delete(userFavoritedContent);
            } else {
                userFavoriteContent.setContentLevel(content.getFkLevel());
                userFavoriteContentRepository.save(userFavoriteContent);
            }
            return content;
        }
        throw new GenericException();
    }

    public String exportContentCsv(String fileTitle, String contentTitle, Integer listSize) {
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
        return exportUrl + fileTitle;
    }

    public String exportContentTxt(String fileTitle, String contentTitle, Integer listSize) {
        FileWriter file = null;
        Formatter formatter = null;
        fileTitle += ".txt";
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

            String header = "00CONTEUDO20222";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            header += "01";
            formatter.format(header + "\n");

            String body;
            for (index = 0; index < contentList.getSize(); index++) {
                Content content = contentList.getElement(index);
                body = "02";
                body += String.format("%-5.5s", content.getIdContent());
                body += String.format("%-30.30s", content.getContentTitle());
                body += String.format("%-50.50s", content.getContent());
                body += String.format("%02d", content.getFkDistro());
                body += String.format("%02d", content.getFkLevel());
                formatter.format(body + "\n");
            }

            String trailer = "01";
            trailer += String.format("%010d", index);
            formatter.format(trailer + "\n");

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
        return exportUrl + fileTitle;
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
