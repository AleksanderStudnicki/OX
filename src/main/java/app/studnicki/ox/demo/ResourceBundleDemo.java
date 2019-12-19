package app.studnicki.ox.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleDemo {

    public static void main(String[] args){

        String language;
        String country;

        if (args.length != 2) {
            language = new String("en");
            country = new String("US");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }


            Locale currentLocale;
            ResourceBundle messages;

            currentLocale = new Locale(language, country);

            messages = ResourceBundle.getBundle("MessagesBundle", new Locale("pl", "PL"));
            System.out.println(messages.getString("greetings"));
            System.out.println(messages.getString("inquiry"));
            System.out.println(messages.getString("farewell"));

    }
}
