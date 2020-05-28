package domain;

import java.util.Date;

public class BrokerMessageLog {

    private String message_id;
    private String message;
    private int try_count;
    private int status;
    private Date next_retry;
    private Date create_time;
    private Date update_time;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTry_count() {
        return try_count;
    }

    public void setTry_count(int try_count) {
        this.try_count = try_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getNext_retry() {
        return next_retry;
    }

    public void setNext_retry(Date next_retry) {
        this.next_retry = next_retry;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
