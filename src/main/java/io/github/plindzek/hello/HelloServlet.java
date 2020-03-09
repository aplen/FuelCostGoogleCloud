package io.github.plindzek.hello;

import io.github.plindzek.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
class HelloServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    HelloServlet(HelloService service) {
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