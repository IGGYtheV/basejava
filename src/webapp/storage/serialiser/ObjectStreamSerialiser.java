package webapp.storage.serialiser;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerialiser implements StreamSerialiser {


    @Override
    public void doWrite(Resume r, OutputStream os) {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        } catch (IOException e) {
            throw new StorageException("Error write resume", r.getUuid(), e);
        }
    }

    @Override
    public Resume doRead(InputStream is) {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
