package com.gyt.eyepetizer.http;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * @author gyt
 * @date on 2018/4/20 16:07
 * @describer TODO
 */

public class LoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static String bodyToString(final RequestBody request) {
        try {
            RequestBody body = request;
            Buffer buffer = new Buffer();
            if (body == null)
                return "";
            body.writeTo(buffer);
            Charset charset = getCharset(body.contentType());
            return buffer.readString(charset);
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private static Charset getCharset(MediaType contentType) {
        Charset charset = contentType != null ? contentType.charset(UTF8) : UTF8;
        if (charset == null)
            charset = UTF8;
        return charset;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间

        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.name(i) + "=" + body.value(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Logger.i(String.format("发送请求 %s on %s%n%s%n%s",
                        request.url(), chain.connection(), request.headers(), sb.toString()));
            } else {
                Logger.i(String.format("发送请求 %s ",
                        request.url()));

                Logger.json(bodyToString(request.body()));
            }
        }

        Response response = null;
        try {
            response = chain.proceed(request);

            long t2 = System.nanoTime();//收到响应的时间

            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);

            Logger.i(String.format("接收响应: [%s] %n %.1fms%n%s",
                    response.request().url(),
                    (t2 - t1) / 1e6d,
                    response.headers()));

            //            Logger.i(responseBody.string());

            try {
                Logger.json(responseBody.string());
            } catch (Exception e) {
                Logger.e("Invalid Json");
                Logger.i(responseBody.string());
            }

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        return response;
    }


}