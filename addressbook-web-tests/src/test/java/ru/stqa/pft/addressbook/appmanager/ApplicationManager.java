package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    protected WebDriver wd;
    private ContactHelper contactHelper;
    private JavascriptExecutor js;

    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        js = (JavascriptExecutor) wd;
        contactHelper = new ContactHelper(wd);
        login("admin", "secret");
    }

    private void login(String username, String password) {
        contactHelper.groupHelper.wd.get("http://localhost/addressbook/addressbook/");
        contactHelper.groupHelper.wd.findElement(By.name("user")).click();
        contactHelper.groupHelper.wd.findElement(By.name("user")).clear();
        contactHelper.groupHelper.wd.findElement(By.name("user")).sendKeys(username);
        contactHelper.groupHelper.wd.findElement(By.name("pass")).click();
        contactHelper.groupHelper.wd.findElement(By.name("pass")).clear();
        contactHelper.groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
        contactHelper.groupHelper.wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void goToGroupPage() {
        contactHelper.groupHelper.wd.findElement(By.linkText("groups")).click();
    }

    public void stop() {
        contactHelper.groupHelper.wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            contactHelper.groupHelper.wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            contactHelper.groupHelper.wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void goToHomePage() {
        contactHelper.groupHelper.wd.findElement(By.linkText("home page")).click();
        contactHelper.groupHelper.wd.get("http://localhost/addressbook/addressbook/index.php");
    }

    public GroupHelper getGroupHelper() {
        return contactHelper.groupHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
