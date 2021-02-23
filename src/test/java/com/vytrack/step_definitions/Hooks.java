package com.vytrack.step_definitions;

import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before

    public void setUp(){

        System.out.println("this is coming from BEFORE");
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();

    }
    @After

    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte [] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }


        Driver.closeDriver();
    }


    @Before("@db")

    public void setDb(){
        System.out.println("connecting database");
    }
    @After("@db")

    public void closDb(){
        System.out.println("disconnecting database");
    }
}