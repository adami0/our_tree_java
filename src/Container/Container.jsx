import React from 'react';
import Navbar from '../Navbar/Navbar'
import Login from '../Login/Login'
import Trees from '../Trees/Trees'
import './Container.css';
import '../../node_modules/siimple'
import { isNoop } from '@babel/types';

class Container extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            authStatus: 'true',
            userEmail: '',
            userId: '42'
        };
    }

    render() {
        return (
            <div id="container">
                <Navbar></Navbar>
                {this.state.authStatus === 'false' ? <Login parentCallback = {this.checkAuthStatus} parentCallback2 = {this.retrieveUserEmail}></Login>:<Trees userId = {this.state.userId}></Trees>}
            </div>
        );
    }

    checkAuthStatus = (authStatusToBe) => {
        this.setState({
            authStatus: authStatusToBe
        });
        console.log(this.state.authStatus);
    }
   
    //we retrieve it from the child component login
    retrieveUserEmail = (userEmail) => {
        this.setState({
            userEmail: userEmail
        })
        console.log(this.state.userEmail);
        this.retrieveUserId(this.state.userEmail);
    }

    //we retrieve it from a request to database
    retrieveUserId = (userEmail) => {
        fetch(`http://localhost:9091/getUser/${userEmail}`, { 
            method: 'get'
        }).then(res => {
            console.log(res);
            //res is a RedeableStream so we need a reader
            let reader = res.body.getReader();
            return reader.read();
        }).then(resp => {
            console.log(resp);
            let user = new TextDecoder("utf-8").decode(resp.value);
            console.log(user);
            user = JSON.parse(user);
            console.log(user.id);
            this.setState({
                userId: user.id
            });
        })
    }

};

export default Container;