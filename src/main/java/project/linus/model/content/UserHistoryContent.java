package project.linus.model.content;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_content_history")
@Data
public class UserHistoryContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistory;
    private Integer fkUser;
    private Integer fkContent;
    private Integer contentLevel;
}
