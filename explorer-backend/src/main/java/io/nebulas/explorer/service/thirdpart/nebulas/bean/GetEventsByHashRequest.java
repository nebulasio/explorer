package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventsByHashRequest implements Serializable {
    private String hash;
}
