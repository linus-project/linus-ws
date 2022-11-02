package project.linus.service.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.linus.model.content.Content;
import project.linus.model.content.ContentManager;
import project.linus.util.exception.GenericException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContentTest {

    @Autowired
    ContentService contentService;

    ContentManager contentManager = new ContentManager(
            "admin",
            "admin",
            "admin",
            0,
            "Test content",
            "Creating a test content",
            3,
            1
    );

    @Test
    @DisplayName("Should create a content")
    void shouldCreateContent() {
        Content createdContent = contentService.createContent(contentManager);
        assertEquals(contentManager.getContentTitle(), createdContent.getContentTitle());
        contentManager.setIdContent(createdContent.getIdContent());
        contentService.deleteContent(contentManager);
    }

    @Test
    @DisplayName("Should return exception when try to create a already existent content")
    void shouldReturnExceptionWhenCreateContent() {
        Content createdContent = contentService.createContent(contentManager);
        assertThrows(GenericException.class, () -> contentService.createContent(contentManager));
        contentManager.setIdContent(createdContent.getIdContent());
        contentService.deleteContent(contentManager);
    }

    @Test
    @DisplayName("Should update a created content")
    void shouldUpdateCreatedContent() {
        Content createdContent = contentService.createContent(contentManager);
        contentManager.setIdContent(createdContent.getIdContent());
        contentManager.setContentTitle("Este conteudo foi editado");
        Content editedContent = contentService.editContent(contentManager);
        assertNotEquals(createdContent.getContentTitle(), editedContent.getContentTitle());
        contentService.deleteContent(contentManager);
    }

    @Test
    @DisplayName("Should delete a created content")
    void shouldDeleteCreatedContent() {
        Content createdContent = contentService.createContent(contentManager);
        contentManager.setIdContent(createdContent.getIdContent());
        contentService.deleteContent(contentManager);
        assertEquals(Optional.empty(), contentService.getContent(contentManager.getIdContent()));
    }

}