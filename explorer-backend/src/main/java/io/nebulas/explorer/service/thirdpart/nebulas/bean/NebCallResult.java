package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NebCallResult {
    private String execute_err;
    private String result;

    public boolean hasError(){
        boolean isEmpty = StringUtils.isEmpty(execute_err);
        if (isEmpty) {
            return false;
        }
        boolean insufficientBalance = execute_err.equals("insufficient balance");
        return !insufficientBalance;
    }
}
