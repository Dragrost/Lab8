package org.queues;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.dataBaseClass.Student;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.io.IOException;

@ApplicationScoped
@Path("/")
public class QueueProducer {
    Student student;
    @Channel("requestQueue")
    Emitter<String> emitter;

    @Incoming("responseQueue")
    public void Listener(JsonObject jsonObject) {
        System.out.println("Принял запрос, обрабатываю: " + jsonObject);
        try {
            student = new ObjectMapper().readValue(jsonObject.toString(), Student.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @POST
    @Path("/postClass")
    @Produces(MediaType.APPLICATION_JSON)
    public Student postStud() throws InterruptedException {
        System.out.println("Отправляю запрос");
        emitter.send("getClass");
        Thread.sleep(500);
        System.out.println("Отправляю..." + student.getInfo());
        return this.student;
    }
}
