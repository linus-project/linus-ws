package project.linus.service.distro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.distro.Distro;
import project.linus.model.distro.DistroManager;
import project.linus.model.login.AdminLogin;
import project.linus.repository.distro.DistroRepository;
import project.linus.service.login.LoginService;
import project.linus.util.exception.GenericException;

import java.util.List;
import java.util.Optional;

@Service
public class DistroService {

    @Autowired
    DistroRepository distroRepository;

    @Autowired
    LoginService loginService;

    public List<Distro> getDistro() {
        return distroRepository.findAll();
    }

    public List<Distro> getDistroByLevel(Long level) {
        return distroRepository.findByFkLevel(level);
    }

    public Distro createDistro(DistroManager distroManager) {
        Distro distro = distroRepository.findByDistroName(distroManager.getDistroName());

        AdminLogin admin = new AdminLogin(
                distroManager.getUsername(),
                distroManager.getPassword(),
                distroManager.getAdminkey());

        Boolean isDistroNameValid = distroManager.getDistroName() != null && !distroManager.getDistroName().equals("");
        Boolean isDistroVersionValid = distroManager.getDistroVersion() != null && !distroManager.getDistroVersion().equals("");
        Boolean isFkDistroValid = distroManager.getFkLevel() != null && distroManager.getFkLevel() != 0;
        Boolean isFkDistroBaseValid = distroManager.getFkDistroBase() != 0;

        if (distro != null && loginService.login(admin) != null) {
            if (isDistroNameValid && isDistroVersionValid && isFkDistroValid && isFkDistroBaseValid) {
                distro.setDistroName(distroManager.getDistroName());
                distro.setDistroVersion(distroManager.getDistroVersion());
                distro.setFkDistroBase(distroManager.getFkDistroBase());
                distro.setFkLevel(distro.getFkLevel());
                distroRepository.save(distro);
                return distro;
            }
        }

        throw new GenericException();
    }

    public Distro deleteDistro(DistroManager distroManager) {
        Optional<Distro> distro = distroRepository.findById(distroManager.getIdDistro());
        AdminLogin admin = new AdminLogin(
                distroManager.getUsername(),
                distroManager.getPassword(),
                distroManager.getAdminkey());
        if (distro.isPresent() && loginService.login(admin) != null) {
            distroRepository.delete(distro.get());
            return distro.get();
        }
        throw new GenericException();
    }

    public boolean verifyIfDistroExists(String distroName) {
        return distroRepository.findByDistroNameContains(distroName) != null;
    }

}
