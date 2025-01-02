package services;

import model.Issue;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

//issue manager , if required there can be different agent manager for now i am avoiding that
public class IssueManager {
    private ConcurrentHashMap<String, Issue> allIssues = new ConcurrentHashMap<>();
    private static final Random rand = new Random();

    private AgentManager agentManager;
    public IssueManager(AgentManager agentManager){
        this.agentManager = agentManager;
    }

    public String createIssue(String issueSubject, String status, String issueType, String description){
        int rand_int1 = rand.nextInt(100000);
        Issue issue = new Issue(String.valueOf(rand_int1), issueSubject, status, issueType, description, null);
        allIssues.put(issue.getIssueId(), issue);
        return issue.getIssueId();
    }

    public Issue getIssueById(String issueId){
        return allIssues.get(issueId);
    }

    public synchronized String createAndAssignIssue(String issueSubject, String status, String issueType, String description, String agentId){
        int rand_int1 = rand.nextInt(100000);
        Issue issue = new Issue(String.valueOf(rand_int1), issueSubject, status, issueType, description, null);
        issue.setAssignedAgentId(agentId);
        allIssues.put(issue.getIssueId(), issue);
        this.agentManager.assignIssue(agentId, issue);
        return issue.getIssueId();
    }

    public synchronized void issueReassignment(String issueId, String previousOwnerId, String newOwnerId){
        Issue issue = allIssues.get(issueId);
        this.agentManager.assignIssue(newOwnerId, issue);
        issue.setAssignedAgentId(newOwnerId);
    }

    public void updateStatus(String issueId, String status){
        Issue issue = allIssues.get(issueId);
        issue.setStatus(status);
        this.agentManager.updateIssueStatus(issue.getAssignedAgentId(), issue);
    }

}
