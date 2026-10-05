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
                .birthDate("26.04.1990");
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
                .citizenBirthDate("15.01.2024")
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
                .citizenBirthDate("16.02.2020")
                .citizenGender("Муж")
                .citizenPassport("АБ123456")
                .citizenAddress("Brest, Levaya 3/15")
                .marriageDate("10.12.2026")
                .marriageNewLastName("Gromov")
                .marriageSpouseLastName("Svetova")
                .marriageSpouseFirstName("Svetlana")
                .marriageSpouseMiddleName("Petrovana")
                .marriageSpouseBirthDate("18.03.2019")
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
                .citizenBirthDate("15.01.1925")
                .citizenGender("Жен")
                .citizenPassport("MA45689")
                .citizenAddress("Minsk, Lesnaya, 3")
                .deathDate("15.10.2026")
                .deathPlace("Minsk");
    }
}

