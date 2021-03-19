package com.bc.app.server.utils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JiGuangPushUtil {


    private final static String appKey = "9bb5379624c1e450e33ec3f4";

    private final static String masterSecret = "602f0c1dc9dd89d0225d0d86";

    private static JPushClient jpushClient = new JPushClient(masterSecret, appKey);

    /**
     * 给所有平台的所有用户发通知
     */
    public static void sendAllsetNotification(String message) {
        Map<String, String> extras = new HashMap<>();
        PushPayload payload = buildPushObjectAllAliasAlert(message, extras);
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给所有平台的所有用户发透传消息
     *
     * @param message
     */
    public static void sendAllMessage(String message) {
        Map<String, String> extras = new HashMap<>();
        // 添加附加信息
        extras.put("extMessage", "我是额外透传的消息");
        PushPayload payload = buildPushObjectAllAliasMessage(message, extras);
        try {
            PushResult result = jpushClient.sendPush(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端 给所有平台的一个或者一组用户发送信息
     */
    public static void sendAlias(String message, List<String> aliasList, Map<String, String> extras) {
        PushPayload payload = allPlatformAndAlias(message, extras, aliasList);
        try {
            PushResult result = jpushClient.sendPush(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端 给平台的一个或者一组标签发送消息。
     */
    public static void sendTag(String message, String messageId, String type, List<String> tagsList, Map<String, String> extras) {
        // 附加字段
        extras.put("messageId", messageId);
        extras.put("typeId", type);
        PushPayload payload = allPlatformAndTag(message, extras, tagsList);
        try {
            PushResult result = jpushClient.sendPush(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PushPayload buildPushObjectAllAliasAlert(String message, Map<String, String> extras) {
        return PushPayload.newBuilder()
                // 设置平台
                .setPlatform(Platform.all())
                // 按什么发送 tag alia
                .setAudience(Audience.all())
                // 发送消息
                .setNotification(
                        Notification
                                .newBuilder()
                                .setAlert(message)
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder().addExtras(extras).build())
                                .addPlatformNotification(
                                        IosNotification.newBuilder().addExtras(extras).build())
                                .build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
    }

    /**
     * 发送透传消息
     *
     * @param message
     * @param extras
     * @return
     */
    private static PushPayload buildPushObjectAllAliasMessage(String message, Map<String, String> extras) {
        return PushPayload.newBuilder()
                // 设置平台
                .setPlatform(Platform.all())
                // 按什么发送 tag alia
                .setAudience(Audience.all())
                // 发送通知
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build()).build();

    }


    /**
     * 极光推送：生成向一个或者一组用户发送的消息。
     */
    private static PushPayload allPlatformAndAlias(String alert, Map<String, String> extras, List<String> aliasList) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(aliasList))
                .setNotification(
                        Notification
                                .newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder().addExtras(extras).build())
                                .addPlatformNotification(
                                        IosNotification.newBuilder().addExtras(extras).build())
                                .build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
    }

    /**
     * 极光推送：生成向一组标签进行推送的消息。
     */
    private static PushPayload allPlatformAndTag(String alert, Map<String, String> extras, List<String> tagsList) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(tagsList))
                .setNotification(
                        Notification
                                .newBuilder()
                                .setAlert(alert)
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder().addExtras(extras).build())
                                .addPlatformNotification(
                                        IosNotification.newBuilder().addExtras(extras).build())
                                .build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
    }

}

