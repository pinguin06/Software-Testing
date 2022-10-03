package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ContactModificationTest extends TestBase {

    @Test()
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.goTo().addContactPage();
            app.getContactHelper().createContact(new ContactData("Rita",
                    "Kukushkina",
                    "pinguin06",
                    "Saint Petersburg",
                    "123456789",
                    "pinguin06@rambler.ru"));
            app.goTo().homePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Olga11",
                "Roman11",
                "null",
                "Saint Petersburg",
                "123456789",
                "pinguin06_mod@rambler.ru",
                null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.goTo().homePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); //преобразование в массив
    }
}
