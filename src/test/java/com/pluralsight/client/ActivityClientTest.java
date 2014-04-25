package com.pluralsight.client;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.model.ActivitySearchType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ActivityClientTest {

    @Test
    public void testDelete() {
        ActivityClient client = new ActivityClient();

        client.delete("1234");
    }

    @Test
    public void testPut() {
        Activity activity = new Activity();

        activity.setId("3456");
        activity.setDescription("Bikram Yoga");
        activity.setDuration(70);

        ActivityClient client = new ActivityClient();

        activity = client.update(activity);

        assertNotNull(activity);
    }

    @Test
    public void testCreate() {
        ActivityClient client = new ActivityClient();

        Activity activity = new Activity();
        activity.setDescription("Swimming");
        activity.setDuration(90);

        activity = client.create(activity);

        assertNotNull(activity);
    }

    @Test
    public void testGet() throws Exception {
        ActivityClient client = new ActivityClient();
        Activity activity = client.get("1234");

        assertNotNull(activity);
    }

    @Test
    public void testGetList() throws Exception {
        ActivityClient client = new ActivityClient();

        List<Activity> activities = client.get();

        assertNotNull(activities);
    }

    @Test(expected = RuntimeException.class)
    public void testGetWithBadRequest() {
        ActivityClient client = new ActivityClient();
        client.get("123");
    }

    @Test(expected = RuntimeException.class)
    public void testGetWithNotFound() {
        ActivityClient client = new ActivityClient();
        client.get("7777");
    }

    @Test
    public void testSearch() {
        ActivitySearchClient client = new ActivitySearchClient();

        String param = "description";
        List<String> searchValues = new ArrayList<>();
        searchValues.add("swimming");
        searchValues.add("running");

        List<Activity> activities = client.search(param, searchValues);

        assertNotNull(activities);
    }

    @Test
    public void testSearchObject() {
        ActivitySearchClient client = new ActivitySearchClient();

        List<String> searchValues = new ArrayList<>();
        searchValues.add("biking");
        searchValues.add("running");

        ActivitySearch search = new ActivitySearch();
        search.setDescriptions(searchValues);
        search.setDurationFrom(30);
        search.setDurationTo(55);
        search.setSearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);

        List<Activity> activities = client.search(search);

        assertNotNull(activities);
    }
}
