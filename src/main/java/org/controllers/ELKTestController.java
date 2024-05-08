package org.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dataBaseClass.Student;
import org.jboss.logging.Logger;

@ApplicationScoped
@Path("/elk")
public class ELKTestController {
    private static final Logger LOG = Logger.getLogger(ELKTestController.class);

    @GET
    @Path("/hello")
    public String print() {
        LOG.info("Отображение Hello");
        return "Hello!";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Student printJSON() {
        LOG.warn("Лог другого типа");
        return new Student("Andrew", "Black");
    }
}
