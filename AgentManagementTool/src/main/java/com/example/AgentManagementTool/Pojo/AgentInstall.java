package com.example.AgentManagementTool.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AgentInstall {
    @JsonProperty("agentIP")
    private String agentIP;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("agentVersion")
    private String agentVersion;

    @JsonProperty("agentType")
    private String agentType;

    @JsonProperty("consoleIP")
    private String consoleIP;

    @JsonProperty("passphrase")
    private String passphrase;

    @JsonProperty("port")
    private String port;

    private String status;

    public AgentInstall(String agentIP, String userName, String password, String agentVersion, String agentType, String consoleIP, String passphrase, String port,String status) {
        this.agentIP = agentIP;
        this.userName = userName;
        this.password = password;
        this.agentVersion = agentVersion;
        this.agentType = agentType;
        this.consoleIP = consoleIP;
        this.passphrase = passphrase;
        this.port = port;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentIP() {
        return agentIP;
    }

    public void setAgentIP(String agentIP) {
        this.agentIP = agentIP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentVersion() {
        return agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    }

    public String getConsoleIP() {
        return consoleIP;
    }

    public void setConsoleIP(String consoleIP) {
        this.consoleIP = consoleIP;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "AgentInstall{" +
                "agentIP='" + agentIP + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", agentVersion='" + agentVersion + '\'' +
                ", agentType='" + agentType + '\'' +
                ", consoleIP='" + consoleIP + '\'' +
                ", passphrase='" + passphrase + '\'' +
                ", port='" + port + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentInstall that = (AgentInstall) o;
        return Objects.equals(agentIP, that.agentIP) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(agentVersion, that.agentVersion) &&
                Objects.equals(agentType, that.agentType) &&
                Objects.equals(consoleIP, that.consoleIP) &&
                Objects.equals(passphrase, that.passphrase) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentIP, userName, password, agentVersion, agentType, consoleIP, passphrase, port);
    }
}
