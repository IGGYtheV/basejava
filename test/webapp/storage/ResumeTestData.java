package webapp.storage;

import webapp.model.*;

public class ResumeTestData {

    public static Resume resumeFactory(String uuid, String fullName) {
                return new Resume(uuid, fullName);
    }
}
