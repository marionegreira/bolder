# DocVentaCab

&nbsp;
&nbsp;

**Methods**
```
        - GET /histdocventa?pageNumber={pageNumber}&pageSize={pageSize}
        - GET /histdocventa/{histDocVentaCabId}
        - GET /histdocventa/{histDocVentaCabId}/attach
```
&nbsp;
&nbsp;
&nbsp;
##### __GET LIST:__
---
```
    GET /histdocventa?pageNumber={pageNumber}&pageSize={pageSize}
```
| @RequestParam | Type | Description |
| :--- | :--- | :--- |
| `pageNumber` | `int` | **Required**. |
| `pageSize` | `int` | **Required**.  |
**Response:**
```json    
    {
        "recordsTotal": 9370,
        "recordsFiltered": 10,
        "data": [
            {
                "id": 9363,
                "descripcion": "Nevera no enciende",
                "centroId": 1,
                "centroNombre": "1Supermercado Norte",
                "clienteId": 1,
                "clienteNombre": "Electricidad Pazos SL",
                "usuarioId": 1,
                "usuarioNombre": "mario",
                "usuarioApellido": "negreira",
                "dispositivoId": 1,
                "dispositivoNombre": "android Julian",
                "fechaCreacion": "2020-04-26T16:21:13.000+0000",
                "fechaModificacion": "2020-04-26T16:21:13.000+0000",
                "nombreAgenteCliente": "",
                "nombreFimante": ""
            }
        ]
    }

```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /histdocventa/{histDocVentaCabId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `histDocVentaCabId` | `int` | **Required**. histDocVentaCab identifier |

**Response:**
    
```json     
[
    {
        "id": 10,
        "descripcion": "sadf",
        "centroId": 1,
        "centroNombre": "1Supermercado Norte",
        "clienteId": 1,
        "clienteNombre": "Electricidad Pazos SL",
        "..."
        notas:[],
        imputables:[],
        maquinas:[],
        attaches:[]
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ATTACHES:__
---
```
    GET /histdocventa/{histDocVentaCabId}/attach
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `histDocVentaCabId` | `int` | **Required**. histDocVentaCabId identifier |

**Response:**
    
```json
[
    {
            "id": 236,
            "descripcion": "attach 1",
            "attach":"asdfkljfas ***  BASE 64 IMAGE  *** dfñlknplok34jh98ugjigo09fuj",
            "tipoAttach": 0,
            "docventaId": 120,
            "fecha": "2020-04-25T17:17:49.000+0000"
    }
]
```
&nbsp;
&nbsp;
&nbsp;


