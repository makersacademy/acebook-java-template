import React from 'react';
import Button from "../UI/Button/Button";

const User = (props) => {
  let controls = null;

  if(props.friendMeWithThem && props.friendThemWithMe) {
    //if we're friends with each other, show unfriend button
    controls = <Button btnType="Danger" clicked={props.deleteFriend}>Unfriend</Button>;
  } else if (props.friendMeWithThem && !props.friendThemWithMe) {
    //if I'm friends with them but not them with me, show that my friend request has been sent and disable button
    controls = <Button btnType="Success" disabled={true}>Request sent</Button>;
  } else if (!props.friendMeWithThem && props.friendThemWithMe) {
    //if they're friends with me but not me with them show accept or reject buttons
    controls = [
        <Button btnType="Success" clicked={props.acceptFriend}>Accept</Button>,
        <Button btnType="Danger" clicked={props.rejectFriend}>Reject</Button>
        ]
  } else {
    //if neither are true then we're not friends, show add friend button
    controls = <Button btnType="Success" clicked={props.addFriend}>Add Friend</Button>;
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