# Centro

&nbsp;
&nbsp;

**Methods**
```
        - GET    /centro
        - POST   /centro
        - PATCH  /centro/{centroId}
        - DELETE /centro/{centroId}
        - GET /centro/{centroId}/maquina
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /centro
```

**Response:**
```json    
[
    {
        "nombre": "Eroski center MALLOS",
        "direccion": "Avda de los Mallos 66-68",
        "poblacion": "La Coruña",
        "provincia": "La Coruña",
        "id": 1
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /centro/{centroId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. centro identifier |

**Response:**
    
```json     
[
    {
    "id":1,
    "nombre": "Eroski center MALLOS",
    "direccion": "Avda de los Mallos 66-68",
    "poblacion": "La Coruña",
    "cp": "15007",
    "provincia": "La Coruña",
    "email": "mario@negreira.net",
    "telefono": "",
    "idGestion": "242",
    "observaciones": "",
    "enabled": true,
        maquinas:[],
    }
]
```
&nbsp;
&nbsp;
&nbsp;


##### __NEW:__
---
```
    POST /centro
```

```json    

    {
    "nombre": "Eroski center MALLOS",
    "direccion": "Avda de los Mallos 66-68",
    "poblacion": "La Coruña",
    "cp": "15007",
    "provincia": "La Coruña",
    "email": "mario@negreira.net",
    "telefono": "",
    "idGestion": "242",
    "observaciones": "",
    "enabled": true,
    }

```

**Request:**

No data

**Response:**

The same as a get list record

&nbsp;
&nbsp;
&nbsp;

#### __MODIFY:__ 
---
Send only items changed

```
    PATCH /centro/{centroId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `centroId` | `int` | **Required**. centro identifier |

**Request:**
The same as a POST.

**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /centro/{centroId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `centroId` | `int` | **Required**. centro identifier |

**Response:**
No data response, only Status Code.

