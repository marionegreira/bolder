# DocVentaCab

&nbsp;
&nbsp;

**Methods**
```
        - GET /mob/docventa/
        - GET /mob/docventa/{docventaId}
        - POST /mob/docventa/{centroId}
        - PATCH /mob/docventa/{docventaId}
        - DELETE /mob/docventa/{docventaId}
```
&nbsp;
&nbsp;
&nbsp;
##### __GET LIST:__
---
```
    GET /mob/docventa/
```
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
            "usuarioId": 1,
            "usuarioNombre": "mario",
            "usuarioApellido": "negreira",
            "dispositivoId": 1,
            "dispositivoNombre": "android Julian",
            "fechaCreacion": "2020-04-23T19:25:11.000+0000",
            "fechaModificacion": "2020-04-23T19:25:11.000+0000",
            "nombreAgenteCliente": null,
            "nombreFimante": null
        }
    ]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /mob/docventa/{docventaId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |

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

##### __NEW:__
---
```
    POST /mob/docventa/{centroId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `centroId` | `int` | **Required**. centroId identifier |
**Request:**
The same as a get list record.
**Response:**
The same as a get list record

&nbsp;
&nbsp;
&nbsp;

#### __MODIFY:__ 
---
Send only items changed

```
    PATCH /mob/docventa/{docventaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |

**Request:**
The same as a get list record. Send only modified items.
**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /mob/docventa/{docventaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |

**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;
&nbsp;

#### __CLOSE:__ 
---
**Request:**

```
    POST /mob/docventa/close/{docventaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |



```json 
    {
        "nombreFimante": "Paula Gomez",
        "firmaImg": "iVBORw0KGgoAAAAN...",
        "tipoAttach": {1 FIRMA || 2 CONFORMIDAD(selfie)}
    }

```

**Response:**
No data response, only Status Code.

