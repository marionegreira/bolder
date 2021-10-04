# Imputable

&nbsp;
&nbsp;
**Methods**
```
        - GET    /imputable
        - POST /imputable
        - PATCH /imputable/{imputableId}
        - DELETE /imputable/{imputableId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /imputable
```

**Response:**
```json    
[
    {
        "id": 1,
        "descripcion": "Desplazamiento",
        "precio": 10.0,
        "idGestion": null
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __NEW:__
---
```
    POST /imputable
```

```json    
[
    {
        "id": 1,
        "descripcion": "Desplazamiento",
        "precio": 10.0,
        "idGestion": null
    }
]
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
    PATCH /imputable/{imputableId}
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
    DELETE /imputable/{imputableId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `imputableId` | `int` | **Required**. imputable identifier |

**Response:**
No data response, only Status Code.

