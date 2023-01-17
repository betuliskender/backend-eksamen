package facades;

import dtos.ProjectDto;
import entities.Project;
import errorhandling.GenericExceptionMapper;
import javax.ws.rs.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectFacade {

    private static EntityManagerFactory emf;

    private static ProjectFacade instance;

    private ProjectFacade(){

    }

    public static ProjectFacade getProjectFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ProjectFacade();
        }
        return instance;
    }

    public List<ProjectDto> getAll() {
        EntityManager em = emf.createEntityManager();
            TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
            List<Project> projectList = query.getResultList();
            return ProjectDto.getDtos(projectList);

    }

    public ProjectDto getProjectById(int id) throws NotFoundException {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Project> query = em.createQuery("SELECT p FROM Project  p WHERE p.id = :id", Project.class);
            query.setParameter("id", id);

            return new ProjectDto(query.getSingleResult());
        } catch (NotFoundException ex) {
            Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
            throw new NotFoundException("No Projects found with that ID");
        }
    }

    public ProjectDto create(ProjectDto projectDto){
        EntityManager em = emf.createEntityManager();

        Project project = new Project(projectDto);
        try{
            em.getTransaction().begin();
            em.persist(project);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new ProjectDto(project);
    }

    public ProjectDto update(ProjectDto projectDto) {
        EntityManager em = emf.createEntityManager();

        Project project = new Project(projectDto);

        try {
            em.getTransaction().begin();
            em.merge(project);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new ProjectDto(project);
    }
}
