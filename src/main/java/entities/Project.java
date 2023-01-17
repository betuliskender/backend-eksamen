package entities;

import dtos.DeveloperDto;
import dtos.ProjectDto;
import dtos.ProjectHourDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name="Project.deleteAllRows",query = "DELETE from Project ")
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 45)
    @NotNull
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<ProjectHour> projectHours = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "project_has_developer",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developers = new LinkedHashSet<>();

    public Project() {
    }

    public Project(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, Set<ProjectHour> projectHours, Set<Developer> developers) {
        this.name = name;
        this.description = description;
        this.projectHours = projectHours;
        this.developers = developers;
    }

    public Project(Integer id, String name, String description, Set<ProjectHour> projectHours, Set<Developer> developers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectHours = projectHours;
        this.developers = developers;
    }

    public Project(ProjectHourDto.ProjectDto projectDto){
        this.id = projectDto.getId();
        this.name = projectDto.getName();
        this.description = projectDto.getDescription();
    }

    public Project(ProjectDto projectDto){
        if(projectDto.getId() != null) {
            this.id = projectDto.getId();
        }
        this.name = projectDto.getName();
        this.description = projectDto.getDescription();
        if(projectDto.getProjectHours() != null) {
            for(ProjectDto.ProjectHourDto projectHourDto : projectDto.getProjectHours()){
                this.projectHours.add(new ProjectHour(projectHourDto));
            }
        }
        if(projectDto.getDevelopers() != null) {
            for(ProjectDto.DeveloperDto developerDto : projectDto.getDevelopers()){
                this.developers.add(new Developer(developerDto));
            }
        }
    }

    public Project(DeveloperDto.ProjectDto projectDto){
        this.id = projectDto.getId();
        this.name = projectDto.getName();
        this.description = projectDto.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProjectHour> getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(Set<ProjectHour> projectHours) {
        this.projectHours = projectHours;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return getId().equals(project.getId()) && getName().equals(project.getName()) && getDescription().equals(project.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}