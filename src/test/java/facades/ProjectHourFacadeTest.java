package facades;

import dtos.ProjectHourDto;
import dtos.ProjectInvoiceDto;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectHourFacadeTest {

    private static EntityManagerFactory emf;

    private static ProjectHourFacade facade;

    Project p1, p2;

    Developer d1, d2;

    ProjectHour ph1, ph2;

    ProjectInvoiceDto pi1;

    public ProjectHourFacadeTest(){
    }

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ProjectHourFacade.getProjectHourFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        p1 = new Project("project 1", "museum app");
        d1 = new Developer("Karen", "karen@karen.dk", "28283928", 200);
        ph1 = new ProjectHour(22, "asdas", 1, d1, p1);
        pi1 = new ProjectInvoiceDto(50000, "FitnessApp", 1, 1, 5, 250, 200);

        try{
            em.getTransaction().begin();
            em.createNamedQuery("ProjectHour.deleteAllRows").executeUpdate();
            em.createNamedQuery("Developer.deleteAllRows").executeUpdate();
            em.createNamedQuery("Project.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(d1);
            em.persist(ph1);
            em.getTransaction().commit();

        }finally {
            em.close();
        }
    }

    @Test
    void getAll(){
        List<ProjectHourDto> projectHourDtos;
        int expected = 1;
        projectHourDtos = facade.getAll();

        assertEquals(expected, projectHourDtos.size());
    }

    @Test
    void create(){
        ProjectHour projectHour = new ProjectHour(333, "edit user", 2, d1, p1);
        ProjectHourDto expected = new ProjectHourDto(projectHour);
        ProjectHourDto actual = facade.create(expected);
        System.out.println(actual);
        assertNotNull(actual.getId());
    }

}
