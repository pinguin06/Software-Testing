package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int index) {
        int i = getContactCount();
        if (index <= 1) {
            click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
        } else {
            if (index <= i + 1) {
                click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[8]/a/img"));
            } else {
                click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + (i + 1) + "]/td[8]/a/img"));
            }
        }
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContactFromEditPage() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/input[2]"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector(("tr[name=entry]")));
        for (WebElement element : elements){
            List<WebElement> contactData = element.findElements(By.tagName("td"));
            String name = contactData.get(2).getText();
            String surname = contactData.get(1).getText();
            ContactData contact = new ContactData(name, surname, null, null,null,null,null);
            contacts.add(contact);
        }
        return contacts;
    }
}
