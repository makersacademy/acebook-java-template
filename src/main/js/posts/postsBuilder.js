import React from 'react';
import Posts from './posts'
const client = require('../client');

class PostsBuilder extends React.Component {
  constructor(props) {
    super(props)
    this.state = {posts: []};
    this.onDelete = this.onDelete.bind(this);
  }

  componentDidMount() {
    client({method: 'GET', path: '/api/posts'}).then(response => {
      this.setState({posts: response.entity._embedded.posts});
    });
  }

  onDelete(post) {
  console.log(post);
  	client({method: 'DELETE', path: post})
  }

	render() {
		return (
      <Posts posts={this.state.posts} onDelete={this.onDelete}/>
		)
	}
}

export default PostsBuilder;
