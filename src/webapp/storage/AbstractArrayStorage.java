package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void replaceUpdatedElement(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void saveInArrayOrList(int index, Resume r) {
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    protected abstract void insertElement(Resume r, int index);


    @Override
    protected Resume getFromArrayOrList(int index) {
        return storage[index];
    }

    @Override
    protected void deleteFromArrayOrList(int index) {
        size--;
        fillDeletedElement(index);
        storage[size] = null;
    }
    protected abstract void fillDeletedElement(int index);

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
