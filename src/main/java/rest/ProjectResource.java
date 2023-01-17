package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ProjectDto;
import facades.ProjectFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("project")
public class ProjectResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ProjectFacade FACADE =  ProjectFacade.getProjectFacade(EMF);
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
        System.out.println(content);
        ProjectDto projectDto = GSON.fromJson(content, ProjectDto.class);
        ProjectDto newProject = FACADE.create(projectDto);
        return Response.ok().entity(GSON.toJson(newProject)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectById(@PathParam("id") int id) throws NotFoundException{
        ProjectDto projectDto = FACADE.getProjectById(id);
        return Response.ok().entity(GSON.toJson(projectDto)).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJourney(@PathParam("id") int id, String content) {
        ProjectDto projectDto = GSON.fromJson(content, ProjectDto.class);
        projectDto.setId(id);
        ProjectDto projectDto1 = FACADE.update(projectDto);

        return Response.ok().entity(GSON.toJson(projectDto1)).build();
    }

}
