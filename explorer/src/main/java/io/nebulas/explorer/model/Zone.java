package io.nebulas.explorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Zone {
    private long from;
    private long to;
}
