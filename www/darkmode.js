module.exports = {
    listen: function (callback) {
        cordova.exec(callback, null, "DarkMode", "listen", []);
    }
};
