import React, { Component } from 'react';
import '../../App.css';
import SlownikiNavbar from './SlownikiNavbar';
import { Link } from 'react-router-dom';
import { Button, Container} from 'reactstrap';

class Slowniki extends Component {
    render() {
        return (
            <div>
                <SlownikiNavbar/>
                <Container fluid>
                    <Button color="link"><Link to="/slowniki/kluby">Kluby</Link></Button>
                    <Button color="link"><Link to="/slowniki/zawodnicy">Zawodnicy</Link></Button>
                    <Button color="link"><Link to="/slowniki/kluby">Kluby</Link></Button>
                    <div> 
                        <hr></hr>
                        <Button color="primary" size="sm" active><Link to="/"> <b> POWRÓT </b></Link></Button>
                    </div>
                </Container>
                
                
            </div>
        );
    }
}

export default Slowniki;