package project.linus.model.commentary;

public class CommentaryResponse {

    private Long idCommentary;

    private String commentaryContent;

    private Long fkContent;

    private Integer fkUser;

    private String username;

    public CommentaryResponse(Long idCommentary, String commentaryContent, Long fkContent, Integer fkUser, String username) {
        this.idCommentary = idCommentary;
        this.commentaryContent = commentaryContent;
        this.fkContent = fkContent;
        this.fkUser = fkUser;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public Long getIdCommentary() {
        return idCommentary;
    }

    public String getCommentaryContent() {
        return commentaryContent;
    }

    public Long getFkContent() {
        return fkContent;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setIdCommentary(Long idCommentary) {
        this.idCommentary = idCommentary;
    }

    public void setCommentaryContent(String commentaryContent) {
        this.commentaryContent = commentaryContent;
    }

    public void setFkContent(Long fkContent) {
        this.fkContent = fkContent;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }
}
