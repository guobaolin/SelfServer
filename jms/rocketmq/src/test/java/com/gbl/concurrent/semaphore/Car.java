package com.gbl.concurrent.semaphore;

/**
 * Created by guobaolin on 2018/10/23.
 */
public class Car extends Thread {

    private Driver driver;

    public Car(Driver driver){
        this.driver = driver;
    }

    @Override
    public void run(){
        driver.driveCar();
    }

}
