package com.aiolos.commons.utils;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.resource.AliyunResource;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Aiolos
 * @date 2020/9/28 8:20 下午
 */
@Slf4j
@Component
public class SMSUtils {

    public final AliyunResource aliyunResource;

    public SMSUtils(AliyunResource aliyunResource) {
        this.aliyunResource = aliyunResource;
    }

    public void sendSMS(String mobile, String code) throws CustomizedException {

        log.info("Enter the method sendSMS, parameter mobile: {}, code: {}", mobile, code);

        if (StringUtils.isBlank(mobile)) {
            throw new CustomizedException(ErrorEnum.PHONE_INCORRECT);
        }

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                aliyunResource.getAccessKeyID(),
                aliyunResource.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "i校易点");
        request.putQueryParameter("TemplateCode", "SMS_178465190");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("aliyun sms response: " + response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
