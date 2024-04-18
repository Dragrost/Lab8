package org.queues;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

@ApplicationScoped
@Path("/")
public class QueueProducer {
    Student student;
    @Channel("requestQueue")
    Emitter<String> emitter;

    @Incoming("responseQueue")
    public void Listener(JsonObject jsonObject) {
        System.out.println("Принял запрос, обрабатываю: " + jsonObject);
        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(jsonObject.toString());
        Gson gson = new Gson();
        student = gson.fromJson(mJson, Student.class);
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
