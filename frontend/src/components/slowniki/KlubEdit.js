import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import SlownikiNavbar from './SlownikiNavbar';

class KlubEdit extends Component {

    emptyItem = {
        nazwa: '',
        temp: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const klub = await (await fetch(`/api/slowniki/kluby/${this.props.match.params.id}`)).json();
            this.setState({item: klub});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/api/slowniki/kluby/' + (item.id ? '/' + item.id : ''), {
        method: (item.id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/slowniki/kluby');
}

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edytuj Klub' : 'Dodaj Klub'}</h2>;

        return <div>
            <SlownikiNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit} color="dark">
                    <FormGroup>
                        <Label for="nazwa">Nazwa</Label>
                        <Input type="text" name="nazwa" id="nazwa" value={item.nazwa || ''}
                               onChange={this.handleChange} autoComplete="nazwa"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="temp">Temp</Label>
                        <Input type="text" name="temp" id="temp" value={item.temp || ''}
                               onChange={this.handleChange} autoComplete="temp"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Zapisz</Button>{' '}
                        <Button color="secondary" tag={Link} to="/slowniki/Kluby">Rezygnuj</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(KlubEdit);