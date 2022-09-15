package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.removeAll(storage);
    }

    @Override
    protected void replaceUpdatedElement(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void saveInArrayOrList(int index, Resume r) {
        storage.add(-index - 1, r);
    }

    @Override
    protected Resume getFromArrayOrList(int index) {
        return storage.get(index);
    }

    @Override
    protected void deleteFromArrayOrList(int index) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Collections.binarySearch(storage, searchKey);
    }
}


