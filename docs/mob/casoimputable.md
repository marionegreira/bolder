# DocVentaCab/Imputable

&nbsp;
&nbsp;
**Methods**
```
        - GET    /mob/docventa/{docventaId}/imputable
        - GET    /mob/docventa/imputable/{imputableId}
        - POST   /mob/docventa/{iddocventa}/imputable/{imputableId}
        - PATCH  /mob/docventa/imputable/{imputableId}
        - DELETE /mob/docventa/imputable/{imputableId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /mob/docventa/{docventaId}/imputable
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
    GET /mob/docventa/imputable/{imputableId}
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
    POST /mob/docventa/{iddocventa}/imputable/{imputableId}
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
    PATCH /mob/docventa/imputable/{imputableId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `imputableId` | `int` | **Required**. imputable identifier |
**Request:**
The same as a get list record. Send only modified items.Image in Base 64
**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /mob/docventa/imputable/{imputableId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `imputableId` | `int` | **Required**. imputable identifier |

**Response:**
No data response, only Status Code.
