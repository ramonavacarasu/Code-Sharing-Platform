package platform.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "Code")
public class Code {

    @Id
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @Column
    private String code;

    @Column
    private String date;

    public Code() {
    }

    public Code(String id, String code, String date) {
        this.id = id;
        this.code = code;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
