package io.nebulas.explorer.util;

import org.bson.types.ObjectId;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-24
 */
public class IdGenerator {
    public static String getId() {
        return ObjectId.get().toHexString();
    }
}
