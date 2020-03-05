package io.github.plindzek.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * servlet at the localhost:8080/api/prices
 * return actual fuel prices
 * @author Adam
 */

@WebServlet(displayName = "Fuels Price Servlet", urlPatterns = {"/api/prices"}, name = "Fuels Price Servlet")
public class FuelsPriceServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(io.github.plindzek.prices.FuelsPriceServlet.class);

    private FuelsPriceService service;
    private ObjectMapper mapper;

    /*
     * servlet container needs it
     */
    public FuelsPriceServlet() {
        this(new FuelsPriceService(), new ObjectMapper());
    }

    FuelsPriceServlet(FuelsPriceService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Request got with parameters: " + req.getParameterMap());
        resp.setContentType("application/json; charset=utf-8");

        mapper.writeValue(resp.getOutputStream(), service.getPrices());

    }
}

