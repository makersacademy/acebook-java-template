import React from 'react';
import moment from 'moment';
import Button from "../UI/Button/Button";

const Comment = (props) => {
  let date = new Date(props.comment.created_at);
  let formattedDate = moment(date).fromNow();
  return (
      <div className='comment-main'>
        <div className="comment-author">
          <span>{`By: ${props.comment.user.firstName} ${props.comment.user.lastName}`}</span><span>{formattedDate}</span>
        </div>
        <div className='comment-content'>
          {props.comment.content}
        </div>
        <div className="controls">
          <Button btnType="Danger" clicked={props.deleteComment}>Delete</Button>
        </div>
      </div>
  )
}

export default Comment;
