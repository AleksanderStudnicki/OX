package app.studnicki.ox.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class Config {

  private Locale locale = Locale.forLanguageTag("en");
  private ResourceBundle rb = ResourceBundle.getBundle("Message", locale);

  private Config() {
  }

  public static Config getInstance() {
    return ConfigHolder.INSTANCE;
  }

  public void changeLanguage(String code) {
    locale = Locale.forLanguageTag(code);
    rb = ResourceBundle.getBundle("Message", locale);
  }

  public ResourceBundle getRb() {
    return rb;
  }

  private static class ConfigHolder {
    private static Config INSTANCE = new Config();
  }
}
