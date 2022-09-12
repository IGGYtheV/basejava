package webapp.storage;

import webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void insertElement(Resume r, int index) {
        int insertIndex = Math.abs(index) - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - index - 1);
        storage[insertIndex] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
