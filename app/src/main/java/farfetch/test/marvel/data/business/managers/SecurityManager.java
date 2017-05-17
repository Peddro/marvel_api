package farfetch.test.marvel.data.business.managers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

@Singleton class SecurityManager {

  private final static String MD5 = "MD5";

  @Inject SecurityManager() {

  }
  public final String md5(final String s) {
    try {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance(MD5);
      digest.update(s.getBytes());
      byte messageDigest[] = digest.digest();

      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte aMessageDigest : messageDigest) {
        String h = Integer.toHexString(0xFF & aMessageDigest);
        while (h.length() < 2) h = "0" + h;
        hexString.append(h);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

}
