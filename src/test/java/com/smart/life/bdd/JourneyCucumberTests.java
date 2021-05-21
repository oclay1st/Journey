package com.smart.life.bdd;

import com.smart.life.JourneyApplication;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
@SpringBootTest
@ContextConfiguration(classes = JourneyApplication.class)
public class JourneyCucumberTests {
}
