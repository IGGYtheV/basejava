package webapp.storage;

import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected void replaceUpdatedElement(Resume r, Object uuid) {
        map.put((String) uuid, r);
    }

    @Override
    protected void saveInArrayOrList(Object uuid, Resume r) {
        map.put((String) uuid, r);
    }


    @Override
    public void delete(String uuid) {
        Resume o = map.remove(uuid);
        if (o == null) {
            throw new NotExistStorageException(uuid);
        }
    }

//    @Override
//    public List<Resume> getAllSorted() {
//        return null;
//    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey((String) uuid);

    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());    }

    @Override
    protected Resume getFromArrayOrList(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    protected void deleteFromArrayOrList(Object uuid) {
        map.remove((String) uuid);
    }
}


