package io.nebulas.explorer.core;

import java.lang.management.ThreadInfo;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-31
 */
public interface DeadlockHandler {
    void handleDeadlock(final ThreadInfo[] deadlockedThreads);
}
