package farfetch.test.marvel.data.networking.api_services.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class ErrorResponse {

  @SerializedName("code") String code;
  @SerializedName("message") String message;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
