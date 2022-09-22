package webapp.storage;

import webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    protected void fillDeletedElement(int index) {
        storage[index] = storage[size];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
