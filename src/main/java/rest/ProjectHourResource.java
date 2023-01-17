package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectHourDto;
import facades.ProjectHourFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("hour")
public class ProjectHourResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProjectHourFacade FACADE =  ProjectHourFacade.getProjectHourFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll(){
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String content){
        ProjectHourDto projectHourDto = GSON.fromJson(content, ProjectHourDto.class);
        ProjectHourDto newProjectHour = FACADE.create(projectHourDto);
        return Response.ok().entity(GSON.toJson(newProjectHour)).build();
    }

    @GET
    @Path("invoice/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getInvoice(@PathParam("id") int id){
        return Response.ok().entity(GSON.toJson(FACADE.getInvoice(id))).build();
    }

}
