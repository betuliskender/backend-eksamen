package facades;

import dtos.DeveloperDto;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeveloperFacade {

    private static EntityManagerFactory emf;

    private static DeveloperFacade instance;

    private DeveloperFacade(){
    }

    public static DeveloperFacade getDeveloperFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new DeveloperFacade();
        }
        return instance;
    }

    public List<DeveloperDto> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Developer> query = em.createQuery("SELECT d FROM Developer d", Developer.class);
        List<Developer> developerList = query.getResultList();
        return DeveloperDto.getDtos(developerList);
    }
}
