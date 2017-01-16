package kz.ukarim.projects.example;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import kz.ukarim.projects.example.api.ExampleApplication;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import static io.undertow.Handlers.resource;

public class Server {

    public static void main(String[] args) {

        UndertowJaxrsServer server = new UndertowJaxrsServer();

        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setApplicationClass(ExampleApplication.class.getName());
        deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment, "/");
        deploymentInfo.setClassLoader(Server.class.getClassLoader());
        deploymentInfo.setDeploymentName("Undertow + Resteasy example");
        deploymentInfo.setContextPath("/api");

        deploymentInfo.addListener(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));

        server.deploy(deploymentInfo);

        server.addResourcePrefixPath("/",
                resource(new ClassPathResourceManager(Server.class.getClassLoader()))
                        .addWelcomeFiles("index.html"));

        Undertow.Builder builder = Undertow.builder()
                .addHttpListener(8080, "localhost");

        server.start(builder);

    }

}
