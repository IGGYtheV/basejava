package webapp.storage;

import webapp.util.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}