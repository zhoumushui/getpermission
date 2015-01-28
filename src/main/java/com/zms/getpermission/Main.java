package com.zms.getpermission;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AlexZhou on 2015/1/27.
 * 8:48
 */
public class Main extends ListActivity {

    HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
    List<String> dataAppList = new ArrayList<String>();
    List<String> systemAppList = new ArrayList<String>();

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageManager pm = getPackageManager();
        Intent query = new Intent(Intent.ACTION_MAIN);
        query.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> resolves = pm.queryIntentActivities(query, PackageManager.GET_ACTIVITIES);

        dataAppList.add("");
        for (int i = 0; i < resolves.size(); i++) {
            ResolveInfo info = resolves.get(i);
            String packageName = info.loadLabel(pm).toString();
            String[] permission;
            try {
                permission = pm.getPackageInfo(info.activityInfo.packageName,
                        PackageManager.GET_PERMISSIONS).requestedPermissions; //获取权限列表
                if (isSystemApp(info)) { // 是否为系统应用
                    systemAppList.add(packageName);
                } else {
                    dataAppList.add(packageName);
                }
                hashMap.put(packageName, permission);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        dataAppList.set(0, "===== Data Apps(" + dataAppList.size() + ") =====");
        dataAppList.add("");
        dataAppList.add("===== System Apps(" + systemAppList.size() + ") =====");
        dataAppList.addAll(systemAppList);

        getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                dataAppList));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String[] permission = hashMap.get(dataAppList.get(position));
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    for (int i = 0; i < permission.length; i++) {
                        stringBuilder.append(getPermissionDetailSys(permission[i]) + "\n");
                    }
                    Intent intent = new Intent(Main.this, ShowPermission.class);
                    intent.putExtra("strPermission", dataAppList.get(position) + " has " + permission.length
                            + " Permission(s):\n\n" + stringBuilder);
                    startActivity(intent);
                } catch (Exception e) {
                    // ToDo:Handle Exception
                }
            }
        });
    }

    private boolean isSystemApp(ResolveInfo resolveInfo) {
        if ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
            return false;
        } else {
            return true;
        }
    }


    public String getPermissionDetailSys(String strPermission) {
        String strPermissionInfo = "No Description";
        try {
            PackageManager pm = Main.this.getPackageManager();
            PermissionInfo tmpPermInfo = pm.getPermissionInfo(strPermission, 0);//通过permName得到该权限的详细信息
            PermissionGroupInfo pgi = pm.getPermissionGroupInfo(
                    tmpPermInfo.group, 0);//权限分为不同的群组，通过权限名，我们得到该权限属于什么类型的权限。

            //tvPermission.append(i + "-" + permName + "\n"); // 权限名
            //tvPermission.append(i + "-" + pgi.loadLabel(pm).toString() + "\n"); // 权限分组
            //tvPermission.append(i + "-" + tmpPermInfo.loadLabel(pm).toString() + "\n");
            //tvPermission.append(i + "-" + tmpPermInfo.loadDescription(pm).toString() + "\n");
            strPermissionInfo = "[" + pgi.loadLabel(pm).toString() + "] " + tmpPermInfo.loadLabel(pm).toString() + ":\n" +
                    tmpPermInfo.loadDescription(pm).toString() + "\n";
        } catch (PackageManager.NameNotFoundException e) {
            return getPermissionDetail(strPermission) + "\n";
        }
        return strPermissionInfo;
    }

    /**
     * This function is too long...
     *
     * @param strPermission
     * @return
     */
    public String getPermissionDetail(String strPermission) {
        switch (strPermission) {
            /***** A *****/
            case "android.permission.ACCESS_ALL_DOWNLOADS":
                return getResources().getString(R.string.ACCESS_ALL_DOWNLOADS);
            case "android.permission.ACCESS_CACHE_FILESYSTEM":
                return getResources().getString(R.string.ACCESS_CACHE_FILESYSTEM);
            case "android.permission.ACCESS_CHECKIN_PROPERTIES":
                return getResources().getString(R.string.ACCESS_CHECKIN_PROPERTIES);
            case "android.permission.ACCESS_COARSE_LOCATION":
                return getResources().getString(R.string.ACCESS_COARSE_LOCATION);
            case "android.permission.ACCESS_COARSE_UPDATES":
                return getResources().getString(R.string.ACCESS_COARSE_UPDATES);
            case "android.permission.ACCESS_DOWNLOAD_MANAGER":
                return getResources().getString(R.string.ACCESS_DOWNLOAD_MANAGER);
            case "android.permission.ACCESS_DRM":
                return getResources().getString(R.string.ACCESS_DRM);
            case "android.permission.ACCESS_FINE_LOCATION":
                return getResources().getString(R.string.ACCESS_FINE_LOCATION);
            case "android.permission.ACCESS_GPS":
                return getResources().getString(R.string.ACCESS_GPS);
            case "android.permission.ACCESS_LOCATION":
                return getResources().getString(R.string.ACCESS_LOCATION);
            case "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS":
                return getResources().getString(R.string.ACCESS_LOCATION_EXTRA_COMMANDS);
            case "android.permission.ACCESS_MOCK_LOCATION":
                return getResources().getString(R.string.ACCESS_MOCK_LOCATION);
            case "android.permission.ACCESS_NETWORK_STATE":
                return getResources().getString(R.string.ACCESS_NETWORK_STATE);
            case "android.permission.ACCESS_SURFACE_FLINGER":
                return getResources().getString(R.string.ACCESS_SURFACE_FLINGER);
            case "android.permission.ACCESS_SUPERUSER":
                return getResources().getString(R.string.ACCESS_SUPERUSER);
            case "android.permission.ACCESS_WIFI_STATE":
                return getResources().getString(R.string.ACCESS_WIFI_STATE);
            case "android.permission.ACCOUNT_MANAGER":
                return getResources().getString(R.string.ACCOUNT_MANAGER);
            case "com.android.settings.permission.ALLSHARE_CAST_SERVICE":
                return getResources().getString(R.string.ALLSHARE_CAST_SERVICE);
            case "android.permission.AUTHENTICATE_ACCOUNTS":
                return getResources().getString(R.string.AUTHENTICATE_ACCOUNTS);
            /***** B *****/
            case "android.permission.BATTERY_STATS":
                return getResources().getString(R.string.BATTERY_STATS);
            case "com.android.vending.BILLING":
                return getResources().getString(R.string.BILLING);
            case "android.permission.BIND_APPWIDGET":
                return getResources().getString(R.string.BIND_APPWIGET);
            case "android.permission.BIND_DEVICE_ADMIN":
                return getResources().getString(R.string.BIND_DEVICE_ADMIN);
            case "android.permission.BIND_INPUT_METHOD":
                return getResources().getString(R.string.BIND_INPUT_METHOD);
            case "android.permission.BIND_WALLPAPER":
                return getResources().getString(R.string.BIND_WALLPAPER);
            case "android.permission.BLUETOOTH":
                return getResources().getString(R.string.BLUETOOTH);
            case "android.permission.BLUETOOTH_ADMIN":
                return getResources().getString(R.string.BULETOOTH_ADMIN);
            case "android.permission.BRICK ":
                return getResources().getString(R.string.BRICK);
            case "android.permission.BROADCAST_PACKAGE_REMOVED":
                return getResources().getString(R.string.BROADCAST_PACKAGE_REMOVED);
            case "android.permission.BROADCAST_SMS":
                return getResources().getString(R.string.BROADCAST_SMS);
            case "android.permission.BROADCAST_STICKY":
                return getResources().getString(R.string.BROADCAST_STICKY);
            /***** C *****/
            case "android.permission.CALL_PHONE":
                return getResources().getString(R.string.CALL_PHONE);
            case "android.permission.CALL_PRIVILEGED":
                return getResources().getString(R.string.CALL_PRIVILEGED);
            case "android.permission.CAMERA":
                return getResources().getString(R.string.CAMERA);
            case "android.permission.CHANGE_COMPONENT_ENABLED_STATE":
                return getResources().getString(R.string.CHANGE_COMPONENT_ENABLED_STATE);
            case "android.permission.CHANGE_CONFIGURATION":
            case "Android.permission.CHANGE_CONFIGURATION":
                return getResources().getString(R.string.CHANGE_CONFIGURATION);
            case "android.permission.CHANGE_NETWORK_STATE":
                return getResources().getString(R.string.CHANGE_NETWORK_STATE);
            case "android.permission.CHANGE_WIFI_MULTICAST_STATE":
                return getResources().getString(R.string.CHANGE_WIFIMULTICAST_STATE);
            case "android.permission.CHANGE_WIFI_STATE":
                return getResources().getString(R.string.CHANGE_WIFI_STATE);
            case "com.android.vending.CHECK_LICENSE":
                return getResources().getString(R.string.CHECK_LICENSE);
            case "android.permission.CLEAR_APP_CACHE":
                return getResources().getString(R.string.CLEAR_APP_CACHE);
            case "android.permission.CLEAR_APP_USER_DATA":
                return getResources().getString(R.string.CLEAR_APP_USER_DATA);
            case "android.permission.CONTROL_LOCATION_UPDATES":
                return getResources().getString(R.string.CONTROL_LOCATION_UPDATES);
            case "com.android.launcher.permission.CREATE_SHORTCUT":
                return getResources().getString(R.string.CREATE_SHORTCUT);
            /***** D *****/
            case "android.permission.DELETE_CACHE_FILES":
                return getResources().getString(R.string.DELETE_CACHE_FILES);
            case "android.permission.DELETE_PACKAGES":
                return getResources().getString(R.string.DLEETE_PACKAGES);
            case "android.permission.DEVICE_POWER":
                return getResources().getString(R.string.DEVICE_POWER);
            case "android.permission.DIAGNOSTIC":
                return getResources().getString(R.string.DIAGNOSTIC);
            case "android.permission.DISABLE_KEYGUARD":
                return getResources().getString(R.string.DISABLE_KEYGUARD);
            case "android.permission.DUMP":
                return getResources().getString(R.string.DUMP);
            case "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION":
                return getResources().getString(R.string.DOWNLOAD_WITHOUT_NOTIFICATION);
            /***** E *****/
            case "android.permission.EXPAND_STATUS_BAR":
                return getResources().getString(R.string.EXPAND_STATUS_BAR);
            /***** F *****/
            case "android.permission.FACTORY_TEST":
                return getResources().getString(R.string.FACTORY_TEST);
            case "android.permission.FLASHLIGHT":
                return getResources().getString(R.string.FLSHLIGHT);
            case "android.permission.FORCE_BACK":
                return getResources().getString(R.string.FORCE_BACK);
            /***** G *****/
            case "android.permission.GET_ACCOUNTS":
                return getResources().getString(R.string.GET_ACCOUNTS);
            case "android.permission.GET_PACKAGE_SIZE":
                return getResources().getString(R.string.GET_PACKAGE_SIZE);
            case "android.permission.GET_TASKS":
                return getResources().getString(R.string.GET_TASKS);
            case "android.permission.GLOBAL_SEARCH":
                return getResources().getString(R.string.GLBOAL_SEARCH);
            /***** H *****/
            case "android.permission.HARDWARE_TEST ":
                return getResources().getString(R.string.HARDWARE_TEST);
            /***** I *****/
            case "android.permission.INJECT_EVENTS":
                return getResources().getString(R.string.INJECT_EVENTS);
            case "android.permission.INSTALL_PERMISSION":
                return getResources().getString(R.string.INSTALL_PERMISSION);
            case "android.permission.INSTALL_LOCATION_PROVIDER":
                return getResources().getString(R.string.INSTALL_LOCATION_PROVIDER);
            case "android.permission.INSTALL_PACKAGES":
                return getResources().getString(R.string.INSTALL_PCAKAGES);
            case "com.android.launcher.permission.INSTALL_SHORTCUT":
                return getResources().getString(R.string.LAUNCHER_INSTALL_SHORTCUT);
            case "android.permission.INTERACT_ACROSS_USERS":
                return getResources().getString(R.string.INTERACT_ACROSS_USERS);
            case "android.permission.INTERACT_ACROSS_USERS_FULL":
                return getResources().getString(R.string.INTERACT_ACROSS_USERS_FULL);
            case "android.permission.INTERNAL_SYSTEM_WINDOW":
                return getResources().getString(R.string.INTERNAL_SYSTEM_WINDOW);
            case "android.permission.INTERNET":
                return getResources().getString(R.string.INTERNET);
            /***** K *****/
            case "android.permission.KILL_BACKGROUND_PROCESSES":
                return getResources().getString(R.string.KILL_BACKGROUND_PROCESSES);
            /***** M *****/
            case "android.permission.MANAGE_ACCOUNTS":
                return getResources().getString(R.string.MANAGE_ACCOUNTS);
            case "android.permission.MANAGE_APP_TOKENS":
                return getResources().getString(R.string.MANAGE_APP_TOKENS);
            case "android.permission.MANAGE_DOCUMENTS":
                return getResources().getString(R.string.MANAGE_DOCUMENTS);
            case "android.permission.MANAGE_NETWORK_POLICY":
                return getResources().getString(R.string.MANAGE_NETWORK_POLICY);
            case "android.permission.MASTER_CLEAR":
                return getResources().getString(R.string.MASTER_CLEAR);
            case "android.permission.MODIFY_AUDIO_SETTINGS":
                return getResources().getString(R.string.MODIFY_AUDIO_SETTINGS);
            case "android.permission.MODIFY_PHONE_STATE":
                return getResources().getString(R.string.MODIFY_PHONE_STATE);
            case "android.permission.MOUNT_FORMAT_FILESYSTEMS":
                return getResources().getString(R.string.MOUNT_FORMAT_FILESYSTEMS);
            case "android.permission.MOUNT_UNMOUNT_FILESYSTEMS":
                return getResources().getString(R.string.MOUNT_UNMOUNT_FILESYSTEMS);
            /***** N *****/
            case "android.permission.NFC":
                return getResources().getString(R.string.NFC);
            /***** P *****/
            case "android.permission.PERSISTENT_ACTIVITY":
                return getResources().getString(R.string.PERSISTENT_ACTIVITY);
            case "android.permission.PROCESS_OUTGOING_CALLS":
                return getResources().getString(R.string.PROCESS_OUTGOING_CALLS);
            /***** R *****/
            case "android.permission.RAISED_THREAD_PRIORITY":
                return getResources().getString(R.string.RAISED_THREAD_PRIORITY);
            case "android.permission.READ_CALL_LOG":
                return getResources().getString(R.string.READ_CALL_LOG);
            case "android.permission.READ_CALENDAR":
                return getResources().getString(R.string.READ_CALENDAR);
            case "android.permission.READ_CONTACTS":
                return getResources().getString(R.string.READ_CONTACTS);
            case "android.permission.READ_FRAME_BUFFER":
                return getResources().getString(R.string.READ_FRAME_BUFFER);
            case "android.permission.READ_HISTORY_BOOKMARKS":
                return getResources().getString(R.string.READ_HISTORY_BOOKMARKS);
            case "com.android.browser.permission.READ_HISTORY_BOOKMARKS":
                return getResources().getString(R.string.READ_HISTORY_BOOKMARKS);
            case "android.permission.READ_INPUT_STATE":
                return getResources().getString(R.string.READ_INPUT_STATE);
            case "android.permission.READ_LOGS":
                return getResources().getString(R.string.READ_LOGS);
            case "android.permission.READ_OWNER_DATA":
                return getResources().getString(R.string.READ_OWNER_DATA);
            case "android.permission.READ_PHONE_STATE":
                return getResources().getString(R.string.READ_PHONE_STATE);
            case "android.permission.READ_PROFILE":
                return getResources().getString(R.string.READ_PROFILE);
            case "com.android.launcher.permission.READ_SETTINGS":
            case "com.android.launcher2.permission.READ_SETTINGS":
            case "com.android.launcher3.permission.READ_SETTINGS":
                return getResources().getString(R.string.LAUNCHER_READ_SETTINGS);
            case "android.permission.READ_SETTINGS":
                return getResources().getString(R.string.READ_SETTINGS);
            case "android.permission.READ_SMS":
                return getResources().getString(R.string.READ_SMS);
            case "android.permission.READ_SYNC_SETTINGS":
                return getResources().getString(R.string.READ_SYNC_SETTINGS);
            case "android.permission.READ_SYNC_STATS":
                return getResources().getString(R.string.READ_SYNC_STATS);
            case "android.permission.REBOOT":
                return getResources().getString(R.string.REBOOT);
            case "com.google.android.c2dm.permission.RECEIVE":
                return getResources().getString(R.string.C2DM_RECEIVE);
            case "android.permission.RECEIVE_BOOT_COMPLETED":
                return getResources().getString(R.string.RECEIVE_BOOT_COMPLETED);
            case "android.permission.RECEIVE_MMS":
                return getResources().getString(R.string.RECEIVE_MMS);
            case "android.permission.RECEIVE_USER_PRESENT":
                return getResources().getString(R.string.RECEIVE_USER_PRESENT);
            case "android.permission.RECEIVE_SMS":
                return getResources().getString(R.string.RECEIVE_SMS);
            case "android.permission.RECEIVE_WAP_PUSH":
                return getResources().getString(R.string.RECEIVE_WAP_PUSH);
            case "android.permission.RECORD_AUDIO":
                return getResources().getString(R.string.RECORD_AUDIO);
            case "android.permission.REORDER_TASKS":
                return getResources().getString(R.string.REORDER_TASKS);
            case "android.permission.RESTART_PACKAGES":
                return getResources().getString(R.string.RESTART_PACKAGES);
            case "android.permission.READ_EXTERNAL_STORAGE":
                return getResources().getString(R.string.READ_EXTERNAL_STORAGE);
            case "android.permission.READ_SECURE_SETTINGS":
                return getResources().getString(R.string.READ_SECURE_SETTINGS);
            case "android.permission.READ_SOCIAL_STREAM":
                return getResources().getString(R.string.READ_SOCIAL_STREAM);
            case "android.permission.RUN_INSTRUMENTATION":
                return getResources().getString(R.string.RUN_INSTRUMENTATION);
            /***** S *****/
            case "android.permission.SEND_SMS":
                return getResources().getString(R.string.SEND_SMS);
            case "android.permission.SEND_SMS_NO_CONFIRMATION":
                return getResources().getString(R.string.SEND_SMS_NO_CONFIRMATION);
            case "android.permission.STOP_APP_SWITCHES":
                return getResources().getString(R.string.STOP_APP_SWITCHES);
            case "com.android.alarm.permission.SET_ALARM":
                return getResources().getString(R.string.SET_ALARM);
            case "android.permission.SET_ACTIVITY_WATCHER":
                return getResources().getString(R.string.SET_ACTIVITY_WATHCER);
            case "android.permission.SET_ALWAYS_FINISH":
                return getResources().getString(R.string.SET_ALWAYS_FINISH);
            case "android.permission.SET_ANIMATION_SCALE":
                return getResources().getString(R.string.SET_ANIMATION_SCALE);
            case "android.permission.SET_DEBUG_APP":
                return getResources().getString(R.string.SET_DEBUG_APP);
            case "android.permission.SET_ORIENTATION":
                return getResources().getString(R.string.SET_ORIENTATION);
            case "android.permission.SET_PROCESS_LIMIT":
                return getResources().getString(R.string.SET_PROCESS_LIMIT);
            case "android.permission.SET_TIME":
                return getResources().getString(R.string.SET_TIME);
            case "android.permission.SET_TIME_ZONE":
                return getResources().getString(R.string.SET_TIME_ZONE);
            case "android.permission.SET_WALLPAPER":
                return getResources().getString(R.string.SET_WALLPAPER);
            case "android.permission.SET_WALLPAPER_HINTS":
                return getResources().getString(R.string.SET_WALLPAPER_HINTS);
            case "android.permission.SET_PREFERRED_APPLICATIONS":
                return getResources().getString(R.string.SET_PREFERRED_APPLICATIONS);
            case "android.permission.SIGNAL_PERSISTENT_PROCESSES":
                return getResources().getString(R.string.SIGNAL_PERSISTENT_PROCESSES);
            case "android.permission.START_BACKGROUND_SERVICE":
                return getResources().getString(R.string.START_BACKGROUND_SERVICE);
            case "android.permission.STATUS_BAR":
                return getResources().getString(R.string.STATUS_BAR);
            case "android.permission.SUBSCRIBED_FEEDS_READ":
                return getResources().getString(R.string.SUBSCRIBED_FEEDS_READ);
            case "android.permission.SUBSCRIBED_FEEDS_WRITE":
                return getResources().getString(R.string.SUBSRIBED_FEEDS_WRITE);
            case "android.permission.SYSTEM_ALERT_WINDOW":
                return getResources().getString(R.string.SYSYTEM_ALERT_WINDOW);
            case "android.permission.SYSTEM_OVERLAY_WINDOW":
                return getResources().getString(R.string.SYSTEM_OVERLAY_WINDOW);
            /***** U *****/
            case "android.permission.UPDATE_DEVICE_STATS":
                return getResources().getString(R.string.UPDATE_DEVICE_STATS);

            case "android.permission.USE_CREDENTIALS":
                return getResources().getString(R.string.USE_CREDENTIALS);
            case "com.android.launcher.permission.UNINSTALL_SHORTCUT":
                return getResources().getString(R.string.LAUNCHER_UNINSTALL_SHORTCUT);
            /***** V *****/
            case "android.permission.VIBRATE":
                return getResources().getString(R.string.VIBRATE);
            /***** W *****/
            case "com.android.launcher.permission.WRITE_SETTINGS":
                return getResources().getString(R.string.LAUNCHER_WRITE_SETTINGS);
            case "android.permission.WAKE_LOCK":
                return getResources().getString(R.string.WAKE_LOCK);
            case "android.permission.WAP_PUSH":
                return getResources().getString(R.string.WAP_PUSH);
            case "android.permission.WRITE_APN_SETTINGS":
                return getResources().getString(R.string.WRITE_APN_SETTINGS);
            case "android.permission.WRITE_CALENDAR":
                return getResources().getString(R.string.WRITE_CALENDAR);
            case "android.permission.WRITE_CALL_LOG":
                return getResources().getString(R.string.WRITE_CALL_LOG);
            case "android.permission.WRITE_CONTACTS":
                return getResources().getString(R.string.WRITE_CONTACTS);
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                return getResources().getString(R.string.WRITE_EXTERNAL_STORAGE);
            case "android.permission.WRITE_GSERVICES":
                return getResources().getString(R.string.WRITE_GSERVICES);
            case "android.permission.WRITE_HISTORY_BOOKMARKS":
            case "com.android.browser.permission.WRITE_HISTORY_BOOKMARKS":
                return getResources().getString(R.string.WRITE_HISTORY_BOOKMARKS);
            case "android.permission.WRITE_INTERNAL_STORAGE":
                return getResources().getString(R.string.WRITE_INTERNAL_STORAGE);
            case "android.permission.WRITE_OWNER_DATA":
                return getResources().getString(R.string.WRITE_OWNER_DATA);
            case "android.permission.WRITE_PROFILE":
                return getResources().getString(R.string.WRITE_PROFILE);
            case "android.permission.WRITE_SECURE_SETTINGS":
                return getResources().getString(R.string.WRITE_SECURE_SETTINGS);
            case "android.permission.WRITE_SETTINGS":
                return getResources().getString(R.string.WRITE_SETTINGS);
            case "android.permission.WRITE_SMS":
                return getResources().getString(R.string.WRITE_SMS);
            case "android.permission.WRITE_SYNC_SETTINGS":
                return getResources().getString(R.string.WRITE_SYNC_SETTINGS);
            case "android.permission.WRITE_MEDIA_STORAGE":
                return getResources().getString(R.string.WRITE_MEDIA_STORAGE);

            /***** ToOrder *****/
            case "android.permission.SEND_DOWNLOAD_COMPLETED_INTENTS":
                return getResources().getString(R.string.SEND_DOWNLOAD_COMPLETED_INTENTS);
            case "android.permission.RECORD_VIDEO":
                return getResources().getString(R.string.RECORD_VIDEO);
            case "android.permission.UPDATE_APP_OPS_STATS":
                return getResources().getString(R.string.UPDATE_APP_OPS_STATS);
            case "android.permission.ACCESS_DEV_STORAGE":
                return getResources().getString(R.string.ACCESS_DEV_STORAGE);
            case "android.permission.MANAGE_DEVICE_ADMINS":
                return getResources().getString(R.string.MANAGE_DEVICE_ADMINS);
            case "android.permission.ACCESS_NOTIFICATIONS":
                return getResources().getString(R.string.ACCESS_NOTIFICATIONS);
            case "android.permission.ACCESS_WIMAX_STATE":
                return getResources().getString(R.string.ACCESS_WIMAX_STATE);
            case "android.permission.CHANGE_WIMAX_STATE":
                return getResources().getString(R.string.CHANGE_WIMAX_STATE);
            case "android.permission.FORCE_STOP_PACKAGES":
                return getResources().getString(R.string.FORCE_STOP_PACKAGES);
            case "android.permission.PACKAGE_USAGE_STATS":
                return getResources().getString(R.string.PACKAGE_USAGE_STATS);
            case "android.permission.MOVE_PACKAGE":
                return getResources().getString(R.string.MOVE_PACKAGE);
            case "android.permission.BACKUP":
                return getResources().getString(R.string.BACKUP);
            case "android.permission.MANAGE_USB":
                return getResources().getString(R.string.MANAGE_USB);
            case "android.permission.SET_POINTER_SPEED":
                return getResources().getString(R.string.SET_POINTER_SPEED);
            case "android.permission.SET_KEYBOARD_LAYOUT":
                return getResources().getString(R.string.SET_KEYBOARD_LAYOUT);
            case "android.permission.MANAGE_USERS":
                return getResources().getString(R.string.MANAGE_USERS);
            case "android.permission.CONFIGURE_WIFI_DISPLAY":
                return getResources().getString(R.string.CONFIGURE_WIFI_DISPLAY);

            /***** Other *****/
            default:
                return strPermission;
        }
    }
}
