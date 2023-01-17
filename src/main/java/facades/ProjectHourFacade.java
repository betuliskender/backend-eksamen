package facades;

import dtos.DeveloperDto;
import dtos.ProjectDto;
import dtos.ProjectHourDto;
import dtos.ProjectInvoiceDto;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectHourFacade {

    private static EntityManagerFactory emf;

    private static ProjectHourFacade instance;

    private ProjectHourFacade(){

    }

    public static ProjectHourFacade getProjectHourFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new ProjectHourFacade();
        }
        return instance;
    }

    public List<ProjectHourDto> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProjectHour> query = em.createQuery("SELECT p FROM ProjectHour p", ProjectHour.class);
        List<ProjectHour> projectList = query.getResultList();
        return ProjectHourDto.getDtos(projectList);
    }

    public ProjectHourDto create(ProjectHourDto projectHourDto){
        EntityManager em = emf.createEntityManager();

        ProjectHour projectHour = new ProjectHour(projectHourDto);
        try{
            em.getTransaction().begin();
            em.persist(projectHour);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new ProjectHourDto(projectHour);
    }

    public boolean delete(int id){
        EntityManager em = emf.createEntityManager();
        ProjectHour projectHour = em.find(ProjectHour.class, id);

        try {
            em.getTransaction().begin();
            em.remove(projectHour);
            em.getTransaction().commit();
        }catch (IllegalArgumentException ex){
            System.out.println(ex);
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public List<ProjectInvoiceDto> getInvoice(int projectId){

        EntityManager em = emf.createEntityManager();

        try{
//            Query query = em.createQuery("SELECT SUM(ph.hoursSpent * d.billingPrHour) AS total, p.description, p.id AS projectId, d.id AS developerId, ph.userStory, ph.hoursSpent AS hours, d.billingPrHour FROM Project p Join p.projectHours ph Join p.developers d WHERE p.id = :id");
            Query query = em.createQuery("SELECT NEW dtos.ProjectInvoiceDto ((ph.hoursSpent*d.billingPrHour ), p.description, p.id, d.id, ph.userStory, ph.hoursSpent, d.billingPrHour) FROM Project p Join p.projectHours ph Join p.developers d WHERE p.id = :id");
            query.setParameter("id", projectId);
            List<ProjectInvoiceDto> projectInvoiceDto = query.getResultList();
            System.out.println(query.getResultList());
            return projectInvoiceDto;
        }finally {
            em.close();
        }


    }
}
