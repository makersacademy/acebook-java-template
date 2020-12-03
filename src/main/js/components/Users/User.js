import React from 'react';
import Button from "../UI/Button/Button";

const User = (props) => {
  let controls = <Button btnType="Success" clicked={props.addFriend}>Add Friend</Button>;
  if(props.friend) {
    controls = <Button btnType="Danger" clicked={props.deleteFriend}>Unfriend</Button>;
  }
  return (
      <div className='post-main'>
        <div className="post-author">
          {`${props.user.firstName} ${props.user.lastName}`}
        </div>
        <div className='post-content'>
          <p>Email: {props.user.email}</p>
        </div>
        <div className="controls">
          {controls}
        </div>
      </div>
  )
}

export default User;