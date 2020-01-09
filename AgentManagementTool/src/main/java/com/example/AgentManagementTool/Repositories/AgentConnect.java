package com.example.AgentManagementTool.Repositories;

import com.example.AgentManagementTool.Pojo.AgentInstall;
import com.example.AgentManagementTool.Services.AgentInstallService;
import com.google.common.io.CharStreams;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static java.util.Arrays.asList;

public class AgentConnect {

    private static String SSH_HOST;
    private static String SSH_LOGIN;
    private static String SSH_PASSWORD;
    public static final AgentInstallService agentInstallService1 = new AgentInstallService();

    public void InstallAgent (String ip, AgentInstall agentdata) {
            try {

                 SSH_HOST = ip;
                 SSH_LOGIN = agentdata.getUserName();
                 SSH_PASSWORD = agentdata.getPassword();
                String agentVersion = agentdata.getAgentVersion();
                String port = agentdata.getPort();
                String agentType = agentdata.getAgentType();
                String consoleIP = agentdata.getConsoleIP();
                String consolePassphrase = agentdata.getPassphrase();

                System.out.println("starting installation");
                if(agentType.equals("TE Agent")) {
                    String script = "echo 'wget http://downloads.tripwirelab.com/static/builds/te-agent-bundle/" + agentVersion + "/" + agentVersion + "/latest/release/rtw/te-agent/" + agentVersion + "/installers/te_agent_" + agentVersion + "_en_linux_amd64.tgz\ntar -xvzf te_agent_" + agentVersion + "_en_linux_amd64.tgz\n./te_agent_" + agentVersion + "_en_linux_amd64/te_agent.bin --silent --eula accept --server-host " + consoleIP + " --server-port " + port + " --passphrase '\"" + consolePassphrase + "\"'\ncd /usr/local/tripwire/te/agent/bin \n./twdaemon start' >> script.sh";
                    System.out.println(script);
                    System.out.println(runCommand(script));
                    System.out.println(runCommand("chmod 777 script.sh"));
                    System.out.println("starting script");
                    System.out.println(runCommand("./script.sh"));
                    System.out.println("installation completed");
                    runCommand("echo 'cd /usr/local/tripwire/te/agent/bin \n./twdaemon status' >> script111.sh");
                    runCommand("chmod 777 script111.sh");
                    List<String> statusofAgent = runCommand("./script111.sh");
                    runCommand("rm -rf script.sh");
                    runCommand("rm -rf script111.sh");
                    System.out.println(statusofAgent.get(0));
                    if(statusofAgent.get(0).contains("Tripwire Enterprise Agent is running")){
                        System.out.println("Installed and running");
                        agentdata.setStatus("Installed and running");
                    }
                    else{
                        System.out.println("not running");
                        agentdata.setStatus("Error in installation");
                    }

                }
                else if(agentType.equals("Axon")){
                    String script = "echo 'wget http://downloads.tripwirelab.com/static/builds/te-axon-bundle/" + agentVersion + "/" + agentVersion + "/latest/release/rtw/axon-agent/" + agentVersion + "/installers/axon_agent_te_" + agentVersion + "_en_linux_x86_64.tgz\n" +
                            "tar -xvzf axon_agent_te_" + agentVersion + "_en_linux_x86_64.tgz\n" +
                            "cd axon_agent_te_" + agentVersion + "_en_linux_x86_64\n" +
                            "rpm -ivh axon-agent-installer-linux-x64.rpm\n" +
                            "cp /etc/tripwire/twagent_sample.conf /etc/tripwire/twagent.conf\n" +
                            "sed -i s/#bridge.host=/bridge.host=" + consoleIP + "/ /etc/tripwire/twagent.conf\n" +
                            "service tripwire-axon-agent start\n" +
                            "service tripwire-axon-agent status' >> script.sh";
                    System.out.println(script);
                    System.out.println(runCommand(script));
                    System.out.println(runCommand("chmod 777 script.sh"));
                    System.out.println("starting script");
                    System.out.println(runCommand("./script.sh"));
                    System.out.println("installation completed");
                    String statusofAgent = runCommand("service tripwire-axon-agent status").toString();
                    runCommand("rm -rf script.sh");
                    System.out.println(statusofAgent);
                    if(statusofAgent.contains("Active: active")){
                        System.out.println("Installed and running");
                        agentdata.setStatus("Installed and running");
                    }
                    else{
                        System.out.println("not running");
                        agentdata.setStatus("Error in installation");
                    }
                    }

                String st = agentInstallService1.SaveData(ip, agentdata);

            } catch (JSchException e) {
            e.printStackTrace();
            }

    }


    private static List<String> runCommand(String command) throws JSchException {
        Session session = setupSshSession();
        session.connect();
        System.out.println("connected successfully");

        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        try {

            channel.setCommand(command);
            channel.setInputStream(null);
            InputStream output = channel.getInputStream();
            channel.connect();

            String result = CharStreams.toString(new InputStreamReader(output));
            return asList(result.split("\n"));

        } catch (Exception e) {
            closeConnection(channel, session);
            throw new RuntimeException(e);

        } finally {
            closeConnection(channel, session);
        }
    }

    private static Session setupSshSession() throws JSchException {
        Session session = new JSch().getSession(SSH_LOGIN, SSH_HOST, 22);
        session.setPassword(SSH_PASSWORD);
        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
        session.setConfig("StrictHostKeyChecking", "no"); // disable check for RSA key
        return session;
    }

    private static void closeConnection(ChannelExec channel, Session session) {
        try {
            channel.disconnect();
            System.out.println("connection close successfully");
        } catch (Exception ignored) {
        }
        session.disconnect();
    }
}