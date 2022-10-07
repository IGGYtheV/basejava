package webapp.storage;

import webapp.model.*;


public class ResumeTestData {

    public static Resume resumeFactory(String uuid, String fullName) {
                return new Resume(uuid, fullName);
    }

    public  static void resumeFiller() {
        AbstractStorageTest.R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        AbstractStorageTest.R1.addContact(ContactType.PHONE, "11111");
        AbstractStorageTest.R4.addContact(ContactType.PHONE, "44444");
        AbstractStorageTest.R4.addContact(ContactType.SKYPE, "Skype");

        AbstractStorageTest.R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        AbstractStorageTest.R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        AbstractStorageTest.R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        AbstractStorageTest.R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
//        AbstractStorageTest.R1.addSection(SectionType.EXPERIENCE,
//                new OrganisationSection(
//                        new Organisation("Organization11", "http://Organization11.ru",
//                                new Organisation.Position(2005, Month.JANUARY, "position1", "content1"),
//                                new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
//        AbstractStorageTest.R1.addSection(SectionType.EDUCATION,
//                new OrganisationSection(
//                        new Organisation("Institute", null,
//                                new Organisation.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
//                                new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
//                        new Organisation("Organization12", "http://Organization12.ru")));
        AbstractStorageTest.R2.addContact(ContactType.SKYPE, "skype2");
        AbstractStorageTest.R2.addContact(ContactType.PHONE, "22222");
//        AbstractStorageTest.R1.addSection(SectionType.EXPERIENCE,
//                new OrganisationSection(
//                        new Organisation("Organisation2", "http://Organisation2.ru",
//                                new Organisation.Position(2015, Month.JANUARY, "position1", "content1"))));

    }
}
