package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
@Data
public class GetDynastyResponse implements Serializable {
    private List<String> delegatees;
}
