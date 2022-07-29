import React, {Component} from 'react';

class Form extends Component {
    constructor(props) {
        super(props);
        
        this.initialState = {
            fullName: '',
            npiNumber: '',
            businessAddress: '',
            telephoneNumber: '',
            emailAddress: ''
        };

        this.state = this.initialState;
    }

    isValidEmail = (email) => {
        return /\S+@\S+\.\S+/.test(email);
    }

    isValidData = (data) => {
        return (data && data.length > 0);
    }

    setFocus = (selectorId) => {
        let selector = document.getElementById(selectorId);
        selector.style.backgroundColor = "red";
        selector.focus();
    }

    isValidInput = (user) => {
        if (user) {
           if (!this.isValidData(user.fullName)) {
              this.setFocus('fullName');
              return false;
           } else if (!this.isValidData(user.npiNumber)) {
              this.setFocus('npiNumber');
             return false;
           } else if (!this.isValidData(user.businessAddress)) {
              this.setFocus('businessAddress');
             return false;
           } else if (!this.isValidData(user.telephoneNumber)) {
              this.setFocus('telephoneNumber');
             return false;
           } else if (!this.isValidData(user.emailAddress) || !this.isValidEmail(user.emailAddress)) {
              this.setFocus('emailAddress');
             return false;
           }
           return true;
        }
        return false;
    }

    handleChange = event => {
        const { name, value } = event.target;

        if (this.isValidData(value)) {
           document.getElementById(name).style.backgroundColor = "";
        }
        this.setState({
            [name] : value
        });
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        if (!this.isValidInput(this.state)) return;
     
        this.props.handleSubmit(this.state);
        this.setState(this.initialState);
    }

    render() {
        const { fullName, npiNumber, businessAddress, telephoneNumber, emailAddress } = this.state; 

        return (
            <form onSubmit={this.onFormSubmit}>
                <label for="fullName">First and Last Name</label>
                <input 
                    type="text" 
                    name="fullName" 
                    id="fullName"
                    value={fullName} 
                    onChange={this.handleChange} />
                <label for="npiNumber">NPI Number</label>
                <input 
                    type="text" 
                    name="npiNumber" 
                    id="npiNumber"
                    value={npiNumber} 
                    onChange={this.handleChange} />
                <label for="businessAddress">Business Address</label>
                <input 
                    type="text" 
                    name="businessAddress" 
                    id="businessAddress"
                    value={businessAddress} 
                    onChange={this.handleChange} />
                <label for="telephoneNumber">Telephone Number</label>
                <input 
                    type="text" 
                    name="telephoneNumber" 
                    id="telephoneNumber"
                    value={telephoneNumber} 
                    onChange={this.handleChange} />
                <label for="emailAddress">Email address</label>
                <input 
                    type="text" 
                    name="emailAddress" 
                    id="emailAddress"
                    value={emailAddress} 
                    onChange={this.handleChange} />
                <button type="submit">
                    Register
                </button>
            </form>
        );
    }
}

export default Form;
