import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import client from '../client';

class Logout extends Component {
  componentDidMount() {
    client({method: 'GET', path: '/logout'}).then(response => {
      console.log("logging out");
    });
  }

  render() {
    window.location.reload(true);
    return <Redirect to="/" />;
  }
}

export default Logout;