package com.cool.request.lib.springmvc.param;

import com.cool.request.components.http.net.MediaTypes;
import com.cool.request.lib.springmvc.HttpRequestInfo;
import com.cool.request.components.http.RequestParameterDescription;
import com.cool.request.lib.springmvc.utils.ParamUtils;
import com.intellij.psi.PsiMethod;

import java.util.ArrayList;
import java.util.List;

public class UrlencodedSpeculate extends BasicUrlParameterSpeculate implements RequestParamSpeculate {
    @Override
    public void set(PsiMethod method, HttpRequestInfo httpRequestInfo) {
        //比如是非GET情况，没有MultipartFile文件
        if (!ParamUtils.isGetRequest(method) &&
                !ParamUtils.hasMultipartFile(method.getParameterList().getParameters())) {
            //有RequestBody注解
            if (ParamUtils.hasRequestBody(method)) return;

            List<RequestParameterDescription> param = new ArrayList<>(super.get(method, false));
            if (!param.isEmpty()) {
                httpRequestInfo.setUrlencodedBody(param);
                httpRequestInfo.setContentType(MediaTypes.APPLICATION_WWW_FORM);
            }
        }
    }
}
