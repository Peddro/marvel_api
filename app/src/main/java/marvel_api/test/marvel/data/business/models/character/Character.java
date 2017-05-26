package marvel_api.test.marvel.data.business.models.character;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import marvel_api.test.marvel.data.business.models.Thumbnail;
import marvel_api.test.marvel.data.business.models.Url;
import marvel_api.test.marvel.data.business.models._base.BaseModel;
import marvel_api.test.marvel.data.business.models.comic.Comic;
import marvel_api.test.marvel.data.business.models.event.Events;
import marvel_api.test.marvel.data.business.models.series.Series;
import marvel_api.test.marvel.data.business.models.story.Story;
import java.util.List;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

public class Character extends BaseModel implements Parcelable {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("modified")
  @Expose
  private String modified;
  @SerializedName("resourceURI")
  @Expose
  private String resourceURI;
  @SerializedName("urls")
  @Expose
  private List<Url> urls = null;
  @SerializedName("thumbnail")
  @Expose
  private Thumbnail thumbnail;
  @SerializedName("comics")
  @Expose
  private Comic comics;
  @SerializedName("stories")
  @Expose
  private Story story;
  @SerializedName("events")
  @Expose
  private Events events;
  @SerializedName("series")
  @Expose
  private Series series;

  private boolean isFavorite;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public String getResourceURI() {
    return resourceURI;
  }

  public void setResourceURI(String resourceURI) {
    this.resourceURI = resourceURI;
  }

  public List<Url> getUrls() {
    return urls;
  }

  public void setUrls(List<Url> urls) {
    this.urls = urls;
  }

  public Thumbnail getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Thumbnail thumbnail) {
    this.thumbnail = thumbnail;
  }

  public Comic getComics() {
    return comics;
  }

  public void setComics(Comic comics) {
    this.comics = comics;
  }

  public Story getStory() {
    return story;
  }

  public void setStory(Story story) {
    this.story = story;
  }

  public Events getEvents() {
    return events;
  }

  public void setEvents(Events events) {
    this.events = events;
  }

  public Series getSeries() {
    return series;
  }

  public void setSeries(Series series) {
    this.series = series;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public void setFavorite(boolean favorite) {
    isFavorite = favorite;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.name);
    dest.writeString(this.description);
    dest.writeString(this.modified);
    dest.writeString(this.resourceURI);
    dest.writeTypedList(this.urls);
    dest.writeParcelable(this.thumbnail, flags);
    dest.writeParcelable(this.comics, flags);
    dest.writeParcelable(this.story, flags);
    dest.writeParcelable(this.events, flags);
    dest.writeParcelable(this.series, flags);
    dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
  }

  protected Character(Parcel in) {
    this.id = in.readString();
    this.name = in.readString();
    this.description = in.readString();
    this.modified = in.readString();
    this.resourceURI = in.readString();
    this.urls = in.createTypedArrayList(Url.CREATOR);
    this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
    this.comics = in.readParcelable(Comic.class.getClassLoader());
    this.story = in.readParcelable(Story.class.getClassLoader());
    this.events = in.readParcelable(Events.class.getClassLoader());
    this.series = in.readParcelable(Series.class.getClassLoader());
    this.isFavorite = in.readByte() != 0;
  }

  public static final Creator<Character> CREATOR = new Creator<Character>() {
    @Override public Character createFromParcel(Parcel source) {
      return new Character(source);
    }

    @Override public Character[] newArray(int size) {
      return new Character[size];
    }
  };
}
