import React, { Component } from 'react';
import './App.css';
import Slowniki from './components/slowniki/Slowniki';
import Aplikacja from './components/Aplikacja'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import KlubList from './components/slowniki/KlubList';
import KlubEdit from './components/slowniki/KlubEdit';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Aplikacja}/>
            <Route path='/slowniki' exact={true} component={Slowniki}/>
            <Route path='/slowniki/kluby' exact={true} component={KlubList}/>
            <Route path='/slowniki/kluby/:id' component={KlubEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;