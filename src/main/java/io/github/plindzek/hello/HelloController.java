package io.github.plindzek.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private HelloService service;

    HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/api")
    String welcome() {
        return welcome(null, null);
    }

    @GetMapping(value="/api", params={"lang", "name"})
    String welcome(@RequestParam("lang") Integer langId, @RequestParam("name") String name) {
        logger.info("Request for welcome message got");
        return service.prepareGreeting(name, langId);
    }
}