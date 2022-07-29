import React, { Component } from 'react';
import Table from './Table';
import Form from './Form';

class App extends Component {
    state = {
        availityUsers: []
    };

    removeAvailityUser = index => {
        const { availityUsers } = this.state;
    
        this.setState({
            availityUsers: availityUsers.filter((availityUser, i) => { 
                return i !== index;
            })
        });
    }

    handleSubmit = availityUser => {
        this.setState({availityUsers: [...this.state.availityUsers, availityUser]});
    }

    render() {
        const { availityUsers } = this.state;
        
        return (
            <div className="container">
                <h1>Availity User Registration Form</h1>
                <p>Please provide the below information:</p>

                <Form handleSubmit={this.handleSubmit} />

                <hr />
                <h3>Display Availity User Registration information to the table:</h3>
                <Table
                    availityUserData={availityUsers}
                    removeAvailityUser={this.removeAvailityUser}
                />
               <h4>Developed by @Pao Sorn Im, July 2022</h4>
            </div>
        );
    }
}

export default App;
