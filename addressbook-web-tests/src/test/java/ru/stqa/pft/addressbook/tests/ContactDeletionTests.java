package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        int index = before.size() - 1;
        app.contact().delete(deletedContact);
        app.goTo().closeAlert();
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);
    }

    @Test()
    public void testContactDeletionFromEditPage() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().deleteFromEditPage(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
