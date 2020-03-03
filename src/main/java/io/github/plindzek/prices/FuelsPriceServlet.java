package io.github.plindzek.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
    /**
     * servlet at the localhost:8080/api/prices
     * return actual fuel prices
     * @author Adam
     */

//    @WebServlet(displayName = "Fuels Price Servlet", urlPatterns = {"/api/prices"}, name = "Fuels Price Servlet")
        @RestController
    class FuelsPriceServlet {

        private final Logger logger = LoggerFactory.getLogger(io.github.plindzek.prices.FuelsPriceServlet.class);

        private FuelsPriceRepository repository;
        private ObjectMapper mapper;

             FuelsPriceServlet(FuelsPriceRepository repository, ObjectMapper mapper) {
            this.repository = repository;
            this.mapper = mapper;
        }

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            logger.info("Request got with parameters: " + req.getParameterMap());
            resp.setContentType("application/json; charset=utf-8");

            mapper.writeValue(resp.getOutputStream(), repository.getPrices());

        }
    }

