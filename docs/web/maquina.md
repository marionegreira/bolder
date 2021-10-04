# Maquina

&nbsp;
&nbsp;

**Methods**

```
    - GET    /centro/maquina/{docventaId}
    - POST   /centro/maquina
    - PATCH  /centro/maquina{centromaquinaId}
    - DELETE /centro/{centromaquinaId}
```
&nbsp;
&nbsp;
&nbsp;

##### __GET LIST:__
---
```
    GET /centro/maquina/{docventaId}
```
| Parameter | Type | Description |
| :--- | :--- | :--- |
| `docventaId` | `int` | **Required**. docventa identifier |

**Response:**
```json    
[
    {
        "id": 1,
        "descripcion": "Nevera",
        "centroId": 1,
        "idGestion": "151",
        "enabled": true
    }
]
```
&nbsp;
&nbsp;
&nbsp;

##### __NEW:__
---
```
    POST /centro/maquina/

```

```json    

    {
        "descripcion": "Nevera",
        "centroId": 1,
        "idGestion": "151",
        "enabled": true
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
    PATCH /centro/{centromaquinaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `centromaquinaId` | `int` | **Required**. centromaquinaId identifier |

**Request:**
The same as a get list record. Send only modified items

**Response:**
No data response, only Status Code.

&nbsp;
&nbsp;

#### __DELETE:__
---
```
    DELETE /centro/{centromaquinaId}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `centromaquinaId` | `int` | **Required**. centromaquinaId identifier |

**Response:**
No data response, only Status Code.

