package com.thales.kafka.producer.dtos.requests;

import java.io.Serializable;
import java.util.Objects;

public class SendMessageToTestRequest implements Serializable {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMessageToTestRequest that = (SendMessageToTestRequest) o;
        return Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg);
    }
}
