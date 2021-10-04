# DocVentaCab

&nbsp;
&nbsp;

**Methods**
```
        - GET /docventa/
        - GET /docventa/{docventaId}
        - POST /docventa/{centroId}
        - DELETE /docventa/{docventaId}
```
&nbsp;
&nbsp;
&nbsp;
##### __GET LIST:__
---
```
    GET docventa/
```
**Response:**
```json    
    [
    {
        "id": 41,
        "descripcion": "cambiar enchufe pescaderia",
        "clienteId": 1,
        "clienteNombre": "Electricidad Pazos SL",
        "centroNombre": "Eroski center AGRA DEL ORZAN",
        "fechaCreacion": "2020-12-15",
        "fechaModificacion": "2020-12-15"
    }
    ]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /docventa/{docventaId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |

**Response:**
    
```json     
[
    {

    "id": 41,
    "descripcion": "cambiar enchufe pescaderia",
    "centroId": 9,
    "centroNombre": "Eroski center AGRA DEL ORZAN",
    "centroMetodoCierre": "FIRMA",
    "clienteId": 1,
    "clienteNombre": "Electricidad Pazos SL",
    "usuarioId": 1,
    "usuarioNombre": "Mario",
    "usuarioApellido": "Negreira",
    "dispositivoId": 1,
    "dispositivoNombre": "android Julian",
    "gestionMaquinas": true,
    "gestionImputables": true,
    "fechaCreacion": "2020-12-15T10:36:34.000+0000",
    "fechaModificacion": "2020-12-15T10:37:14.000+0000",
    "nombreAgenteCliente": null,
    "nombreFimante": null,
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

##### __NEW:__
---
```
    POST /docventa/{centroId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `centroId` | `int` | **Required**. centroId identifier |

**Request:**
```json 
{
    "centroId":1
    "clienteId": 1,
    "descripcion": "cambiar enchufe pescaderia",
}
```

**Response:**
    data record inserted

&nbsp;
&nbsp;
&nbsp;


#### __DELETE:__
---
```
    DELETE /docventa/{docventaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |

**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;
&nbsp;


