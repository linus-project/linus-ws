package project.linus.model.commentary;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_commentary")
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommentary;

    private String commentaryContent;

    private Long fkContent;

    private Integer fkUser;

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
