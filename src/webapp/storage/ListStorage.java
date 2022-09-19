package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }


    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        list.set(searchKey, r);
    }

    @Override
    protected void doSave(Integer searchKey, Resume r) {
        list.add(-(Integer)searchKey - 1, r);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove(searchKey.intValue());
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "NewName");
        return Collections.binarySearch(list, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return list;
    }
}


