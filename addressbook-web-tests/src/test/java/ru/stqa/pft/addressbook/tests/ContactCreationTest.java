package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTest extends TestBase {
    @Test
    public void testContactCreation() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Olga")
                .withLastname("Romanshchak")
                .withNickname("pinguin06")
                .withAddress("Saint Petersburg")
                .withMobile("123456789")
                .withEmail("pinguin06@rambler.ru");
        app.goTo().addContactPage();
        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
