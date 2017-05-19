package farfetch.test.marvel.ui._base;

import android.support.annotation.CallSuper;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by pedrogloria on 19/05/17 .
 */

public abstract class BaseActivityTest {

    protected MockWebServer server;

    @CallSuper public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        server.enqueue(new MockResponse());

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        okHttpClient.newCall(new Request.Builder().url(server.url("/").url().toString()).build()).execute();

        RecordedRequest request = server.takeRequest();
    }

    @CallSuper public void tearDown() throws Exception {
        server.shutdown();
    }
}
