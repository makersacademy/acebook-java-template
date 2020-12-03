import React, { Component } from 'react';
import client from '../../client';
import Aux from "../../hoc/Aux/Aux";
import User from "./User";
import Spinner from "../UI/Spinner/Spinner";
import Button from "../UI/Button/Button";

class Users extends Component {
  constructor(props) {
    super(props)
    this.state = {
      users: [],
      displayedUsers: [],
      loaded: false,
      search: ""
    }
    this.getUsers = this.getUsers.bind(this);
    this.onSearch = this.onSearch.bind(this);
    this.checkFriend = this.checkFriend.bind(this);
    this.addFriend = this.addFriend.bind(this);
    this.deleteFriend = this.deleteFriend.bind(this);
  }

  componentDidMount() {
    this.getUsers();
  }

  getUsers() {
    client({method: 'GET', path: '/users'}).then(response => {
      let users = response.entity
      users = users.filter(user => {
        return user.id != this.props.user.id;
      })
      this.setState({
        users: users,
        displayedUsers: users,
        loaded: true});
    });
  }

  inputChangeHandler(event) {
    this.setState({
      search: event.target.value.toLowerCase()
    });
  }

  onSearch(event) {
    event.preventDefault();
    if(this.state.search === "") {
      this.getUsers();
      return
    }
    let usersToDisplay = [...this.state.users];
    usersToDisplay = usersToDisplay.filter(user => {
      return user.firstName.toLowerCase().includes(this.state.search) ||
          user.lastName.toLowerCase().includes(this.state.search) ||
          user.email.toLowerCase().includes(this.state.search)
    });
    this.setState({
      displayedUsers: usersToDisplay
    });
  }

  checkFriend(user) {
    let isFriend = false;
    this.props.user.friends.forEach(friend => {
      if(friend.id == user.id) {
        isFriend = true;
      }
    })
    return isFriend;
  }

  addFriend(friend) {
    client({method: 'POST',
      path: '/friends',
      entity: {"person_id": this.props.user.id, "friend_id": friend.id },
      headers: {"Content-Type": "application/json"}
    }).then(response => {
      this.props.getCurrentUser();
    })
  }

  deleteFriend(friend) {
    client({method: 'POST',
      path: '/delete-friends',
      entity: {"person_id": this.props.user.id, "friend_id": friend.id },
      headers: {"Content-Type": "application/json"}
    }).then(response => {
      this.props.getCurrentUser();
    })
  }

  render() {
    let users = <Spinner />

    if(this.state.loaded) {
      users = this.state.displayedUsers.map(user => {
        return <User
            key={user.id}
            user={user}
            friend={this.checkFriend(user)}
            addFriend={() => this.addFriend(user)}
            deleteFriend={() => this.deleteFriend(user)}
            />
      });
    }
    return (
        <Aux>
          <form onSubmit={this.onSearch}>
            <input type="text" value={this.state.search} onChange={(event) => this.inputChangeHandler(event)}/>
            <Button btnType="Success">Search</Button>
          </form>
          {users}
        </Aux>
    )
  }
}

export default Users;