package zags.data;

import zags.api.models.AdminProcessRequest;
import zags.api.models.AdminRequest;
import zags.api.models.UserCreateRequest;

public class UserBuilder {
  public static   UserCreateRequest userWeddingRequest () {
   return    UserCreateRequest.builder()
              .mode("wedding")
              .personalLastName("Gromov")
              .personalFirstName("Mark")
              .personalMiddleName("Ivanovich")
              .personalPhoneNumber("79991234567")
              .personalNumberOfPassport("AB123456")
              .citizenLastName("Gromov")
              .citizenFirstName("Mark")
              .citizenMiddleName("Ivanovich")
              .citizenBirthDate("1998-06-11")
              .citizenNumberOfPassport("AB123456")
              .citizenGender("Муж")
              .dateOfMarriage("2026-10-11")
              .newLastName("Gromov")
              .anotherPersonLastName("Svetova")
              .anotherPersonFirstName("Svetlana")
              .anotherPersonMiddleName("Petrovna")
              .anotherPersonBirthDate("2001-06-11")
              .anotherPersonPassport("AB176543")
              .build();
  }
  public static  UserCreateRequest userBirthRequest (){
      return UserCreateRequest
              .builder()
              .mode("birth")
              .personalLastName("Ivanov")
              .personalFirstName("Ivan")
              .personalMiddleName("Ivanovich")
              .personalPhoneNumber("79991234567")
              .personalNumberOfPassport("AB123456")
              .citizenLastName("Ivanov")
              .citizenFirstName("Petr")
              .citizenMiddleName("Ivanovich")
              .citizenBirthDate("2001-06-11")
              .citizenNumberOfPassport("AB123456")
              .citizenGender("Муж")
              .birthPlace("Brest")
              .birthMother("Ivanova Maria")
              .birthFather("Ivanov Ivan")
              .birthGrandma("Vilova Inna")
              .birthGrandpa("Vilou David")
              .build();
  }

  public static    UserCreateRequest userDeathRequest(){
      return UserCreateRequest.builder()
              .mode("death")
              .personalLastName("Vladi")
              .personalFirstName("Olga")
              .personalMiddleName("Ivanovna")
              .personalPhoneNumber("7888823")
              .personalNumberOfPassport("MA456444")
              .citizenLastName("Vladi")
              .citizenFirstName("Olga")
              .citizenMiddleName("Ivanovna")
              .citizenBirthDate("1998-06-11")
              .citizenNumberOfPassport("MA45689")
              .citizenGender("Жен")
              .deathDateOfDeath("2026-10-11")
              .deathPlaceOfDeath("Minsk")
              .build();
  }


}
