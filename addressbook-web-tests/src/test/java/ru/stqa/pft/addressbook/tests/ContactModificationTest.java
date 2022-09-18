package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test(enabled = false)
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()){
            app.goTo().addContactPage();
            app.getContactHelper().createContact(new ContactData("Rita",
                    "Kukushkina",
                    "pinguin06",
                    "Saint Petersburg",
                    "123456789",
                    "pinguin06@rambler.ru",
                    "test1_mod"));
            app.goTo().homePage();
            int before = app.getContactHelper().getContactCount();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModification(0);
        app.getContactHelper().fillContactForm(
                new ContactData("Olga",
                        "siztt",
                        "pinguin06_mod",
                        "Saint Petersburg",
                        "123456789",
                        "pinguin06_mod@rambler.ru",
                        null),
                        false);
        app.getContactHelper().submitContactModification();
        app.goTo().homePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);


    }
}
