package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
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
            System.out.println("update: no such element: " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (STORAGE_LIMIT <= size) {
            System.out.println("save: no space in array");
        } else if (index > 0) {
            System.out.println("save. resume already exists: " + r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    protected abstract void insertElement(Resume r, int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("delete: no such element: " + uuid);
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
            System.out.println("get: no such element: " + uuid);
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
