package kz.ukarim.projects.example.api;

import kz.ukarim.projects.example.service.HelloWorldService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("example")
public class HelloWorldResource {

    private HelloWorldService helloWorldService;

    public HelloWorldService getHelloWorldService() {
        return helloWorldService;
    }

    @Inject
    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        String hello = helloWorldService.sayHello();
        return Response.ok(hello).build();
    }

}
