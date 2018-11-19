package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetEventsByHashResponse implements Serializable {
    private List<Event> events;
}
