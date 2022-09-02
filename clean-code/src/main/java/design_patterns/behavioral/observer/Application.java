package design_patterns.behavioral.observer;

public class Application {

    public static void main(String[] args) {
        var subject = new EventsBus();
        subject.addConsumer(new Logger());
        subject.addConsumer(new EmailSender());
        subject.publish(new ServerEvent("[payload]"));
    }

}
