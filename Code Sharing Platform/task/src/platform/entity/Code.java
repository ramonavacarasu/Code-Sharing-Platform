package platform.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String id;

    String code;
    String date;

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
