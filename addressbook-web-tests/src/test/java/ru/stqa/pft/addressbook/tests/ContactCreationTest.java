package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {
    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().goToAddContactPage();
        app.getContactHelper().fillContactForm(
                new ContactData("Olga", "Romanshchak", "pinguin06", "Saint Petersburg", "123456789", "pinguin06@rambler.ru"));
        app.getContactHelper().submitContactCreation();
        app.goToHomePage();
    }
}
