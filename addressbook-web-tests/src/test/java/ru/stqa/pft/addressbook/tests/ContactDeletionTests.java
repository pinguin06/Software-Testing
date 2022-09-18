package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {


    @Test(enabled = false)
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
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
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteSelectedContacts();
        app.goTo().closeAlert();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

    @Test(enabled = false)
    public void testContactDeletionFromEditPage() {
        if (!app.getContactHelper().isThereAContact()) {
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
        app.getContactHelper().initContactModification(3);
        app.getContactHelper().deleteSelectedContactFromEditPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
