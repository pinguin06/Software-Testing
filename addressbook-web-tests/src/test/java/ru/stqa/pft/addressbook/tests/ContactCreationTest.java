package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> contactDataFromCsv() throws IOException {
        File photo = new File("src/test/resources/dog.jpg");
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new ContactData().withFirstname(split[0])
                        .withLastname(split[1])
                        .withNickname(split[2]).withAddress(split[3])
                        .withMobilePhone(split[4]).withEmail1(split[5])
                        .withPhoto(photo)});
                line = reader.readLine();
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> contactDataFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            xStream.allowTypes(new Class[]{ContactData.class});
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> contactDataFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "contactDataFromXml")
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
