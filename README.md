# Cordova plugin DarkMode

### !! Android only !!

This plugin **only detects if dark (also called night) mode is enable or disable** on Android. It can tell you if inverted color mode is enabled, too.

This is usefull in case Android WebView on user device is not updated or you are using Crosswalk, otherwise you should use [**prefer-color-scheme**](https://developer.mozilla.org/en-US/docs/Web/CSS/@media/prefers-color-scheme) in css.

### Install
```
cordova plugin add https://github.com/EricYChu/cordova-plugin-darkmode.git
```

### Example
```js
DarkMode.listen(enabled => {
    console.log(enabled ? 'On' : 'Off')
})
```
