package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ContractCallParameter {
    private String function;
    private String args;
}
