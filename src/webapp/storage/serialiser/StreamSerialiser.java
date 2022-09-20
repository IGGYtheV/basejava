package webapp.storage.serialiser;

import webapp.model.Resume;

import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerialiser {
    void doWrite(Resume r, OutputStream os);

    Resume doRead(InputStream is);
}