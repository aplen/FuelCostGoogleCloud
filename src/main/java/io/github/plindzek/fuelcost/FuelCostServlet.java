package io.github.plindzek.fuelcost;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.plindzek.car.CarRepository;
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

//@WebServlet(displayName = "FuelCost Servlet", urlPatterns = {"/api/fuelcost/*"}, name = "FuelCost Servlet")
    @RestController
class FuelCostServlet {

    private final Logger logger = LoggerFactory.getLogger(io.github.plindzek.fuelcost.FuelCostServlet.class);
    private FuelCostService fuelCostService;
    private CarRepository repository;
    private ObjectMapper mapper;

    FuelCostServlet(FuelCostService fuelCostService, CarRepository repository, ObjectMapper mapper) {
        this.fuelCostService = fuelCostService;
        this.repository = repository;
        this.mapper=mapper;

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var trip = mapper.readValue(req.getInputStream(), Trip.class);

        String pathInfo = req.getPathInfo();
        logger.info("Request got with path info  " + pathInfo);

        try {

            var id = Integer.valueOf(pathInfo.substring(1));
            var car = repository.findById(id).orElse(null);

            double wynik = fuelCostService.calcCost(car, trip);
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().write((Double.toString(wynik)));

        } catch (NumberFormatException | NullPointerException e) {
            logger.info("Wrong path: " + pathInfo);
        }

    }
}
