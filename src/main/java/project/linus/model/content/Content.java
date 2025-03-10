package project.linus.model.content;

import javax.persistence.*;

@Entity
@Table(name = "tb_content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContent;

    private String contentTitle;

    private String content;

    private Integer fkDistro;

    private Integer fkLevel;

    private String videoPath;

    public Content() {
    }

    public Content(Integer idContent, String contentTitle, String content, Integer fkDistro, Integer fkLevel) {
        this.idContent = idContent;
        this.contentTitle = contentTitle;
        this.content = content;
        this.fkDistro = fkDistro;
        this.fkLevel = fkLevel;
    }

    public Integer getIdContent() {
        return idContent;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFkDistro() {
        return fkDistro;
    }

    public void setFkDistro(Integer fkDistro) {
        this.fkDistro = fkDistro;
    }

    public Integer getFkLevel() {
        return fkLevel;
    }

    public void setFkLevel(Integer fkLevel) {
        this.fkLevel = fkLevel;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
