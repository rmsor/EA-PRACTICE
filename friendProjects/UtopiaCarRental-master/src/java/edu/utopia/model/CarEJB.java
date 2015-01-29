/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utopia.model;

import edu.utopia.entities.Car;
import edu.utopia.facades.CarFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fjoseph1313
 */
@Stateless
public class CarEJB {

    @EJB
    private CarFacade carFacade;

    public @NotNull
    Car createCar(@NotNull Car car) {
        this.carFacade.create(car);
        return car;
    }

    public Car findCar(Long id) {
        return this.carFacade.find(id);
    }

    //this method uses facade's find all method

    public List<Car> findAllCars() {
        return this.carFacade.findAll();
    }

    //this method uses facade's named query

    public List<Car> findCars() {
        return this.carFacade.findCarsByNamedQuery();
    }

    public List<Car> findCarsForRental(Long loc, Long id) {
        System.out.println("location:"+loc);
        System.out.println("category id  : "+id);
        return this.carFacade.findCarByLocationAndCategory(loc, id);
    }

    public @NotNull
    Car updateCar(@NotNull Car car) {
        this.carFacade.edit(car);
        return car;
    }

}
