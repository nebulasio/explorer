package io.nebulas.explorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DposContext implements Serializable {
    private String dynastyRoot;
    private String nextDynastyRoot;
    private String delegateRoot;
    private String candidateRoot;
    private String voteRoot;
    private String mintCntRoot;
}
