package utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * @author jfoley.
 */
public class TmpFile implements Closeable {
    private static final Logger logger = Logger.getLogger(TmpFile.class.getName());

    private final File fp;

    public TmpFile() throws IOException {
        this.fp = File.createTempFile("bimito", "tmp");
        this.fp.deleteOnExit();
    }

    public File get() {
        return this.fp;
    }

    @Override
    public void close() throws IOException {
        if (!this.fp.delete()) {
            logger.warning("Couldn't delete temporary file: " + this.fp.getAbsolutePath());
        }
    }

    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(get());
    }

    public String getPath() {
        return fp.getAbsolutePath();
    }
}
