package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("update: no such element: " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        if (STORAGE_LIMIT <= size) {
            System.out.println("save: no space in array");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("save. resume already exists: " + r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("delete: no such element: " + uuid);
        } else {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
