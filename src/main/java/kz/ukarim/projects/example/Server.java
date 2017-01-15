package kz.ukarim.projects.example;

import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import kz.ukarim.projects.example.api.ExampleApplication;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

public class Server {

    public static void main(String[] args) {

        UndertowJaxrsServer server = new UndertowJaxrsServer();

        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setApplicationClass(ExampleApplication.class.getName());

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment, "/");
        deploymentInfo.setClassLoader(Server.class.getClassLoader());
        deploymentInfo.setDeploymentName("Undertow + Resteasy example");
        deploymentInfo.setContextPath("/api");

        server.deploy(deploymentInfo);

        Undertow.Builder builder = Undertow.builder()
                .addHttpListener(8080, "localhost");

        server.start(builder);

    }

}
