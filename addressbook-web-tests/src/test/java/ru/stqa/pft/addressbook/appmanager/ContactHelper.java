package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
            Select se = new Select(wd.findElement(By.name("new_group")));
            List<WebElement> l = se.getOptions();
            int i = l.size();
            if (i == 1) {
                new Select(wd.findElement(By.name("new_group"))).selectByIndex(0);
            } else {
                new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int index) {
        click(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + (index + 2) + "]/td[8]/a/img"));
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void deleteSelectedContactFromEditPage() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/input[2]"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        contactsCache = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactsCache = null;
    }

    public void deleteFromEditPage(int index) {
        initContactModification(index);
        deleteSelectedContactFromEditPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactsCache = null;
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector(("tr[name=entry]")));
        for (WebElement element : elements) {
            List<WebElement> contactData = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = contactData.get(2).getText();
            String surname = contactData.get(1).getText();
            contacts.add(new ContactData().withId(id).withFirstname(name).withLastname(surname));
        }
        return contacts;
    }

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector(("tr[name=entry]")));
        for (WebElement element : elements) {
            List<WebElement> contactData = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = contactData.get(2).getText();
            String surname = contactData.get(1).getText();
            contactsCache.add(new ContactData().withId(id).withFirstname(name).withLastname(surname));
        }
        return new Contacts(contactsCache);
    }

    private Contacts contactsCache = null;

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


}
