import React, { useEffect, useState } from "react";
import { Table, Button, Form, ButtonGroup} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";


const Igraci = () => {
  
  const [igraci, setIgraci] = useState([])
  const [search, setSearch] = useState({pozicija: "", klubNaziv: ""})
  const [pageNo, setPageNo] = useState(0)
  const [totalPages, setTotalPages] = useState(1)
  const navigate = useNavigate()

  useEffect(()=>{
    getData();
  }, [])

  const getData = () => {
    getIgraci(0);
  }

  const transferuj = (igracId) => {
    navigate("/transferi/dodaj/" + igracId);
  }

  const getIgraci = (page) => {
    let config = { params: {
      pageNo: page
    } };

    TestAxios.get("/igraci", config)
    .then((result)=>{
      setPageNo(page)
      setIgraci(result.data)
      setTotalPages(result.headers["total-pages"])
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const searchIgraci = (page,newSearch) => {
    let config = { params: {
      pageNo: page
    } };

    config.params.pozicija = newSearch.pozicija;
    config.params.klubNaziv = newSearch.klubNaziv;

    TestAxios.get("/igraci", config)
    .then((result)=>{
      setPageNo(page)
      setIgraci(result.data)
      setTotalPages(result.headers["total-pages"])
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const goToAdd = () => {
    navigate("/igraci/add");
  }

  const doDelete = (igracId) => {
      TestAxios.delete("/igraci/" + igracId)
      .then(()=>{
        var nextPage
        if(pageNo==totalPages-1 && igraci.length==1){
          nextPage = pageNo-1
        }else{
          nextPage = pageNo
        }
        getIgraci(nextPage);
      }).catch((error) => {
        alert("Nije uspelo brisanje.");
      })
  }

  const searchValueInputChange = (event) => {
    let newSearch = {...search}

    const name = event.target.name;
    const value = event.target.value;

    newSearch[name] = value
    setSearch(newSearch);
    searchIgraci(0,newSearch);
  }

  const renderHeader = () => {
    const admin = window.localStorage['role']=="ROLE_ADMIN";
    return  <tr>
    <th>Ime i prezime</th>
    <th>Pozicija</th>
    <th>Broj dresa</th>
    <th>Datum rodjenja</th>
    <th>Na prodaju</th>
    <th>Naziv kluba</th>
    <th hidden={!admin}></th>
    <th hidden={!admin}></th>
  </tr>;
  }

  const renderBody = () => {
    const admin = window.localStorage['role']=="ROLE_ADMIN";
    return igraci.map((igrac) => {
      const naProdaju = igrac.naProdaju
    return <tr key={igrac.id}>
        <td>{igrac.imePrezime}</td>
        <td>{igrac.pozicija}</td>
        <td>{igrac.brojDresa}</td>
        <td>{igrac.datumRodjenja}</td>
        <td>{igrac.naProdaju ? "Da" : "Ne"}</td>
        <td>{igrac.klubNaziv}</td>
        <td hidden={!admin}> 
          <Button
            variant="danger"
            onClick={() => doDelete(igrac.id)}
            style={{ marginLeft: 5 }}>
            Obrisi
          </Button>
        </td>
        <td hidden={!admin || (admin && !naProdaju)}> 
        <Button
          variant="info"
          onClick={() => transferuj(igrac.id)}
          style={{ marginLeft: 5 }}>
          Transfer
        </Button>
        </td>
      </tr>
  })}

  return (
    <div>
      <h1>Igraci</h1>
      {/*Deo za Search*/}
      <Form style={{marginTop:10}}>
      <Form.Group>
          <Form.Label>Pozicija</Form.Label>
          <Form.Control
            onChange={(event) => searchValueInputChange(event)}
            placeholder="Izaberi poziciju"
            name="pozicija"
            value={search.pozicija}
            as="select">
            <option value={-1}>Izaberi poziciju</option>
            <option value="Golman">Golman</option>
            <option value="Napadac">Napadac</option>
            <option value="Vezni">Vezni</option>
            <option value="Odbrambeni">Odbrambeni</option>
          </Form.Control>
        </Form.Group>
      <Form.Group>
          <Form.Label>Naziv kluba</Form.Label>
          <Form.Control
            onChange={(event) => searchValueInputChange(event)}
            name="klubNaziv"
            value={search.klubNaziv}
            as="input">
          </Form.Control>
        </Form.Group>
      </Form>
      
      {/*Deo za ADD dugme*/}
      {window.localStorage['role']=="ROLE_ADMIN"?
      <ButtonGroup style={{ marginTop: 25, float:"left"}}>
        <Button variant="info" onClick={() => goToAdd()}>
          Dodaj takmicara
        </Button>
      </ButtonGroup>
      :null}
      
        {/*Deo za prikaz Takmicara*/}
      <ButtonGroup style={{ marginTop: 25, float:"right"}}>
        <Button 
          style={{ margin: 3, width: 90}}
          disabled={pageNo==0} onClick={()=>getIgraci(pageNo-1)}>
          Prethodna
        </Button>
        <Button
          style={{ margin: 3, width: 90}}
          disabled={pageNo==totalPages-1} onClick={()=>getIgraci(pageNo+1)}>
          Sledeca
        </Button>
      </ButtonGroup>
      {/* Tabela za prikaz igraci */}
      <Table bordered striped style={{ marginTop: 5 }}>
        <thead className="thead-dark">
          {renderHeader()}
        </thead>
        <tbody>
          {renderBody()} 
        </tbody>
      </Table>
    </div>
  );
}

export default Igraci
