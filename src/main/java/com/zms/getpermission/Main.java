package com.zms.getpermission;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
                        stringBuilder.append(getPermissionDetail(permission[i]) + "\n");
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

    public String getPermissionDetail(String strPermission) {
        /***** A *****/
        if (strPermission.equals("android.permission.ACCESS_ALL_DOWNLOADS")) {
            return getResources().getString(R.string.ACCESS_ALL_DOWNLOADS);
        } else if (strPermission.equals("android.permission.ACCESS_CHECKIN_PROPERTIES")) {
            return getResources().getString(R.string.ACCESS_CHECKIN_PROPERTIES);
        } else if (strPermission.equals("android.permission.ACCESS_COARSE_LOCATION")) {
            return getResources().getString(R.string.ACCESS_COARSE_LOCATION);
        } else if (strPermission.equals("android.permission.ACCESS_FINE_LOCATION")) {
            return getResources().getString(R.string.ACCESS_FINE_LOCATION);
        } else if (strPermission.equals("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS")) {
            return getResources().getString(R.string.ACCESS_LOCATION_EXTRA_COMMANDS);
        } else if (strPermission.equals("android.permission.ACCESS_MOCK_LOCATION")) {
            return getResources().getString(R.string.ACCESS_MOCK_LOCATION);
        } else if (strPermission.equals("android.permission.ACCESS_NETWORK_STATE")) {
            return getResources().getString(R.string.ACCESS_NETWORK_STATE);
        } else if (strPermission.equals("android.permission.ACCESS_SURFACE_FLINGER")) {
            return getResources().getString(R.string.ACCESS_SURFACE_FLINGER);
        } else if (strPermission.equals("android.permission.ACCESS_SUPERUSER")) {
            return getResources().getString(R.string.ACCESS_SUPERUSER);
        } else if (strPermission.equals("android.permission.ACCESS_WIFI_STATE")) {
            return getResources().getString(R.string.ACCESS_WIFI_STATE);
        } else if (strPermission.equals("android.permission.ACCOUNT_MANAGER")) {
            return getResources().getString(R.string.ACCOUNT_MANAGER);
        } else if (strPermission.equals("android.permission.AUTHERTICATE_ACCOUTS")) {
            return getResources().getString(R.string.AUTHERTICATE_ACCOUTS);
        }
        /***** B *****/
        else if (strPermission.equals("android.permission.BATTERY_STATS")) {
            return getResources().getString(R.string.BATTERY_STATS);
        } else if (strPermission.equals("android.permission.BIND_APPWIDGET")) {
            return getResources().getString(R.string.BIND_APPWIGET);
        } else if (strPermission.equals("android.permission.BIND_DEVICE_ADMIN")) {
            return getResources().getString(R.string.BIND_DEVICE_ADMIN);
        } else if (strPermission.equals("android.permission.BIND_INPUT_METHOD")) {
            return getResources().getString(R.string.BIND_INPUT_METHOD);
        } else if (strPermission.equals("android.permission.BIND_WALLPAPER")) {
            return getResources().getString(R.string.BIND_WALLPAPER);
        } else if (strPermission.equals("android.permission.BLUETOOTH")) {
            return getResources().getString(R.string.BLUETOOTH);
        } else if (strPermission.equals("android.permission.BLUETOOTH_ADMIN")) {
            return getResources().getString(R.string.BULETOOTH_ADMIN);
        } else if (strPermission.equals("android.permission.BRICK ")) {
            return getResources().getString(R.string.BRICK);
        } else if (strPermission.equals("android.permission.BROADCAST_PACKAGE_REMOVED")) {
            return getResources().getString(R.string.BROADCAST_PACKAGE_REMOVED);
        } else if (strPermission.equals("android.permission.BROADCAST_SMS")) {
            return getResources().getString(R.string.BROADCAST_SMS);
        } else if (strPermission.equals("android.permission.BROADCAST_STICKY")) {
            return getResources().getString(R.string.BROADCAST_STICKY);
        }
        /***** C *****/
        else if (strPermission.equals("android.permission.CALL_PHONE")) {
            return getResources().getString(R.string.CALL_PHONE);
        } else if (strPermission.equals("android.permission.CALL_PRIVILEGED")) {
            return getResources().getString(R.string.CALL_PRIVILEGED);
        } else if (strPermission.equals("android.permission.CAMERA")) {
            return getResources().getString(R.string.CAMERA);
        } else if (strPermission.equals("android.permission.CHANGE_COMPONENT_ENABLED_STATE")) {
            return getResources().getString(R.string.CHANGE_COMPONENT_ENABLED_STATE);
        } else if (strPermission.equals("android.permission.CHANGE_CONFIGURATION")) {
            return getResources().getString(R.string.CHANGE_CONFIGURATION);
        } else if (strPermission.equals("android.permission.CHANGE_NETWORK_STATE")) {
            return getResources().getString(R.string.CHANGE_NETWORK_STATE);
        } else if (strPermission.equals("android.permission.CHANGE_WIFI_MULTICAST_STATE")) {
            return getResources().getString(R.string.CHANGE_WIFIMULTICAST_STATE);
        } else if (strPermission.equals("android.permission.CHANGE_WIFI_STATE")) {
            return getResources().getString(R.string.CHANGE_WIFI_STATE);
        } else if (strPermission.equals("android.permission.CLEAR_APP_CACHE")) {
            return getResources().getString(R.string.CLEAR_APP_CACHE);
        } else if (strPermission.equals("android.permission.CLEAR_APP_USER_DATA")) {
            return getResources().getString(R.string.CLEAR_APP_USER_DATA);
        } else if (strPermission.equals("android.permission.CONTROL_LOCATION_UPDATES")) {
            return getResources().getString(R.string.CONTROL_LOCATION_UPDATES);
        }
        /***** D *****/
        else if (strPermission.equals("android.permission.DELETE_CACHE_FILES")) {
            return getResources().getString(R.string.DELETE_CACHE_FILES);
        } else if (strPermission.equals("android.permission.DELETE_PACKAGES")) {
            return getResources().getString(R.string.DLEETE_PACKAGES);
        } else if (strPermission.equals("android.permission.DEVICE_POWER")) {
            return getResources().getString(R.string.DEVICE_POWER);
        } else if (strPermission.equals("android.permission.DIAGNOSTIC")) {
            return getResources().getString(R.string.DIAGNOSTIC);
        } else if (strPermission.equals("android.permission.DISABLE_KEYGUARD")) {
            return getResources().getString(R.string.DISABLE_KEYGUARD);
        } else if (strPermission.equals("android.permission.DUMP")) {
            return getResources().getString(R.string.DUMP);
        } else if (strPermission.equals("android.permission.DOWNLOAD_WITHOUT_NOTIFICATION")) {
            return getResources().getString(R.string.DOWNLOAD_WITHOUT_NOTIFICATION);
        }
        /***** E *****/
        else if (strPermission.equals("android.permission.EXPAND_STATUS_BAR")) {
            return getResources().getString(R.string.EXPAND_STATUS_BAR);
        }
        /***** F *****/
        else if (strPermission.equals("android.permission.FACTORY_TEST")) {
            return getResources().getString(R.string.FACTORY_TEST);
        } else if (strPermission.equals("android.permission.FLASHLIGHT")) {
            return getResources().getString(R.string.FLSHLIGHT);
        } else if (strPermission.equals("android.permission.FORCE_BACK")) {
            return getResources().getString(R.string.FORCE_BACK);
        }
        /***** G *****/
        else if (strPermission.equals("android.permission.GET_ACCOUNTS")) {
            return getResources().getString(R.string.GET_ACCOUNTS);
        } else if (strPermission.equals("android.permission.GET_PACKAGE_SIZE")) {
            return getResources().getString(R.string.GET_PACKAGE_SIZE);
        } else if (strPermission.equals("android.permission.GET_TASKS")) {
            return getResources().getString(R.string.GET_TASKS);
        } else if (strPermission.equals("android.permission.GLOBAL_SEARCH")) {
            return getResources().getString(R.string.GLBOAL_SEARCH);
        }
        /***** H *****/
        else if (strPermission.equals("android.permission.HARDWARE_TEST ")) {
            return getResources().getString(R.string.HARDWARE_TEST);
        }
        /***** I *****/
        else if (strPermission.equals("android.permission.INJECT_EVENTS")) {
            return getResources().getString(R.string.INJECT_EVENTS);
        } else if (strPermission.equals("android.permission.INSTALL_LOCATION_PROVIDER")) {
            return getResources().getString(R.string.INSTALL_LOCATION_PROVIDER);
        } else if (strPermission.equals("android.permission.INSTALL_PACKAGES")) {
            return getResources().getString(R.string.INSTALL_PCAKAGES);
        } else if (strPermission.equals("com.android.launcher.permission.INSTALL_SHORTCUT")) {
            return getResources().getString(R.string.LAUNCHER_INSTALL_SHORTCUT);
        } else if (strPermission.equals("android.permission.INTERNAL_SYSTEM_WINDOW")) {
            return getResources().getString(R.string.INTERNAL_SYSTEM_WINDOW);
        } else if (strPermission.equals("android.permission.INTERNET")) {
            return getResources().getString(R.string.INTERNET);
        }
        /***** K *****/
        else if (strPermission.equals("android.permission.KILL_BACKGROUND_PROCESSES")) {
            return getResources().getString(R.string.KILL_BACKGROUND_PROCESSES);
        }
        /***** M *****/
        else if (strPermission.equals("android.permission.MANAGE_ACCOUNTS")) {
            return getResources().getString(R.string.MANAGE_ACCOUNTS);
        } else if (strPermission.equals("android.permission.MANAGE_APP_TOKENS")) {
            return getResources().getString(R.string.MANAGE_APP_TOKENS);
        } else if (strPermission.equals("android.permission.MASTER_CLEAR")) {
            return getResources().getString(R.string.MASTER_CLEAR);
        } else if (strPermission.equals("android.permission.MODIFY_AUDIO_SETTINGS")) {
            return getResources().getString(R.string.MODIFY_AUDIO_SETTINGS);
        } else if (strPermission.equals("android.permission.MODIFY_PHONE_STATE")) {
            return getResources().getString(R.string.MODIFY_PHONE_STATE);
        } else if (strPermission.equals("android.permission.MOUNT_FORMAT_FILESYSTEMS")) {
            return getResources().getString(R.string.MOUNT_FORMAT_FILESYSTEMS);
        } else if (strPermission.equals("android.permission.MOUNT_UNMOUNT_FILESYSTEMS")) {
            return getResources().getString(R.string.MOUNT_UNMOUNT_FILESYSTEMS);
        }
        /***** N *****/
        else if (strPermission.equals("android.permission.NFC")) {
            return getResources().getString(R.string.NFC);
        }
        /***** P *****/
        else if (strPermission.equals("android.permission.PERSISTENT_ACTIVITY")) {
            return getResources().getString(R.string.PERSISTENT_ACTIVITY);
        } else if (strPermission.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
            return getResources().getString(R.string.PROCESS_OUTGOING_CALLS);
        }
        /***** R *****/
        else if (strPermission.equals("android.permission.READ_CALL_LOG")) {
            return getResources().getString(R.string.READ_CALL_LOG);
        } else if (strPermission.equals("android.permission.READ_CALENDAR")) {
            return getResources().getString(R.string.READ_CALENDAR);
        } else if (strPermission.equals("android.permission.READ_CONTACTS")) {
            return getResources().getString(R.string.READ_CONTACTS);
        } else if (strPermission.equals("android.permission.READ_FRAME_BUFFER")) {
            return getResources().getString(R.string.READ_FRAME_BUFFER);
        } else if (strPermission.equals("android.permission.READ_HISTORY_BOOKMARKS") ||
                strPermission.equals("com.android.browser.permission.READ_HISTORY_BOOKMARKS")) {
            return getResources().getString(R.string.READ_HISTORY_BOOKMARKS);
        } else if (strPermission.equals("android.permission.READ_INPUT_STATE")) {
            return getResources().getString(R.string.READ_INPUT_STATE);
        } else if (strPermission.equals("android.permission.READ_LOGS")) {
            return getResources().getString(R.string.READ_LOGS);
        } else if (strPermission.equals("android.permission.READ_OWNER_DATA")) {
            return getResources().getString(R.string.READ_OWNER_DATA);
        } else if (strPermission.equals("android.permission.READ_PHONE_STATE")) {
            return getResources().getString(R.string.READ_PHONE_STATE);
        } else if (strPermission.equals("com.android.launcher.permission.READ_SETTINGS") ||
                strPermission.equals("com.android.launcher2.permission.READ_SETTINGS") ||
                strPermission.equals("com.android.launcher3.permission.READ_SETTINGS")) {
            return getResources().getString(R.string.LAUNCHER_READ_SETTINGS);
        } else if (strPermission.equals("android.permission.READ_SMS")) {
            return getResources().getString(R.string.READ_SMS);
        } else if (strPermission.equals("android.permission.READ_SYNC_SETTINGS")) {
            return getResources().getString(R.string.READ_SYNC_SETTINGS);
        } else if (strPermission.equals("android.permission.READ_SYNC_STATS")) {
            return getResources().getString(R.string.READ_SYNC_STATS);
        } else if (strPermission.equals("android.permission.REBOOT")) {
            return getResources().getString(R.string.REBOOT);
        } else if (strPermission.equals("android.permission.RECEIVE_BOOT_COMPLETED")) {
            return getResources().getString(R.string.RECEIVE_BOOT_COMPLETED);
        } else if (strPermission.equals("android.permission.RECEIVE_MMS")) {
            return getResources().getString(R.string.RECEIVE_MMS);
        } else if (strPermission.equals("android.permission.RECEIVE_SMS")) {
            return getResources().getString(R.string.RECEIVE_SMS);
        } else if (strPermission.equals("android.permission.RECEIVE_WAP_PUSH")) {
            return getResources().getString(R.string.RECEIVE_WAP_PUSH);
        } else if (strPermission.equals("android.permission.RECORD_AUDIO")) {
            return getResources().getString(R.string.RECORD_AUDIO);
        } else if (strPermission.equals("android.permission.REORDER_TASKS")) {
            return getResources().getString(R.string.REORDER_TASKS);
        } else if (strPermission.equals("android.permission.RESTART_PACKAGES")) {
            return getResources().getString(R.string.RESTART_PACKAGES);
        } else if (strPermission.equals("android.permission.READ_EXTERNAL_STORAGE")) {
            return getResources().getString(R.string.READ_EXTERNAL_STORAGE);
        } else if (strPermission.equals("android.permission.READ_SECURE_SETTINGS")) {
            return getResources().getString(R.string.READ_SECURE_SETTINGS);
        }
        /***** S *****/
        else if (strPermission.equals("android.permission.SEND_SMS")) {
            return getResources().getString(R.string.SEND_SMS);
        } else if (strPermission.equals("android.permission.SET_ACTIVITY_WATCHER")) {
            return getResources().getString(R.string.SET_ACTIVITY_WATHCER);
        } else if (strPermission.equals("android.permission.SET_ALWAYS_FINISH")) {
            return getResources().getString(R.string.SET_ALWAYS_FINISH);
        } else if (strPermission.equals("android.permission.SET_ANIMATION_SCALE")) {
            return getResources().getString(R.string.SET_ANIMATION_SCALE);
        } else if (strPermission.equals("android.permission.SET_DEBUG_APP")) {
            return getResources().getString(R.string.SET_DEBUG_APP);
        } else if (strPermission.equals("android.permission.SET_ORIENTATION")) {
            return getResources().getString(R.string.SET_ORIENTATION);
        } else if (strPermission.equals("android.permission.SET_PROCESS_LIMIT")) {
            return getResources().getString(R.string.SET_PROCESS_LIMIT);
        } else if (strPermission.equals("android.permission.SET_TIME")) {
            return getResources().getString(R.string.SET_TIME);
        } else if (strPermission.equals("android.permission.SET_TIME_ZONE")) {
            return getResources().getString(R.string.SET_TIME_ZONE);
        } else if (strPermission.equals("android.permission.SET_WALLPAPER")) {
            return getResources().getString(R.string.SET_WALLPAPER);
        } else if (strPermission.equals("android.permission.SET_WALLPAPER_HINTS")) {
            return getResources().getString(R.string.SET_WALLPAPER_HINTS);
        } else if (strPermission.equals("android.permission.SET_PREFERRED_APPLICATIONS")) {
            return getResources().getString(R.string.SET_PREFERRED_APPLICATIONS);
        } else if (strPermission.equals("android.permission.SIGNAL_PERSISTENT_PROCESSES")) {
            return getResources().getString(R.string.SIGNAL_PERSISTENT_PROCESSES);
        } else if (strPermission.equals("android.permission.STATUS_BAR")) {
            return getResources().getString(R.string.STATUS_BAR);
        } else if (strPermission.equals("android.permission.SUBSCRIBED_FEEDS_READ")) {
            return getResources().getString(R.string.SUBSCRIBED_FEEDS_READ);
        } else if (strPermission.equals("android.permission.SUBSCRIBED_FEEDS_WRITE")) {
            return getResources().getString(R.string.SUBSRIBED_FEEDS_WRITE);
        } else if (strPermission.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
            return getResources().getString(R.string.SYSYTEM_ALERT_WINDOW);
        }
        /***** U *****/
        else if (strPermission.equals("android.permission.UPDATE_DEVICE_STATS")) {
            return getResources().getString(R.string.UPDATE_DEVICE_STATS);
        } else if (strPermission.equals("android.permission.USE_CREDENTIALS")) {
            return getResources().getString(R.string.USE_CREDENTIALS);
        } else if (strPermission.equals("com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
            return getResources().getString(R.string.LAUNCHER_UNINSTALL_SHORTCUT);
        }
        /***** V *****/
        else if (strPermission.equals("android.permission.VIBRATE")) {
            return getResources().getString(R.string.VIBRATE);
        }
        /***** W *****/
        else if (strPermission.equals("com.android.launcher.permission.WRITE_SETTINGS")) {
            return getResources().getString(R.string.LAUNCHER_WRITE_SETTINGS);
        } else if (strPermission.equals("android.permission.WAKE_LOCK")) {
            return getResources().getString(R.string.WAKE_LOCK);
        } else if (strPermission.equals("android.permission.WAP_PUSH")) {
            return getResources().getString(R.string.WAP_PUSH);
        } else if (strPermission.equals("android.permission.WRITE_APN_SETTINGS")) {
            return getResources().getString(R.string.WRITE_APN_SETTINGS);
        } else if (strPermission.equals("android.permission.WRITE_CALENDAR")) {
            return getResources().getString(R.string.WRITE_CALENDAR);
        } else if (strPermission.equals("android.permission.WRITE_CALL_LOG")) {
            return getResources().getString(R.string.WRITE_CALL_LOG);
        } else if (strPermission.equals("android.permission.WRITE_CONTACTS")) {
            return getResources().getString(R.string.WRITE_CONTACTS);
        } else if (strPermission.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
            return getResources().getString(R.string.WRITE_EXTERNAL_STORAGE);
        } else if (strPermission.equals("android.permission.WRITE_GSERVICES")) {
            return getResources().getString(R.string.WRITE_GSERVICES);
        } else if (strPermission.equals("android.permission.WRITE_HISTORY_BOOKMARKS") ||
                strPermission.equals("com.android.browser.permission.READ_HISTORY_BOOKMARKS")) {
            return getResources().getString(R.string.WRITE_HISTORY_BOOKMARKS);
        } else if (strPermission.equals("android.permission.WRITE_OWNER_DATA")) {
            return getResources().getString(R.string.WRITE_OWNER_DATA);
        } else if (strPermission.equals("android.permission.WRITE_SECURE_SETTINGS")) {
            return getResources().getString(R.string.WRITE_SECURE_SETTINGS);
        } else if (strPermission.equals("android.permission.WRITE_SETTINGS")) {
            return getResources().getString(R.string.WRITE_SETTINGS);
        } else if (strPermission.equals("android.permission.WRITE_SMS")) {
            return getResources().getString(R.string.WRITE_SMS);
        } else if (strPermission.equals("android.permission.WRITE_SYNC_SETTINGS")) {
            return getResources().getString(R.string.WRITE_SYNC_SETTINGS);
        } else if (strPermission.equals("android.permission.WRITE_MEDIA_STORAGE")) {
            return getResources().getString(R.string.WRITE_MEDIA_STORAGE);
        }
        /***** Other *****/
        else {
            return strPermission;
        }
    }
}
