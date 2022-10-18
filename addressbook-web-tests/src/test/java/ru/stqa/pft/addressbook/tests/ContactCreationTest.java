package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactDataFromCsv() throws IOException {
        File photo = new File("src/test/resources/dog.jpg");
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstname("Olga1").withLastname("Suvorova1")
                .withNickname("nick1").withAddress("Address1")
                .withMobilePhone("123456781").withEmail1("email1@mail.ru")
                .withPhoto(photo)});
        list.add(new Object[]{new ContactData().withFirstname("Olga2").withLastname("Suvorova2")
                .withNickname("nick2").withAddress("Address2")
                .withMobilePhone("123456782").withEmail1("email2@mail.ru")
                .withPhoto(photo)});
        list.add(new Object[]{new ContactData().withFirstname("Olga3").withLastname("Suvorova3")
                .withNickname("nick3").withAddress("Address3")
                .withMobilePhone("123456783").withEmail1("email3@mail.ru")
                .withPhoto(photo)});
        return list.iterator();
    }

    @Test(dataProvider = "contactDataFromCsv")
    public void testContactCreation(ContactData contact) throws Exception {
        Contacts before = app.contact().all();
        app.goTo().addContactPage();
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
