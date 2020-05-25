package io.github.plindzek.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/lang")
    Lang changeLang(@PathVariable Integer langId){
        return langService.findById(langId);
    }

    @GetMapping
    ResponseEntity<String> welcome() {
        return returnGreeting(null);
    }

    @GetMapping(params={"lang"})
    ResponseEntity<String> returnGreeting(@RequestParam("lang") Integer langId) {
        logger.info("Request for lang change got");
        return ResponseEntity.status(HttpStatus.OK).body(langService.prepareLogin(langId));
    }
}