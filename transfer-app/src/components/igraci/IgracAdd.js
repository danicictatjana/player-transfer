import React, { useEffect, useState } from "react";
import { Button, Form} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";

const IgracAdd = () => {
  //potrebno je zbog create-a
  const emptyIgrac = {
    imePrezime: "",
    pozicija: "",
    brojDresa: "",
    datumRodjenja: "",
    naProdaju: "",
    klubId: -1
  }
  const [igrac, setIgrac] = useState(emptyIgrac)
  const [klubovi, setKlubovi] = useState([])
  const navigate = useNavigate()

  useEffect(()=>{
    getData();
  }, [])

  const getData = () => {
    getKlubovi();
  }

  const getKlubovi = () => {
    TestAxios.get("/klubovi").then((result) => {
      setKlubovi(result.data)
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const doAdd = () => {
    TestAxios.post("/igraci/", igrac)
    .then(()=>{
      //bitno je da bi "resetovali" polja za kreiranje nakon kreiranja
      let igrac = {
        imePrezime: "",
        pozicija: "",
        brojDresa: "",
        datumRodjenja: "",
        naProdaju: "",
        klubId: -1
      };
      setIgrac(igrac)
      navigate("/igraci");
    }).catch(() =>{
      alert("Nije uspelo dodavanje.");
    })
  }

  const addValueInputChange = (event) => {
    let noviIgrac = {...igrac}

    const name = event.target.name;
    const value = event.target.value;

    noviIgrac[name] = value
    setIgrac(noviIgrac);
  }

  const addCheckBoxInputChange = (event) => {
    let noviIgrac = {...igrac}

    const name = event.target.name;
    const value = event.target.checked;

    console.log(value);

    noviIgrac[name] = value
    setIgrac(noviIgrac);
  }

  const canCreateIgrac = () => {
    return igrac.imePrezime!="" && 
      igrac.pozicija!="" && igrac.datumRodjenja!="" && igrac.brojDresa> 0 && igrac.brojDresa<= 99 &&
      igrac.klubId != -1
  }

  return (
    <div>
      {/*Deo za ADD*/}
      <Form>
        <Form.Group>
          <Form.Label>Ime i prezime</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Ime i prezime"
            name="imePrezime"
            value={igrac.imePrezime}
            as="input"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Pozicija</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Izaberi poziciju"
            name="pozicija"
            value={igrac.pozicija}
            as="select">
            <option value={-1}>Izaberi poziciju</option>
            <option value="Golman">Golman</option>
            <option value="Napadac">Napadac</option>
            <option value="Vezni">Vezni</option>
            <option value="Odbrambeni">Odbrambeni</option>
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Broj dresa</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Broj dresa"
            name="brojDresa"
            value={igrac.brojDresa}
            as="input"
            type="number"
            min = "0"
            step = "1"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Datum rodjenja</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="dd-MM-yyyy"
            name="datumRodjenja"
            value={igrac.datumRodjenja}
            type="date"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Na prodaju</Form.Label>
          <Form.Control
            onChange={(event) => addCheckBoxInputChange(event)}
            type="checkbox"
            name="naProdaju"
            value={igrac.naProdaju}
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Klub</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            name="klubId"
            value={igrac.klubId}
            as="select">
            <option value={-1}>Izaberi klub</option>
            {klubovi.map((klub) => {
              return (
                <option value={klub.id} key={klub.id}>
                  {klub.naziv}
                </option>
              );
            })}
          </Form.Control>
        </Form.Group>
        <Button disabled = {!canCreateIgrac()} variant="primary" onClick={() => doAdd()}>
          Dodaj igraca
        </Button>
      </Form>
    </div>
  );
}

export default IgracAdd
