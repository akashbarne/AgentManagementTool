package com.example.AgentManagementTool.Services;

import com.example.AgentManagementTool.Pojo.AgentInstall;
import com.example.AgentManagementTool.Pojo.AgentStatus;
import com.example.AgentManagementTool.Repositories.AgentInstallImpl;

import java.util.List;

public class AgentInstallService {
    private AgentInstallImpl agentInstall = new AgentInstallImpl();

    public String SaveData(String ip, AgentInstall agentStatus){
        return agentInstall.SaveData(ip, agentStatus);
    }

    public void InstallAgent(String ip, AgentInstall agentdata){
         agentInstall.InstallAgent(ip,agentdata);
    }

    public String[] getIPs(String s){
        return agentInstall.getIPs(s);
    }

    public List<AgentStatus> getData(){
        return agentInstall.getData();
    }

}
