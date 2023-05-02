import React, { useEffect, useState } from "react";
import { Button, Form} from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";

const TransferAdd = () => {
  //potrebno je zbog create-a
  const emptyTransfer = {
    cena: "",
    klubId: -1,
    igracId: -1
  }
  const [transfer, setTransfer] = useState(emptyTransfer)
  const [klubovi, setKlubovi] = useState([])
  const navigate = useNavigate()
  const routeParams = useParams()

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
    TestAxios.post("/transferi/", transfer)
    .then(()=>{
      //bitno je da bi "resetovali" polja za kreiranje nakon kreiranja
      let transfer = {
        cena: "",
        klubId: -1,
        igracId: -1
      };
      setTransfer(transfer)
      navigate("/igraci");
    }).catch(() =>{
      alert("Nije uspelo dodavanje.");
    })
  }

  const addValueInputChange = (event) => {
    let noviTransfer = {...transfer}

    const name = event.target.name;
    const value = event.target.value;

    noviTransfer[name] = value
    noviTransfer["igracId"] = routeParams.id
    setTransfer(noviTransfer);
  }

  const canCreateIgrac = () => {
    return  transfer.cena> 0 && transfer.klubId != -1
  }

  return (
    <div>
      {/*Deo za ADD*/}
      <Form>
        <Form.Group>
          <Form.Label>Cena</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Cena"
            name="cena"
            value={transfer.cena}
            as="input"
            type="number"
            min = "0"
            step = "1"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Klub</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            name="klubId"
            value={transfer.klubId}
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
          Uradi transfer
        </Button>
      </Form>
    </div>
  );
}

export default TransferAdd
