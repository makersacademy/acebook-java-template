// import jQuery from "jquery";
// window.$ = window.jQuery = jQuery;

// For opening and closing tabs on friends page
function openTab(event, tabName) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace("active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(tabName).style.display = "block";
  event.currentTarget.className += "active";
}

// Below 2 are for getting friends of friends to work...
function deleteNoMutualDiv(divId, mutualCount) {
  if (mutualCount == 0) {
    var div = document.getElementById(divId);
    div.parentElement.remove();
  }
}

function cleanFriendsOfFriends() {
  var div = document.getElementById("0");
  var p1 = document.getElementById("P1")
  if (div.innerHTML.trim().length <= 41) {
    p1.textContent = "No recommendations.";
    div.remove();
  }
}

// Make tool tip appear on mouse hover
var tooltipSpan = document.getElementById('tooltip-span');
window.onmousemove = function (e) {
  var x = e.clientX,
      y = e.clientY;
  tooltipSpan.style.top = (y + 20) + 'px';
  tooltipSpan.style.left = (x + 20) + 'px';
};

function getSearchDropdown() {
  function show() {
    document.getElementById("searchDropdown").classList.toggle("show");
  }
  setTimeout(show, 120);
}

function filterFunction() {
  var input, filter, ul, li, a, i;
  var inputCount = document.getElementById("myInput").value.length;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  div = document.getElementById("searchDropdown");
  a = div.getElementsByClassName("searchwrapper");
  div.style.display = "none";
  if (inputCount > 0) {
    for (i = 0; i < a.length; i++) {
      txtValue = a[i].textContent || a[i].innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        div.style.display = "";
        a[i].style.display = "";
      } else {
        a[i].style.display = "none";
      }
    }
  }
}

function scrollSearches() {

}