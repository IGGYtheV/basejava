package webapp.storage;

import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected void replaceUpdatedElement(Resume r, Object index) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void saveInArrayOrList(Object index, Resume r) {
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
    protected boolean isExist(Object index) {
        return index != null;

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected Resume getFromArrayOrList(Object index) {
        return (Resume) index;
    }

    @Override
    protected void deleteFromArrayOrList(Object index) {
        map.remove(((Resume) index).getUuid());
    }
}


