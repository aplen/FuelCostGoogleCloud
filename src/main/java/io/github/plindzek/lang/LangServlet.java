package io.github.plindzek.lang;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * servlet at the localhost:8080/api/langs method doGet runs automatic by Jetty. It
 * use LangService class to give response
 *
 * @author Adam
 */

// @WebServlet(displayName = "Lang Servlet", urlPatterns = {"/api/langs/*"}, name = "Lang Servlet")
@RestController
@RequestMapping("/api")
class LangServlet {

    /**
     * define references needed to handle response (eg. service, mapper or
     * repository)
     * mapper is build in Spring, we dont need it anymore
     */
    private LangService service;

    LangServlet(LangService service) {
        this.service = service;
    }

    //we dont need methods doGet etc

    @GetMapping("/langs")
    ResponseEntity<List<LangDTO>> findAllLangs() {

//resp.setContentType("text/html; charset=utf-8");
//resp.setContentType("application/json; charset=utf-8");
//mapper.writeValue(resp.getOutputStream(), service.findAll());
return ResponseEntity.ok(service.findAll());
}
}
