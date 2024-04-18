package org.queues;

import jakarta.enterprise.context.ApplicationScoped;
import org.dataBaseClass.Student;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class QueueConsumer {
    Student student;
    @Channel("responseIncomingQueue")
    Emitter<Student> emitter;

    @Incoming("requestIncomingQueue")
    public void Listener(String msg) {
        System.out.println("Получил " + msg);
        if (msg.equals("getClass"))
            student = new Student("Ivan", "Ivanov");
        emitter.send(student);
    }
}