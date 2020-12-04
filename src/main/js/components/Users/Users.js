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
    this.checkFriendMeWithThem = this.checkFriendMeWithThem.bind(this);
    this.checkFriendThemWithMe = this.checkFriendThemWithMe.bind(this);
    this.addFriend = this.addFriend.bind(this);
    this.deleteFriend = this.deleteFriend.bind(this);
    this.getFriendData = this.getFriendData.bind(this);
    this.acceptFriend = this.acceptFriend.bind(this);
    this.rejectFriend = this.rejectFriend.bind(this);

  }

  componentDidMount() {
    this.getUsers();
    this.getFriendData();
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

  getFriendData() {
    client({method: 'GET', path: '/friends'}).then(response => {
      let friendData = response.entity
      friendData = this.mapFriendData(friendData)
      this.setState({
        friendData: friendData
      });
    })
  }

  mapFriendData(data) {
    data = data.map(entry => {
      return {
        id: entry[0],
        person_id: entry[1],
        friend_id: entry[2],
        accepted: entry[3]
      }
    })
    console.log(data)
    return data;
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

  checkFriendMeWithThem(user) {
    let isFriend = false;
    this.props.user.friends.forEach(friend => {
      if(friend.id == user.id) {
        isFriend = true;
      }
    })
    return isFriend;
  }

  checkFriendThemWithMe(user) {
    let isFriend = false;
    this.props.user.friendOf.forEach(friend => {
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

  acceptFriend(friend) {
    client({method: 'POST',
      path: '/accept-friends',
      entity: {"person_id": this.props.user.id, "friend_id": friend.id },
      headers: {"Content-Type": "application/json"}
    }).then(response => {
      this.props.getCurrentUser();
    })
  }

  rejectFriend(friend) {
    client({method: 'POST',
      path: '/reject-friends',
      entity: {"person_id": friend.id, "friend_id": this.props.user.id },
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
            friendMeWithThem={this.checkFriendMeWithThem(user)}
            friendThemWithMe={this.checkFriendThemWithMe(user)}
            acceptFriend={() => this.acceptFriend(user)}
            rejectFriend={() => this.rejectFriend(user)}
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