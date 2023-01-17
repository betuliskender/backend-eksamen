package dtos;

import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link entities.Developer} entity
 */
public class DeveloperDto implements Serializable {
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
    private Set<ProjectHourDto> projectHours = new LinkedHashSet<>();
    private Set<ProjectDto> projects = new LinkedHashSet<>();

    public DeveloperDto(Integer id, String name, String email, String phone, Integer billingPrHour, Set<ProjectHourDto> projectHours, Set<ProjectDto> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
        this.projectHours = projectHours;
        this.projects = projects;
    }

    public DeveloperDto(Developer developer){
        this.id = developer.getId();
        this.name = developer.getName();
        this.email = developer.getEmail();
        this.phone = developer.getPhone();
        this.billingPrHour = developer.getBillingPrHour();
        if(developer.getProjectHours() != null) {
            for(ProjectHour projectHour : developer.getProjectHours()) {
                this.projectHours.add(new ProjectHourDto(projectHour));
            }
        }
        if(developer.getProjects() != null) {
            for(Project project : developer.getProjects()) {
                this.projects.add(new ProjectDto(project));
            }
        }
    }

    public static List<DeveloperDto> getDtos(List<Developer> developers) {
        List<DeveloperDto> developerDtos = new ArrayList<>();
        developers.forEach(developer -> developerDtos.add(new DeveloperDto(developer)));
        return developerDtos;
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

    public Set<ProjectHourDto> getProjectHours() {
        return projectHours;
    }

    public Set<ProjectDto> getProjects() {
        return projects;
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
                Objects.equals(this.billingPrHour, entity.billingPrHour) &&
                Objects.equals(this.projectHours, entity.projectHours) &&
                Objects.equals(this.projects, entity.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, billingPrHour, projectHours, projects);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ", " +
                "phone = " + phone + ", " +
                "billingPrHour = " + billingPrHour + ", " +
                "projectHours = " + projectHours + ", " +
                "projects = " + projects + ")";
    }

    /**
     * A DTO for the {@link entities.ProjectHour} entity
     */
    public static class ProjectHourDto implements Serializable {
        private final Integer id;
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

        public ProjectHourDto(ProjectHour projectHour) {
            this.id = projectHour.getId();
            this.hoursSpent = projectHour.getHoursSpent();
            this.description = projectHour.getDescription();
            this.userStory = projectHour.getUserStory();
        }

        public static List<ProjectHourDto> getDtos(List<ProjectHour> projectHours) {
            List<DeveloperDto.ProjectHourDto> projectHourDtos = new ArrayList<>();
            projectHours.forEach(projectHour -> projectHourDtos.add(new DeveloperDto.ProjectHourDto(projectHour)));
            return projectHourDtos;
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
     * A DTO for the {@link entities.Project} entity
     */
    public static class ProjectDto implements Serializable {
        private final Integer id;
        @Size(max = 45)
        @NotNull
        private final String name;
        @Size(max = 45)
        @NotNull
        private final String description;

        public ProjectDto(Integer id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public ProjectDto(Project project) {
            this.id = project.getId();
            this.name = project.getName();
            this.description = project.getDescription();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProjectDto entity = (ProjectDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "description = " + description + ")";
        }
    }
}