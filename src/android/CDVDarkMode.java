package cordova.plugins.darkmode;

import android.content.res.Configuration;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;

public class CDVDarkMode extends CordovaPlugin {

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) {

        if (action.equals("listen")) {
            if (this.callbackContext != null) {
                return true;
            }

            this.callbackContext = callbackContext;

            this.sendPluginResult();

            return true;
        }

        return false;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        this.sendPluginResult(newConfig);
    }

    private void sendPluginResult() {
        if (this.callbackContext != null) {
            Configuration config = cordova.getActivity().getResources().getConfiguration();
            this.sendPluginResult(config);
        }
    }

    private void sendPluginResult(Configuration config) {
        if (this.callbackContext != null) {
            boolean enabled = isDarkModeEnabled(config);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, enabled);
            pluginResult.setKeepCallback(true);
            this.callbackContext.sendPluginResult(pluginResult);
        }
    }

    private boolean isDarkModeEnabled(Configuration config) {
        int uiMode = config.uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (uiMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                return false;

            // Night mode is not active, we're in day time
            case Configuration.UI_MODE_NIGHT_YES:
                return true;

            // Night mode is active, we're at night!
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // We don't know what mode we're in, assume notnight
                return false;
        }

        return false;
    }
}
