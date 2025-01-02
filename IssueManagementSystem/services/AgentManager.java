package services;

import model.Agent;
import model.Issue;
import enums.Skill;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AgentManager {
    private ConcurrentHashMap<String, Agent> allAgents;
    private static final Random rand = new Random();

    public AgentManager(){
        allAgents = new ConcurrentHashMap<>();
    }
    public String createAgent(List<String> skills){
        Set<Skill> agentSkills = new HashSet<>();
        for(String skilLabel : skills){
            agentSkills.add(Skill.validOfLabel(skilLabel));
        }
        String rand_int1 = String.valueOf(rand.nextInt(100000));
        Agent agent = new Agent(rand_int1, agentSkills);
        allAgents.put(rand_int1, agent);
        return rand_int1;
    }

    public void addSkill(String agentId, List<String> skill){
        Agent agent = getAgentFromId(agentId);
        agent.addSkill(skill);
    }
    public Agent getAgentFromId(String agentId){
        return allAgents.get(agentId);
    }
    public List<String> getAllAssignedIssues(String agentId){
        Agent agent = getAgentFromId(agentId);
        return agent.getAllIssues();
    }
    public List<String> getAllIssuesByStatus(String agentId, String status){
        Agent agent = getAgentFromId(agentId);
        return agent.getIssueIdsByStatus(status);
    }

    public void assignIssue(String agentId, Issue issue){
        Agent agent = getAgentFromId(agentId);
        agent.assignIssue(issue);
    }

    public void removeAssignedIssue(String agentId, Issue issue){
        Agent agent = getAgentFromId(agentId);
        agent.removeAssignedIssue(issue);
    }

    public void updateIssueStatus(String agentId, Issue issue){
        Agent agent = getAgentFromId(agentId);
        agent.updateIssueStatus(issue);
    }

}
