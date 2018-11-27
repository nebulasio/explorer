package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event implements Serializable {

    private String topic;
    private String data;

}
