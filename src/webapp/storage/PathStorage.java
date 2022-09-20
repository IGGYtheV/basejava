package webapp.storage;

import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.serialiser.StreamSerialiser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerialiser streamSerialiser;



    protected PathStorage(String dir, StreamSerialiser streamSerialiser) {
        this.streamSerialiser = streamSerialiser;
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
//        OLD VERSION (works):
//        String[] list = directory.toFile().list();
//        if (list == null) {
//            throw new StorageException("Directory read error", null);
//        }
//        return list.length;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);

        //        OLD VERSION (works):
//        File file = new  File(directory.toFile(), uuid);
//        return file.toPath();
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamSerialiser.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
        //        OLD VERSION (works):
//        return Files.exists(path);
    }

    @Override
    protected void doSave(Path path, Resume r) {
        try {
           Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path, getFileNameString(path), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerialiser.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileNameString(path), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileNameString(path), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesList().map(this::doGet)
                .collect(Collectors.toList());

//        OLD VERSION (works)
//        File[] paths = directory.toFile().listFiles();
//        if (paths == null) {
//            throw new StorageException("Directory read error", null);
//        }
//        List<Resume> list = new ArrayList<>(paths.length);
//        for (File path : paths) {
//            list.add(doGet(path.toPath()));
//        }
//        return list;
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
    private String getFileNameString(Path path){
        return path.getFileName().toString();
    }
}
