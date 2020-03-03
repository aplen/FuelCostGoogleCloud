package io.github.plindzek.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Adam
 */

//@WebServlet(displayName = "Car Servlet", urlPatterns = {"/api/cars/*"}, name = "Car Servlet")
    @RestController
class CarServlet  {

    private final Logger logger = LoggerFactory.getLogger(CarServlet.class);
    private static final String NAME_PARAM = "name";
    /**
     * define references needed to handle response (eg. service, mapper or
     * repository)
     */
    private CarRepository repository;
    private ObjectMapper mapper;

    CarServlet(CarRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Request got with parameters: " + req.getParameterMap());


        /**
         * what we want to do in response to given request
         */
        resp.setContentType("text/html; charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());

        // resp.getWriter().println(service.prepareGreeting(name, lang));

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        logger.info("Request got with path info  " + pathInfo);
        var newName = req.getParameter(NAME_PARAM);

        try {
            var id = Integer.valueOf(pathInfo.substring(1));
            var car = repository.updateCar(id, newName);
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), car);

        } catch (NumberFormatException e) {
            logger.info("Wrong path: " + pathInfo);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var newCar = mapper.readValue(req.getInputStream(), Car.class);
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.addCar(newCar));
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //to check
        String pathInfo = req.getPathInfo();
        logger.info("Request to delete got with path info  " + pathInfo);
        try {
            var id = Integer.valueOf(pathInfo.substring(1));
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().println(repository.deleteCar(id));
        } catch (NumberFormatException e) {
            logger.info("Wrong path: " + pathInfo);
        }

    }
}
