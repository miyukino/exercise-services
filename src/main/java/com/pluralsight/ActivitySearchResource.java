package com.pluralsight;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.repository.ActivityRepository;
import com.pluralsight.repository.ActivityRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("search/activities")
public class ActivitySearchResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchForActivities(ActivitySearch search) {
        List<Activity> activities = activityRepository.findByConstraints(search);

        if (activities == null || activities.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchForActivities(@QueryParam(value = "description") List<String> description) {
        List<Activity> activities = activityRepository.findByDescription(description);

        if (activities == null || activities.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
        }).build();
    }
}
