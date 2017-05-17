package farfetch.test.marvel.data.business.models._base;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class Summary implements Parcelable {

  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("type")
  @Expose
  private String type;

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Summary() {
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.resourceURI);
    dest.writeString(this.name);
    dest.writeString(this.type);
  }

  protected Summary(Parcel in) {
    this.resourceURI = in.readString();
    this.name = in.readString();
    this.type = in.readString();
  }

  public static final Creator<Summary> CREATOR = new Creator<Summary>() {
    @Override public Summary createFromParcel(Parcel source) {
      return new Summary(source);
    }

    @Override public Summary[] newArray(int size) {
      return new Summary[size];
    }
  };
}
