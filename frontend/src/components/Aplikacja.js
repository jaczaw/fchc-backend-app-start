import React, { Component } from 'react';
import '../App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Aplikacja extends Component {
    render() {
        return (
        <>
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Button color="link"><Link to="/slowniki">Slowniki</Link></Button>
                    <Button color="link"><Link to="/slowniki">Rozgrywki</Link></Button>
                    <Button color="link"><Link to="/slowniki">Wyniki</Link></Button>
                    <Button color="link"><Link to="/slowniki">Statystyka</Link></Button>
                </Container>
            </div>

        </>
        );
    }
}

export default Aplikacja;