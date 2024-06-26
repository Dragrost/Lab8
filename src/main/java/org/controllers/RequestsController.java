package org.controllers;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dataBaseClass.Student;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Path("/request")
public class RequestsController {
    private static final Logger LOG = Logger.getLogger(RequestsController.class);
    Student stud;
    List<Student> listStudents = new ArrayList<>();

    public RequestsController() {
        stud = new Student("Tolya", "Kolya");
        listStudents.add(stud);
        stud = new Student("Mew", "Maw");
        listStudents.add(stud);
    }

    /**
     * Обычный get запрос
     */
    @GET
    @Path("/hello")
    public String print() {
        LOG.info("Отображение Hello");
        return "Hello!";
    }

    /**
     * get с отправкой объекта в json-формате
     */
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(value = "counted.method", extraTags = {"extra", "annotated"})
    @Timed(value = "call", extraTags = {"region", "test"})
    public Student printJSON() {
        LOG.info("Отображение Andrew Black");
        return new Student("Andrew", "Black");
    }

    /**
     * get с параметром @PathParam
     */
    @GET
    @Path("/getPath/{value}/")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("value") String message) {
        return message;
    }

    /**
     * get с параметром @QueryParam
     */
    @GET
    @Path("/getQuery/")
    @Produces(MediaType.APPLICATION_JSON)
    public Student helloQuery(@QueryParam("name") String name, @QueryParam("surname") String surname) {
        return new Student(name, surname);
    }

    /**
     * post с получением объекта в json-формате, переименование и отправка Class.
     */
    @POST
    @Path("/getjson")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getJSON(Student getStud) {
        System.out.println(getStud);
        getStud.setName("NewName");
        return getStud;
    }

    /**
     * post с получением Class в json-формате и отправкой list<Class>
     */
    @POST
    @Path("/getList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getListClass(Student getStud) {
        return Arrays.asList(getStud, new Student("Tolya", "Smirnov"));
    }

    /**
     * post с получением List<Class> в json-формате и отправкой Class
     */
    @POST
    @Path("/getClassList")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getClassFromListClass(List<Student> listStudents) {
        return listStudents.get(0);
    }

    /**
     * IN-MEMORY COLLECTION POST
     */
    @POST
    @Path("/getINMEMORYPost")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getInMemoryClass() {
        return stud;
    }

    /**
     * IN-MEMORY COLLECTION PUT
     */
    @PUT
    @Path("/getINMEMORYPut")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> putInMemory(Student student) {
        listStudents.add(student);
        return listStudents;
    }

    /**
     * IN-MEMORY COLLECTION DELETE
     */
    @DELETE
    @Path("/getINMEMORYDel")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> delInMemory(@QueryParam("name") String name) {
        listStudents.removeIf(student -> student.getName().equals(name));
        return listStudents;
    }

}
