import React, { Component } from 'react';
import { Button, Container, Table} from 'reactstrap';
//import AppNavbar from './AppNavbar';
import SlownikiNavbar from './SlownikiNavbar';
import { Link } from 'react-router-dom';

class KlubList extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            kluby: []
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/api/slowniki/kluby')
            .then(response => response.json())
            .then(data => this.setState({
                kluby: data}
                ));
    }

    async remove(id) {
        await fetch(`/api/slowniki/kluby/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedKluby = [...this.state.kluby].filter(i => i.id !== id);
            this.setState({kluby: updatedKluby});
        });
    }

    render() {
        const {kluby} = this.state;

        const klubList = kluby.map(klub => {
            return <tr key={klub.id}>
                <td style={{whiteSpace: 'nowrap'}}>{klub.nazwa}</td>
                <td>{klub.temp}</td>
                <td>
                    <Button size="sm" color="primary" tag={Link} to={"/slowniki/kluby/" + klub.id}>Edycja</Button> {'     '}
                    <Button size="sm" color="danger" onClick={() => this.remove(klub.id)}>Usun</Button>
                </td>
            </tr>
        });

        return (
            <div>
                <SlownikiNavbar/>
                <Container className="themed-container" fluid={true} color="dark">
                    <div className="float-right">
                            <Button  color="primary" tag={Link} to="/slowniki"> Cofnij </Button> {' '}
                            <Button color="success" tag={Link} to="/slowniki/kluby/new"> Dodaj Klub </Button>
                    </div>
                    <h3>Kluby</h3>
                    <Table dark bordered className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Nazwa</th>
                            <th width="30%">Temp</th>
                        </tr>
                        </thead>
                        <tbody>
                        {klubList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default KlubList;