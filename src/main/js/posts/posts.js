import React from 'react';
import Post from './post'

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.getPosts = this.getPosts.bind(this);
    this.handleDelete = this.handleDelete.bind(this);

  }

  handleDelete(event) {
  const id = event.target.id;
  console.log(id)
  		this.props.onDelete(id);
  }

   update() {
    this
   }

	render() {
		return (
      <article className='posts-main'>
        <h1 className='posts-title'>
           chickens
        </h1>
  			<div className='posts-items'>
  				{this.getPosts()}
  			</div>
      </article>
		)
	}

  getPosts() {
    return this.props.posts.map(post =>
			<Post key={post._links.self.href} post={post} handleDelete={this.handleDelete} update={this.update}/>

		);
  }

}

export default Posts;
