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
@NamedQuery(name="Developer.deleteAllRows",query = "DELETE from Developer")
@Table(name = "developer")
public class Developer {
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
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 45)
    @NotNull
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @NotNull
    @Column(name = "billing_pr_hour", nullable = false)
    private Integer billingPrHour;

    @OneToMany(mappedBy = "developer")
    private Set<ProjectHour> projectHours = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "project_has_developer",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new LinkedHashSet<>();

    public Developer() {
    }

    public Developer(Integer id, String name, String email, String phone, Integer billingPrHour) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
    }

    public Developer(String name, String email, String phone, Integer billingPrHour) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
    }

    public Developer(Integer id, String name, String email, String phone, Integer billingPrHour, Set<ProjectHour> projectHours, Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingPrHour = billingPrHour;
        this.projectHours = projectHours;
        this.projects = projects;
    }

    public Developer(ProjectDto.DeveloperDto developerDto){
        this.id = developerDto.getId();
        this.name = developerDto.getName();
        this.email = developerDto.getEmail();
        this.phone = developerDto.getPhone();
        this.billingPrHour = developerDto.getBillingPrHour();
    }

    public Developer(ProjectHourDto.DeveloperDto developerDto){
        this.id = developerDto.getId();
        this.name = developerDto.getName();
        this.email = developerDto.getEmail();
        this.phone = developerDto.getPhone();
        this.billingPrHour = developerDto.getBillingPrHour();
    }

    public Developer(DeveloperDto developerDto){
        this.id = developerDto.getId();
        this.name = developerDto.getName();
        this.email = developerDto.getEmail();
        this.phone = developerDto.getPhone();
        this.billingPrHour = developerDto.getBillingPrHour();
        if(developerDto.getProjectHours() != null){
            for(DeveloperDto.ProjectHourDto projectHourDto : developerDto.getProjectHours()){
                this.projectHours.add(new ProjectHour(projectHourDto));
            }
        }
        if(developerDto.getProjects() != null){
            for(DeveloperDto.ProjectDto projectDto : developerDto.getProjects()){
                this.projects.add(new Project(projectDto));
            }
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBillingPrHour() {
        return billingPrHour;
    }

    public void setBillingPrHour(Integer billingPrHour) {
        this.billingPrHour = billingPrHour;
    }

    public Set<ProjectHour> getProjectHours() {
        return projectHours;
    }

    public void setProjectHours(Set<ProjectHour> projectHours) {
        this.projectHours = projectHours;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", billingPrHour=" + billingPrHour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Developer)) return false;
        Developer developer = (Developer) o;
        return getId().equals(developer.getId()) && getName().equals(developer.getName()) && getEmail().equals(developer.getEmail()) && getPhone().equals(developer.getPhone()) && getBillingPrHour().equals(developer.getBillingPrHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPhone(), getBillingPrHour());
    }
}