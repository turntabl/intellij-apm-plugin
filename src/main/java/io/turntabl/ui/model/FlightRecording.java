package io.turntabl.ui.model;

public class FlightRecording {

    private String StartTime;
    private String Duration;
    private String EndTime;
    private String EventThread;
    private int Id;
    private String Name;
    private String Destination;
    private String MaxAge;
    private String MaxSize;
    private String RecordingDuration;

    public FlightRecording() {
    }

    public FlightRecording(String StartTime, String Duration, String EndTime, String EventThread, int Id, String Name, String Destination, String MaxAge, String MaxSize, String RecordingDuration) {
        this.StartTime = StartTime;
        this.Duration = Duration;
        this.EndTime = EndTime;
        this.EventThread = EventThread;
        this.Id = Id;
        this.Name = Name;
        this.Destination = Destination;
        this.MaxAge = MaxAge;
        this.MaxSize = MaxSize;
        this.RecordingDuration = RecordingDuration;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getEventThread() {
        return EventThread;
    }

    public void setEventThread(String eventThread) {
        this.EventThread = EventThread;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public String getDestination(){
        return Destination;
    }

    public void setDestination(String destination) {
        this.Destination = Destination;
    }

    public String getMax_age() {
        return MaxAge;
    }

    public void setMax_age(String MaxAge) {
        this.MaxAge = MaxAge;
    }

    public String getMax_size() {
        return MaxSize;
    }

    public void setMax_size(String MaxSize) {
        this.MaxSize = MaxSize;
    }

    public String getRecording_duration() {
        return RecordingDuration;
    }

    public void setRecording_duration(String RecordingDuration) {
        this.RecordingDuration = RecordingDuration;
    }
}
