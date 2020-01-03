package app.studnicki.ox;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Configuration class for the whole application. It is implemented as a singleton (enum property).
 * Config can retrieve values for specified keys from properties files and change language of them.
 *
 * @author Aleksander Studnicki
 */
enum Config {
  INSTANCE;

  static final int MINIMUM_DIMENSION = 3;
  static final int MAXIMUM_DIMENSION = 30;
  private Locale locale = Locale.forLanguageTag("en");

  /**
   * Changes language of locale field based on language tag (for example: en for English).
   *
   * @param tag Language tag as a string
   */
  void changeLanguage(String tag) {
    locale = Locale.forLanguageTag(tag);
  }

  /**
   * Return value for key from properties file.
   *
   * @param messageKey Key from properties file as a MessageKey enum
   * @return Value of key from properties file for the specified language
   *        (previously set by changeLanguage method or default in English).
   */
  String getMessage(MessageKey messageKey) {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Message", locale);
    return resourceBundle.getString(messageKey.toString());
  }
}
