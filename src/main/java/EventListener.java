public class EventListener extends Thread {

    private String messageToListenFor;
    private String messageToReplyWith;
    private Tracker eventTracker;

    public EventListener(String message, String reply) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = EventTracker.getInstance();
    }

    public EventListener(String message, String reply, Tracker tracker) {
        this.messageToListenFor = message;
        this.messageToReplyWith = reply;
        this.eventTracker = tracker;
    }

    public void run() {
        while(!readyToQuit()){
            if(shouldReply()){
                eventTracker.handle(messageToListenFor, this::reply);
            }

        }
    }

    public Boolean readyToQuit() {
        //should this be synced?
        if(this.eventTracker.has("quit")){
            return true;
        }
        return false;
    }

    public Boolean shouldReply() {
        //should this be set to true if there is a matching message at all or if its being handled?
        if(this.eventTracker.has(messageToListenFor)){
            return true;
        }
        return false;
    }

    public void reply() {
        System.out.println(messageToReplyWith);
    }
}