package webapp.storage;

import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK>  implements Storage {
//    protected final Logger log = Logger.getLogger(getClass().getName());
    private static final Logger LOG= Logger.getLogger(AbstractStorage.class.getName());
    protected final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);



    protected abstract SK getSearchKey(String uuid);
    protected abstract List<Resume> doCopyAll();

    @Override
    public void update(Resume resume) {
        LOG.info("update" + resume);
        SK searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }
    protected abstract void doUpdate(Resume r, SK searchKey);

    @Override
    public void save(Resume r) {
        LOG.info("save" + r);

        SK searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }
    protected abstract void doSave(Resume r, SK searchKey);

    @Override
    public Resume get(String uuid) {
        LOG.info("get" + uuid);

        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }
    protected abstract Resume doGet(SK searchKey);

    @Override
    public void delete(String uuid) {
        LOG.info("delete" + uuid);

        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }
    protected abstract void doDelete(SK searchKey);



    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " doesn't exist");

            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected boolean isExist(SK index) {
        return (Integer) index >= 0;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}

