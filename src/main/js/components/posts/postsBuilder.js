import React from 'react';
import Posts from './posts'
import Aux from "../../hoc/Aux/Aux";
import Button from "../UI/Button/Button";
import Spinner from "../UI/Spinner/Spinner";
const client = require('../../client');

class PostsBuilder extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      posts: [],
      newPostText: "",
      showCommentId: null,
      loaded: false
    };
    this.deletePost = this.deletePost.bind(this);
    this.getPosts = this.getPosts.bind(this);
    this.createPost = this.createPost.bind(this);
    this.getComments = this.getComments.bind(this);
    this.showComments = this.showComments.bind(this);
    this.updateComments = this.updateComments.bind(this);
    this.sortByDate = this.sortByDate.bind(this);
  }

  componentDidMount() {
    this.getPosts();
  }

  getPosts() {
    client({method: 'GET', path: '/posts'}).then(response => {
      let posts = response.entity;
      this.setState({
        posts: []
      })
      //get an array of friend's ids
      let friendIds = []
      this.props.user.friends.forEach(friend => {
        friendIds.push(friend.id);
      })
      //use that array to remove any posts not by the logged in user or their friends
      posts = posts.filter(post => {
        return post.user.id === this.props.user.id || friendIds.includes(post.user.id);
      });
      //find comments for each post we're going to display
      posts.forEach(post => {
        this.getComments(post);
      })
      //if there are no posts get Comments never gets called, so we have to set loaded here instead
      if(posts.length === 0) {
        this.setState({
          loaded: true
        })
      }
    });
  }

  sortByDate(array) {
    //sort posts, newest first
    let sortedArray = array.sort((a, b) => {
      let aDate = new Date(a.created_at);
      let bDate = new Date(b.created_at);
      return bDate - aDate;
    });
    return sortedArray;
  }

  getComments(post) {
    client({method: 'GET', path: '/comments/' + post.id}).then(response => {
      let comments = response.entity
      comments = this.sortByDate(comments);
      post.comments = comments;
      let posts = [...this.state.posts];
      posts.push(post);
      let sortedPosts = this.sortByDate(posts);
      this.setState({
        posts: sortedPosts,
        loaded: true
      });
    });
  }

  updateComments(post_id) {
    client({method: 'GET', path: '/comments/' + post_id}).then(response => {
      let posts = [...this.state.posts];
      let postToUpdate = posts.find(post => post.id == post_id);
      let indexToUpdate = posts.indexOf(postToUpdate);
      postToUpdate.comments = response.entity;
      posts[indexToUpdate] = postToUpdate
      this.setState({
        posts: posts
      });
    });
  }

  showComments(post_id) {
    if(this.state.showCommentId == post_id) {
      this.setState({
        showCommentId: null
      });
    } else {
      this.setState({
        showCommentId: post_id
      });
    }
  }

  deletePost(id) {
    client({method: 'DELETE',
      path: '/posts/' + id
    }).then(response => {
      this.getPosts();
    })
  }

  inputChangeHandler(event) {
    this.setState({
      newPostText: event.target.value
    });
  }

  createPost(event) {
    event.preventDefault();
    client({method: 'POST',
      path: '/posts',
      entity: {"content": this.state.newPostText, "user_id": this.props.user.id },
      headers: {"Content-Type": "application/json"}
    }).then(response => {
      this.getPosts();
      this.setState({
        newPostText: ""
      })
    })
  }

	render() {
    let posts = <Spinner />
    if(this.state.loaded) {
      posts = <Posts
                user={this.props.user}
                posts={this.state.posts}
                deletePost={this.deletePost}
                showCommentId={this.state.showCommentId}
                showComments={this.showComments}
                updateComments={this.updateComments}/>
    }

		return (
		    <Aux>
          <h3>New Post</h3>
          <form onSubmit={this.createPost}>
            <textarea
                cols="80"
                rows="6"
                value={this.state.newPostText}
                onChange={(event) => this.inputChangeHandler(event)}></textarea>
            <br/>
            <Button btnType="Success">Post</Button>
          </form>
          {posts}
        </Aux>
		)
	}
}

export default PostsBuilder;
