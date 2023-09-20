import { Button } from "antd";
import { useState, useEffect } from "react";
import "./App.css";

function App() {
  const [data, setData] = useState(null);

  //permite hacer la peticion http
  useEffect(() => {
    fetch("http://localhost:8080/api/patients")
      .then((res) => res.json())
      .then((data) => setData(data));
  }, []);

  return (
    <div className="App">
      <h1>Boca</h1>
      <div className="card">
        <ul>
          {data?.map((patient) => (
            <li key={patient.id}> {patient.name} </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;
