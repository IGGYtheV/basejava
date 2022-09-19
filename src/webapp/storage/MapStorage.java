package webapp.storage;

import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected void doUpdate(Resume r, String uuid) {
        map.put(uuid, r);
    }

    @Override
    protected void doSave(String uuid, Resume r) {
        map.put(uuid, r);
    }


    @Override
    public void delete(String uuid) {
        Resume o = map.remove(uuid);
        if (o == null) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);

    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove(uuid);
    }
}

