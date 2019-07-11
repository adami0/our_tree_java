import React from 'react';
import './Login.css';
import '../../node_modules/siimple'

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            identifierInput: 'Email:',
            passwordInput: 'Password:',
            pageName: 'Log in',
            input1value: '',
            input2value: '',
            pageChoice: 'Sign up',
            messageToUser: '',
            authStatusToBe: 'false',
            userEmail: ''
        };
    }

    render() {
        return (
            <div id="login-container">                
                <div id="page-name">{this.state.pageName}</div>
                {this.state.messageToUser && this.state.messageToUser !== 'account created' ? <div id="messageToUser" className="siimple-alert siimple-alert--error">{this.state.messageToUser}</div> : null}
                {this.state.messageToUser === 'account created' ? <div id="messageToUser" className="siimple-alert siimple-alert--success">{this.state.messageToUser}</div> : null}
                <div id="input1header">{this.state.identifierInput}</div>
                <input id="input1" type="email" className="siimple-input" onChange={(evt) => this.handleChangeInput1(evt)}></input>
                <div id="input2header">{this.state.passwordInput}</div>
                <input id="input2" type="text" className="siimple-input" onChange={(evt) => this.handleChangeInput2(evt)}></input>
                <button className="siimple-btn btn" onClick={()=> this.logInOrSignUp()}>Validate</button>
                <div id="sign-up-container">
                    <div id="sign-up" onClick={() => this.loginOrsignUpPage()}>{this.state.pageChoice}</div>
                </div>
            </div>
        );
    }

    handleChangeInput1(evt) {
        this.setState({
            input1value: evt.target.value
        })
    }

    handleChangeInput2(evt) {
        this.setState({
            input2value: evt.target.value
        })
    }

    //to know which form send
    logInOrSignUp() {
        if (this.state.pageName === 'Log in') {
            this.sendFormToLogIn();
        } else {
            this.sendFormToSignUp();
        }
    }

    //to know which page display
    loginOrsignUpPage() {
        if (this.state.pageName === 'Log in') {
            this.signUpPage();
        } else {
            this.logInPage();
        }
    }

    //loginPage configuration
    logInPage() {
        this.setState({
            pageName: 'Log in',
            pageChoice: 'Sign Up',
            messageToUser: ''
        })
    }

    //authentification
    sendFormToLogIn() {
        if (this.state.input1value !== '' && this.state.input2value !== '') {
            const user = { email: this.state.input1value, password: this.state.input2value };
            fetch('http://localhost:9091/login', {
                method: 'post',
                body: JSON.stringify(user),
                headers: { "Content-Type": "application/json" }
            }).then(res => {
                //res is a RedeableStream so we need a reader
                let reader = res.body.getReader();
                return reader.read();
            }).then(resp => {
                console.log(resp);
                let string = new TextDecoder("utf-8").decode(resp.value);
                if (string === "false") {
                    this.setState({
                        messageToUser: 'invalid email/password'
                    })
                } else {
                    console.log(this.state.authStatusToBe)
                    this.setState({
                        messageToUser: '',
                        authStatusToBe: 'true',
                        userEmail: user.email
                    })
                    console.log(this.state.authStatusToBe)
                    //how the app knows the authentification status
                    this.sendAuthStatusToParent();
                    this.sendUserEmailToParent();
                }
            })
        }else {
            this.setState({
                messageToUser: 'please indicate your email and password'
            })
        }
    }

    //registering user
    sendFormToSignUp() {
        console.log('postUser');
        if (this.state.input1value !== '' && this.state.input2value !== '') {
            const user = { email: this.state.input1value, password: this.state.input2value };
            fetch('http://localhost:9091/postUser', {
                method: 'POST',
                body: JSON.stringify(user),
                headers: { "Content-Type": "application/json" }
            }).then(res => {
                //res is a RedeableStream so we need a reader
                let reader = res.body.getReader();
                return reader.read();
            }).then(resp => {
                console.log(resp);
                let string = new TextDecoder("utf-8").decode(resp.value);
                console.log(string);
                let displayString = (string === 'an account already exists with this email') ? this.setState({messageToUser: string}) : this.setState({messageToUser: 'account created'});
            });
        } else {
            this.setState({
                messageToUser: 'please put an email and a password'
            })
        }
    }

    //to send authentification status to parent
    sendAuthStatusToParent() {
        this.props.parentCallback(this.state.authStatusToBe);
    }

    sendUserEmailToParent() {
        this.props.parentCallback2(this.state.userEmail);
    }

    //sign up configuration
    signUpPage() {
        this.setState({
            pageName: 'Sign up',
            pageChoice: 'Log in',
            messageToUser: ''
        })
    }
    

};

export default Login;