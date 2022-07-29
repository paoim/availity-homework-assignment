import React from 'react';

const TableHeader = (props) => {
    return (
        <thead>
            <tr>
                <th>First and Last Name</th>
                <th>NPI Number</th>
                <th>Business Address</th>
                <th>Telephone Number</th>
                <th>Email address</th>
                <th>Remove</th>
            </tr>
        </thead>
    );
}

const TableBody = props => { 
    const users = props.availityUserData.map((user, index) => {
        return (
            <tr key={index}>
                <td>{user.fullName}</td>
                <td>{user.npiNumber}</td>
                <td>{user.businessAddress}</td>
                <td>{user.telephoneNumber}</td>
                <td>{user.emailAddress}</td>
                <td><button onClick={() => props.removeAvailityUser(index)}>Delete</button></td>
            </tr>
        );
    });

    return <tbody>{users}</tbody>;
}

const Table = (props) => {
    const { availityUserData, removeAvailityUser } = props;
        return (
            <table>
                <TableHeader />
                <TableBody availityUserData={availityUserData} removeAvailityUser={removeAvailityUser} />
            </table>
        );
}

export default Table;
