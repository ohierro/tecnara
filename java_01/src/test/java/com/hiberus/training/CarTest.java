package com.hiberus.training;


import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class CarTest {

    @Test
    public void testMoveUp() throws NotEnoughFuelException {
        Car car = new Car(10, 1);

        car.moveUp(5);

        assertEquals(5f, car.getFuel(), 0);
    }

    @Test
    public void testInvalidMove() {
        Car car = new Car(10, 1);

        try {
            car.moveUp(15);

            fail("Car should throw an exception");
        } catch (NotEnoughFuelException e) {
        }
    }
}
