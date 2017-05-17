package farfetch.test.marvel.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pedro Glória on 16/05/2017.
 */

abstract class BasePreferences {

  abstract String getPreferenceName();

  final Context context;

  BasePreferences(Context context) {
    this.context = context;
  }

  // We need to force save the set: https://issuetracker.google.com/issues/36943216
  // The ways to workaround this issue is to make a copy of the Set returned by
  // SharedPreferences.getStringSet or force the write to memory using other preference that always change
  // (for example, a property that stores the size of the set each time)
  protected void saveSetPreference(String key, Set<String> value) {
    int forceSave = value.size() == getIntPreference("forceSave", -1) ? -1 : value.size();
    SharedPreferences preferences = getSharedPreferences();
    SharedPreferences.Editor editor = preferences.edit();
    Set<String> newSet = new HashSet<>(value);
    editor.putStringSet(key, newSet);
    editor.putInt("forceSave", forceSave);
    editor.apply();
  }

  protected Set<String> getSetPreference(String key) {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.getStringSet(key, new HashSet<>());
  }

  protected void saveStringPreference(String key, String value) {
    SharedPreferences preferences = getSharedPreferences();
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(key, value);
    editor.apply();
  }

  protected String getStringPreference(String key) {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.getString(key, null);
  }

  protected void saveBooleanPreference(String key, boolean value) {
    SharedPreferences preferences = getSharedPreferences();
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean(key, value);
    editor.apply();
  }

  protected boolean getBooleanPreference(String key, boolean defaultValue) {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.getBoolean(key, defaultValue);
  }

  protected void saveIntPreference(String key, int value) {
    SharedPreferences.Editor editor = getEditor();
    editor.putInt(key, value);
    editor.apply();
  }

  protected int getIntPreference(String key, int defaultValue) {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.getInt(key, defaultValue);
  }

  protected void deletePreference(String key) {
    SharedPreferences preferences = getSharedPreferences();
    SharedPreferences.Editor editor = preferences.edit();
    editor.remove(key);
    editor.apply();
  }

  private SharedPreferences.Editor getEditor() {
    SharedPreferences preferences = getSharedPreferences();
    return preferences.edit();
  }

  private SharedPreferences getSharedPreferences() {
    return context.getSharedPreferences(getPreferenceName(), Context.MODE_PRIVATE);
  }

  public void clear() {
    SharedPreferences.Editor editor = getSharedPreferences().edit();
    editor.clear();
    editor.apply();
  }
}
