package com.example.AgentManagementTool.Controller;

import com.example.AgentManagementTool.Pojo.AgentInstall;
import com.example.AgentManagementTool.Pojo.AgentStatus;
import com.example.AgentManagementTool.Services.AgentInstallService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class controller {

    public static final AgentInstallService agentInstallService = new AgentInstallService();


    @CrossOrigin(origins = "http://10.248.139.45:3000")
    @PostMapping("/installAgent")
    public AgentInstall InsallAgent(@RequestBody AgentInstall agentData){
        System.out.println(agentData);
        String[] ipaddresses = new String[255];
        if(agentData.getAgentIP().contains("-")){
            ipaddresses =  agentInstallService.getIPs(agentData.getAgentIP());

            for(int i = 0 ; ipaddresses[i]!=null ; i++){
                System.out.println(i);
                agentInstallService.InstallAgent(ipaddresses[i], agentData);
            }
        }
        else {
            agentInstallService.InstallAgent(agentData.getAgentIP(),agentData);
        }
        return agentData;
    }

    @CrossOrigin(origins = "http://10.248.139.45:3000")
    @GetMapping("/getAgent")
    public List<AgentStatus> getData(){
        return agentInstallService.getData();
    }
}
