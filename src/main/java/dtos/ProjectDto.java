package dtos;

import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link entities.Project} entity
 */
public class ProjectDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private final String name;
    @Size(max = 45)
    @NotNull
    private final String description;
    private Set<ProjectHourDto> projectHours = new LinkedHashSet<>();
    private Set<DeveloperDto> developers = new LinkedHashSet<>();

    public ProjectDto(Integer id, String name, String description, Set<ProjectHourDto> projectHours, Set<DeveloperDto> developers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectHours = projectHours;
        this.developers = developers;
    }

    public ProjectDto(Project project){
        if(project.getId() != null) {
            this.id = project.getId();
        }
        this.name = project.getName();
        this.description = project.getDescription();
        if(project.getProjectHours() != null) {
            for(ProjectHour projectHour : project.getProjectHours()){
                this.projectHours.add(new ProjectHourDto(projectHour));
            }
        }
        if(project.getDevelopers() != null) {
            for(Developer developer : project.getDevelopers()){
                this.developers.add(new DeveloperDto(developer));
            }
        }
    }

    public static List<ProjectDto> getDtos(List<Project> projects) {
        List<ProjectDto> projectDtos = new ArrayList<>();
        projects.forEach(project -> projectDtos.add(new ProjectDto(project)));
        return projectDtos;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProjectHourDto> getProjectHours() {
        return projectHours;
    }

    public Set<DeveloperDto> getDevelopers() {
        return developers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto entity = (ProjectDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.projectHours, entity.projectHours) &&
                Objects.equals(this.developers, entity.developers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, projectHours, developers);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "projectHours = " + projectHours + ", " +
                "developers = " + developers + ")";
    }

    /**
     * A DTO for the {@link entities.ProjectHour} entity
     */
    public static class ProjectHourDto implements Serializable {
        private Integer id;
        @NotNull
        private final Integer hoursSpent;
        @Size(max = 45)
        @NotNull
        private final String description;
        @NotNull
        private final Integer userStory;

        public ProjectHourDto(Integer id, Integer hoursSpent, String description, Integer userStory) {
            this.id = id;
            this.hoursSpent = hoursSpent;
            this.description = description;
            this.userStory = userStory;
        }

        public ProjectHourDto(ProjectHour projectHour){
            this.id = projectHour.getId();
            this.hoursSpent = projectHour.getHoursSpent();
            this.description = projectHour.getDescription();
            this.userStory = projectHour.getUserStory();
        }

        public Integer getId() {
            return id;
        }

        public Integer getHoursSpent() {
            return hoursSpent;
        }

        public String getDescription() {
            return description;
        }

        public Integer getUserStory() {
            return userStory;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProjectHourDto entity = (ProjectHourDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.hoursSpent, entity.hoursSpent) &&
                    Objects.equals(this.description, entity.description) &&
                    Objects.equals(this.userStory, entity.userStory);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, hoursSpent, description, userStory);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "hoursSpent = " + hoursSpent + ", " +
                    "description = " + description + ", " +
                    "userStory = " + userStory + ")";
        }
    }

    /**
     * A DTO for the {@link entities.Developer} entity
     */
    public static class DeveloperDto implements Serializable {
        private Integer id;
        @Size(max = 45)
        @NotNull
        private final String name;
        @Size(max = 45)
        @NotNull
        private final String email;
        @Size(max = 45)
        @NotNull
        private final String phone;
        @NotNull
        private final Integer billingPrHour;

        public DeveloperDto(Integer id, String name, String email, String phone, Integer billingPrHour) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.billingPrHour = billingPrHour;
        }

        public DeveloperDto(Developer developer){
            this.id = developer.getId();
            this.name = developer.getName();
            this.email = developer.getEmail();
            this.phone = developer.getPhone();
            this.billingPrHour = developer.getBillingPrHour();
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public Integer getBillingPrHour() {
            return billingPrHour;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DeveloperDto entity = (DeveloperDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.email, entity.email) &&
                    Objects.equals(this.phone, entity.phone) &&
                    Objects.equals(this.billingPrHour, entity.billingPrHour);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, email, phone, billingPrHour);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "email = " + email + ", " +
                    "phone = " + phone + ", " +
                    "billingPrHour = " + billingPrHour + ")";
        }
    }
}