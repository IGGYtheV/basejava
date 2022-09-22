package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {


    protected static final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;



    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        if (STORAGE_LIMIT <= size) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    protected abstract void insertElement(Resume r, int index);


    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doDelete(Integer index) {
        size--;
        fillDeletedElement(index);
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
