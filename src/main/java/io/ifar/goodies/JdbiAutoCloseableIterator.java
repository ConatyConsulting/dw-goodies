package io.ifar.goodies;

import org.skife.jdbi.v2.ResultIterator;

/**
 * Builds AutoCloseableIterator for JDBI ResultIterators.
 *
 * By keeping this separate from {@link AutoCloseableIterator}, classes in non DropWizard/JDBI projects are safe to reference
 * {@link AutoCloseableIterator}.
 *
 * @deprecated Recent versions of jDBI have {@link java.io.Closeable} support.
 */
public class JdbiAutoCloseableIterator {
    public static <T> AutoCloseableIterator<T> wrap(final ResultIterator<T> wrapped) {
        return new AutoCloseableIterator <T>(wrapped, new AutoCloseable() {
            @Override
            public void close() throws Exception {
                ((ResultIterator<T>) wrapped).close();
            }
        });
    }
}
