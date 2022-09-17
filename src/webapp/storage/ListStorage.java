package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage  {
    List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }


    @Override
    protected void replaceUpdatedElement(Resume r, Object index) {
        list.set((Integer) index, r);
    }

    @Override
    protected void saveInArrayOrList(Object index, Resume r) {
        list.add(-(Integer)index - 1, r);
    }

    @Override
    protected Resume getFromArrayOrList(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected void deleteFromArrayOrList(Object index) {
        list.remove(((Integer) index).intValue());
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


