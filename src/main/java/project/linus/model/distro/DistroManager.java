package project.linus.model.distro;

import project.linus.util.manager.Manager;

public class DistroManager extends Manager {

    private final String adminkey;

    private final Long idDistro;

    private final String distroName;

    private final Long fkDistroBase;

    private final String distroVersion;

    private final Long fkLevel;

    public DistroManager(
            String username,
            String password,
            String adminkey,
            Long idDistro,
            String distroName,
            Long fkDistroBase,
            String distroVersion,
            Long fkLevel
    ) {
        super(username, password);
        this.adminkey = adminkey;
        this.idDistro = idDistro;
        this.distroName = distroName;
        this.fkDistroBase = fkDistroBase;
        this.distroVersion = distroVersion;
        this.fkLevel = fkLevel;
    }

    public String getAdminkey() {
        return adminkey;
    }

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
}
