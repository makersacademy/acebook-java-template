// theme.js
(function() {
    const root = document.documentElement;

    function setTheme(newTheme) {
        window.__theme = newTheme;
        root.classList.remove("dark", "light");
        root.classList.add(newTheme);
    }

    var preferredTheme = "system";
    var darkQuery = window.matchMedia('(prefers-color-scheme: dark)');
    setTheme(preferredTheme === "system" ? (darkQuery.matches ? 'dark' : 'light') : preferredTheme);
})();

// https://gist.github.com/paulirish/1579671
(function() {
    var lastTime = 0;
    if (!window.requestAnimationFrame) {
        window.requestAnimationFrame = window['webkitRequestAnimationFrame'];
        window.cancelAnimationFrame = window['webkitCancelAnimationFrame'] || window['webkitCancelRequestAnimationFrame'];
    }

    if (!window.requestAnimationFrame) {
        window.requestAnimationFrame = function(callback, element) {
            var currTime = new Date().getTime();
            var timeToCall = Math.max(0, 16 - (currTime - lastTime));
            var id = window.setTimeout(function() {
                    callback(currTime + timeToCall);
                },
                timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        };
    }

    if (!window.cancelAnimationFrame) {
        window.cancelAnimationFrame = function(id) {
            clearTimeout(id);
        };
    }
}());