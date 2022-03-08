package api.rooftop.challenge.dto;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.Data;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@Data
public class RequestDTO {

    private String text;
    private Integer chars;

    public String getSHA256() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String out = null;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update((this.text + String.valueOf(this.chars)).getBytes("utf8"));
        out = String.format("%064x", new BigInteger(1, digest.digest()));

        return out;
    }
}
