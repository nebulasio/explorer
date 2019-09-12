package io.nebulas.explorer.model.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.nebulas.explorer.domain.NaxStage;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NaxStageVo {
    private long stage;
    private long start;
    private long end;
    private String estimateNax;
    private String distributedNax;
    private String destroyedNax;
    private String totalNax;
    private String pledgeNas;
    private String totalNas;
    private Date createdAt;

    public NaxStageVo(){

    }

    public NaxStageVo(NaxStage stage){
        this.stage = stage.getStage();
        this.start = stage.getStart();
        this.end = stage.getEnd();
        this.estimateNax = stage.getEstimateNax().stripTrailingZeros().toPlainString();
        this.distributedNax = stage.getActualNax().stripTrailingZeros().toPlainString();
        this.destroyedNax = stage.getDestroyedNax().stripTrailingZeros().toPlainString();
        this.totalNax = stage.getTotalNax().stripTrailingZeros().toPlainString();
        this.pledgeNas = stage.getPledgeNas().stripTrailingZeros().toPlainString();
        this.totalNas = stage.getTotalNas().stripTrailingZeros().toPlainString();
        this.createdAt = stage.getCreatedAt();
    }
}
