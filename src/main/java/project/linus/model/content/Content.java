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
}
