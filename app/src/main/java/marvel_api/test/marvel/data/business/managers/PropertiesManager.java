package marvel_api.test.marvel.data.business.managers;

import java.util.Properties;
import javax.inject.Inject;
import javax.inject.Singleton;
import timber.log.Timber;

/**
 * Created by Pedro Glória on 14/05/2017.
 */

@Singleton public class PropertiesManager {

  // Web Services
  private static final String API_BASE_URL_KEY = "api.baseUrl";
  private static final String API_VERSION = "api.version";
  private static final String API_PUBLIC_KEY = "api.publicKey";
  private static final String API_PRIVATE_KEY = "api.privateKey";

  private final Properties properties;
  private final SecurityManager securityManager;

  @Inject PropertiesManager(Properties properties, SecurityManager securityManager) {
    this.properties = properties;
    this.securityManager = securityManager;
    printLoadedConfiguration();
  }

  private void printLoadedConfiguration() {
    Timber.i("================[ ]================");
    Timber.i(API_BASE_URL_KEY + " = " + getBaseUrl());
    Timber.i(API_VERSION + " = " + getApiVersion());
    Timber.i(API_PUBLIC_KEY + " = " + getApiPublicKey());
    Timber.i("====================================");
  }

  //<editor-fold desc="Getters">
  public String getBaseUrl() {
    return properties.getProperty(API_BASE_URL_KEY, "");
  }

  public String getApiVersion() {
    return properties.getProperty(API_VERSION, "");
  }

  public String getApiPublicKey() {
    return properties.getProperty(API_PUBLIC_KEY, "");
  }

  public String getApiPrivateKey() {
    return properties.getProperty(API_PRIVATE_KEY, "");
  }

  public String getHash(String ts) {
    return securityManager.md5(ts + getApiPrivateKey() + getApiPublicKey());
  }
  //</editor-fold>
}
