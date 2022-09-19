package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.*;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(Path directory) {
        super(directory.toString());
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
