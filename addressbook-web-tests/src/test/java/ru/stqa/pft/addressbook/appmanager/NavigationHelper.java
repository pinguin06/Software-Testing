package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
   private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home page")).click();
        wd.get("http://localhost/addressbook/addressbook/index.php");
    }

    public void goToAddContactPage() {
        wd.findElement(By.linkText("add new")).click();
        wd.get("http://localhost/addressbook/addressbook/edit.php");
    }
}
