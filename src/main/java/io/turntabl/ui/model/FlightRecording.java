package io.turntabl.ui.model;

public class FlightRecording {

    private String startTime;
    private String duration;
    private String endTime;
    private String eventThread;
    private int id;
    private String name;
    private String destination;
    private String max_age;
    private String max_size;
    private String recording_duration;

    public FlightRecording(String startTime, String duration, String endTime, String eventThread, int id, String name, String destination, String max_age, String max_size, String recording_duration) {
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
        this.eventThread = eventThread;
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.max_age = max_age;
        this.max_size = max_size;
        this.recording_duration = recording_duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEventThread() {
        return eventThread;
    }

    public void setEventThread(String eventThread) {
        this.eventThread = eventThread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMax_age() {
        return max_age;
    }

    public void setMax_age(String max_age) {
        this.max_age = max_age;
    }

    public String getMax_size() {
        return max_size;
    }

    public void setMax_size(String max_size) {
        this.max_size = max_size;
    }

    public String getRecording_duration() {
        return recording_duration;
    }

    public void setRecording_duration(String recording_duration) {
        this.recording_duration = recording_duration;
    }
}
