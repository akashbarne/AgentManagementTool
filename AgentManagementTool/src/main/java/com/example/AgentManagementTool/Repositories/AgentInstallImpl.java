package com.example.AgentManagementTool.Repositories;

import com.example.AgentManagementTool.Pojo.AgentInstall;
import com.example.AgentManagementTool.Pojo.AgentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AgentInstallImpl {


    AgentConnect agentConnect = new AgentConnect();

    @Autowired
    CreateJdbcTemplate createJdbcTemplate = new CreateJdbcTemplate();
    JdbcTemplate jdbcTemplate;
    {
        try {
            jdbcTemplate = createJdbcTemplate.CreateTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String SaveData(String ip, AgentInstall agentStatus){

        int i = jdbcTemplate.update("insert into agent_status(agent_ip,agent_type,agent_version,console_ip,console_port,agent_status)\n values('"+ip+"','"+agentStatus.getAgentType()+"','"+agentStatus.getAgentVersion()+"','"+agentStatus.getConsoleIP()+"','"+agentStatus.getPort()+"','"+agentStatus.getStatus()+"')");
        if (i==0){
          return "Failed to insert data in DB";
        }
        return "";
    }

    public List<AgentStatus> getData(){

        String sql = "SELECT * FROM agent_status";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new AgentStatus(
                        rs.getString("agent_ip"),
                        rs.getString("agent_version"),
                        rs.getString("agent_type"),
                        rs.getString("console_ip"),
                        rs.getString("agent_status")
                ));
    }

    public void InstallAgent(String ip, AgentInstall agentdata){
        agentConnect.InstallAgent(ip,agentdata);
    }

    public  String [] getIPs(String range){
        range=range.replaceAll(" ","");
        String startingIP = range.substring(0,range.indexOf("-"));
        String str =startingIP.substring(startingIP.lastIndexOf(".")+1);
        int start = Integer.parseInt(str);
        String str1 = range.substring(range.lastIndexOf(".")+1);
        int end = Integer.parseInt(str1);
        String IP = startingIP.substring(0,startingIP.lastIndexOf(".")+1);
        String[] IPs = new String[255];
        int q =start;
        for(int i=0;i<=end-start;i++){
            IPs[i] = IP+""+q+"";
            q++;
            System.out.println(IPs[i]);
        }
        return  IPs;
    }


}
