package facades;

import dtos.ProjectDto;
import entities.Developer;
import entities.Project;
import entities.ProjectHour;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectFacadeTest {

    private static EntityManagerFactory emf;

    private static ProjectFacade facade;

    Project p1, p2;

    Developer d1, d2;

    ProjectHour ph1, ph2;

    public ProjectFacadeTest(){
    }

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ProjectFacade.getProjectFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        p1 = new Project("project 1", "museum app");
        d1 = new Developer("Karen", "karen@karen.dk", "28283928", 200);
        ph1 = new ProjectHour(22, "asdas", 1, d1, p1);

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
    void getAll() {
        List<ProjectDto> projectDtoList;
        int expected = 1;
        projectDtoList = facade.getAll();

        assertEquals(expected, projectDtoList.size());
    }

    @Test
    void projectGetByIdException(){
        ProjectFacade projectFacade = ProjectFacade.getProjectFacade(emf);

        assertThrows(NoResultException.class, () -> {
            projectFacade.getProjectById(1111111);
        });

    }

    @Test
    void create() {
        Project project = new Project("project 2", "test app");
        ProjectDto expected = new ProjectDto(project);
        ProjectDto actual = facade.create(expected);
        System.out.println(actual);
        assertNotNull(actual.getId());
    }

    @Test
    void getProjectById () {
        ProjectDto result = facade.getProjectById(p1.getId());

        assertEquals(p1.getName(), result.getName());
    }

    @Test
    void update() {
        p1.setName("project 15");
        ProjectDto expected = new ProjectDto(p1);
        ProjectDto actual = facade.update(expected);
        assertEquals(expected, actual);
    }
}