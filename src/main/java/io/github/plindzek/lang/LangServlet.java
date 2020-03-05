package io.github.plindzek.lang;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class LangServlet {

    private LangService service;

    LangServlet(LangService service) {
        this.service = service;
    }

    @GetMapping("/langs")
    ResponseEntity<List<LangDTO>> findAllLangs() {
        return ResponseEntity.ok(service.findAll());
    }
}
