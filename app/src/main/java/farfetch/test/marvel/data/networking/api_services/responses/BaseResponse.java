package farfetch.test.marvel.data.networking.api_services.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pedro Glória on 14/05/2017.
 * Generated code from jsonschema2pojo.org with some modifications to make it more generic
 */
public class BaseResponse<T> {

  @SerializedName("code")
  @Expose
  private String code;
  @SerializedName("status")
  @Expose
  private String status;
  @SerializedName("copyright")
  @Expose
  private String copyright;
  @SerializedName("attributionText")
  @Expose
  private String attributionText;
  @SerializedName("attributionHTML")
  @Expose
  private String attributionHTML;
  @SerializedName("data")
  @Expose
  private T data;
  @SerializedName("etag")
  @Expose
  private String etag;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getAttributionText() {
    return attributionText;
  }

  public void setAttributionText(String attributionText) {
    this.attributionText = attributionText;
  }

  public String getAttributionHTML() {
    return attributionHTML;
  }

  public void setAttributionHTML(String attributionHTML) {
    this.attributionHTML = attributionHTML;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getEtag() {
    return etag;
  }

  public void setEtag(String etag) {
    this.etag = etag;
  }
}
