// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import controller.IssueController;
import services.AgentManager;
import services.IssueManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Main execution start here\n");
//        for( String arg : args){
//            System.out.println(arg+"\n");
//        }
//        Scanner myObj = new Scanner(System.in);
//        System.out.println(myObj.nextLine());
        AgentManager agentManager = new AgentManager();
        IssueManager issueManager  = new IssueManager(agentManager);
        IssueController issueController = new IssueController(issueManager, agentManager);
        String agentId1 = issueController.addNewAgent(new ArrayList<String>(Arrays.asList("java","Python")));
        //System.out.println("agent id");
        //System.out.println(agentId1);
        String agentId3 = issueController.addNewAgent(new ArrayList<String>(Arrays.asList("java")));
        //System.out.println("agent id");
        //System.out.println(agentId3);
        String agentId2 = issueController.addNewAgent(new ArrayList<String>(Arrays.asList("quality assurance")));
        //System.out.println(agentId3);
        String issueId1 = issueController.creteIssue("test issue1","new","bug","test new issue ");
        //System.out.println(issueId1);
        String issueId2 = issueController.creteIssue("test issue2","new","work item","test new issue ");
        //System.out.println(issueId2);
        String issueId4 = issueController.creteIssue("test issue4","new","work item","test new issue ");
        String issueId6 = issueController.creteIssue("test issue6","in progress","work item","test new issue ");
        //System.out.println(issueId4);
        issueController.assignIssue(issueId6, agentId1);
        System.out.println("Assigned "+issueId6+" to "+agentId1);
        issueController.assignIssue(issueId1, agentId1);
        System.out.println("Assigned "+issueId1+" to "+agentId1);
        issueController.assignIssue(issueId4, agentId1);
        System.out.println("Assigned "+issueId4+" to "+agentId1);
        issueController.assignIssue(issueId2, agentId2);
        System.out.println("Assigned "+issueId2+" to "+agentId2);
        String issueId3 = issueController.creteAndAssignIssue("test issue3","New","bug","test new issue ", agentId1);
        String issueId5 = issueController.creteAndAssignIssue("test issue5","New","bug","test new issue ", agentId3);
        System.out.println("Assigned "+issueId3+" to "+agentId1);
        System.out.println(issueController.getAgentHistory(agentId2));
        issueController.assignIssue(issueId3,agentId2);
        System.out.println("Assigned "+issueId3+" to "+agentId2);
        System.out.println(agentId1+"----"+issueController.getAgentHistory(agentId1));
        System.out.println(agentId2+"----"+issueController.getAgentHistory(agentId2));
        System.out.println(agentId3+"----"+issueController.getAgentHistory(agentId3));
        System.out.println(issueController.getAgentIssuesByStatus(agentId1, "new"));
        System.out.println(issueController.getAgentIssuesByStatus(agentId1, "in progress"));
    }
}