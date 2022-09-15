package webapp.storage;

import webapp.model.Resume;

public abstract class AbstractStorage  implements Storage {

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public int size() {
        return 0;
    }
}
