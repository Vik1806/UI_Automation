package com.swaglabs;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
public class LoginPage {

    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    public void SetUp(){
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }
    //Invalid login tc
    @Test()
    @Description("This is a Invalid Test case")
    public void InvalidLogin() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("abcd");
        driver.findElement(By.id("password")).sendKeys("asdfgh");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        String errormessage = driver.findElement(By.xpath("//h3[@data-test = 'error']")).getText();
        Assert.assertEquals(errormessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    @Description("This is a valid test case")
    public void ValidLogin() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();

    }


}

