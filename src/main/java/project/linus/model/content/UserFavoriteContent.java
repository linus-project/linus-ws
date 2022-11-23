package project.linus.model.content;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_favorite_content")
public class UserFavoriteContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFavoriteContent;
    private Integer fkUser;
    private Integer fkContent;
    private Integer contentLevel;

    public Integer getIdFavoriteContent() {
        return idFavoriteContent;
    }

    public void setIdFavoriteContent(Integer idFavoriteContent) {
        this.idFavoriteContent = idFavoriteContent;
    }

    public Integer getFkUser() {
        return fkUser;
    }

    public void setFkUser(Integer fkUser) {
        this.fkUser = fkUser;
    }

    public Integer getFkContent() {
        return fkContent;
    }

    public void setFkContent(Integer fkContent) {
        this.fkContent = fkContent;
    }

    public Integer getContentLevel() {
        return contentLevel;
    }

    public void setContentLevel(Integer contentLevel) {
        this.contentLevel = contentLevel;
    }
}
