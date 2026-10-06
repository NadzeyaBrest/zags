package zags.pages;

public class CommonLocators {
    private CommonLocators() {
    }

    public static final String LAST_NAME_INPUT =
            "//label[contains(text(),'Фамилия')]/../following-sibling::input";
    public static final String FIRST_NAME_INPUT =
            "//label[contains(text(),'Имя')]/../following-sibling::input";
    public static final String MIDDLE_NAME_INPUT =
            "//label[contains(text(),'Отчество')]/../following-sibling::input";
    public static final String PHONE_INPUT =
            "//label[contains(text(),'Телефон')]/../following-sibling::input";
    public static final String PASSPORT_INPUT =
            "//label[contains(text(),'Номер паспорта')]/../following-sibling::input";
    public static final String ADDRESS_INPUT =
            "//label[contains(text(),'Адрес прописки')]/../following-sibling::input";
    public static final String BIRTH_DATE_INPUT =
            "//label[contains(text(),'Дата рождения')]/../following-sibling::input";
    public static final String GENDER_INPUT =
            "//label[contains(text(),'Пол')]/../following-sibling::input";

    public static final String BIRTH_PLACE_INPUT =
            "//label[contains(text(),'Место рождения')]/../following-sibling::input";
    public static final String BIRTH_MOTHER_INPUT =
            "//label[contains(text(),'Мать')]/../following-sibling::input";
    public static final String BIRTH_FATHER_INPUT =
            "//label[contains(text(),'Отец')]/../following-sibling::input";
    public static final String BIRTH_GRANDMOTHER_INPUT =
            "//label[contains(text(),'Бабушка')]/../following-sibling::input";
    public static final String BIRTH_GRANDFATHER_INPUT =
            "//label[contains(text(),'Дедушка')]/../following-sibling::input";

    public static final String DEATH_DATE_INPUT =
           "//label[contains(text(),'Дата смерти')]/../following-sibling::input";
   public static final String DEATH_PLACE_INPUT =
           "//label[contains(text(),'Место смерти')]/../following-sibling::input";

   public static final String MARRIAGE_DATE_INPUT =
           "//label[text()='Дата регистрации']/../following-sibling::input";
   public static final String MARRIAGE_NEW_LAST_NAME_INPUT =
           "//label[text()='Новая фамилия']/../following-sibling::input";
   public static final String MARRIAGE_SPOUSE_LAST_NAME_INPUT =
           "//label[text()='Фамилия супруга/и']/../following-sibling::input";
   public static final String MARRIAGE_SPOUSE_FIRST_NAME_INPUT =
           "//label[text()='Имя супруга/и']/../following-sibling::input";
   public static final String MARRIAGE_SPOUSE_MIDDLE_NAME_INPUT =
           "//label[text()='Отчество супруга/и']/../following-sibling::input";
   public static final String MARRIAGE_SPOUSE_BIRTH_DATE_INPUT =
           "//label[text()='Дата рождения супруга/и']/../following-sibling::input";
   public static final String MARRIAGE_SPOUSE_PASSPORT_INPUT =
           "//label[text()='Номер паспорта супруга/и']/../following-sibling::input";

   public static final String APPLICATION_NUMBER_TEXT =
           "//span[contains(text(),'Ваша заявка №')]";
   public static final String STATUS_VALUE_TEXT =
           "//span[contains(text(), 'Статус заявки:')]";
   public static final String THANK_YOU_TEXT =
           "//span[contains(text(),'Спасибо за обращение')]";

   public static final String MARRIAGE_SERVICE_BUTTON =
           "//button[contains(text(), 'брак')]";
   public static final String BIRTH_SERVICE_BUTTON =
           "//button[contains(text(), 'рождения')]";
   public static final String DEATH_SERVICE_BUTTON =
           "//button[contains(text(), 'смерти')]";
   public static final String REFRESH_BUTTON =
           "//button[contains(text(),'Обновить')]";
   public static final String CREATE_NEW_APPLICATION_BUTTON =
           "//button[contains(text(),'Создать новую заявку')]";
   public static final String NEXT_BUTTON =
           "//button[contains(text(), 'Далее')]";
   public static final String CLOSE_BUTTON =
           "//button[contains(text(), 'Закрыть')]";
   public static final String BACK_BUTTON =
           "//button[contains(text(), 'Назад')]";
   public static final String FINISH_BUTTON =
           "//button[contains(text(), 'Завершить')]";
    public static final String USER_MODE_BUTTON =
            "//button[contains(text(), 'пользователь')]";
    public static final String ADMIN_MODE_BUTTON =
            "//button[contains(text(), 'администратор')]";
    public static final String REFERENCE_BUTTON =
            "//button[contains(text(), 'справк')]";
}
