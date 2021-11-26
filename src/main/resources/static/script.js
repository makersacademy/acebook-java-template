const getLike = document.querySelector('.like');
const getLikeNum = document.querySelector('.likeNum');

const getdisLike = document.querySelector('.dislike');
const getdisLikeNum = document.querySelector('.dislikeNum');

let like = 0;
let dislike = 0;
increaseLike = () => {
    like++
    getLikeNum.innerHTML = `${like}`
}
decreaseLike = () => {
    dislike--
    getdisLikeNum.innerHTML = `${dislike}`
}

likeClick = () => {
    increaseLike();
}

dislikeClick = () => {
    decreaseLike();
}

getLike.addEventListener(('click'), likeClick);
getdisLike.addEventListener(('click'), dislikeClick);

