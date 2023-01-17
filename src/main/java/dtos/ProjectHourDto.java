package dtos;

import entities.Developer;
import entities.Project;
import entities.ProjectHour;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.ProjectHour} entity
 */
public class ProjectHourDto implements Serializable {
    private Integer id;
    @NotNull
    private final Integer hoursSpent;
    @Size(max = 45)
    @NotNull
    private final String description;
    @NotNull
    private final Integer userStory;
    @NotNull
    private DeveloperDto developer;
    @NotNull
    private ProjectDto project;

    public ProjectHourDto(Integer id, Integer hoursSpent, String description, Integer userStory, DeveloperDto developer, ProjectDto project) {
        this.id = id;
        this.hoursSpent = hoursSpent;
        this.description = description;
        this.userStory = userStory;
        this.developer = developer;
        this.project = project;
    }

    public ProjectHourDto(ProjectHour projectHour){
        if(projectHour.getId() != null) {
            this.id = projectHour.getId();
        }
        this.hoursSpent = projectHour.getHoursSpent();
        this.description = projectHour.getDescription();
        this.userStory = projectHour.getUserStory();
        if(projectHour.getDeveloper() != null) {
            this.developer = new DeveloperDto(projectHour.getDeveloper());
        }
        if(projectHour.getProject() != null) {
            this.project = new ProjectDto(projectHour.getProject());
        }
    }

    public static List<ProjectHourDto> getDtos(List<ProjectHour> projectHours) {
        List<ProjectHourDto> projectHourDtos = new ArrayList<>();
        projectHours.forEach(projectHour -> projectHourDtos.add(new ProjectHourDto(projectHour)));
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

    public DeveloperDto getDeveloper() {
        return developer;
    }

    public ProjectDto getProject() {
        return project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectHourDto entity = (ProjectHourDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.hoursSpent, entity.hoursSpent) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.userStory, entity.userStory) &&
                Objects.equals(this.developer, entity.developer) &&
                Objects.equals(this.project, entity.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoursSpent, description, userStory, developer, project);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "hoursSpent = " + hoursSpent + ", " +
                "description = " + description + ", " +
                "userStory = " + userStory + ", " +
                "developer = " + developer + ", " +
                "project = " + project + ")";
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

        public DeveloperDto(Developer developer) {
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

    /**
     * A DTO for the {@link entities.Project} entity
     */
    public static class ProjectDto implements Serializable {
        private Integer id;
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

        public static List<ProjectDto> getDtos(List<Project> projects) {
            List<ProjectHourDto.ProjectDto> projectDtos = new ArrayList<>();
            projects.forEach(project -> projectDtos.add(new ProjectHourDto.ProjectDto(project)));
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