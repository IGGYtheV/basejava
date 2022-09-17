package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.*;

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
    protected void replaceUpdatedElement(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void saveInArrayOrList(Object index, Resume r) {
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (Integer) index);
            size++;
        }
    }

    protected abstract void insertElement(Resume r, int index);


    @Override
    protected Resume getFromArrayOrList(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void deleteFromArrayOrList(Object index) {
        size--;
        fillDeletedElement((Integer) index);
        storage[size] = null;
    }
    protected abstract void fillDeletedElement(int index);

    @Override
    public List<Resume> doCopyAll() {

        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public int size() {
        return size;
    }
}
