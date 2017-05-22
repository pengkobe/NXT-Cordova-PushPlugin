package com.jingoal.push.sdk;

import android.content.Context;
import android.content.SharedPreferences;

import com.huawei.android.pushagent.api.PushManager;
import com.jingoal.push.receiver.JingoalReceiver;

/**
 * 华为推送初始化以及别名管理
 */
public class HuaWeiPushClient implements JingoalPushClient {

    @Override public void registerPush(Context ctx) {
        PushManager.requestToken(ctx);
    }

    @Override public void unRegisterPush(Context context) {
        //虽然提供了该API,但是测试时未起作用,所以此处暂时注释掉,以免引起其他问题。
        //比如:某一天华为后台修复此问题,如果启用该方法,客户端收不到消息,下次再调用requestToken可能依然收不到消息,
        //因为其他API出现过类似情况,所以此处是合理怀疑
        //PushManager.deregisterToken(context,getToken(context));

        SharedPreferences sharedPreference =
                context.getSharedPreferences(JingoalReceiver.JINGOAL_PUSH_SP,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreference.edit();
        edit.remove(JingoalReceiver.SP_KEY_HUAWEI_TOKEN);
        edit.commit();

    }

    @Override public void setAlias(Context context, String deviceId, String alias) {
        //    to do nothing 华为不支持别名
    }

    @Override public void deleteAlias(Context context, String deviceId, String alias) {
        //    to do nothing 华为不支持别名
    }

    @Override public String getToken(Context context) {
        SharedPreferences sharedPreference =
                context.getSharedPreferences(JingoalReceiver.JINGOAL_PUSH_SP,Context.MODE_PRIVATE);
        return sharedPreference.getString(JingoalReceiver.SP_KEY_HUAWEI_TOKEN, null);
    }

    @Override public int getClientType() {
        return JingoalReceiver.PushClientType.HUA_WEI;
    }
}
