# DocVentaCab/Attach

&nbsp;
&nbsp;
**Methods**
```
        - GET    /docventa/{docventaId}/attach
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /docventa/{docventaId}/attach
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


