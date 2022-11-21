// main

function FindPhoto(){
    const photoEl = document.getElementById("my-photo");
    fetch('/users/myphotoraw')
    .then((response) => response.text())
    .then((data) =>  photoEl.setAttribute("src",data));
}
FindPhoto();