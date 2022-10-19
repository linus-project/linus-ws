package project.linus.model.content;

import project.linus.util.manager.Manager;

public class ContentManager extends Manager {
    private String adminkey;
    private Integer idContent;
    private String contentTitle;
    private String content;
    private Integer fkLevel;
    private Integer fkDistro;

    public ContentManager(String username, String password, String adminkey, Integer idContent, String contentTitle, String content, Integer fkLevel, Integer fkDistro) {
        super(username, password);
        this.adminkey = adminkey;
        this.idContent = idContent;
        this.contentTitle = contentTitle;
        this.content = content;
        this.fkLevel = fkLevel;
        this.fkDistro = fkDistro;
    }

    public String getAdminkey() {
        return adminkey;
    }

    public void setAdminkey(String adminkey) {
        this.adminkey = adminkey;
    }

    public Integer getIdContent() {
        return idContent;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
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

    public Integer getFkLevel() {
        return fkLevel;
    }

    public void setFkLevel(Integer fkLevel) {
        this.fkLevel = fkLevel;
    }

    public Integer getFkDistro() {
        return fkDistro;
    }

    public void setFkDistro(Integer fkDistro) {
        this.fkDistro = fkDistro;
    }
}
