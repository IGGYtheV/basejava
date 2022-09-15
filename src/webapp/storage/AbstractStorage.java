package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

public abstract class AbstractStorage  implements Storage {

//    @Override
//    public void clear() {
//    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            replaceUpdatedElement(resume, index);
        }
    }
    protected abstract void replaceUpdatedElement (Resume r, int index);

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveInArrayOrList(index, r);
        }
    }
    protected abstract void saveInArrayOrList(int index, Resume r);

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getFromArrayOrList(index);
    }
    protected abstract Resume getFromArrayOrList(int index);

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteFromArrayOrList(index);
        }
    }
    protected abstract void deleteFromArrayOrList(int index);

//    @Override
//    public Resume[] getAll() {return null;}
//
//    @Override
//    public int size() {
//        return 0;
//    }

    protected abstract int getIndex(String uuid);
}
