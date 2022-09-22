package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r, Resume index) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        map.put(r.getUuid(), r);
    }

//    @Override
//    public List<Resume> getAllSorted() {
//        List<Resume> = map.values();
//        return null;
//    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    protected boolean isExist(Resume index) {
        return index != null;

    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected Resume doGet(Resume index) {
        return index;
    }

    @Override
    protected void doDelete(Resume index) {
        map.remove(index.getUuid());
    }
}


