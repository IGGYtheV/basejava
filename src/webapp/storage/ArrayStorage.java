package webapp.storage;

import webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    protected void fillDeletedElement(int index) {
        storage[index] = storage[size];
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
