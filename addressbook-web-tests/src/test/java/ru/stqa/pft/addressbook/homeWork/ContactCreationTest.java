package ru.stqa.pft.addressbook.homeWork;

import org.testng.annotations.*;

import ru.stqa.pft.addressbook.TestBase;

public class ContactCreationTest extends TestBase {
    @Test
    public void testContactCreation() throws Exception {
        goToAddContactPage();
        fillContactForm(
                new ContactData("Olga", "Romanshchak", "pinguin06", "Saint Petersburg", "123456789", "pinguin06@rambler.ru"));
        submitContactCreation();
        goToHomePage();
    }

}
