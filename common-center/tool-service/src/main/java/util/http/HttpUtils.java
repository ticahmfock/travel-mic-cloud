package util.http;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

/**
 * HTTP工具类 OKHTTP
 *
 * @author: TK
 * @date: 2021/10/22 15:31
 */
public class HttpUtils {

  private static final OkHttpClient client = new OkHttpClient();

  public static void sendGet(String url){
    final Request request = new Request
        .Builder()
        .url(url)
        .get()
        .build();
    Call call = client.newCall(request);
    call.enqueue(new Callback() {
      public void onFailure(@NotNull Call call, @NotNull IOException e) {
        System.out.println(call.request().url());
      }

      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        System.out.println(response);
      }
    });
  }

  public static void main(String[] args) {
    sendGet("http://www.baidu.com");
  }
}
