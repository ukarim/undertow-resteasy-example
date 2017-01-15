package kz.ukarim.projects.example.api;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ExampleApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(HelloWorldResource.class);
        return classes;
    }
}
