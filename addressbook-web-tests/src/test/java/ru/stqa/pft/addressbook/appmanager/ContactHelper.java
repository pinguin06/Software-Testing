package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
    protected final GroupHelper groupHelper = new GroupHelper();
    private final WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void submitContactCreation() {
        groupHelper.wd.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData contactData) {
        groupHelper.wd.findElement(By.name("firstname")).click();
        groupHelper.wd.findElement(By.name("firstname")).clear();
        groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        groupHelper.wd.findElement(By.name("lastname")).click();
        groupHelper.wd.findElement(By.name("lastname")).clear();
        groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        groupHelper.wd.findElement(By.name("nickname")).click();
        groupHelper.wd.findElement(By.name("nickname")).clear();
        groupHelper.wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        groupHelper.wd.findElement(By.name("address")).click();
        groupHelper.wd.findElement(By.name("address")).clear();
        groupHelper.wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        groupHelper.wd.findElement(By.name("mobile")).click();
        groupHelper.wd.findElement(By.name("mobile")).clear();
        groupHelper.wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        groupHelper.wd.findElement(By.name("email")).click();
        groupHelper.wd.findElement(By.name("email")).clear();
        groupHelper.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void goToAddContactPage() {
        groupHelper.wd.findElement(By.linkText("add new")).click();
        groupHelper.wd.get("http://localhost/addressbook/addressbook/edit.php");
    }
}
