package model;

import enums.IssueType;
import enums.Status;

//model.Issue entity
public class Issue {
    private String issueId;

    private String issueSubject;

    private Status status;

    private IssueType issueType;
    private String description;
    private String assignedAgentId;
    private String resolution;



    public Issue(String issueId, String issueSubject, String statusLabel, String issueTypeLabel, String description, String assignedAgentId){
        this.issueId = issueId;
        this.issueSubject = issueSubject;
        this.status = Status.valueOfLabel(statusLabel);
        this.issueType = IssueType.valueOfLabel(issueTypeLabel);
        this.description = description;
        this.assignedAgentId = assignedAgentId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedAgentId() {
        return assignedAgentId;
    }

    public void setAssignedAgentId(String assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String statusLabel) {
        this.status = Status.valueOfLabel(statusLabel);
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueTypeLabel) {
        this.issueType = IssueType.valueOfLabel(issueTypeLabel);
    }

    public Boolean isResolved(){
        return status.label.equals("completed");
    }

    public void resolveIssue(String resolution){
        this.resolution = resolution;
        this.status = Status.valueOfLabel("completed");
    }

}
