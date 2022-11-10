package project.linus.model.distro;

import javax.persistence.*;

@Entity
@Table(name = "tb_distro")
public class Distro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistro;

    private String distroName;

    private Long fkDistroBase;

    private String distroVersion;

    private Long fkLevel;

    public Long getIdDistro() {
        return idDistro;
    }

    public String getDistroName() {
        return distroName;
    }

    public Long getFkDistroBase() {
        return fkDistroBase;
    }

    public String getDistroVersion() {
        return distroVersion;
    }

    public Long getFkLevel() {
        return fkLevel;
    }

    public void setIdDistro(Long idDistro) {
        this.idDistro = idDistro;
    }

    public void setDistroName(String distroName) {
        this.distroName = distroName;
    }

    public void setFkDistroBase(Long fkDistroBase) {
        this.fkDistroBase = fkDistroBase;
    }

    public void setDistroVersion(String distroVersion) {
        this.distroVersion = distroVersion;
    }

    public void setFkLevel(Long fkLevel) {
        this.fkLevel = fkLevel;
    }
}
