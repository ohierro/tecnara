package com.hiberus.training;

/**
 * Created by ohierro on 4/24/18.
 */
public class Car {
    private float fuel;
    private float consumption;

    public Car(int initialFuel, float fuelConsumption) {
        this.fuel = initialFuel;
        this.consumption = fuelConsumption;
    }

    public void moveUp(int units) throws NotEnoughFuelException {

        if (fuel - (units * consumption) < 0) {
            throw new NotEnoughFuelException();
        }

        fuel -= units * consumption;
    }

    public float getFuel() {
        return fuel;
    }
}
