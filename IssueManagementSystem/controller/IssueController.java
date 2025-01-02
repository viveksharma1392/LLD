package controller;

import model.Issue;
import services.AgentManager;
import services.IssueManager;

import java.util.List;

//All apis of issue system
public class IssueController {
    private IssueManager issueManager;
    private AgentManager agentManager;
    //initialize the controller
    public IssueController(IssueManager issueManager, AgentManager agentManager){
        this.issueManager = issueManager;
        this.agentManager = agentManager;
    }

    //Create a new issue
    public String creteIssue(String issueSubject, String status, String issueType, String description ){
        try {
            return this.issueManager.createIssue(issueSubject, status, issueType, description);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public String creteAndAssignIssue(String issueSubject, String status, String issueType, String description, String agentId ){
        try {
            return this.issueManager.createAndAssignIssue(issueSubject, status, issueType, description, agentId);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    //resolve the existing issue
    public Boolean resolveIssue(String issueId){
        try {
            updateIssueStatus(issueId, "completed");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean updateIssueStatus(String issueId, String issueStatus){
        try {
            this.issueManager.updateStatus(issueId, issueStatus);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //add new agent in the system
    public String addNewAgent(List<String> skills){
        try {
            return this.agentManager.createAgent(skills);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    //get history of the agent
    public List<String> getAgentHistory(String agentId){
        try {
        return this.agentManager.getAllAssignedIssues(agentId);
        }catch(Exception e){
            return null;
        }
    }

    public List<String> getAgentIssuesByStatus(String agentId, String status){
        try {
            return this.agentManager.getAllIssuesByStatus(agentId, status);
        }catch(Exception e){
            return null;
        }
    }

    //assign issue to an agent
    public String assignIssue(String issueId, String agentId){
        try {
            Issue issue = this.issueManager.getIssueById(issueId);
            if(issue.getAssignedAgentId()!=null){
                this.agentManager.removeAssignedIssue(issue.getAssignedAgentId(), issue);
            }
            this.agentManager.assignIssue(agentId, issue);
            return "Successfully assigned";
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
