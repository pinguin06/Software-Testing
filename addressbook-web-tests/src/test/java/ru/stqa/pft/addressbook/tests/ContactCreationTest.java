package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {
    @Test()
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
  //      int before = app.getContactHelper().getContactCount();
        app.goTo().addContactPage();
        app.getContactHelper().createContact( new ContactData("Olga",
                        "Romanshchak",
                        "pinguin06",
                        "Saint Petersburg",
                        "123456789",
                        "pinguin06@rambler.ru"));
        app.goTo().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();
//        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
