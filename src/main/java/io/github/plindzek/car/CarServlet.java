package io.github.plindzek.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Adam
 */

//@WebServlet(displayName = "Car Servlet", urlPatterns = {"/api/cars/*"}, name = "Car Servlet")
@RestController
@RequestMapping("/api")
class CarServlet {

    private final Logger logger = LoggerFactory.getLogger(CarServlet.class);

    /**
     * define references needed to handle response (eg. service, mapper or
     * repository)
     */
    private CarRepository repository;

    CarServlet(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars")
    ResponseEntity<List<Car>> findAllCars() {
        logger.info("Request got");
        return ResponseEntity.ok(repository.findAll());
    }



    @GetMapping("/cars/*")
        ResponseEntity<Car> addCar(){
        //var newCar = mapper.readValue(req.getInputStream(), Car.class);

      //  resp.setContentType("application/json;charset=UTF-8");
        return ResponseEntity.ok(repository.addCar(newCar));

    }

   // protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, //IOException {
        //to check
   //     String pathInfo = req.getPathInfo();
     //   logger.info("Request to delete got with path info  " + pathInfo);
       // try {
         //   var id = Integer.valueOf(pathInfo.substring(1));
           // resp.setContentType("text/html; charset=utf-8");
            //r/esp.getWriter().println(repository.deleteCar(id));
        //} catch (NumberFormatException e) {
         //   logger.info("Wrong path: " + pathInfo);
        //}
   // }
}

//NOT USED
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        String pathInfo = req.getPathInfo();
//        logger.info("Request got with path info  " + pathInfo);
//        var newName = req.getParameter(NAME_PARAM);
//
//        try {
//            var id = Integer.valueOf(pathInfo.substring(1));
//            var car = repository.updateCar(id, newName);
//            resp.setContentType("application/json;charset=UTF-8");
//            mapper.writeValue(resp.getOutputStream(), car);
//        } catch (NumberFormatException e) {
//            logger.info("Wrong path: " + pathInfo);
//        }
//    }

