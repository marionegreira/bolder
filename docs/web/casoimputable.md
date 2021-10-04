# DocVentaCab/Imputable

&nbsp;
&nbsp;
**Methods**
```
        - GET    /docventa/{docventaId}/imputable
        - GET    /docventa/imputable/{imputableId}
        - POST   /docventa/{iddocventa}/imputable/{imputableId}
        - PATCH  /docventa/imputable/{imputableId}
        - DELETE /docventa/imputable/{imputableId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /docventa/{docventaId}/imputable
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |
**Response:**
```json    
[
    {
        "id": 1,
        "descripcion": "Mano de obra",
        "idGestion": null,
        "cantidad": 2.0,
        "precio": 30.0
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /docventa/imputable/{imputableId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `imputableId` | `int` | **Required**. imputable identifier |

**Response:**
The same as a get list record

&nbsp;
&nbsp;
&nbsp;

##### __NEW:__
---
```
    POST /docventa/{iddocventa}/imputable/{imputableId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |
| `imputableId` | `int` | **Required**. imputable identifier |

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
    PATCH /docventa/imputable/{imputableId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `imputableId` | `int` | **Required**. imputable identifier |
**Request:**
The same as a get list record. Send only modified items.
**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /docventa/imputable/{imputableId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `imputableId` | `int` | **Required**. imputable identifier |

**Response:**
No data response, only Status Code.
