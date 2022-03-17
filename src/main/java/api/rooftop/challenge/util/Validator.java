package api.rooftop.challenge.util;

import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Component
public class Validator {

    public Object verifyCharsOfDTO(Object chars) {

        if (chars == null || !(chars instanceof Integer)) {
            chars = 2;
        }

        if ((Integer) chars < 2) {
            chars = 2;
        }

        return chars;
    }

    public Integer verifyChars(Integer chars) {
        if (chars < 2) {
            chars = 2;
        }
        return chars;
    }

    public Integer verifyEndIndex(Integer index, String text) {
        if (index > text.length()) {
            index = text.length();
        }
        return index;
    }

    public Integer verifyPage(Integer page) {
        if (page < 1) {
            page = 1;
        }

        return page - 1;
    }

    public Integer verifyRpp(Integer rpp) {
        if (rpp < 10) {
            rpp = 10;
        }

        if (rpp > 100) {
            rpp = 100;
        }

        return rpp;
    }

}
