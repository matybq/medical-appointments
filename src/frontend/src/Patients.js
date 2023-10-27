import React, {Component} from 'react';
import axios from 'axios'

class Patients extends Component {

    constructor(props) {
        super(props)
        this.state = {
            patients: []
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8080/api/v1/patients/').then(response => {
            console.log(response);
        })
    }

    render() {
        return(
                <div>
                  <header>
                  </header>
                </div>
        )
    }


}

export default Patients;
