package project.linus.service.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.util.content.Content;
import project.linus.repository.content.ContentRepository;
import project.linus.util.exception.ContentException;
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

    public Optional<Content> getContent(Integer idContent) {
        return contentRepository.findById(idContent);
    }

    public List<Content> getContentByTitle(String contentTitle) {
        return contentRepository.findByContentTitleContains(contentTitle);
    }

    public Content createContent(Content content) {
        if (verifyIfContentTitleExists(content)) {
            contentRepository.save(content);
            return content;
        }
        throw new ContentException();
    }

    public String exportContent(String fileTitle, String contentTitle, Integer listSize) {
        FileWriter file = null;
        Formatter formatter = null;
        fileTitle += ".csv";
        ObjectList<Content> contentList = new ObjectList(listSize);

        int index = 0;

        for (Content content : contentRepository.findByContentTitleContains(contentTitle)){
            contentList.add(content);
            if (index == listSize-1) break;
            index++;
        }

        try {
            file = new FileWriter(fileTitle);
            formatter = new Formatter(file);
        } catch (IOException error) {
            System.out.println("[ERROR] - exportContent: " + error);
        }

        try {
            formatter.format("TITULO;CONTEUDO;DISTRO;NIVEL\n");
            for(index = 0; index < contentList.getSize(); index++) {
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
            System.out.println("[ERROR] - exportContent: " + error);
        } finally {
            formatter.close();
            try {
                file.close();
            } catch (IOException error) {
                System.out.println("[ERROR] - exportContent: " + error);
            }
        }
        return "The file " + fileTitle + " has been exported successfully!";
    }

    public boolean verifyIfContentTitleExists(Content content) {
        return contentRepository.findByContentTitle(content.getContentTitle()) == null;
    }

}
