package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(
                new ContactData("Olga",
                        "Romanshchak",
                        "pinguin06_mod",
                        "Saint Petersburg",
                        "123456789",
                        "pinguin06_mod@rambler.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();

    }
}
