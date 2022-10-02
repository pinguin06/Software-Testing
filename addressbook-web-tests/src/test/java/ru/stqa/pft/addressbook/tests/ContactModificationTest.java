package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTest extends TestBase{

    @Test()
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()){
            app.goTo().addContactPage();
            app.getContactHelper().createContact(new ContactData("Rita",
                    "Kukushkina",
                    "pinguin06",
                    "Saint Petersburg",
                    "123456789",
                    "pinguin06@rambler.ru",
                    "test1"));
            app.goTo().homePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
       // int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().fillContactForm(
                new ContactData("Olga1",
                        "siztt",
                        "pinguin06_mod1",
                        "Saint Petersburg",
                        "123456789",
                        "pinguin06_mod@rambler.ru",
                        null),
                        false);
        app.getContactHelper().submitContactModification();
        app.goTo().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();
       // int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size());


    }
}
