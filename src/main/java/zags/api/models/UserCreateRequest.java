package zags.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateRequest {
    private String mode;
    private String personalLastName;
    private String personalFirstName;
    private String personalMiddleName;
    private String personalPhoneNumber;
    private String personalNumberOfPassport;
    private String personalAddress;
    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenNumberOfPassport;
    private String citizenGender;
    private String citizenAddress;
    private String dateOfMarriage;
    private String newLastName;
    private String anotherPersonLastName;
    private String anotherPersonFirstName;
    private String anotherPersonMiddleName;
    @JsonProperty("birth_of_anotoherPerson")
    private String anotherPersonBirthDate;
    private String anotherPersonPassport;
    @JsonProperty("birth_place")
    private String birthPlace;
    @JsonProperty("birth_mother")
    private String birthMother;
    @JsonProperty("birth_father")
    private String birthFather;
    @JsonProperty("birth_grandpa")
    private String birthGrandpa;
    @JsonProperty("birth_grandma")
    private String birthGrandma;
    @JsonProperty("death_dateOfDeath")
    private String deathDateOfDeath;
    @JsonProperty("death_placeOfDeath")
    private String deathPlaceOfDeath;
}
