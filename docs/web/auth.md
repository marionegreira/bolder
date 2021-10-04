# Authentication

&nbsp;
&nbsp;
**Methods**

```html
        - POST /auth
```
&nbsp;
&nbsp;
&nbsp;

##### __LOGIN__

---
```
    POST /auth
```


**Request:**


```json    

    {
        "username": "pepe@hotmail.com",
        "password": "ca2rgo5go"
    }

```
**Response:** 
    if validate success we can take the "token bearer" from the header.
    if authentication fail no data response and "204 No content" status code
                 

&nbsp;
&nbsp;
&nbsp;



