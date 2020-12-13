package platform.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "Code")
public class Code {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long orderTicket;
    private String code;
    private String date;
    private long time;
    private long views;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean timeRestriction;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean viewsRestriction;

    public Code() {
    }

    public Code(String id, long orderTicket, String code, String date, long time, long views,
                boolean timeRestriction, boolean viewsRestriction) {
        this.id = id;
        this.orderTicket = orderTicket;
        this.code = code;
        this.time = time;
        this.date = date;
        this.views = views;
        this.timeRestriction = timeRestriction;
        this.viewsRestriction = viewsRestriction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(boolean timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public boolean isViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(boolean viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    public long getOrderTicket() {
        return orderTicket;
    }

    public void setOrderTicket(long orderTicket) {
        this.orderTicket = orderTicket;
    }
}