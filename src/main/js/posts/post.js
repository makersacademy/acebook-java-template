import React from 'react';

const Post = (props) => {
	return (
		<div className='post-main'>
			<div className='post-content'>
				{props.post.content}
			</div>
			<div className='post-date'>
			    {props.post.postdate}
			</div>
        <button id={props.post._links.self.href} onClick={props.handleDelete}>Delete</button>
        <button onClick={props.update}>Delete</button>
        <h1> {props.post._links.self.href} </h1>
		</div>
	)
}

export default Post;
