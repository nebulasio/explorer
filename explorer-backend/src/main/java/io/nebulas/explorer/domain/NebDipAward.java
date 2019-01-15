package io.nebulas.explorer.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebDipAward {
    private int id;
    private String contract;
    private String creator;
    private String award;
    private String txHash;
    private Date txTimestamp;
    private int startHeight;
    private int endHeight;
    private int year;
    private int week;
    private Date createdAt;
}
