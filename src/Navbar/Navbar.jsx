import React from 'react';
import logo from '../logo.svg';
import '../App.css'
import './Navbar.css';

class Navbar extends React.Component {
    /*constructor(props) {
      super(props);
      this.state = {
        squares: Array(9).fill(null),
        xIsnext: true
      };
    }*/
    render() {
        return (
            <div id="navbar">
                <img src={logo} className="App-logo" alt="logo" />
            </div>
        );
    }
}

export default Navbar;