# DocVentaCab/Nota

&nbsp;
&nbsp;
**Methods**
```
        - GET    /mob/docventa/{docventaId}/nota
        - GET    /mob/docventa/nota/{notaId}
        - POST   /mob/docventa/{iddocventa}/nota
        - PATCH  /mob/docventa/nota/{notaId}
        - DELETE /mob/docventa/nota/{notaId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /mob/docventa/{docventaId}/nota
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |
**Response:**
```json    
[
    {
        "id": 1,
        "descripcion": "nota1",
        "fecha": "2020-02-27T19:19",
        "docventaId": 1,
        "usuarioId": 1,
        "usuarioNombre": "mario",
        "usuarioApellido": "negreira",
        "dispositivoId": null,
        "dispositivoNombre": null,
        "infoCreador": null
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /mob/docventa/nota/{notaId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `notaId` | `int` | **Required**. nota identifier |

**Response:**
The same as a get list record

&nbsp;
&nbsp;
&nbsp;

##### __NEW:__
---
```
    POST /mob/docventa/{iddocventa}/nota
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |


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
    PATCH /mob/docventa/nota/{notaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `notaId` | `int` | **Required**. nota identifier |
**Request:**
The same as a get list record. Send only modified items.Image in Base 64
**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /mob/docventa/nota/{notaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `notaId` | `int` | **Required**. nota identifier |

**Response:**
No data response, only Status Code.
