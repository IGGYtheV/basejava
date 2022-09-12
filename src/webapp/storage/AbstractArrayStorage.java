package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (index > 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            size--;
            fillDeletedElement(index);
            storage[size] = null;
        }
    }

    protected abstract void fillDeletedElement(int index);

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
