package model;
import enums.Skill;
import enums.Status;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ConcurrentHashMap;

public class Agent {
    private String agentId;

    private Set<Skill> skills;

    private ConcurrentHashMap<String, Set<String>> issueByStatus;


    public Agent(String agentId, Set<Skill> skills){
        this.agentId = agentId;
        this.skills = skills;
        issueByStatus = new ConcurrentHashMap<String, Set<String>>();
    }

    public void addSkill(List<String> skills){
        for(String skill : skills){
            this.skills.add(Skill.validOfLabel(skill));
        }
    }

    public void assignIssue(Issue issue){
        System.out.println(issue.getIssueId());
        Status status = issue.getStatus();
        Set<String> currentIssues = issueByStatus.get(status.label);
        if(currentIssues==null){
            currentIssues= new HashSet<String>();
        }
        currentIssues.add(issue.getIssueId());
        issueByStatus.put(status.label,currentIssues);
    }

    public void removeAssignedIssue(Issue issue){
        Status status = issue.getStatus();
        Set<String> currentIssues = issueByStatus.get(status.label);
        currentIssues.remove(issue.getIssueId());
        issueByStatus.put(status.label,currentIssues);
    }

    public List<String> getIssueIdsByStatus(String statusLabel){
        return new ArrayList<String>(issueByStatus.get(statusLabel));
    }

    public List<String> getAllIssues(){
        return new ArrayList<String>(issueByStatus.values().stream().flatMap(Set::stream).collect(Collectors.toSet()));
    }

    public  void updateIssueStatus(Issue issue){
        String issueId = issue.getIssueId();
        String newStatus = issue.getStatus().label;
        for(String status : issueByStatus.keySet()){
            Set<String> issuesBuyPreStatus = issueByStatus.get(status);
            if(issuesBuyPreStatus.contains(issueId)){
                if (!newStatus.equals(status)){
                    issuesBuyPreStatus.remove(issueId);
                    issueByStatus.put(status, issuesBuyPreStatus);
                }
            }
        }
        Set<String> issuesBuyNewStatus = issueByStatus.get(newStatus);
        issuesBuyNewStatus.add(issueId);
        issueByStatus.put(newStatus, issuesBuyNewStatus);
    }
}
