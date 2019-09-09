package io.nebulas.explorer.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebTransactionEvent {
    private int id;
    private String txHash;
    private long block;
    private String topic;
    private String data;
    private Date timestamp;
    private Date createdAt;
}
