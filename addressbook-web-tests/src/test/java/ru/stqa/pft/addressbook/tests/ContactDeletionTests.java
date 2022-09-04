package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("Rita",
                    "Kukushkina",
                    "pinguin06",
                    "Saint Petersburg",
                    "123456789",
                    "pinguin06@rambler.ru",
                    "test1_mod"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().closeAlert();
    }

    @Test
    public void testContactDeletionFromEditPage() {
        if (!app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().goToAddContactPage();
            app.getContactHelper().createContact(new ContactData("Rita",
                    "Kukushkina",
                    "pinguin06",
                    "Saint Petersburg",
                    "123456789",
                    "pinguin06@rambler.ru",
                    "test1_mod"));
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().deleteSelectedContactFromEditPage();
    }
}
