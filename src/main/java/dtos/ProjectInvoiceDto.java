package dtos;

public class ProjectInvoiceDto {

    int hours;
    String description;
    int billingPrHour;
    int userStory;
    int developerId;
    int ProjectId;
    int total;

    public ProjectInvoiceDto(int total, String description, int projectId, int developerId, int userStory, int hours, int billingPrHour) {
        this.total = total;
        this.hours = hours;
        this.description = description;
        this.billingPrHour = billingPrHour;
        this.userStory = userStory;
        this.developerId = developerId;
        ProjectId = projectId;
    }

    public ProjectInvoiceDto() {
    }

    public int getHours() {
        return hours;
    }

    public String getDescription() {
        return description;
    }

    public int getBillingPrHour() {
        return billingPrHour;
    }

    public int getUserStory() {
        return userStory;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public int getTotal() {
        return total;
    }
}
