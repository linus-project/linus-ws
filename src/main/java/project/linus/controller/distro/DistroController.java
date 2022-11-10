package project.linus.controller.distro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.distro.Distro;
import project.linus.model.distro.DistroManager;
import project.linus.service.distro.DistroService;

import java.util.List;

@RestController
@RequestMapping("/distro")
public class DistroController {

    @Autowired
    DistroService distroService;

    @GetMapping
    public ResponseEntity<List<Distro>> getDistro() {
        return ResponseEntity.ok(distroService.getDistro());
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Distro>> getDistroByLevel(@PathVariable Long level) {
        return ResponseEntity.ok(distroService.getDistroByLevel(level));
    }

    @PostMapping
    public ResponseEntity<Distro> createDistro(DistroManager distroManager) {
        return ResponseEntity.ok(distroService.createDistro(distroManager));
    }

    @DeleteMapping
    public ResponseEntity<Distro> deleteDistro(DistroManager distroManager) {
        return ResponseEntity.ok(distroService.deleteDistro(distroManager));
    }

}
