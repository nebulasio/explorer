package io.nebulas.explorer.util;

import org.junit.Test;

/**
 * Created by Bill Lv on 24/01/2018.
 */
public class IdGeneratorTest {
    @Test
    public void getId() throws Exception {
        for (int pos = 0; pos < 5; pos++) {
            String id = IdGenerator.getId();
            System.out.println(String.format("id: %s, len: %d", id, id.length()));
        }
    }

}