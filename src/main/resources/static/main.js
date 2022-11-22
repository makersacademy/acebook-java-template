// main

function FindPhoto(){
    const photoEl = document.getElementById("my-photo");
    if(photoEl !== null){
        fetch('/users/myphotoraw')
        .then((response) => response.text())
        .then((data) =>  photoEl.setAttribute("src",data));
        console.log("Photo set");
    }
}

function SetReplyButtons(){
    const replybuttons = document.getElementsByClassName("reply-btn")
    for (let i =0; i < replybuttons.length;i++) {
        current_btn = replybuttons[i];
        current_btn.addEventListener("click", ReplyButtonFunction);
    }
    console.log("Reply buttons set");
}
function ReplyButtonFunction(e) {
    const ReplyWrapper = e.currentTarget.parentElement.parentElement.parentElement.parentElement.getElementsByClassName("reply")[0];
    ReplyWrapper.style.display = 'flex';
    ReplyWrapper.querySelector(".main-input").focus();
}

function AjaxReady(){
    const Forms = document.getElementsByTagName("form");
    for(let i=0;i<Forms.length;i++){

        const Form = Forms[i];
        const FormAction = Form.getAttribute("action");

        if(FormAction.includes("/users")){
            continue;
        }

        const Elements = Form.elements;

        let FormButton = null;
        for (let i = 0; i < Elements.length; i++) {
            const Element = Elements[i];
            if ((Element.nodeName === "INPUT" && Element.getAttribute("type")=="submit") || Element.nodeName==="BUTTON") {
                FormButton = Element;
            }
        }
        if(FormButton == null){
            continue;
        }

        // replace submit input with button
        if(FormButton.nodeName === "INPUT" && FormButton.getAttribute("type")=="submit"){
            const newFormButton = document.createElement("button");
            newFormButton.className=FormButton.className;
            newFormButton.innerHTML=FormButton.getAttribute("value");
            FormButton.parentElement.appendChild(newFormButton);
            FormButton.remove();
            FormButton = newFormButton;
        }

        FormButton.onclick = function(e){

            const cachedButtonTxt = FormButton.innerHTML;
            FormButton.innerHTML = "<img src='/loading-tiny.gif' height='25px'>";

            e.preventDefault();
            
            let data = {};
            for (let i = 0; i < Elements.length; i++) {
                const Element = Elements[i];
                if (Element.nodeName === "INPUT" || Element.nodeName === "TEXTAREA") {
                    data[Element.getAttribute("name")]=Element.value;
                }
            }
            console.log(data);
            const XHR = new XMLHttpRequest();
            const FD = new FormData();
            for (const [name, value] of Object.entries(data)) {
                FD.append(name, value);
            }
            XHR.addEventListener('error', (event) => {
                alert('Something went wrong.');
            });
            XHR.onreadystatechange = function() {
                //"post-footer-right flex-center"
                if(XHR.responseText.includes("error")){
                    const parsed = JSON.parse(XHR.responseText);
                    alert(parsed.error);
                    FormButton.innerHTML = cachedButtonTxt;
                }
                if(XHR.readyState == 4 && XHR.status == 200) {
                    let htmlObject = document.createElement('html');
                    htmlObject.innerHTML = XHR.responseText;

                    if(FormAction == "/likes" || FormAction == "/posts/reply"){
                        const ElementId = "#post-wrapper-"+data.post_id;
                        const newElement = htmlObject.querySelector(ElementId);
                        const currentElement = document.querySelector(ElementId);
                        const cachedFooter = currentElement.querySelector(".post-footer-right.flex-center").innerHTML;
                        currentElement.innerHTML=newElement.innerHTML;
                        currentElement.querySelector(".post-footer-right.flex-center").innerHTML=cachedFooter;
                    }else{
                        document.getElementsByTagName("body")[0].innerHTML=htmlObject.getElementsByTagName("body")[0].innerHTML;
                    }
                    SetupAll();
                }
            }
            XHR.open('POST', FormAction);
            XHR.send(FD);

        }

    }
    console.log("Ajax is ready");
}

function SetupProfileImageUploader(path){
    let Dimensions = [300,100];
    if(path[2]=="new"){
        Dimensions[0]=100;
    }
    const fileUploaderContainer = document.getElementById("file-uploader-container");
    if(fileUploaderContainer !== null){
        const fileInput = document.getElementById("file-upload-input");
        const base64DataInput = document.getElementById("base64-data-input");
        fileInput.onchange = function(){
            let reader = new FileReader();
            reader.readAsDataURL(fileInput.files[0]);
            reader.onload = function(e) {
                // get the data from the upload input
                const imageSrc = e.target.result;
                // draw onto a canvas
                let cv = document.createElement("canvas");
                cv.setAttribute("width",Dimensions[0]+"px");
                cv.setAttribute("height",Dimensions[1]+"px");
                let cvtx = cv.getContext("2d");
                let ti = new Image();
                ti.src = imageSrc;
                ti.onload = function(){
                    // crop the image nicely
                    let nw = Dimensions[0];
                    let nh = Dimensions[0]*(ti.naturalHeight/ti.naturalWidth);
                    if(nh < Dimensions[1]){
                        nw = Dimensions[1]*(ti.naturalWidth/ti.naturalHeight);
                        nh = Dimensions[1];
                    }
                    console.log(nw,nh);
                    let hOffset = -(nh*0.5);
                    if(nh <= Dimensions[1]){
                        hOffset = 0;
                    }
                    cvtx.drawImage(ti,0,hOffset,nw,nh);
                    console.log("drawn");
                    // extract final base64 data
                    const exp = cv.toDataURL();
                    // set form input to have it as a value
                    base64DataInput.value=exp;
                    // remove the file input
                    //fileInput.remove();
                    // show the image to the user
                    ti.src=exp;
                    ti.className="preview-photo";
                    ti.onload = function(){
                    console.log("done");
                    }
                    fileUploaderContainer.appendChild(ti);
                }
            }
        }
        console.log("Profile image uploader setup");
    }
}

function SetupAll(){
    FindPhoto();
    AjaxReady();
    SetReplyButtons();
    const path = window.location.pathname.split("/");
    if(path[1]=="users" && path.length > 2){
        SetupProfileImageUploader(path);
    }
}

window.addEventListener("load",function(){
    SetupAll();
});