const commentBtn = document.getElementById('button_comment');
const commentSections = [...document.querySelectorAll(`[id*="comment_section"]`)];
const allCommentButtons = Array.from(document.getElementsByClassName('comment_button'));
console.log(allCommentButtons);

 
allCommentButtons.forEach( (button) => {
  button.addEventListener('click', (event) => {
    const id = event.srcElement.nextElementSibling.id;
    
    const displayCommentSection = document.getElementById(id);
    displayCommentSection.classList.toggle("display_flex")
  })
} )

