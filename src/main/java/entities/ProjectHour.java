package entities;

import dtos.DeveloperDto;
import dtos.ProjectDto;
import dtos.ProjectHourDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@NamedQuery(name="ProjectHour.deleteAllRows",query = "DELETE from ProjectHour ")
@Table(name = "project_hours")
public class ProjectHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "hours_spent", nullable = false)
    private Integer hoursSpent;

    @Size(max = 45)
    @NotNull
    @Column(name = "description", nullable = false, length = 45)
    private String description;

    @NotNull
    @Column(name = "user_story", nullable = false)
    private Integer userStory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "developer_id", nullable = false)
    private Developer developer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public ProjectHour() {
    }

    public ProjectHour(Integer id, Integer hoursSpent, String description, Integer userStory) {
        this.id = id;
        this.hoursSpent = hoursSpent;
        this.description = description;
        this.userStory = userStory;
    }

    public ProjectHour(Integer hoursSpent, String description, Integer userStory) {
        this.hoursSpent = hoursSpent;
        this.description = description;
        this.userStory = userStory;
    }

    public ProjectHour(Integer id, Integer hoursSpent, String description, Integer userStory, Developer developer, Project project) {
        this.id = id;
        this.hoursSpent = hoursSpent;
        this.description = description;
        this.userStory = userStory;
        this.developer = developer;
        this.project = project;
    }

    public ProjectHour(Integer hoursSpent, String description, Integer userStory, Developer developer, Project project) {
        this.hoursSpent = hoursSpent;
        this.description = description;
        this.userStory = userStory;
        this.developer = developer;
        this.project = project;
    }

    public ProjectHour(ProjectDto.ProjectHourDto projectHourDto){
        this.hoursSpent = projectHourDto.getHoursSpent();
        this.description = projectHourDto.getDescription();
        this.userStory = projectHourDto.getUserStory();
    }

    public ProjectHour(DeveloperDto.ProjectHourDto projectHourDto){
        this.id = projectHourDto.getId();
        this.hoursSpent = projectHourDto.getHoursSpent();
        this.description = projectHourDto.getDescription();
        this.userStory = projectHourDto.getUserStory();
    }

    public ProjectHour(ProjectHourDto projectHourDto){
        if(projectHourDto.getId() != null){
            this.id = projectHourDto.getId();
        }
        this.hoursSpent = projectHourDto.getHoursSpent();
        this.description = projectHourDto.getDescription();
        this.userStory = projectHourDto.getUserStory();
        if(projectHourDto.getDeveloper() != null){
            this.developer = new Developer(projectHourDto.getDeveloper());
        }
        if(projectHourDto.getProject() != null){
            this.project = new Project(projectHourDto.getProject());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(Integer hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserStory() {
        return userStory;
    }

    public void setUserStory(Integer userStory) {
        this.userStory = userStory;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ProjectHour{" +
                "id=" + id +
                ", hoursSpent=" + hoursSpent +
                ", description='" + description + '\'' +
                ", userStory=" + userStory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectHour)) return false;
        ProjectHour that = (ProjectHour) o;
        return getId().equals(that.getId()) && getHoursSpent().equals(that.getHoursSpent()) && getDescription().equals(that.getDescription()) && getUserStory().equals(that.getUserStory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHoursSpent(), getDescription(), getUserStory());
    }
}