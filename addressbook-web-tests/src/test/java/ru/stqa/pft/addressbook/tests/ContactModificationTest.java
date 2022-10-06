package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Rita")
                    .withLastname("Kukushkina")
                    .withNickname("pinguin06")
                    .withAddress("Saint Petersburg")
                    .withMobile("123456789")
                    .withEmail("pinguin06@rambler.ru"));
            app.goTo().homePage();
        }
    }

    @Test()
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Olga")
                .withLastname("Romanshchak")
                .withNickname("pinguin06")
                .withAddress("Saint Petersburg")
                .withMobile("123456789")
                .withEmail("pinguin06@rambler.ru");
        app.contact().modify(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
