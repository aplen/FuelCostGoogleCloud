package io.github.plindzek.hello;

import java.io.IOException;
import java.util.Optional;
import java.util.OptionalInt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * servlet at the localhost:8080/api/ method doGet runs automatic by Jetty. It
 * use MyService class to give response
 *
 * @author Adam
 */

//@WebServlet(displayName = "Nazwa z pola displayName HelloServlet", urlPatterns = {"/api"}, name = "Hello //Servlet")
@RestController
class HelloServlet {

    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    // define parameters expected in request
    private static final String NAME_PARAM = "name";
    private static final String LANG_PARAM = "lang";

    /**
     * define dependencies needed to handle response (eg. service, mapper or
     * repository)
     */
    private HelloService service;

    HelloServlet(HelloService service) {
        this.service = service;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got with parameters: " + req.getParameterMap());
        var name = req.getParameter(NAME_PARAM);
        var lang = req.getParameter(LANG_PARAM);
        Integer langId=null;

        try {
            langId = Integer.valueOf(lang);
        } catch (NumberFormatException e) {

            logger.info("Non-numeric language id used: " + lang);
        }
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().println(service.prepareGreeting(name, langId));


    }
}