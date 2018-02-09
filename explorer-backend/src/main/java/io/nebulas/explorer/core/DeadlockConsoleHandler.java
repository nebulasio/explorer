package io.nebulas.explorer.core;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class DeadlockConsoleHandler implements DeadlockHandler {

    @Override
    public void handleDeadlock(ThreadInfo[] deadlockedThreads) {
        if (deadlockedThreads != null) {
            log.error("Deadlock detected!");
            for (ThreadInfo threadInfo : deadlockedThreads) {
                if (threadInfo != null) {
                    for (Thread thread : Thread.getAllStackTraces().keySet()) {
                        if (thread.getId() == threadInfo.getThreadId()) {
                            log.error(threadInfo.toString().trim());

                            for (StackTraceElement ste : thread.getStackTrace()) {
                                log.error("\t" + ste.toString().trim());
                            }
                        }
                    }
                }
            }
        }
    }

}
