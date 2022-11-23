

if(document.getElementsByClassName("main-btn").length > 0 && document.getElementsByClassName("main-input").length > 0){
    const inputBox = document.getElementsByClassName("main-input")[0];

    const mainPostBtn = document.getElementsByClassName("main-btn")[0];
    mainPostBtn.style.background = "buttonface";
    mainPostBtn.disabled = 'true';

    inputBox.addEventListener('input', output);
    function output(e) {
        if (e.target.value == "") {
            mainPostBtn.style.background = "buttonface";
            mainPostBtn.disabled = 'true';
        }else {
            mainPostBtn.style.background = "buttonface";
            mainPostBtn.removeAttribute("disabled");
        }

    }
}