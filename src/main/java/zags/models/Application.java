package zags.models;

public class Application {
    private final ServiceType serviceType;

    private String personalLastName;
    private String personalFirstName;
    private String personalMiddleName;
    private String personalPhone;
    private String personalPassport;
    private String personalAddress;

    private String citizenLastName;
    private String citizenFirstName;
    private String citizenMiddleName;
    private String citizenBirthDate;
    private String citizenGender;
    private String citizenPassport;
    private String citizenAddress;

    private String birthPlace;
    private String birthMother;
    private String birthFather;
    private String birthGrandmother;
    private String birthGrandfather;

    private String marriageDate;
    private String marriageNewLastName;
    private String marriageSpouseLastName;
    private String marriageSpouseFirstName;
    private String marriageSpouseMiddleName;
    private String marriageSpouseBirthDate;
    private String marriageSpousePassport;

    private String deathDate;
    private String deathPlace;


    public Application(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() { return serviceType; }

    public String getPersonalLastName()   { return personalLastName; }
    public String getPersonalFirstName()  { return personalFirstName; }
    public String getPersonalMiddleName() { return personalMiddleName; }
    public String getPersonalPhone()      { return personalPhone; }
    public String getPersonalPassport()   { return personalPassport; }
    public String getPersonalAddress()    { return personalAddress; }

    public String getCitizenLastName()   { return citizenLastName; }
    public String getCitizenFirstName()  { return citizenFirstName; }
    public String getCitizenMiddleName() { return citizenMiddleName; }
    public String getCitizenBirthDate()  { return citizenBirthDate; }
    public String getCitizenGender()     { return citizenGender; }
    public String getCitizenPassport()   { return citizenPassport; }
    public String getCitizenAddress()    { return citizenAddress; }

    public String getBirthPlace()       { return birthPlace; }
    public String getBirthMother()      { return birthMother; }
    public String getBirthFather()      { return birthFather; }
    public String getBirthGrandmother() { return birthGrandmother; }
    public String getBirthGrandfather() { return birthGrandfather; }

    public String getMarriageDate()             { return marriageDate; }
    public String getMarriageNewLastName()      { return marriageNewLastName; }
    public String getMarriageSpouseLastName()   { return marriageSpouseLastName; }
    public String getMarriageSpouseFirstName()  { return marriageSpouseFirstName; }
    public String getMarriageSpouseMiddleName() { return marriageSpouseMiddleName; }
    public String getMarriageSpouseBirthDate()  { return marriageSpouseBirthDate; }
    public String getMarriageSpousePassport()   { return marriageSpousePassport; }

    public String getDeathDate()              { return deathDate; }
    public String getDeathPlace()             { return deathPlace; }

    public Application personalLastName(String v)   { this.personalLastName = v; return this; }
    public Application personalFirstName(String v)  { this.personalFirstName = v; return this; }
    public Application personalMiddleName(String v) { this.personalMiddleName = v; return this; }
    public Application personalPhone(String v)      { this.personalPhone = v; return this; }
    public Application personalPassport(String v)   { this.personalPassport = v; return this; }
    public Application personalAddress(String v)    { this.personalAddress = v; return this; }

    public Application citizenLastName(String v)   { this.citizenLastName = v; return this; }
    public Application citizenFirstName(String v)  { this.citizenFirstName = v; return this; }
    public Application citizenMiddleName(String v) { this.citizenMiddleName = v; return this; }
    public Application citizenBirthDate(String v)  { this.citizenBirthDate = v; return this; }
    public Application citizenGender(String v)     { this.citizenGender = v; return this; }
    public Application citizenPassport(String v)   { this.citizenPassport = v; return this; }
    public Application citizenAddress(String v)    { this.citizenAddress = v; return this; }

    public Application birthPlace(String v)       { this.birthPlace = v; return this; }
    public Application birthMother(String v)      { this.birthMother = v; return this; }
    public Application birthFather(String v)      { this.birthFather = v; return this; }
    public Application birthGrandmother(String v) { this.birthGrandmother = v; return this; }
    public Application birthGrandfather(String v) { this.birthGrandfather = v; return this; }

    public Application marriageDate(String v)             { this.marriageDate = v; return this; }
    public Application marriageNewLastName(String v)      { this.marriageNewLastName = v; return this; }
    public Application marriageSpouseLastName(String v)   { this.marriageSpouseLastName = v; return this; }
    public Application marriageSpouseFirstName(String v)  { this.marriageSpouseFirstName = v; return this; }
    public Application marriageSpouseMiddleName(String v) { this.marriageSpouseMiddleName = v; return this; }
    public Application marriageSpouseBirthDate(String v)  { this.marriageSpouseBirthDate = v; return this; }
    public Application marriageSpousePassport(String v)   { this.marriageSpousePassport = v; return this; }


    public Application deathDate(String v)              { this.deathDate = v; return this; }
    public Application deathPlace(String v)             { this.deathPlace = v; return this; }
}