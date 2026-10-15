package zags.core;

import zags.models.Application;
import zags.models.ServiceType;

public class TestDataForCucumber {
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
}

