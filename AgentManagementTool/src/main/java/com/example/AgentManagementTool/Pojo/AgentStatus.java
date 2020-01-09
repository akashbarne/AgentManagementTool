package com.example.AgentManagementTool.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentStatus {
    private String agentIP;
    private String agentVersion;
    private String agentType;
    private String consoleIP;
    private String status;


    public String getAgentIP() {
        return agentIP;
    }

    public void setAgentIP(String agentIP) {
        this.agentIP = agentIP;
    }

    public String getAgentVersion() {
        return agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getConsoleIP() {
        return consoleIP;
    }

    public void setConsoleIP(String consoleIP) {
        this.consoleIP = consoleIP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AgentStatus(String agentIP, String agentVersion, String agentType, String consoleIP, String status) {
        this.agentIP = agentIP;
        this.agentVersion = agentVersion;
        this.agentType = agentType;
        this.consoleIP = consoleIP;
        this.status = status;
    }
}
