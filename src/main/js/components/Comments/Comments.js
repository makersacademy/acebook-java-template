import React, { Component } from 'react';
import client from '../../client';
import Comment from "./Comment";
import Button from "../UI/Button/Button";
import Aux from "../../hoc/Aux/Aux";

class Comments extends Component {
  constructor(props) {
    super(props)
    this.state = {
      newCommentText: ""
    };
    this.createComment = this.createComment.bind(this);
  }

  inputChangeHandler(event) {
    this.setState({
      newCommentText: event.target.value
    });
  }

  createComment(event) {
    event.preventDefault();
    client({method: 'POST',
      path: '/comments',
      entity: {
        "content": this.state.newCommentText,
        "user_id": this.props.user.id,
        "post_id": this.props.post_id},
      headers: {"Content-Type": "application/json"}
    }).then(response => {
      this.props.updateComments(this.props.post_id);
      this.setState({
        newCommentText: ""
      })
    })
  }

  deleteComment(id) {
    client({method: 'DELETE',
      path: '/api/comments/' + id
    }).then(response => {
      this.props.updateComments(this.props.post_id);
    })
  }

  render() {
    const comments = this.props.comments.map(comment => {
      return <Comment
          key={comment.id}
          comment={comment}
          deleteComment={() => this.deleteComment(comment.id)}/>
    });

    return(
        <div className="comment-container">
          <h4>New Comment</h4>
          <form onSubmit={this.createComment}>
            <textarea
                cols="50"
                rows="4"
                value={this.state.newCommentText}
                onChange={(event) => this.inputChangeHandler(event)}></textarea>
            <br/>
            <Button btnType="Success">Comment</Button>
          </form>
          {comments.length == 0 ? null : <h4>Comments</h4>}
          {comments}
        </div>

    )
  }

}

export default Comments;