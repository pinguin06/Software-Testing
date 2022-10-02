package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {
    @Test()
    public void testContactCreation() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.goTo().addContactPage();
        app.getContactHelper().createContact( new ContactData("Olga",
                        "Romanshchak",
                        "pinguin06",
                        "Saint Petersburg",
                        "123456789",
                        "pinguin06@rambler.ru"));
        app.goTo().homePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
