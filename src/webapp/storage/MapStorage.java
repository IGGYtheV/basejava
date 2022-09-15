package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void update(Resume r) {
        Resume o = storage.replace(r.getUuid(), r);
        if (o == null) {
            throw new NotExistStorageException(r.getUuid());
        }


//        if (!storage.containsKey(resume.getUuid())) {
//            throw new NotExistStorageException(resume.getUuid());
//        } else {
//            storage.replace(resume.getUuid(), resume);
//        }
    }

    @Override
    public void save(Resume r) {
        if (storage.containsKey(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        } else {
            storage.put(r.getUuid(), r);
        }
    }

    @Override
    public void delete(String uuid) {
        Resume o = storage.remove(uuid);
        if (o == null) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume o = storage.get(uuid);
        if (o == null) {
            throw new NotExistStorageException(uuid);
        } else {
            return o;
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }
}


