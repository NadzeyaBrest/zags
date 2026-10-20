package zags.data;

import zags.models.Admin;
import zags.models.Application;
import zags.models.ServiceType;

public class TestData {
    public static Admin getAdmin(){
        return new Admin()
                .lastName("Fedorov")
                .firstName("Fedor")
                .middleName("Borisovich")
                .phone("123456")
                .passportNumber("Ba12345")
                .birthDate("1925-01-15");
    }
    public static Application getBirthApplication() {
        return new Application(ServiceType.BIRTH)
                .personalLastName("Ivanov")
                .personalFirstName("Ivan")
                .personalMiddleName("Ivanovich")
                .personalPhone("7999123")
                .personalPassport("АБ123456")
                .personalAddress("Brest, Sovetskaya 3/15")
                .citizenLastName("Ivanov")
                .citizenFirstName("Petr")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("1990-01-15")
                .citizenGender("Муж")
                .citizenPassport("АБ123456")
                .citizenAddress("Brest, Sovetskaya 3/15")
                .birthPlace("Brest")
                .birthMother("Ivanova Maria Sergeevana")
                .birthFather("Ivanov Ivan Ivanovich")
                .birthGrandmother("Petrova Anna Ivanovna")
                .birthGrandfather("Petrov Petr Petrovich");
    }

    public static Application getMarriageApplication() {
        return new Application(ServiceType.MARRIAGE)
                .personalLastName("Gromov")
                .personalFirstName("Mark")
                .personalMiddleName("Ivanovich")
                .personalPhone("7999123")
                .personalPassport("АБ123456")
                .personalAddress("Brest, Levaya 3/15")
                .citizenLastName("Gromov")
                .citizenFirstName("Mark")
                .citizenMiddleName("Ivanovich")
                .citizenBirthDate("2000-09-16")
                .citizenGender("Муж")
                .citizenPassport("АБ123456")
                .citizenAddress("Brest, Levaya 3/15")
                .marriageDate("2026-12-10")
                .marriageNewLastName("Gromov")
                .marriageSpouseLastName("Svetova")
                .marriageSpouseFirstName("Svetlana")
                .marriageSpouseMiddleName("Petrovana")
                .marriageSpouseBirthDate("2000-02-16")
                .marriageSpousePassport("АБ176543");
    }

    public static Application getDeathApplication() {
        return new Application(ServiceType.DEATH)
                .personalLastName("Vladi")
                .personalFirstName("Olga")
                .personalMiddleName("Ivanovna")
                .personalPhone("7888823")
                .personalPassport("MA456444")
                .personalAddress("Minsk, Lesnaya 3")
                .citizenLastName("Vladi")
                .citizenFirstName("Olga")
                .citizenMiddleName("Ivanovna")
                .citizenBirthDate("1925-02-18")
                .citizenGender("Жен")
                .citizenPassport("MA45689")
                .citizenAddress("Minsk, Lesnaya, 3")
                .deathDate("2026-10-22")
                .deathPlace("Minsk");
    }
}

