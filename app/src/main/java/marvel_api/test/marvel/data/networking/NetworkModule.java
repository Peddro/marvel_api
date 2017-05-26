package marvel_api.test.marvel.data.networking;

import dagger.Module;
import dagger.Provides;
import marvel_api.test.marvel.BuildConfig;
import marvel_api.test.marvel.data.business.managers.PropertiesManager;
import marvel_api.test.marvel.data.networking.api_services.CharacterApi;
import java.util.Calendar;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

@Module public class NetworkModule {

    private static final String PUBLIC_VERSION = "public";

    public NetworkModule() {
    }

    @Provides @Singleton @Named("baseUrl") String providesBaseUrl(PropertiesManager propertiesManager) {
        return String.format("%s/%s/%s/", propertiesManager.getBaseUrl(), propertiesManager.getApiVersion(),
                PUBLIC_VERSION);
    }

    @Provides @Singleton HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);
        return loggingInterceptor;
    }

    @Provides @Singleton ApiQueryInterceptor provideApiKeyInterceptor(PropertiesManager propertiesManager) {
        String ts = String.valueOf(Calendar.getInstance().getTimeInMillis());
        return new ApiQueryInterceptor(propertiesManager.getApiPublicKey(), ts,
                propertiesManager.getHash(ts));
    }

    @Provides @Singleton OkHttpClient provideHttpClient(ApiQueryInterceptor apiQueryInterceptor,
            HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(apiQueryInterceptor)
                                         .addInterceptor(loggingInterceptor).build();
    }

    @Provides @Singleton Retrofit provideRestAdapter(OkHttpClient client, @Named("baseUrl") String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl).client(client)
                                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                     .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides @Singleton CharacterApi provideCharacterApi(Retrofit retrofit) {
        return retrofit.create(CharacterApi.class);
    }
}
