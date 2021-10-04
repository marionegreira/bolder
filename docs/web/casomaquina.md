# DocVentaCab/Maquina

&nbsp;
&nbsp;
**Methods**
```
        - GET    /docventa/{docventaId}/maquina
        - POST   /docventa/{iddocventa}/maquina/{maquinaId}
        - PATCH  /docventa/maquina/{maquinaId}
        - DELETE /docventa/maquina/{maquinaId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /docventa/{docventaId}/maquina
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |
**Response:**
```json    
[
    {
        "id": 2,
        "docventaId": 1,
        "maquinaId": 1,
        "maquinaDescripcion": "Nevera",
        "maquinaIdGestion": "151"
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __NEW:__
---
```
    POST /docventa/{iddocventa}/maquina/{maquinaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |
| `maquinaId` | `int` | **Required**. maquina identifier |

**Request:**
The same as a get list record. Image in Base 64
**Response:**
The same as a get list record

&nbsp;
&nbsp;
&nbsp;

#### __MODIFY:__ 
---
Send only items changed

```
    PATCH /docventa/maquina/{maquinaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `maquinaId` | `int` | **Required**. maquina identifier |
**Request:**
The same as a get list record. Send only modified items.Image in Base 64
**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /docventa/maquina/{maquinaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `maquinaId` | `int` | **Required**. maquina identifier |

**Response:**
No data response, only Status Code.
