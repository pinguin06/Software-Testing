package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]> ();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
    @Test(dataProvider = "validGroupsJson")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertEquals(app.group().count(), before.size() + 1);
        Groups after = app.group().all();

        assertThat(after, equalTo(before
                .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }


    @Test
    public void testBadGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test3'");
        app.group().create(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }
}
