import React from 'react';
import NavigationLink from './NavigationLink/NavigationLink';

const NavigationLinks = (props) => {
  return (
      <div className="NavBar">
        <ul className="NavLinks">
          <NavigationLink link="/" exact>Home</NavigationLink>
          <NavigationLink link="/newsfeed" exact>Posts</NavigationLink>
          <NavigationLink link="/userslist" exact>Users</NavigationLink>
          <NavigationLink link="/logout" exact>Logout</NavigationLink>
        </ul>
      </div>
  )
}

export default NavigationLinks;