# Authentication

&nbsp;
&nbsp;
**Methods**

```html
        - GET /mob/auth/cliente?nif={nif}
        - GET /mob/auth/getkey/{commietId}
        - GET /mob/auth/register?key={key}&uuid={uuid}
        - GET /mob/auth
```
&nbsp;
&nbsp;
&nbsp;

##### __GET COMMITER BY CIF__
---
```
    GET /mob/auth/cliente?nif={nif}
```
| @RequestParam | Type | Description |
| :--- | :--- | :--- |
| `nif` | `int` | **Required**. nif identifier  |
**Response:**
```json    
[
    {
        "id": 1,
        "nombre": "Electricidad Pazos SL",
        "direccion": "c\\pamplona, 10",
        "poblacion": "La Coruña",
        "delegacion": 1
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __SEND KEY BY EMAIL__
---

**Request:**

Request to send mail with a key registration
```
    POST /mob/auth/getkey
```
&nbsp;
```json 
    {
        "clienteId": 124,
        "nombre": "Juan Lopez",
    }
```

**Response:**
get email sended registration key.

```json    

    {
        "in********@gmail.com"
    }

```

&nbsp;
&nbsp;
&nbsp;

##### __TRY CONFIRM KEY__
---
**Request:**
```
    POST /mob/auth/register
```
```json 
    {
        "key": 518616371,
        "uuid": "0b90m45tnj0g9k7ju4",
        "nombre": "Tecnico 1",
        "gmailAccount": "pepe@gmail.com"
    }

```
&nbsp;

**Response:**
if authentication fail no data response and "204 No content" status code.

```json    
    {
        "cif": "A38431s",
        "cp": "15008",
        "direccion": "C/ Huertas 11",
        "email": "info@sweeepend.com",
        "endpointCode": "00010001",
        "nombre": "Argo supermercados s.a.",
        "password": "a13piN2FerrUbnhSNWxULnafRL",
        "poblacion": "La Coruña",
        "provincia": "La Coruña",
        "telefono": "981212112",
        "token": null,
        "tokenExp": null,
        "username": "47",
        "www": "www.argo.es"
    }    
```
&nbsp;
&nbsp;
&nbsp;

##### __LOGIN__
---
```
    GET /mob/auth
```


**Request:**
if authentication fail no data response and "204 No content" status code.

```json    

    {
        "username": "pepe@hotmail.com",
        "password": "cargogo"
    }

```
**Request:** if validate if success we can take the "token bearer" from the header.

&nbsp;
&nbsp;
&nbsp;



