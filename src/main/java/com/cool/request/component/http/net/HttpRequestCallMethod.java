package com.cool.request.component.http.net;

import com.cool.request.common.constant.CoolRequestConfigConstant;
import com.cool.request.common.state.SettingPersistentState;
import com.cool.request.common.state.SettingsState;
import com.cool.request.component.http.net.request.HttpRequestParamUtils;
import com.cool.request.component.http.net.request.StandardHttpRequestParam;
import com.cool.request.lib.springmvc.Body;
import com.cool.request.lib.springmvc.EmptyBody;
import com.cool.request.lib.springmvc.FormBody;
import com.cool.request.utils.StringUtils;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HttpRequestCallMethod extends BasicControllerRequestCallMethod {

    private final SimpleCallback simpleCallback;

    public HttpRequestCallMethod(StandardHttpRequestParam reflexHttpRequestParam, SimpleCallback simpleCallback) {
        super(reflexHttpRequestParam);
        this.simpleCallback = simpleCallback;

    }

    /**
     * 当非GET请求时候应用除了form data的数据
     */
    private void applyBodyIfNotGet(Request.Builder request) {
        if (!HttpMethod.GET.equals(getInvokeData().getMethod())) {
            String contentType = HttpRequestParamUtils.getContentType(getInvokeData(), MediaTypes.TEXT);
            if (!MediaTypes.MULTIPART_FORM_DATA.equalsIgnoreCase(contentType)) {
                Body body = getInvokeData().getBody();
                if (body != null && !(body instanceof EmptyBody)) {
                    RequestBody requestBody = RequestBody.create(body.contentConversion(), okhttp3.MediaType.parse(contentType));
                    request.method(getInvokeData().getMethod().toString(), requestBody);
                }
            }
        }
    }

    /**
     * 应用form data数据
     */
    private void applyBodyIfForm(Request.Builder request) {
        String contentType = HttpRequestParamUtils.getContentType(getInvokeData(), MediaTypes.TEXT);
        if (!HttpMethod.GET.equals(getInvokeData().getMethod()) && MediaTypes.MULTIPART_FORM_DATA.equalsIgnoreCase(contentType)) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            if (getInvokeData().getBody() instanceof FormBody) {
                List<FormDataInfo> formDataInfos = ((FormBody) getInvokeData().getBody()).getData();
                if (formDataInfos.isEmpty()) {
                    RequestBody emptyRequestBody = RequestBody.create(null, new byte[0]);
                    request.method(getInvokeData().getMethod().toString(), emptyRequestBody);
                    return; //防止空数据
                }
                for (FormDataInfo formDatum : formDataInfos) {
                    if (CoolRequestConfigConstant.Identifier.FILE.equals(formDatum.getType())) {
                        File file = new File(formDatum.getValue());
                        builder.addFormDataPart(formDatum.getName(), file.getName(), RequestBody.create(file, okhttp3.MediaType.parse("application/octet-stream")));
                    }
                    if (CoolRequestConfigConstant.Identifier.TEXT.equals(formDatum.getType())) {
                        builder.addFormDataPart(formDatum.getName(), formDatum.getValue());
                    }
                }
                request.method(getInvokeData().getMethod().toString(), builder.build());
            }

        }
    }

    private OkHttpClient createOKHttp() {
        SettingsState state = SettingPersistentState.getInstance().getState();
        String proxyIp = state.proxyIp;
        if (StringUtils.isEmpty(proxyIp)) {
            return new OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.HOURS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build();
        }
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, state.proxyPort));
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.HOURS)
                .connectTimeout(5, TimeUnit.SECONDS);
        builder.setProxy$okhttp(proxy);
        return builder.build();


    }

    @Override
    public void invoke() {
        String fullUrl = HttpRequestParamUtils.getFullUrl(getInvokeData());
        Request.Builder request = new Request.Builder()
                .get()
                .url(fullUrl);
        applyBodyIfNotGet(request);
        applyBodyIfForm(request);

        Headers.Builder builder = new Headers.Builder();

        for (KeyValue header : getInvokeData().getHeaders()) {
            builder.addUnsafeNonAscii(header.getKey(), header.getValue());
        }
        request.headers(builder.build());

        createOKHttp().newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                simpleCallback.onError(getInvokeData().getId(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                simpleCallback.onResponse(getInvokeData().getId(), response.code(), response);
            }
        });
    }


    public interface SimpleCallback {
        public void onResponse(String requestId, int code, Response response);

        public void onError(String requestId, IOException e);
    }
}
