package io.github.plindzek.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class LangController {
    private final Logger logger = LoggerFactory.getLogger(LangController.class);
    private LangService langService;

    LangController(LangService langService) {
        this.langService = langService;
    }

    @GetMapping("/langs")
    ResponseEntity<List<LangDTO>> findAllLangs() {
        logger.info("Request for all langs got");

        return ResponseEntity.ok(langService.findAll());
    }
    @GetMapping
    ResponseEntity<String> welcome() {
        return loginUserAndReturnGreeting(null);
    }

    @GetMapping(params={"lang"})

    ResponseEntity<String> loginUserAndReturnGreeting (@RequestParam("lang") Integer langId) {
        logger.info("Request for lang change got");
        return ResponseEntity.status(HttpStatus.OK).body(langService.prepareLogin(langId));
    }
}
