package com.client.rest.clientrest.controller;

import com.client.rest.clientrest.model.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/restClient/cars")
public class RestClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cars.url}")
    private String url;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return restTemplate.postForEntity(url, car, Car.class).getBody();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable int id) {
        return restTemplate.getForObject(url + "/" + id, Car.class);
    }

    @GetMapping
    public String getCars(Model model) {
        List<Car> cars = restTemplate.getForObject(url, List.class);
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/showUpdatePage/{carId}")
    public String showCarUpdate(@PathVariable("carId") int carId, Model model) {
        model.addAttribute("car", restTemplate.getForObject(url + "/" + carId, Car.class));
        return "update";
    }

    @PostMapping("/{carId}")
    public String updateCar(@ModelAttribute(name = "car") @Valid Car car, @PathVariable int carId, Model model) {

        restTemplate.put(url, car);

        return getCars(model);
    }
}
