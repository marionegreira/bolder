# DocVentaCab/Attach

&nbsp;
&nbsp;
**Methods**
```
        - GET    /mob/docventa/{docventaId}/attach
        - GET    /mob/docventa/attach/{attachId}
        - POST   /mob/docventa/{docventaId}/attach
        - PATCH  /mob/docventa/attach/{attachId}
        - DELETE /mob/docventa/attach/{attachId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /mob/docventa/{docventaId}/attach
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |
**Response:**
```json    
[
    {
        "id": 1,
        "descripcion": "attach 1",
        "attach": "/9j/asdfmov874j0 ***  BASE64 IMAGE ***   9g874wj0gv8u0vb'jk430fj0fn",
        "tipoAttach": 0,
        "docventaId": 1,
        "fecha": "2020-02-28T12:40:20.000+0000"
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __GET ONE:__
---
```
    GET /mob/docventa/attach/{attachId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `attachId` | `int` | **Required**. attach identifier |

**Response:**
The same as a get list record

&nbsp;
&nbsp;
&nbsp;

##### __NEW:__
---
```
    POST /mob/docventa/{docventaId}/attach/
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. attach identifier |

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
    PATCH /mob/docventa/attach/{attachId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `attachId` | `int` | **Required**. attach identifier |
**Request:**
The same as a get list record. Send only modified items.Image in Base 64
**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /mob/docventa/attach/{attachId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `attachId` | `int` | **Required**. attach identifier |

**Response:**
No data response, only Status Code.
