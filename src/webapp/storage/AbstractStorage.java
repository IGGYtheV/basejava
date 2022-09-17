package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage  implements Storage {
    protected final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    protected abstract Object getSearchKey(String uuid);
    protected abstract List<Resume> doCopyAll();

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        replaceUpdatedElement(resume, searchKey);
    }
    protected abstract void replaceUpdatedElement (Resume r, Object searchKey);

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        saveInArrayOrList(searchKey, r);
    }
    protected abstract void saveInArrayOrList(Object searchKey, Resume r);

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return getFromArrayOrList(searchKey);
    }
    protected abstract Resume getFromArrayOrList(Object searchKey);

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        deleteFromArrayOrList(searchKey);
    }
    protected abstract void deleteFromArrayOrList(Object searchKey);



    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}

